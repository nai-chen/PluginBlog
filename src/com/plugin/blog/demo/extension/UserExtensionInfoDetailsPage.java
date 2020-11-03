package com.plugin.blog.demo.extension;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;

import com.plugin.blog.demo.data.UserInfo;
import com.plugin.blog.demo.edit.AbstractDetailsPage;

public class UserExtensionInfoDetailsPage extends AbstractDetailsPage {
    private List<Text> mTextList = new ArrayList<>(); 
    private List<UserExtensionInfoItem> mInfoItemList =
            UserExtensionInfoItem.getExtensionInfoList();

    @Override
    protected void createContentInfo(IManagedForm form, Composite parent) {
        for (UserExtensionInfoItem info : mInfoItemList) {
            mTextList.add(createText(form, parent, info.getName()));
        }
    }

    @Override
    protected void updateUI(UserInfo userInfo) {
        super.updateUI(userInfo);
        if (userInfo == null) {
            for (Text text : mTextList) {
                text.setText("");
            }
        } else {
            UserExtensionInfo extensionInfo = (UserExtensionInfo) userInfo;

            for (int index = 0; index < mTextList.size(); index++) {
                String id = mInfoItemList.get(index).getId();				
                mTextList.get(index).setText(extensionInfo.getValue(id));
            }
        }
    }

    @Override
    protected void updateInfo(UserInfo userInfo) {
        super.updateInfo(userInfo);

        UserExtensionInfo extensionInfo = (UserExtensionInfo) userInfo;

        for (int index = 0; index < mTextList.size(); index++) {
            String id = mInfoItemList.get(index).getId();			
            extensionInfo.setValue(id, mTextList.get(index).getText());
        }
    }

}