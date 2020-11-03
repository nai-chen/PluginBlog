package com.plugin.blog.demo.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class UserBasicInfoPage extends WizardPage implements IWizardPage {

    private Combo mSexComb;
    private Text mAgeText;

    protected UserBasicInfoPage() {
        super("UserBasicInfoPage");

        setTitle("�û�������Ϣ");
        setDescription("�û�������Ϣע������");
    }

    @Override
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        final GridLayout containerLayout = new GridLayout(2, false);
        container.setLayout(containerLayout);

        setControl(container);

        Group group = new Group(container, SWT.NONE);
        group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
        group.setText("�û�������Ϣ");
        GridLayout groupLayout = new GridLayout(2, false);
        groupLayout.marginTop = 10;
        groupLayout.marginLeft = 10;
        groupLayout.marginRight = 10;
        group.setLayout(groupLayout);

        GridData sexGd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        sexGd.widthHint = 40;
        Label sexLabel = new Label(group, SWT.NONE);
        sexLabel.setText("�Ա�");
        sexLabel.setLayoutData(sexGd);

        mSexComb = new Combo(group, SWT.BORDER|SWT.READ_ONLY);
        mSexComb.setItems(new String[]{"��", "Ů"});
        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, true, false);
        gd.widthHint = 60;
        mSexComb.setLayoutData(gd);
        mSexComb.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                checkButtonStatus();
            }
        });

        Label ageLabel = new Label(group, SWT.NONE);
        ageLabel.setText("���䣺");
        ageLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

        mAgeText = new Text(group, SWT.BORDER);
        mAgeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        mAgeText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                checkButtonStatus();
            }
        });

        checkButtonStatus();
    }

    private void checkButtonStatus() {
        if (mSexComb.getText() == null || mSexComb.getText().isEmpty()) {
            setErrorMessage("�Ա���Ϊ��");
            setPageComplete(false);
            return;
        }

        if (mAgeText.getText() == null || mSexComb.getText().isEmpty()) {
            setErrorMessage("���䲻��Ϊ��");
            setPageComplete(false);
            return;
        }

        try {
            Integer.parseInt(mAgeText.getText());
        } catch (NumberFormatException e) {
            setErrorMessage("�����ʽ����");
            setPageComplete(false);
            return;
        }

        setErrorMessage(null);
        setPageComplete(true);
    }

    public boolean isMale() {
        return mSexComb.getSelectionIndex() == 0;
    }

    public int getAge() {
        return Integer.parseInt(mAgeText.getText());
    }

}
