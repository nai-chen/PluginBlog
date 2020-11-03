package com.plugin.blog.demo.preference;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FontFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.plugin.blog.demo.Activator;

public class SamplePreferencePage extends FieldEditorPreferencePage
        implements IWorkbenchPreferencePage {
    private final static String[][] RADIO_ARRAY = new String[][] {
        {"radio1", "value1"}, {"radio2", "value2"}, {"radio3", "value3"}};

    public SamplePreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Field Sample");
    }

    @Override
    public void init(IWorkbench workbench) {
    }

    @Override
    protected void createFieldEditors() {
        Composite parent = getFieldEditorParent();

        addField(new BooleanFieldEditor(PreferenceConstants.P_BOOLEAN,
                "Boolean", parent));

        addField(new RadioGroupFieldEditor(PreferenceConstants.P_RADIO,
                "Radio", 1, RADIO_ARRAY, parent, true));

        addField(new ComboFieldEditor(PreferenceConstants.P_COMBO,
                "Combo", RADIO_ARRAY, parent));

        addField(new StringFieldEditor(PreferenceConstants.P_STRING,
                "String", parent));

        addField(new IntegerFieldEditor(PreferenceConstants.P_INTEGER,
                "Integer", parent));

        addField(new ColorFieldEditor(PreferenceConstants.P_COLOR, 
                "Color", parent));

        addField(new FontFieldEditor(PreferenceConstants.P_FONT, 
                "Font", parent));

        addField(new DirectoryFieldEditor(PreferenceConstants.P_DIR, 
                "Directory", parent));
    }
}
