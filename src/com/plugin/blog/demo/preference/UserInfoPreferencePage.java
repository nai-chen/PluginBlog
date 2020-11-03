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
        setDescription("用户信息配置");
    }

    @Override
    protected void createFieldEditors() {
        Composite parent = getFieldEditorParent();
        addField(new BooleanFieldEditor(PreferenceConstants.P_SHOW_PREVIEW, 
            "显示预览分页", parent));
    }

    @Override
    public void init(IWorkbench workbench) {
    }
    
}
