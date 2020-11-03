package com.plugin.blog.demo.edit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;

import com.plugin.blog.demo.Activator;
import com.plugin.blog.demo.data.UserInfoManager;
import com.plugin.blog.demo.preference.PreferenceConstants;

public class DemoEditorPart extends FormEditor {

    private UserInfoFormPage mInfoFormPage;
    private UserPreviewFormPage mPreviewFormPage;

    private UserInfoManager mManager;
    
    private IPreferenceStore mStore;

    public DemoEditorPart() {
        mStore = Activator.getDefault().getPreferenceStore();
        mStore.addPropertyChangeListener(new IPropertyChangeListener() {
            @Override
            public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
                if (PreferenceConstants.P_SHOW_PREVIEW.equals(event.getProperty())) {
                    boolean show = (Boolean) event.getNewValue();
                    try {
                        if (show && getPageCount() == 1) {
                            mPreviewFormPage = new UserPreviewFormPage(DemoEditorPart.this);
                            addPage(mPreviewFormPage);
                        } else if (!show && getPageCount() == 2) {
                            setActivePage(0);
                            removePage(1);
                        }					
                    }catch (PartInitException e) {
                    }
                }
            }
        });
    }
    
    @Override
    protected void addPages() {
        mInfoFormPage = new UserInfoFormPage(this);
        mPreviewFormPage = new UserPreviewFormPage(this);
        try {
            addPage(mInfoFormPage);
            if (mStore.getBoolean(PreferenceConstants.P_SHOW_PREVIEW))
                addPage(mPreviewFormPage);
        } catch (PartInitException e) {
        }

        IEditorInput input = getEditorInput();
        setPartName(input.getName());
        setTitleToolTip(input.getToolTipText());		

        mManager = ((DemoEditorInput) input).getManager();
        mManager.addPropertyChangeListener(new PropertyChangeListener() {			
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                firePropertyChange(PROP_DIRTY);				
            }
        });
    }

    @Override
    public boolean isDirty() {
        return mManager.isDirty();
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        mManager.doSave(monitor);
        firePropertyChange(PROP_DIRTY);
    }

    @Override
    public void doSaveAs() {
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

}
