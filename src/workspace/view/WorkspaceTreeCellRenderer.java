package workspace.view;

import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import workspace.model.Attribute;
import workspace.model.AttributeConstraint;
import workspace.model.Entity;
import workspace.model.InformationResource;

public class WorkspaceTreeCellRenderer extends DefaultTreeCellRenderer{

	@Override
	public Component getTreeCellRendererComponent(JTree tree,
													Object value,
													boolean selected,
													boolean expanded,
													boolean leaf,
													int row,
													boolean hasFocus) {
		
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		
		if(value instanceof InformationResource) {
			URL image = this.getClass().getResource("/icons/Info.png");
			Icon icon = null;
			if(image != null) {
				icon = new ImageIcon(image);
				this.setIcon(icon);
			}
		}
		else if(value instanceof Entity) {
			URL image = getClass().getResource("/icons/Tables.png");
			Icon icon = null;
			if(image != null) {
				icon = new ImageIcon(image);
				this.setIcon(icon);
			}
		}
		else if(value instanceof Attribute) {
			URL image = getClass().getResource("/icons/Attributes.png");
			Icon icon = null;
			if(image != null) {
				icon = new ImageIcon(image);
				this.setIcon(icon);
			}
		}
		
		else if(value instanceof AttributeConstraint) {
			URL image = this.getClass().getResource("/icons/Constraint.png");
			Icon icon = null;
			if(image != null) {
				icon = new ImageIcon(image);
				this.setIcon(icon);
			}
		}
		return this;
	}

}
