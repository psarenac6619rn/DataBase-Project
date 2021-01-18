package actionManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.MainFrame;
import view.Tabs;
import workspace.model.Attribute;
import workspace.model.Entity;

public class UpdateAction extends AbstractAction{
	
	
	private ArrayList<Attribute> attr;
	private String query;
	Entity o;
	
	public UpdateAction() {
		putValue(NAME, "Update");
		putValue(SHORT_DESCRIPTION, "Update");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog jd = new JDialog();
		JPanel jp = new JPanel();
		JComboBox<String> jcb1 = new JComboBox<String>();
		JComboBox<String> jcb2 = new JComboBox<String>();
		jd.setSize(600,200);
		jd.setLayout(new BorderLayout());
		jp.setLayout(new FlowLayout());
		jd.add(jp,BorderLayout.CENTER);
		
		
		
		for(Entity en : MainFrame.getInstance().getAppCore().getIr().getEntities()) {
			if(en.getName().equals(((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getName())) {
				o = en;
			}
					
		}
		
		JLabel jl = new JLabel("Novo polje");
		
		jp.add(jl);
		
		String imeTabele = o.getName();
			
		attr = o.getAttributes();
			
		for(Attribute at:attr) {
				
			jcb1.addItem(at.getName());
			jcb2.addItem(at.getName());
		}
		
		jp.add(jcb1);
		
		JTextField jtx1 = new JTextField();
		jtx1.setPreferredSize(new Dimension(100,24));
		jp.add(jtx1);
		
		JLabel jl2 = new JLabel("Staro polje");
		
		jp.add(jl2);
		
		jp.add(jcb2);
		
		
		JTextField jtx2 = new JTextField();
		jtx2.setPreferredSize(new Dimension(100,24));
		jp.add(jtx2);
		
		JButton jb = new JButton("UPDATE");
		
		jd.add(jb,BorderLayout.SOUTH);
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				query = "update " + imeTabele + " set ";
				query = query + jcb1.getSelectedItem().toString();
				query = query + "='" + jtx1.getText() + "'" + " where ";
				query = query + jcb2.getSelectedItem().toString() + "='" + jtx2.getText() + "'";
				
				System.out.println(query);
				
				MainFrame.getInstance().getAppCore().getDatabase().update(query);
				
				MainFrame.getInstance().getAppCore().readDataFromTable(o.getName(), ((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getTableModel());
				
				jd.setVisible(false);
			}
		});
		
		jd.setVisible(true);
	}

}
