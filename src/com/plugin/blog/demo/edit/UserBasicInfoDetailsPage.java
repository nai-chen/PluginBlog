package com.plugin.blog.demo.edit;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.plugin.blog.demo.data.UserBasicInfo;
import com.plugin.blog.demo.data.UserInfo;

public class UserBasicInfoDetailsPage extends AbstractDetailsPage {
    private List<Button> mSexBtnList;
    private Text mText;

    @Override
    protected void createContentInfo(IManagedForm form, Composite parent) {
        FormToolkit toolkit = form.getToolkit();
        Label sexLabel = toolkit.createLabel(parent, "性别：");
        GridData sexGd = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1); 
        sexLabel.setLayoutData(sexGd);

        mSexBtnList = createChoice(form, parent, new String[]{"男", "女"}, 0);
        mText = createText(form, parent, "年龄");
    }

    @Override
    protected void updateUI(UserInfo userInfo) {
        if (userInfo == null) {
            mSexBtnList.get(0).setSelection(true);
            mSexBtnList.get(1).setSelection(false);
            mText.setText("");
        } else {
            UserBasicInfo userBasicInfo = (UserBasicInfo) userInfo;

            boolean male = userBasicInfo.isMale();
            mSexBtnList.get(0).setSelection(male);
            mSexBtnList.get(1).setSelection(!male);

            if (userBasicInfo.getAge() <= 0) {
                mText.setText("");
            } else {
                mText.setText(Integer.toString(userBasicInfo.getAge()));
            }
        }
    }

    private int parseInteger(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    @Override
    protected void updateInfo(UserInfo userInfo) {
        super.updateInfo(userInfo);
        UserBasicInfo userBasicInfo = (UserBasicInfo) userInfo;
        if (mSexBtnList.get(0).getSelection()) {
            userBasicInfo.setMale(true);
        } else if (mSexBtnList.get(1).getSelection()) {
            userBasicInfo.setMale(false);
        }
        userBasicInfo.setAge(parseInteger(mText.getText()));
    }

}

