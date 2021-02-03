package com.plugin.blog.demo.edit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import com.plugin.blog.demo.ImageKeys;
import com.plugin.blog.demo.data.UserInfo;
import com.plugin.blog.demo.data.UserInfoManager;

public class UserPreviewFormPage extends FormPage 
            implements PropertyChangeListener {
    private CTabFolder mTabFolder;
    private Text mText;
    private UserInfoManager mManager;

    public UserPreviewFormPage(FormEditor editor) {
        super(editor, "com.plugin.blog.demo.edit.UserPreviewFormPage", "预览");
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        ScrolledForm form = managedForm.getForm();
        form.setText("信息预览");
        form.setBackgroundImage(ImageKeys.getImageDescriptor(
                ImageKeys.IMAGE_FORM_BANNER).createImage());
        FormToolkit toolkit = managedForm.getToolkit();

        Composite parentComp = form.getBody();
        parentComp.setLayout(new GridLayout());

        mTabFolder = new CTabFolder(parentComp, SWT.FLAT | SWT.TOP);
        toolkit.adapt(mTabFolder, true, true);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.heightHint = 0;
        mTabFolder.setLayoutData(gd);

        toolkit.paintBordersFor(mTabFolder);

        createTitle();
        createContent(toolkit, parentComp);
        mTabFolder.setSelection(0);

        mTabFolder.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                updateSelection();
            }
        });

        updateSelection();
    }

    private void createTitle() {
        mManager = ((DemoEditorInput) getEditor().getEditorInput()).getManager();
        Object[] elementArray = mManager.toArray();

        for (Object element : elementArray) {
            CTabItem item = new CTabItem(mTabFolder, SWT.NULL);
            UserInfo userInfo = (UserInfo) element;
            item.setText(userInfo.getTitle());
            userInfo.addPropertyChangeListener(this);
        }
    }

    // 数据模型修改后，刷新当前界面
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateSelection();
    }

    private void createContent(FormToolkit toolkit, Composite parent) {
        Composite tabContentComp = toolkit.createComposite(parent);
        tabContentComp.setLayoutData(new GridData(GridData.FILL_BOTH));
        tabContentComp.setLayout(new GridLayout());

        mText = toolkit.createText(tabContentComp, "", SWT.MULTI | SWT.WRAP);
        mText.setLayoutData(new GridData(GridData.FILL_BOTH));

        mText.setEditable(false);
    }

    private void updateSelection() {
        if (mTabFolder == null)
            return;
        int index = mTabFolder.getSelectionIndex();
        mText.setText(mManager.getUserInfo(index).getText());
    }

}
