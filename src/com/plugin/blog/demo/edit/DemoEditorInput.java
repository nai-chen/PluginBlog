package com.plugin.blog.demo.edit;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.plugin.blog.demo.data.User;
import com.plugin.blog.demo.data.UserInfoManager;

public class DemoEditorInput implements IEditorInput {
    private User mUser;
    private UserInfoManager mManager;

    public DemoEditorInput(User user) {
        this.mUser = user;
        mManager = UserInfoManager.getInstance(mUser.getName());
    }

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        return null;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    @Override
    public String getName() {
        return mUser.getName();
    }

    @Override
    public IPersistableElement getPersistable() {
        return null;
    }

    @Override
    public String getToolTipText() {
        return getName() + "пео╒";
    }

    public UserInfoManager getManager() {
        return mManager;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DemoEditorInput) {
            return mUser == ((DemoEditorInput) obj).mUser;
        }
        return super.equals(obj);
    }
}
