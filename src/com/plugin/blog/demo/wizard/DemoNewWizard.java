package com.plugin.blog.demo.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import com.plugin.blog.demo.data.User;
import com.plugin.blog.demo.data.UserBasicInfo;
import com.plugin.blog.demo.data.UserContractInfo;
import com.plugin.blog.demo.data.UserInfoManager;
import com.plugin.blog.demo.data.UserManager;
import com.plugin.blog.demo.edit.DemoEditorInput;

public class DemoNewWizard extends Wizard implements INewWizard {
    private IWorkbench mWorkbench;

    private NewUserPage mNewUserPage;
    private UserBasicInfoPage mBasicInfoPage;
    private UserContractInfoPage mContractInfoPage;

    public DemoNewWizard() {
        setWindowTitle("新建用户");
    }

    @Override
    public void addPages() {
        mNewUserPage = new NewUserPage();
        addPage(mNewUserPage);
        mBasicInfoPage = new UserBasicInfoPage();
        addPage(mBasicInfoPage);
        mContractInfoPage = new UserContractInfoPage();
        addPage(mContractInfoPage);
    }

    @Override
    public boolean canFinish() {
        if (getContainer().getCurrentPage() == mNewUserPage
                && mNewUserPage.isPageComplete()) {
            return true;
        }
        return super.canFinish();				
    }

    @Override
    public boolean performFinish() {
        if (getContainer().getCurrentPage() == mNewUserPage) {
            String name = mNewUserPage.getName();
            String category = mNewUserPage.getCategory();

            saveUser(name, category);
        } else {
            String name = mNewUserPage.getName();
            String category = mNewUserPage.getCategory();
            boolean sex = mBasicInfoPage.isMale();
            int age = mBasicInfoPage.getAge();
            String phone = mContractInfoPage.getPhone();
            String address = mContractInfoPage.getAddress();

            saveUserInfo(name, category, sex, age, phone, address);
        }
        return true;
    }

    private User saveUser(String name, String category) {
        if (mWorkbench != null && mWorkbench.getActiveWorkbenchWindow() != null) {
            IWorkbenchWindow window = mWorkbench.getActiveWorkbenchWindow();
            try {
                window.getActivePage().showView(
                            "com.plugin.blog.demo.view.DemoViewPart");
            } catch (PartInitException e) {
                e.printStackTrace();
            }
        }
        User user = new User(name, category);
        UserManager.getInstance().addObject(user);
        return user;
    }

    private void saveUserInfo(String name, String category, boolean male,
            int age, String phone, String address) {
        User user = saveUser(name, category);

        UserInfoManager manager = UserInfoManager.getInstance(name);
        UserBasicInfo basicInfo = (UserBasicInfo) manager.getUserInfo(0);
        basicInfo.setMale(male);
        basicInfo.setAge(age);

        UserContractInfo contractInfo = (UserContractInfo) manager.getUserInfo(1);
        contractInfo.setPhone(phone);
        contractInfo.setAddress(address);

        manager.doSave(null);

        if (mWorkbench != null && mWorkbench.getActiveWorkbenchWindow() != null) {
            IWorkbenchWindow window = mWorkbench.getActiveWorkbenchWindow();
            try {
                DemoEditorInput input = new DemoEditorInput(user);
                window.getActivePage().openEditor(input, 
                        "com.plugin.blog.demo.edit.DemoEditorPart");
            } catch (PartInitException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        mWorkbench = workbench;
    }

}
