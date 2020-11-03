package com.plugin.blog.demo.edit;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.plugin.blog.demo.ImageKeys;
import com.plugin.blog.demo.data.UserBasicInfo;
import com.plugin.blog.demo.data.UserContractInfo;
import com.plugin.blog.demo.data.UserInfo;
import com.plugin.blog.demo.extension.UserExtensionInfo;
import com.plugin.blog.demo.extension.UserExtensionInfoDetailsPage;

public class UserInfoFormPage extends FormPage {
    private UserInfoBlock mBlock;

    public UserInfoFormPage(FormEditor formEditor) {
        super(formEditor, "com.plugin.blog.demo.edit.UserInfoFormPage", "编辑");
        mBlock = new UserInfoBlock(this);
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        ScrolledForm form = managedForm.getForm();
        form.setText("编辑个人信息");
        form.setBackgroundImage(ImageKeys.getImageDescriptor(
                ImageKeys.IMAGE_FORM_BANNER).createImage());

        mBlock.createContent(managedForm);
    }

    public class UserInfoBlock extends MasterDetailsBlock {
        private FormPage mFormPage;

        private AbstractDetailsPage mBasicInfoDetailPage, mContractInfoDetailsPage, mExtensionInoDetailsPage;

        public UserInfoBlock(FormPage formPage) {
            this.mFormPage = formPage;
            mBasicInfoDetailPage = new UserBasicInfoDetailsPage();
            mContractInfoDetailsPage = new UserContractInfoDetailsPage();
            mExtensionInoDetailsPage = new UserExtensionInfoDetailsPage();
        }

        @Override
        protected void createMasterPart(IManagedForm managedForm, Composite parent) {
            FormToolkit toolkit = managedForm.getToolkit();
            Section section = toolkit.createSection(parent,
                    Section.DESCRIPTION | Section.TITLE_BAR);
            section.setText("信息列表");
            section.setDescription("包括基本信息、联系方式和其他信息");
            section.marginHeight = 5;

            Composite compUserInfo = toolkit.createComposite(section, SWT.WRAP);
            compUserInfo.setLayout(new FillLayout());

            Table table = toolkit.createTable(compUserInfo, SWT.NULL);
            toolkit.paintBordersFor(compUserInfo);
            section.setClient(compUserInfo);
            final SectionPart sectionPart = new SectionPart(section);
            managedForm.addPart(sectionPart);

            TableViewer tableViewer = new TableViewer(table);
            tableViewer.setContentProvider(new UserInfoContentProvider());
            tableViewer.setLabelProvider(new UserInfoLabelProvider());
            tableViewer.setInput(mFormPage.getEditor().getEditorInput());

            tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {				
                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    managedForm.fireSelectionChanged(sectionPart, event.getSelection());
                }
            });
            
            MenuManager menuManager = new MenuManager();
            menuManager.setRemoveAllWhenShown(true);

            Menu menu = menuManager.createContextMenu(table);
            table.setMenu(menu);
            getEditor().getSite().registerContextMenu(menuManager, tableViewer);
        }

        @Override
        protected void registerPages(DetailsPart detailsPart) {
            // 和上面的managedForm.fireSelectionChanged()方法相对应
            // 最终会调用Selection.element.getClass对应的IDetailsPage
            detailsPart.registerPage(UserBasicInfo.class, mBasicInfoDetailPage);
            detailsPart.registerPage(UserContractInfo.class, mContractInfoDetailsPage);
            detailsPart.registerPage(UserExtensionInfo.class, mExtensionInoDetailsPage);
        }

        @Override
        protected void createToolBarActions(IManagedForm managedForm) {
        }

    }

    private static class UserInfoContentProvider implements IStructuredContentProvider {
        @Override
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof DemoEditorInput) {
                return ((DemoEditorInput) inputElement).getManager().toArray();
            }
            return new Object[0];
        }
    }

    private static class UserInfoLabelProvider implements ITableLabelProvider {

        @Override
        public void addListener(ILabelProviderListener listener) {
        }

        @Override
        public void dispose() {
        }

        @Override
        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        @Override
        public void removeListener(ILabelProviderListener listener) {

        }

        @Override
        public Image getColumnImage(Object element, int columnIndex) {
            return null;
        }

        @Override
        public String getColumnText(Object element, int columnIndex) {
            if (element instanceof UserInfo) {
                return ((UserInfo) element).getTitle();
            }
            return null;
        }
    }

}

