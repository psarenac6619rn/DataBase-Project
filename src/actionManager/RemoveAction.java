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

public class RemoveAction extends AbstractAction{
	
	private String tableName;
	private JComboBox<String> jcbx;
	private ArrayList<Attribute> atr;
	private String query;
	private Entity o;
	
	public RemoveAction() {
		putValue(NAME, "Remove");
		putValue(SHORT_DESCRIPTION, "Remove");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		atr = new ArrayList<Attribute>();
		
		JDialog jd = new JDialog();
		JPanel jp = new JPanel();
		
		jd.setSize(500,200);
		jd.setLayout(new BorderLayout());
		jp.setLayout(new FlowLayout());
		jd.add(jp,BorderLayout.CENTER);
		
		jcbx = new JComboBox<String>();
		
		
		JLabel jl=new JLabel("Obrisi");
		
		jp.add(jl);
		
		for(Entity en : MainFrame.getInstance().getAppCore().getIr().getEntities()) {
			if(en.getName().equals(((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getName())) {
				o = en;
			}
					
		}
			
			atr = o.getAttributes();
			
			for(Attribute atrr : atr) {
				
				jcbx.addItem(atrr.getName());
			}
		
		jp.add(jcbx);
		
		JTextField jtx = new JTextField();
		jtx.setPreferredSize(new Dimension(100,24));
		
		jp.add(jtx);
		
		
		JButton jcb = new JButton("REMOVE");
		
		jd.add(jcb,BorderLayout.SOUTH);
		
		jcb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				query = "delete from " + tableName;
				query = query + " where ";
				
				Object obj = jcbx.getSelectedItem();
				
				query = query + obj.toString();
				
				String pam = "='" + jtx.getText() + "'";
				
				query = query + pam;
				
				System.out.println(query);
				
				MainFrame.getInstance().getAppCore().getDatabase().remove(query);
				
				MainFrame.getInstance().getAppCore().readDataFromTable(o.getName(), ((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getTableModel());
				
				jd.setVisible(false);
				
			}
		});
		
		
		
		jd.setVisible(true);
	}

}
