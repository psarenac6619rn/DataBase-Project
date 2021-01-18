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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.MainFrame;
import view.Tabs;
import workspace.enums.AttributeType;
import workspace.model.Attribute;
import workspace.model.Entity;

public class SearchAction extends AbstractAction{

	private ArrayList<Attribute> att;
	private JComboBox<Attribute> jcbx;
	private String query;
	private Entity o;
	
	
	public SearchAction() {
		putValue(NAME, "Search");
		putValue(SHORT_DESCRIPTION, "Search");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JDialog jd = new JDialog();
		JPanel jp = new JPanel();
		
		jd.setSize(470,200);
		jd.setLayout(new BorderLayout());
		jp.setLayout(new FlowLayout());
		jd.add(jp,BorderLayout.CENTER);
		jd.setVisible(true);
		jcbx= new JComboBox<Attribute>();
		
		JLabel jl1= new JLabel("Pretrazi");
		query = "select * from ";
		jp.add(jl1);
		att=new ArrayList<Attribute>();

		for(Entity en : MainFrame.getInstance().getAppCore().getIr().getEntities()) {
			if(en.getName().equals(((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getName())) {
				o = en;
			}
					
		}
			
		att = o.getAttributes();
		
		for(Attribute at : att) {;
			jcbx.addItem(at);
				
		}
		
		query = query + o.getName();
		jp.add(jcbx);
		JTextField jtx = new JTextField();
		jp.add(jtx);
		jtx.setPreferredSize(new Dimension(100,24));
		JButton jbAnd = new JButton("And");
		JButton jbOr = new JButton("Or");
		JButton search = new JButton("Search");
		jp.add(jbAnd);
		jp.add(jbOr);
		jd.add(search,BorderLayout.SOUTH);
		query = query + " where ";
		
		jbAnd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(((Attribute)jcbx.getSelectedItem()).getAttributeType().equals(AttributeType.CHAR) || ((Attribute)jcbx.getSelectedItem()).getAttributeType().equals(AttributeType.VARCHAR)) {
					query = query + jcbx.getSelectedItem().toString() + " LIKE '" + jtx.getText() + "' AND ";
					System.out.println(query);
					jtx.setText("");
				}else if(((Attribute)jcbx.getSelectedItem()).getAttributeType().equals(AttributeType.NUMERIC) || ((Attribute)jcbx.getSelectedItem()).getAttributeType().equals(AttributeType.INT)){
					query = query + jcbx.getSelectedItem().toString() + jtx.getText() + " AND ";
					System.out.println(query);
					jtx.setText("");
				}
			}
		});
		
		jbOr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(((Attribute)jcbx.getSelectedItem()).getAttributeType().equals(AttributeType.CHAR) || ((Attribute)jcbx.getSelectedItem()).getAttributeType().equals(AttributeType.VARCHAR)) {
					query = query + jcbx.getSelectedItem().toString() + " LIKE '" + jtx.getText() + "' OR ";
					System.out.println(query);
					jtx.setText("");
				}else if(((Attribute)jcbx.getSelectedItem()).getAttributeType().equals(AttributeType.NUMERIC) || ((Attribute)jcbx.getSelectedItem()).getAttributeType().equals(AttributeType.INT)){
					query = query + jcbx.getSelectedItem().toString() + jtx.getText() + " OR ";
					System.out.println(query);
					jtx.setText("");
				}
				
			}
		});
		
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(((Attribute)jcbx.getSelectedItem()).getAttributeType().equals(AttributeType.CHAR) || ((Attribute)jcbx.getSelectedItem()).getAttributeType().equals(AttributeType.VARCHAR)) {
					query = query + jcbx.getSelectedItem().toString() + " LIKE '" + jtx.getText() + "'";
					System.out.println(query);

				}else if(((Attribute)jcbx.getSelectedItem()).getAttributeType().equals(AttributeType.NUMERIC) || ((Attribute)jcbx.getSelectedItem()).getAttributeType().equals(AttributeType.INT)){
					query = query + jcbx.getSelectedItem().toString() + " " + jtx.getText();
					System.out.println(query);

				}
				
				MainFrame.getInstance().getAppCore().search(query, ((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getTableModel(),o);
				jd.setVisible(false);

			}
		});
		
	}

}
