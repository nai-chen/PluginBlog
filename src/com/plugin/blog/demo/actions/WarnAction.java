package com.plugin.blog.demo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IViewPart;

import com.plugin.blog.demo.ImageKeys;

public class WarnAction extends Action {
	private IViewPart mPart;
 
	public WarnAction(IViewPart targetPart) {
		super("少御", ImageKeys.getImageDescriptor(ImageKeys.IMAGE_WARN));
		this.mPart = targetPart;
	}
 
	@Override
	public void run() {
		MessageBox mb = new MessageBox(mPart.getSite().getShell());
		mb.setText("少御");
		mb.setMessage("少御");
		mb.open();
	}
	
}
