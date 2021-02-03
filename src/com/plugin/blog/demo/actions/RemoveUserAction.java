package com.plugin.blog.demo.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.plugin.blog.demo.data.User;
import com.plugin.blog.demo.data.UserManager;

public class RemoveUserAction implements IObjectActionDelegate {
    private IWorkbenchPart mWorkbenchPart;
    private ISelection mSelection;

    @Override
    public void run(IAction action) {
        if (mWorkbenchPart != null && mSelection != null) {
            IStructuredSelection ss = (IStructuredSelection) mSelection;
            if (!ss.isEmpty()) {
                User user = (User) ss.getFirstElement();
                boolean result = MessageDialog.openConfirm(
                        mWorkbenchPart.getSite().getShell(), 
                        "删除用户", "您是否确认删除" + user.getName() + "?");
                if (result) {
                    UserManager.getInstance().removeObject(user);
                }
            }
        }
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        this.mSelection = selection;
    }

    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        this.mWorkbenchPart = targetPart;
    }

}
