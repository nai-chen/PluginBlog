package com.plugin.blog.demo.data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.IMemento;

public class UserManager {
    public final static String PROPERTY_ADD 	= "add";
    public final static String PROPERTY_REMOVE 	= "remove";
    public final static String PROPERTY_MODIFY	= "modify";

    private List<User> mData = new ArrayList<User>();
    private PropertyChangeSupport mSupport = new PropertyChangeSupport(this);
    
    private boolean mInitialized;

    private UserManager() {
        // 显示数据，后面删除
//        mData.add(new User("Mike", User.CATEGORY_ARRAY[1]));
//        mData.add(new User("Tom", User.CATEGORY_ARRAY[2]));
    }

    public static UserManager getInstance() {
        return UserManagerHolder.INSTANCE;
    }

    public void addObject(User data) {
        if (mData.add(data)) {
            mSupport.firePropertyChange(PROPERTY_ADD, null, data);
        }
    }

    public void removeObject(User data) {
        if (mData.remove(data)) {
            mSupport.firePropertyChange(PROPERTY_REMOVE, data, null);
        }
    }

    public void modifyObject(Object data) {
        mSupport.firePropertyChange(PROPERTY_MODIFY, null, data);
    }

    public Object[] getElements() {
        return mData.toArray();
    }

    public int getLength() {
        return mData.size();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        mSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mSupport.removePropertyChangeListener(listener);
    }
    
    public void init(IMemento memento) {
    	System.out.println("init");
        if (mInitialized) {        	
            return;
        }
        if (memento != null) {
	        IMemento[] children = memento.getChildren("UserManager");
	        if (children != null) {
	            for (IMemento child : children) {
	                addObject(new User(child.getString("name"), child.getString("category")));
	            }
	        }
        }
        mInitialized = true;
    }

    public void saveState(IMemento memento) {
        for (User user : mData) {
            IMemento child = memento.createChild("UserManager");
            child.putString("name", user.getName());
            child.putString("category", user.getCategory());
        }
    }

    private static class UserManagerHolder {
        private static UserManager INSTANCE = new UserManager();
    }
}
