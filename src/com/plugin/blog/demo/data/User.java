package com.plugin.blog.demo.data;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.views.properties.IPropertySource;

public class User implements IAdaptable {
    public final static String[] CATEGORY_ARRAY = 
            {"朋友", "同事", "同学", "亲属"};

    private String mName;
    private String mCategory;

    public User(String name, String category) {
        this.mName = name;
        this.mCategory = category;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
        UserManager.getInstance().modifyObject(this);
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
        UserManager.getInstance().modifyObject(this);
    }

	@Override
	public IPropertySource getAdapter(Class adapter) {
		if (adapter == IPropertySource.class) {
			return new UserPropertySource(this);
		}
		return null;
	}
}
