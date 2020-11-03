package com.plugin.blog.demo.edit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import com.plugin.blog.demo.data.UserInfo;

public abstract class AbstractDetailsPage implements IDetailsPage {
    private IManagedForm mForm;
    protected Section mSection;
    private UserInfo mUserInfo;
    private boolean mRefresh;

    @Override
    public void initialize(IManagedForm form) {
        this.mForm = form;
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public void commit(boolean onSave) {
    }

    @Override
    public boolean setFormInput(Object input) {
        return false;
    }

    @Override
    public void setFocus() {
    }

    @Override
    public boolean isStale() {
        return false;
    }

    @Override
    public void refresh() {
        mRefresh = true;
        updateUI(mUserInfo);
        mRefresh = false;
    }

    @Override
    public void selectionChanged(IFormPart part, ISelection selection) {
        if (selection != null && !selection.isEmpty()) {
            mUserInfo = (UserInfo) ((IStructuredSelection) selection).getFirstElement();
            mSection.setText(mUserInfo.getTitle());
            mSection.setDescription(mUserInfo.getDescription());

            mRefresh = true;
            updateUI(mUserInfo);
            mRefresh = false;
        }
    }

    protected void updateUI(UserInfo userInfo) {
    }

    @Override
    public void createContents(Composite parent) {
        parent.setLayout(new TableWrapLayout());

        FormToolkit toolkit = mForm.getToolkit();
        mSection = toolkit.createSection(parent, Section.DESCRIPTION|Section.TITLE_BAR);
        TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
        td.grabHorizontal = true;
        mSection.setLayoutData(td);

        Composite compParent = toolkit.createComposite(mSection, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        compParent.setLayout(layout);

        mSection.setClient(compParent);

        createContentInfo(mForm, compParent);
    }

    protected abstract void createContentInfo(IManagedForm form, Composite parent);

    protected Text createText(IManagedForm form, Composite parent, String name) {
        Label label = form.getToolkit().createLabel(parent, name);
        GridData labelGd = new GridData(SWT.FILL, SWT.CENTER, false, false);
        labelGd.widthHint = 60;
        label.setLayoutData(labelGd);

        Text text = form.getToolkit().createText(parent, "");
        GridData textGd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        text.setLayoutData(textGd);
        text.addModifyListener(new ModifyListener() {			
            @Override
            public void modifyText(ModifyEvent e) {
                if (!mRefresh) {
                    updateInfo(mUserInfo);
                }
            }
        });

        return text;
    }

    protected List<Button> createChoice(IManagedForm form, Composite parent,
                    String[] array, int selectIndex) {
        Composite choiceComp = form.getToolkit().createComposite(parent);
        GridData choiceCompGd = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
        choiceComp.setLayoutData(choiceCompGd);

        GridLayout choiceCompLayout = new GridLayout(array.length, false);
        choiceComp.setLayout(choiceCompLayout);

        List<Button> btnList = new ArrayList<>();
        for (String str : array) {
            Button btn = form.getToolkit().createButton(choiceComp, str, SWT.RADIO);
            btn.setText(str);
            GridData btnGd = new GridData(SWT.FILL, SWT.CENTER, false, false);
            btn.setLayoutData(btnGd);
            btn.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    if (!mRefresh) {
                        updateInfo(mUserInfo);
                    }
                }
            });

            btnList.add(btn);
        }
        btnList.get(selectIndex).setSelection(true);
        return btnList;
    }

    protected void updateInfo(UserInfo userInfo) {
    }

}
