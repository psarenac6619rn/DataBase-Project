package actionManager;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import app.MainFrame;
import view.Tabs;
import workspace.model.Entity;

public class PocetnaTabelaAction extends AbstractAction {

	Entity o;
	
	public PocetnaTabelaAction() {
		putValue(NAME, "Pocetna_Tabela");
		putValue(SHORT_DESCRIPTION, "Pocetna_Tabela");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(Entity en : MainFrame.getInstance().getAppCore().getIr().getEntities()) {
			if(en.getName().equals(((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getName())) {
				o = en;
			}		
		}
		
		MainFrame.getInstance().getAppCore().readDataFromTable(o.getName(),((Tabs)MainFrame.getInstance().getTabbedPane().getSelectedComponent()).getTableModel());
	}

}
