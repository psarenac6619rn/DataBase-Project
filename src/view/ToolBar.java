package view;
import actionManager.ActionManager;

import javax.swing.JToolBar;

public class ToolBar extends JToolBar{
	
	public ToolBar() {
		add(ActionManager.getInstance().getAddAction());
		add(ActionManager.getInstance().getRemoveAction());
		add(ActionManager.getInstance().getUpdateAction());
		add(ActionManager.getInstance().getFilterAction());
		add(ActionManager.getInstance().getSortAction());
		add(ActionManager.getInstance().getCountAction());
		add(ActionManager.getInstance().getAverageAction());
		add(ActionManager.getInstance().getSearchAction());
		add(ActionManager.getInstance().getPocetnaTabelaAction());
		
		this.setFloatable(false);
	}
}
