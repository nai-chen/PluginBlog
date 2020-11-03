package com.plugin.blog.demo;

import org.eclipse.jface.resource.ImageDescriptor;

public class ImageKeys {
	public static final String IMAGE_WARN = "icons/icon_warn.png";
	public static final String IMAGE_FORM_BANNER = "icons/icon_form_banner.png";

	public static ImageDescriptor getImageDescriptor(String path) {
		return Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, path);
	}

}
