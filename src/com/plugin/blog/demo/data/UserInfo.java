package com.plugin.blog.demo.data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class UserInfo {
    private String mTitle, mDescription;
    private PropertyChangeSupport mSupport;

    public UserInfo(String title, String description) {
        this.mTitle = title;
        this.mDescription = description;
        mSupport = new PropertyChangeSupport(this);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public abstract String getText();

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        mSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mSupport.removePropertyChangeListener(listener);
    }

    public void firePropertyChange(String propertyName,
            Object oldValue, Object newValue) {
        mSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

}
