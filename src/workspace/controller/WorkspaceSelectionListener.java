package workspace.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import app.MainFrame;
import view.TableModel;
import view.Tabs;
import workspace.model.Attribute;
import workspace.model.AttributeConstraint;
import workspace.model.Entity;

public class WorkspaceSelectionListener implements TreeSelectionListener {

	@Override
	public void valueChanged(TreeSelectionEvent selectedNode) {
		TreePath path = selectedNode.getPath();
		MainFrame.getInstance().getWorkspace().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
					if(node instanceof Entity) {
						Entity ent = (Entity) node;
						
						Tabs tab = new Tabs(new TableModel());
						tab.setName(ent.getName());
						//TableModel tableModel = ((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getTableModel();
						MainFrame.getInstance().getAppCore().readDataFromTable(ent.getName(),tab.getTableModel());
						int flag = 0;
						for(int i = 0; i < MainFrame.getInstance().getTabbedPane().getTabCount(); i ++) {
							if(MainFrame.getInstance().getTabbedPane().getTitleAt(i).equalsIgnoreCase(ent.getName()))
								flag = 1;
						}
						if(flag == 0) {
						MainFrame.getInstance().getTabbedPane().addTab(ent.getName(), tab);
						
						for(int i = 0; i < ent.getAttributes().size(); i++) {
							Attribute a = ent.getAttributes().get(i);
							if(a.getInRelationWith() != null) {
								Tabs tab2 = new Tabs(new TableModel());
								//tab2.setName(((Entity)a.getInRelationWith().getParent()).getName());
								MainFrame.getInstance().getAppCore().readDataFromTable(((Entity)a.getInRelationWith().getParent()).getName(), tab2.getTableModel());
								tab2.removeToolbar();
								MainFrame.getInstance().getTabbedDonji().addTab(((Entity)a.getInRelationWith().getParent()).getName(), tab2);
								
							}else {
								System.out.println("Nije u relaciji");
							}
						}
						}	
						
					}else if(node instanceof Attribute){
						JOptionPane.showMessageDialog(null, "Selektuj tabelu");
					}else if(node instanceof AttributeConstraint) {
						JOptionPane.showMessageDialog(null, "Selektuj tabelu");
					}
				}
			}
			
		});
		
		DefaultMutableTreeNode d = (DefaultMutableTreeNode) path.getPathComponent(path.getPathCount() - 1);
		System.out.println(d.toString());
	}

}
