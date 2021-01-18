package workspace.model;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class InformationResource extends DefaultMutableTreeNode {

	private ArrayList<Entity> entities;
	String name;
	
	public InformationResource(String name) {
		super();
		this.name = name;
		entities = new ArrayList<Entity>();
		
	}
	
	public InformationResource() {
		super();
		entities = new ArrayList<Entity>();
		
	}

	@Override
	public void add(MutableTreeNode newChild) {
		Entity entity = (Entity)newChild;
		this.getEntities().add(entity);
	}

	@Override
	public Enumeration<TreeNode> children() {
		return (Enumeration<TreeNode>)entities;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int index) {
		return this.entities.get(index);
	}

	@Override
	public int getChildCount() {
		return this.entities.size();
	}

	@Override
	public int getIndex(TreeNode aChild) {
		return this.entities.indexOf(aChild);
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public void insert(MutableTreeNode arg0, int arg1) {
		this.entities.add(arg1, (Entity)arg0);
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public void remove(int childIndex) {
		this.entities.remove(childIndex);
	}

	@Override
	public void remove(MutableTreeNode aChild) {
		this.entities.remove(aChild);
	}

	@Override
	public boolean isRoot() {
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}