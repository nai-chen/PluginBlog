package com.plugin.blog.demo.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;

import com.plugin.blog.demo.data.User;
import com.plugin.blog.demo.data.UserManager;
import com.plugin.blog.demo.wizard.DemoNewWizard;

public class AddUserAction implements IViewActionDelegate {

    private IViewPart mViewPart;

    @Override
    public void run(IAction action) {
        if (mViewPart != null) {
//            InputDialog dialog = new InputDialog(mViewPart.getViewSite().getShell(),
//                "添加用户", "输入用户名", "", null);
//            dialog.open();
//            String value = dialog.getValue();
//            if (value != null && value.length() > 0) {
//                UserManager.getInstance().addObject(
//                    new User(value, User.CATEGORY_ARRAY[0]));
//            }
        	DemoNewWizard wizard = new DemoNewWizard();
        	wizard.init(mViewPart.getSite().getWorkbenchWindow().getWorkbench(), null);
        	WizardDialog dialog = new WizardDialog(mViewPart.getSite().getShell(), wizard);
        	dialog.open();
        }
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
    }

    @Override
    public void init(IViewPart view) {
        mViewPart = view;
    }

}
