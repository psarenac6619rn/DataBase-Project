package actionManager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.MainFrame;
import view.Tabs;
import workspace.model.Attribute;
import workspace.model.Entity;



public class FilterAction extends AbstractAction {
	
	private ArrayList<JCheckBox> jchb;
	private ArrayList<Attribute> atri;
	private String query;
	private String nastavak;
	private ArrayList<String> jtlista;
	private Entity o;
	
	public FilterAction() {
		putValue(NAME, "Filter");
		putValue(SHORT_DESCRIPTION, "Filter");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JDialog jd = new JDialog();
		JPanel jp = new JPanel();
		
		jd.setSize(500,200);
		jd.setLayout(new BorderLayout());
		jp.setLayout(new FlowLayout());
		jd.add(jp,BorderLayout.CENTER);
		
		
		jchb = new ArrayList<JCheckBox>();
		atri = new ArrayList<Attribute>();
		jtlista= new ArrayList<String>();
		
		for(Entity en : MainFrame.getInstance().getAppCore().getIr().getEntities()) {
			if(en.getName().equals(((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getName())) {
				o = en;
			}
					
		}
			
		atri = o.getAttributes();
			
		for(Attribute att : atri) {
				
			JCheckBox jcbx = new JCheckBox(att.getName());
			jp.add(jcbx);
			System.out.println(jcbx.getText());
			jchb.add(jcbx);
				
		}
		
		JButton jcb = new JButton("Filter");
		
		jd.add(jcb,BorderLayout.SOUTH);
		
		jcb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				query = "SELECT ";
				
				for(JCheckBox jcbx : jchb) {
					
					if(jcbx.isSelected()) {
						jtlista.add(jcbx.getText());
					}
				}
				
				try {
					nastavak = jtlista.get(0);
					
					for(int i = 1; i < jtlista.size(); i++) {
						
						nastavak = nastavak + ", " + jtlista.get(i);
						
					}
					
					
					query = query + nastavak + " FROM " + o.getName();
					
					System.out.println(query);
					System.out.println("" + ((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getTableModel());
					
					MainFrame.getInstance().getAppCore().filter(query, ((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getTableModel(),o);
										
					jd.setVisible(false);
				}catch(IndexOutOfBoundsException ex){
					JOptionPane.showMessageDialog(null, "Nista nije selektovano");
				}
			}
		});
		
		jd.setVisible(true);
		
	}

}
