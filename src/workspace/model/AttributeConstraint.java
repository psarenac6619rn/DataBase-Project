package workspace.model;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import workspace.enums.*;


public class AttributeConstraint extends DefaultMutableTreeNode {
	
	private ConstraintType constraintType;
	private String name;
	private Attribute parent;
	
	
	public AttributeConstraint(String name, Attribute parent, ConstraintType constraintType) {
	        this.name = name;
	        this.parent = parent;
	        this.constraintType = constraintType;
	        }

	@Override
	public void add(MutableTreeNode newChild) {
    	return;
	}

	@Override
	public Enumeration<TreeNode> children() {
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public TreeNode getChildAt(int index) {
		return null;
	}

	@Override
	public int getChildCount() {
		return super.getChildCount();
	}

	@Override
	public int getIndex(TreeNode aChild) {
		return super.getIndex(aChild);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public void remove(int childIndex) {
		return;
	}

	@Override
	public void remove(MutableTreeNode aChild) {
		return;
	}

	@Override
	public boolean isRoot() {
		return false;
	}
	
	@Override
	public String toString() {
		return name;
	}
	

	public ConstraintType getConstraintType() {
		return constraintType;
	}


	public void setConstraintType(ConstraintType constraintType) {
		this.constraintType = constraintType;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setParent(Attribute parent) {
		this.parent = parent;
	}
	
}
