package com.plugin.blog.demo.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

public class OpenViewAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow mWindow;
	
	@Override
	public void run(IAction action) {
//		MessageBox mb = new MessageBox(mWindow.getShell());
//		mb.setText("消息");
//		mb.setMessage("打开视图");
//		mb.open();
		IWorkbenchPage page = mWindow.getActivePage();
        if (page != null) {
            try {
                page.showView("com.plugin.blog.demo.view.DemoViewPart");
            } catch (PartInitException e) {
            }
        }
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
