package com.plugin.blog.demo.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.plugin.blog.demo.data.UserInfo;

public class EditorObjectAction implements IObjectActionDelegate {

    private IWorkbenchPart mWorkbenchPart;
    private ISelection mSelection;

    @Override
    public void run(IAction action) {
        if (mWorkbenchPart != null && mSelection != null) {
            IStructuredSelection ss = (IStructuredSelection) mSelection;
            UserInfo userInfo = (UserInfo) ss.getFirstElement();

            MessageBox mb = new MessageBox(mWorkbenchPart.getSite().getShell());
            mb.setText("消息");
            mb.setMessage("用户信息：\n" + userInfo.getText());
            mb.open();
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
