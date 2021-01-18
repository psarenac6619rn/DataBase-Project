package actionManager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.MainFrame;
import view.Tabs;
import workspace.model.Attribute;
import workspace.model.Entity;

public class SortAction extends AbstractAction{

	private ArrayList<Attribute> atri;
	private String query;
	private Entity o;
	
	
	public SortAction() {
		putValue(NAME, "Sort");
		putValue(SHORT_DESCRIPTION, "Sort");
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog jd = new JDialog();
		JPanel jp = new JPanel();
		JComboBox<String> jcb = new JComboBox<String>();
		JComboBox<String> jcb2 = new JComboBox<String>();
		
		jcb2.addItem("ASC");
		jcb2.addItem("DESC");
		
		jd.setSize(500,200);
		jd.setLayout(new BorderLayout());
		jp.setLayout(new FlowLayout());
		jd.add(jp,BorderLayout.CENTER);
		
		
		atri = new ArrayList<Attribute>();
		String ime;
		
		for(Entity en : MainFrame.getInstance().getAppCore().getIr().getEntities()) {
			if(en.getName().equals(((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getName())) {
				o = en;
			}
					
		}
		ime = o.getName();
		
		JLabel jl = new JLabel("Izaberi po cemu sortiras");
			
		atri = o.getAttributes();
			
		for(Attribute at : atri) {
				
			jcb.addItem(at.getName());
				
		}
		
		jp.add(jl);
		jp.add(jcb);
		jp.add(jcb2);
		
		JButton jb = new JButton("SORT");
		JButton jbAnd = new JButton("AND");
		
		jp.add(jbAnd);
		jp.add(jb,BorderLayout.SOUTH);
		
		query = "SELECT * FROM " + ime + " ORDER BY ";
		
		jbAnd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				query = query + jcb.getSelectedItem().toString() + " " + jcb2.getSelectedItem().toString() + ", ";
				System.out.println(query);
			}
		});
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
				query = query  + jcb.getSelectedItem().toString() + " " +jcb2.getSelectedItem().toString() ;
				
				System.out.println(query);
				
				MainFrame.getInstance().getAppCore().sort(query, ((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getTableModel(),o);
				
				jd.setVisible(false);
				
				}catch(IndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(null, "Pogresno uneti parametri");
				}
			}
		});
		
		jd.setVisible(true);
		
	}

}
