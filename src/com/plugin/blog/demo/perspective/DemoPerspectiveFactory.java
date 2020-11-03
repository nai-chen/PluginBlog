package com.plugin.blog.demo.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class DemoPerspectiveFactory implements IPerspectiveFactory {
    public final static String ID_VIEW = "com.plugin.blog.demo.view.DemoViewPart";

    @Override
    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();

        IFolderLayout left = layout.createFolder("left", 
                IPageLayout.LEFT, 0.25f, editorArea);
        left.addView(ID_VIEW);

        IFolderLayout bottom = layout.createFolder("bottom", 
                IPageLayout.BOTTOM, 0.66f, editorArea);
        bottom.addView(IPageLayout.ID_PROP_SHEET);
    }

}
