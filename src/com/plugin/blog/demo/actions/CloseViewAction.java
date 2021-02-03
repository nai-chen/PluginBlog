package com.plugin.blog.demo.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class CloseViewAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow mWindow;
	
	@Override
	public void run(IAction action) {
        MessageBox mb = new MessageBox(mWindow.getShell());
        mb.setText("消息");
        mb.setMessage("关闭视图");
        mb.open();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.mWindow = window;
	}

}
