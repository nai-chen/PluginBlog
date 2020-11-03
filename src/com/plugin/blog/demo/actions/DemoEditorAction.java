package com.plugin.blog.demo.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

public class DemoEditorAction implements IEditorActionDelegate {

    private IEditorPart mEditorPart;

    @Override
    public void run(IAction action) {
        MessageBox mb = new MessageBox(mEditorPart.getSite().getShell());
        mb.setText("ÏûÏ¢");
        mb.setMessage("±à¼­Æ÷²Ëµ¥");
        mb.open();
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
    }

    @Override
    public void setActiveEditor(IAction action, IEditorPart targetEditor) {
        this.mEditorPart = targetEditor;
    }

}
