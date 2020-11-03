package com.plugin.blog.demo.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import com.plugin.blog.demo.data.User;

public class UserInfoAction implements IViewActionDelegate {
	private IViewPart mView;
    private User mUser;
    
	@Override
	public void run(IAction action) {
		if (mView != null && mUser != null) {
            MessageBox mb = new MessageBox(mView.getSite().getShell());
            mb.setText("消息");
            mb.setMessage("用户" + mUser.getName() + "被选择！");
            mb.open();
        }
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection != null) {
            IStructuredSelection ss = (IStructuredSelection) selection;
            if (!ss.isEmpty()) {
                mUser = (User) ss.getFirstElement();
                action.setEnabled(true);
            } else {
                action.setEnabled(false);
            }
        } else {
            action.setEnabled(false);
        }
	}

	@Override
	public void init(IViewPart view) {
		this.mView = view;
	}

}
