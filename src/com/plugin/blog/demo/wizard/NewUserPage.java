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

import com.plugin.blog.demo.data.User;
import com.plugin.blog.demo.data.UserManager;

public class NewUserPage extends WizardPage implements IWizardPage {
    private Text mNameText;
    private Combo mCategoryCombo;

    protected NewUserPage() {
        super("NewUserPage");
        setTitle("新建用户");
        setDescription("设置注意事项");
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

        GridData nameGd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        nameGd.widthHint = 40;
        Label nameLabel = new Label(group, SWT.NONE);
        nameLabel.setText("姓名：");
        nameLabel.setLayoutData(nameGd);

        mNameText = new Text(group, SWT.BORDER);
        mNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        mNameText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                checkButtonStatus();
            }
        });

        Label ageLabel = new Label(group, SWT.NONE);
        ageLabel.setText("类别：");
        ageLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

        mCategoryCombo = new Combo(group, SWT.BORDER|SWT.READ_ONLY);
        mCategoryCombo.setItems(User.CATEGORY_ARRAY);
        GridData categoryGd = new GridData(SWT.LEFT, SWT.CENTER, true, false);
        categoryGd.widthHint = 60;
        mCategoryCombo.setLayoutData(categoryGd);
        mCategoryCombo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                checkButtonStatus();
            }
        });

        checkButtonStatus();
    }

    private void checkButtonStatus() {
        if (mNameText.getText() == null || mNameText.getText().isEmpty()) {
            setErrorMessage("姓名不能为空");
            setPageComplete(false);
            return;
        }

        String name = mNameText.getText();
        for (Object obj : UserManager.getInstance().getElements()) {
            User user = (User)obj;
            if (user.getName().equals(name)) {
                setErrorMessage("该姓名已经存在");
                setPageComplete(false);
                return;
            }
        }

        if (mCategoryCombo.getText() == null || mCategoryCombo.getText().isEmpty()) {
            setErrorMessage("类别不能为空");
            setPageComplete(false);
            return;
        }

        setErrorMessage(null);
        setPageComplete(true);
    }

    public String getName() {
        return mNameText.getText();
    }

    public String getCategory() {
        return mCategoryCombo.getText();
    }
}

