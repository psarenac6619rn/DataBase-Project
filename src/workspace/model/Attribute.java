package workspace.model;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import app.MainFrame;
import workspace.enums.*;

public class Attribute extends DefaultMutableTreeNode {
    
	private ArrayList<AttributeConstraint> attributeConstraints;
	private String name;
	private Entity parent;
	private AttributeType attributeType;
    private int length;
    private Attribute inRelationWith;

    public Attribute(String name, Entity parent) {
        this.name = name;
        this.parent = parent;
        attributeConstraints = new ArrayList<AttributeConstraint>();
    }

    public Attribute(String name, Entity parent, AttributeType attributeType, int length) {
        this.name = name;
        this.parent = parent;
        this.attributeType = attributeType;
        this.length = length;
        attributeConstraints = new ArrayList<AttributeConstraint>();
    }

    @Override
	public void add(MutableTreeNode newChild) {
    	AttributeConstraint entity = (AttributeConstraint)newChild;
		attributeConstraints.add(entity);
	}

	@Override
	public Enumeration<TreeNode> children() {
		return (Enumeration<TreeNode>)attributeConstraints;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int index) {
		return this.attributeConstraints.get(index);
	}

	@Override
	public int getChildCount() {
		return this.attributeConstraints.size();
	}

	@Override
	public int getIndex(TreeNode aChild) {
		return this.attributeConstraints.indexOf(aChild);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public void insert(MutableTreeNode arg0, int arg1) {
		this.attributeConstraints.add(arg1, (AttributeConstraint)arg0);
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public void remove(int childIndex) {
		this.attributeConstraints.remove(childIndex);
	}

	@Override
	public void remove(MutableTreeNode aChild) {
		this.attributeConstraints.remove(aChild);
	}

	@Override
	public boolean isRoot() {
		return false;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public ArrayList<AttributeConstraint> getAttributeConstraints() {
		return attributeConstraints;
	}

	public void setAttributeConstraints(ArrayList<AttributeConstraint> attributeConstraints) {
		this.attributeConstraints = attributeConstraints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(Entity parent) {
		this.parent = parent;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Attribute getInRelationWith() {
		return inRelationWith;
	}

	public void setInRelationWith(Attribute inRelationWith) {
		this.inRelationWith = inRelationWith;
	}
	
	public void napraviRelaciju() {
		for(int i = 0; i < attributeConstraints.size(); i++) {
			AttributeConstraint ac = this.getAttributeConstraints().get(i);
			if(ac.getConstraintType().equals(ConstraintType.FOREIGN_KEY)) {
				for(int j = 0; j < MainFrame.getInstance().getAppCore().getIr().getEntities().size(); j++) {
					Entity e = MainFrame.getInstance().getAppCore().getIr().getEntities().get(j);
					for(int k = 0; k < e.getAttributes().size(); k++) {
						Attribute a = e.getAttributes().get(k);
						if(this.getName().equals(a.getName())) {
							this.setInRelationWith(a);
						}						
					}
				}
			}
		}
	}
	
}
