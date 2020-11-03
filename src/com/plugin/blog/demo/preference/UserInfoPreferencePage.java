package com.plugin.blog.demo.preference;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.plugin.blog.demo.Activator;

public class UserInfoPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public UserInfoPreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("�û���Ϣ����");
    }

    @Override
    protected void createFieldEditors() {
        Composite parent = getFieldEditorParent();
        addField(new BooleanFieldEditor(PreferenceConstants.P_SHOW_PREVIEW, 
            "��ʾԤ����ҳ", parent));
    }

    @Override
    public void init(IWorkbench workbench) {
    }
    
}
