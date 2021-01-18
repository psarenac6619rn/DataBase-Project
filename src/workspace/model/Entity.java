package workspace.model;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;


public class Entity extends DefaultMutableTreeNode{

	private ArrayList<Attribute> attributes;
	private String name;
	private InformationResource parent;
	
	public Entity(String name, InformationResource parent) {
		this.name = name;
		this.parent = parent;
		attributes = new ArrayList<Attribute>();
    }

	@Override
	public void add(MutableTreeNode newChild) {
		Attribute entity = (Attribute)newChild;
		attributes.add(entity);
	}

	@Override
	public Enumeration<TreeNode> children() {
		return (Enumeration<TreeNode>)attributes;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int index) {
		return this.attributes.get(index);
	}

	@Override
	public int getChildCount() {
		return this.attributes.size();
	}

	@Override
	public int getIndex(TreeNode aChild) {
		return this.attributes.indexOf(aChild);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public void insert(MutableTreeNode arg0, int arg1) {
		this.attributes.add(arg1, (Attribute)arg0);
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public void remove(int childIndex) {
		this.attributes.remove(childIndex);
	}

	@Override
	public void remove(MutableTreeNode aChild) {
		this.attributes.remove(aChild);
	}

	@Override
	public boolean isRoot() {
		return false;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(InformationResource parent) {
		this.parent = parent;
	}
	
	
}