package actionManager;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import workspace.enums.AttributeType;
import workspace.model.Attribute;
import workspace.model.Entity;
import app.MainFrame;
import view.Tabs;


public class AddAction extends AbstractAction{
	
	private ArrayList<AttributeType> atyl;
	private ArrayList<JTextField> jtarr;
	private ArrayList<String> jtlista;
	private String nastavak;
	private String polja;
	private ArrayList<String> polja1;
	private Entity o;

	public AddAction() {
		putValue(NAME, "Add");
		putValue(SHORT_DESCRIPTION, "Add");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		JDialog jd = new JDialog();
		JPanel jp = new JPanel();
		
		jd.setSize(500,200);
		jd.setLayout(new BorderLayout());
		jp.setLayout(new FlowLayout());
		jd.add(jp,BorderLayout.CENTER);
		jd.setVisible(true);
		
		
		
		atyl = new ArrayList<AttributeType>();
		jtarr = new ArrayList<JTextField>();
		jtlista= new ArrayList<String>();
		polja1 = new ArrayList<String>();
		
		
		for(Entity en : MainFrame.getInstance().getAppCore().getIr().getEntities()) {
			if(en.getName().equals(((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getName())) {
				o = en;
			}		
		}
			ArrayList<Attribute> att = o.getAttributes();
			
			for(Attribute at : att) {
				String p = at.getName();
				System.out.println(" " + p);
				AttributeType aty=at.getAttributeType();
				System.out.println(" " + aty);
				int br= at.getLength();
				System.out.println(" " + br);
				atyl.add(aty);
				jp.add(new JLabel(" " + p));
				
				JTextField jt = new JTextField();
				jtarr.add(jt);
				jt.setPreferredSize(new Dimension(100,24));
				jp.add(jt);
				
				polja1.add(at.getName());
				
			}
		
		JButton jb = new JButton("ADD");
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(JTextField jt : jtarr) {
					jtlista.add("'" + jt.getText() + "'");
				}
				
				polja = " " + polja1.get(0);
				
				for(int i = 1 ; i< polja1.size();i++) {
					polja = polja + ", " + polja1.get(i);
				}
				
				
				nastavak = " " +  jtlista.get(0);
				for(int i = 1; i<jtlista.size(); i++) {
					nastavak = nastavak +", " + jtlista.get(i);
				}
				
				System.out.println(nastavak);
				
				String query = "insert into " + o.getName() + " (" + polja;
				query = query + ") " + "values ";
				query = query + "(";
				query = query + nastavak;
				query = query + ")";
				System.out.println(query);
				
				MainFrame.getInstance().getAppCore().getDatabase().addRow(query);
				
				MainFrame.getInstance().getAppCore().readDataFromTable(o.getName(), ((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getTableModel());
				query = " ";
				
				nastavak = "";
				jd.setVisible(false);
			}
		});
		
		jd.add(jb,BorderLayout.SOUTH);
		
		
		jd.setVisible(true);
		
		
	}	
	
}
