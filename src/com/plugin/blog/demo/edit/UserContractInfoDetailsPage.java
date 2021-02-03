package com.plugin.blog.demo.edit;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;

import com.plugin.blog.demo.data.UserContractInfo;
import com.plugin.blog.demo.data.UserInfo;

public class UserContractInfoDetailsPage extends AbstractDetailsPage {
    private Text mPhoneText;
    private Text mAddressText;

    @Override
    protected void createContentInfo(IManagedForm form, Composite parent) {
        mPhoneText = createText(form, parent, "手机号");
        mAddressText = createText(form, parent, "家庭地址");
    }

    @Override
    protected void updateUI(UserInfo userInfo) {
        if (userInfo == null) {
            mPhoneText.setText("");
            mAddressText.setText("");
        } else {
            UserContractInfo userContractInfo = (UserContractInfo) userInfo;

            mPhoneText.setText(userContractInfo.getPhone() == null ?
                        "" : userContractInfo.getPhone());
            mAddressText.setText(userContractInfo.getAddress() == null ?
                        "" : userContractInfo.getAddress());
        }
    }

    @Override
    protected void updateInfo(UserInfo userInfo) {
        super.updateInfo(userInfo);

        UserContractInfo userContractInfo = (UserContractInfo) userInfo;
        userContractInfo.setPhone(mPhoneText.getText());
        userContractInfo.setAddress(mAddressText.getText());		
    }

}

