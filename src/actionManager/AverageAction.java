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
import javax.swing.JPanel;

import app.MainFrame;
import view.Tabs;
import workspace.enums.AttributeType;
import workspace.model.Attribute;
import workspace.model.Entity;

public class AverageAction extends AbstractAction {

	private ArrayList<Attribute> attr;
	private JComboBox<String> jcb;
	private JComboBox<String> jcbgb;
	private String query;
	private Entity o;
	
	
	public AverageAction() {
		putValue(NAME, "Average");
		putValue(SHORT_DESCRIPTION, "Average");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog jd = new JDialog();
		JPanel jp = new JPanel();
		
		
		jd.setSize(500,200);
		jd.setLayout(new BorderLayout());
		jp.setLayout(new FlowLayout());
		jd.add(jp,BorderLayout.CENTER);
		
		jcbgb = new JComboBox<String>();
		jcb = new JComboBox<String>();
		JLabel jl = new JLabel("Izaberi prosecnu vrednost za");
		
		jp.add(jl);
	
		
		attr = new ArrayList<Attribute>();
		
		for(Entity en : MainFrame.getInstance().getAppCore().getIr().getEntities()) {
			if(en.getName().equals(((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getName())) {
				o = en;
			}
					
		}
		
		String ime = o.getName();
			
		attr = o.getAttributes();
			
		for(Attribute at : attr) {
			if(at.getAttributeType().equals(AttributeType.INT)|| at.getAttributeType().equals(AttributeType.FLOAT) || at.getAttributeType().equals(AttributeType.DECIMAL) || at.getAttributeType().equals(AttributeType.NUMERIC))	
			jcb.addItem(at.getName());
			jcbgb.addItem(at.getName());	
		}
		
		jp.add(jcb);
		
		JLabel labela = new JLabel("i grupisi po");
		
		jp.add(labela);
		
		jp.add(jcbgb);
		
		JButton jb = new JButton("Average");
		
		jd.add(jb,BorderLayout.SOUTH);
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				query = "select avg(";
				query = query + jcb.getSelectedItem().toString();
				query = query + ") as AVERAGE," + jcbgb.getSelectedItem().toString() +  " from " + ime;
				query = query + " GROUP BY " + jcbgb.getSelectedItem().toString();
				
				System.out.println(query);
				
				MainFrame.getInstance().getAppCore().avg(query, ((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getTableModel(),o);
				
				jd.setVisible(false);
			}
		});
		
		
		
		jd.setVisible(true);
		
	}

}