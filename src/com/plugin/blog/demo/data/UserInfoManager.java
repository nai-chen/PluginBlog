package com.plugin.blog.demo.data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.XMLMemento;

import com.plugin.blog.demo.Activator;
import com.plugin.blog.demo.extension.UserExtensionInfo;
import com.plugin.blog.demo.extension.UserExtensionInfoItem;

public class UserInfoManager implements PropertyChangeListener {
    private String mName;
    private boolean mDirty;
    private List<UserInfo> mUserInfoList;
    private UserBasicInfo mBasicInfo;
    private UserContractInfo mContractInfo;
    private UserExtensionInfo mExtensionInfo;

    private PropertyChangeSupport mSupport;

    private UserInfoManager(String name) {
        this.mName = name;
        mUserInfoList = new ArrayList<>();
        mSupport = new PropertyChangeSupport(this);
        load();
        mDirty = false;
    }

    private void load() {
        mBasicInfo = new UserBasicInfo();
        mContractInfo = new UserContractInfo();
        mExtensionInfo = new UserExtensionInfo();
        
        try {
            XMLMemento memento = XMLMemento.createReadRoot(new FileReader(getStoreFile()));
            IMemento child = memento.getChild("basic_info");
            mBasicInfo.setMale(child.getBoolean("male"));
            mBasicInfo.setAge(child.getInteger("age"));

            child = memento.getChild("contract_info");
            mContractInfo.setPhone(child.getString("phone"));
            mContractInfo.setAddress(child.getString("address"));
            
            child = memento.getChild("extension_info");
            if (child != null) {
                for (UserExtensionInfoItem infoItem :
                        UserExtensionInfoItem.getExtensionInfoList()) {
                    String value = child.getString(infoItem.getId());
                    if (value != null) {
                        mExtensionInfo.setValue(infoItem.getId(), value);
                    }
                }
            }
        } catch (Exception e) {		
        }

        mBasicInfo.addPropertyChangeListener(this);
        mUserInfoList.add(mBasicInfo);

        mContractInfo.addPropertyChangeListener(this);
        mUserInfoList.add(mContractInfo);
        
        mExtensionInfo.addPropertyChangeListener(this);
        mUserInfoList.add(mExtensionInfo);
    }

    public Object[] toArray() {
        return mUserInfoList.toArray();
    }

    public boolean isDirty() {
        return mDirty;
    }

    public void doSave(IProgressMonitor monitor) {
        XMLMemento memento = XMLMemento.createWriteRoot("user");
        IMemento child = memento.createChild("basic_info");
        child.putBoolean("male", mBasicInfo.isMale());
        child.putInteger("age", mBasicInfo.getAge());

        child = memento.createChild("contract_info");
        child.putString("phone", mContractInfo.getPhone());
        child.putString("address", mContractInfo.getAddress());

        child = memento.createChild("extension_info");
        for (UserExtensionInfoItem infoItem :
                UserExtensionInfoItem.getExtensionInfoList()) {
            String id = infoItem.getId();
            child.putString(id, mExtensionInfo.getValue(id));
        }
        
        try {
            memento.save(new FileWriter(getStoreFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mDirty = false;
    }
    
    private File getStoreFile() {
        return Activator.getDefault().getStateLocation()
                .append("user_" + mName + ".xml").toFile();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!mDirty) {
            mDirty = true;
            mSupport.firePropertyChange("PROP_DIRTY", false, mDirty);	
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        mSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mSupport.removePropertyChangeListener(listener);
    }

    public UserInfo getUserInfo(int index) {
        if (index >= 0 && index < mUserInfoList.size()) {
            return mUserInfoList.get(index);
        }
        return null;
    }

    public static UserInfoManager getInstance(String name) {
        return new UserInfoManager(name);
    }

}

