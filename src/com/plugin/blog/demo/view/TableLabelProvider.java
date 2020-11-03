package com.plugin.blog.demo.view;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.plugin.blog.demo.data.User;

public class TableLabelProvider implements ITableLabelProvider {

    @Override
    public void addListener(ILabelProviderListener listener) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
    }

    @Override
    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    @Override
    public String getColumnText(Object element, int columnIndex) {
        if (element instanceof User) {
            User user = (User) element;
            if (columnIndex == 0) {
                return user.getName();
            } else if (columnIndex == 1) {
                return user.getCategory();
            }
        }
        return null;
    }

}
