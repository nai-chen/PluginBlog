package com.plugin.blog.demo.view;

import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.plugin.blog.demo.actions.WarnAction;
import com.plugin.blog.demo.data.User;
import com.plugin.blog.demo.data.UserManager;
import com.plugin.blog.demo.edit.DemoEditorInput;

public class DemoViewPart extends ViewPart {

    private TableViewer mTableViewer;
    private Table mTable;

    private UserManager mManager = UserManager.getInstance();
    private WarnAction mWarnAction;

    @Override
    public void createPartControl(Composite parent) {
        mTableViewer = new TableViewer(parent,
                SWT.BORDER|SWT.FULL_SELECTION|SWT.MULTI);
        mTableViewer.setContentProvider(new ListContentProvider());
        mTableViewer.setLabelProvider(new TableLabelProvider());

        mTable = mTableViewer.getTable();
        TableColumn nameColumn = new TableColumn(mTable, SWT.NONE);
        nameColumn.setText("name");
        nameColumn.setWidth(120);

        TableColumn categoryColumn = new TableColumn(mTable, SWT.NONE);
        categoryColumn.setWidth(80);
        categoryColumn.setText("category");

        mTable.setHeaderVisible(true);
        mTable.setLinesVisible(true);

        mTableViewer.setInput(mManager);
        
        getViewSite().setSelectionProvider(mTableViewer);
        
        mTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {			
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                if (!selection.isEmpty()) {
                    User user = (User) selection.getFirstElement();
                    getViewSite().getActionBars().getStatusLineManager().setMessage(
                        "name = " + user.getName() + " category = " + user.getCategory());
                }
            }
        });
        
        mTableViewer.addDoubleClickListener(new IDoubleClickListener() {			
            @Override
            public void doubleClick(DoubleClickEvent event) {
                ISelection selection = mTableViewer.getSelection();
                User user = (User) ((IStructuredSelection) selection).getFirstElement();
                IWorkbenchPage activePage = getSite().getWorkbenchWindow().getActivePage();
                if (activePage != null) {
                    try {
                        activePage.openEditor(new DemoEditorInput(user), 
                            "com.plugin.blog.demo.edit.DemoEditorPart");
                    } catch (PartInitException e) {
                    }
                }
            }
        });
        
        MenuManager menuManager = new MenuManager();
        menuManager.setRemoveAllWhenShown(true);

        Menu menu = menuManager.createContextMenu(mTable);
        mTable.setMenu(menu);
        getSite().registerContextMenu(menuManager, mTableViewer);
        
        addMenu();
    }
    
    private void addMenu() {
        mWarnAction = new WarnAction(this);
	
        MenuManager menuManager = new MenuManager();
        menuManager.setRemoveAllWhenShown(true);
        menuManager.addMenuListener(new IMenuListener() {
            @Override
            public void menuAboutToShow(IMenuManager manager) {
                addMenu(manager);				
            }
        });

        Menu menu = menuManager.createContextMenu(mTable);
        mTable.setMenu(menu);
        getSite().registerContextMenu(menuManager, mTableViewer);

        IActionBars actionBar = getViewSite().getActionBars();
        addMenu(actionBar.getMenuManager());
        addMenu(actionBar.getToolBarManager());
    }

    private void addMenu(IContributionManager manager) {
        manager.add(mWarnAction);		
    }

    @Override
    public void setFocus() {
        mTable.setFocus();
    }
    
    @Override
    public void init(IViewSite site, IMemento memento) throws PartInitException {
        super.init(site, memento);
        mManager.init(memento);		
    }

    @Override
    public void saveState(IMemento memento) {		
        super.saveState(memento);
        mManager.saveState(memento);
    }
    
}
