package com.plugin.blog.demo.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.plugin.blog.demo.Activator;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

    public void initializeDefaultPreferences() {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        store.setDefault(PreferenceConstants.P_BOOLEAN, true);
        store.setDefault(PreferenceConstants.P_RADIO, "value2");
        store.setDefault(PreferenceConstants.P_COMBO, "value3");
        store.setDefault(PreferenceConstants.P_STRING, "This is a String");
        store.setDefault(PreferenceConstants.P_INTEGER, 17);
        PreferenceConverter.setDefault(store, PreferenceConstants.P_COLOR,
                Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE).getRGB());
        PreferenceConverter.setDefault(store, PreferenceConstants.P_FONT,
                Display.getCurrent().getSystemFont().getFontData());
        store.setDefault(PreferenceConstants.P_DIR, "");

        store.setDefault(PreferenceConstants.P_SHOW_PREVIEW, true);
    }

}
