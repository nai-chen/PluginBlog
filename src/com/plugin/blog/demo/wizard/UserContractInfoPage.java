package com.plugin.blog.demo.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class UserContractInfoPage extends WizardPage implements IWizardPage {
    private Text mPhoneText;
    private Text mAddressText;

    protected UserContractInfoPage() {
        super("UserContractInfoPage");
        setTitle("用户联系信息");
        setDescription("用户联系信息注意事项");
    }

    @Override
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        container.setLayout(new GridLayout(2, false));
        setControl(container);

        Group group = new Group(container, SWT.NONE);
        group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
        group.setText("创建用户");
        GridLayout groupLayout = new GridLayout(2, false);
        groupLayout.marginTop = 10;
        groupLayout.marginLeft = 10;
        groupLayout.marginRight = 10;
        group.setLayout(groupLayout);

        GridData phoneGd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        phoneGd.widthHint = 40;
        Label phoneLabel = new Label(group, SWT.NONE);
        phoneLabel.setText("手机号：");
        phoneLabel.setLayoutData(phoneGd);

        mPhoneText = new Text(group, SWT.BORDER);
        mPhoneText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        mPhoneText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                checkButtonStatus();
            }
        });

        Label addressLabel = new Label(group, SWT.NONE);
        addressLabel.setText("住址：");
        addressLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

        mAddressText = new Text(group, SWT.BORDER);
        mAddressText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        mAddressText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                checkButtonStatus();
            }
        });

        checkButtonStatus();
    }

    private void checkButtonStatus() {
        if (mPhoneText.getText() == null || mPhoneText.getText().isEmpty()) {
            setErrorMessage("手机号不能为空");
            setPageComplete(false);
            return;
        }
        if (mAddressText.getText() == null || mAddressText.getText().isEmpty()) {
            setErrorMessage("地址不能为空");
            setPageComplete(false);
            return;
        }
        setErrorMessage(null);
        setPageComplete(true);		
    }

    public String getPhone() {
        return mPhoneText.getText();
    }

    public String getAddress() {
        return mAddressText.getText();
    }
}
