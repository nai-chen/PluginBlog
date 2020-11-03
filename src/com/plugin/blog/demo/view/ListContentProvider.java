package com.plugin.blog.demo.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.plugin.blog.demo.data.UserManager;

public class ListContentProvider implements IStructuredContentProvider,
		PropertyChangeListener {

    private Viewer viewer;

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof UserManager) {
            return ((UserManager) inputElement).getElements();
        }
        return new Object[0];
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        this.viewer = viewer;
        if (oldInput instanceof UserManager) {
            ((UserManager) oldInput).removePropertyChangeListener(this);
        }

        if (newInput instanceof UserManager) {
            ((UserManager) newInput).addPropertyChangeListener(this);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (UserManager.PROPERTY_ADD.equals(evt.getPropertyName())) {
            viewer.refresh();
        } else if (UserManager.PROPERTY_REMOVE.equals(evt.getPropertyName())) {
            viewer.refresh();
        } else if (UserManager.PROPERTY_MODIFY.equals(evt.getPropertyName())) {
            viewer.refresh();
        }
    }

}
