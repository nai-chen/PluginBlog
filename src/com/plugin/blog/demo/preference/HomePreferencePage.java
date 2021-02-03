package com.plugin.blog.demo.preference;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class HomePreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	@Override
	protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout containerLayout = new GridLayout();
        container.setLayout(containerLayout);

        Label label = new Label(container, SWT.NONE);
        label.setText("用户配置");

        label = new Label(container, SWT.NONE);
        label.setText("这是用户配置的首页");

        return container;
	}

	@Override
	public void init(IWorkbench workbench) {
		noDefaultAndApplyButton();
	}
	
}
