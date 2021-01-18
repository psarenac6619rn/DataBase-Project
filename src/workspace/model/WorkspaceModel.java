package workspace.model;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import app.MainFrame;
import dataBase.DatabaseImplementation;
import dataBase.MSSQLrepository;
import dataBase.settings.SettingsImplementation;

public class WorkspaceModel extends DefaultTreeModel{
	
	public WorkspaceModel() {
		super(MainFrame.getInstance().getAppCore().getIr());
		
	}

	@Override
	public void addTreeModelListener(TreeModelListener listener) {
		// TODO Auto-generated method stub
		super.addTreeModelListener(listener);
	}

	@Override
	public Object getChild(Object parent, int index) {
		return ((DefaultMutableTreeNode) parent).getChildAt(index);
	}

	@Override
	public int getChildCount(Object parent) {
		return ((DefaultMutableTreeNode) parent).getChildCount();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		return ((DefaultMutableTreeNode) parent).getIndex((DefaultMutableTreeNode)child);
	}

	@Override
	public Object getRoot() {
		// TODO Auto-generated method stub
		return super.getRoot();
	}

	@Override
	public void removeTreeModelListener(TreeModelListener listener) {
		// TODO Auto-generated method stub
		super.removeTreeModelListener(listener);
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {
		// TODO Auto-generated method stub
		super.valueForPathChanged(arg0, arg1);
	}

	@Override
	public boolean isLeaf(Object arg0) {
		return ((DefaultMutableTreeNode) arg0).isLeaf();
	}



}
