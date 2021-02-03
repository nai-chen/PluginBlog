package com.plugin.blog.demo.data;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class UserPropertySource implements IPropertySource {
	public final static String PROPERTY_NAME = "com.plugin.blog.demo.PROPERTY_NAME";
	public final static String PROPERTY_CATEGORY = "com.plugin.blog.demo.PROPERTY_CATEGORY";

	private User mUser;

	public UserPropertySource(User user) {
		mUser = user;
	}

	@Override
	public Object getEditableValue() {
		return mUser;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		TextPropertyDescriptor namePropertyDescriptor = new TextPropertyDescriptor(PROPERTY_NAME, "姓名");

		ComboBoxPropertyDescriptor agePropertyDescriptor = new ComboBoxPropertyDescriptor(PROPERTY_CATEGORY, "类别",
				User.CATEGORY_ARRAY);
		agePropertyDescriptor.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return User.CATEGORY_ARRAY[(Integer) element];
			}
		});

		return new IPropertyDescriptor[] { namePropertyDescriptor, agePropertyDescriptor };
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (PROPERTY_NAME.equals(id)) {
			return mUser.getName();
		} else if (PROPERTY_CATEGORY.equals(id)) {
			for (int index = 0; index < User.CATEGORY_ARRAY.length; index++) {
				String category = User.CATEGORY_ARRAY[index];
				if (category.equals(mUser.getCategory())) {
					return index;
				}
			}
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		if (PROPERTY_NAME.equals(id) || PROPERTY_CATEGORY.equals(id)) {
			return true;
		}
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (PROPERTY_NAME.equals(id)) {
			mUser.setName(value.toString());
		} else if (PROPERTY_CATEGORY.equals(id)) {
			int index = (Integer) value;
			if (index >= 0 && index < User.CATEGORY_ARRAY.length) {
				mUser.setCategory(User.CATEGORY_ARRAY[index]);
			}
		}

	}
}
