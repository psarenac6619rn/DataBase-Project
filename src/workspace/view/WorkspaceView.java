package workspace.view;

import javax.swing.JTree;
import workspace.controller.*;

public class WorkspaceView extends JTree{

	public WorkspaceView() {
		setCellRenderer(new WorkspaceTreeCellRenderer());
		addTreeSelectionListener(new WorkspaceSelectionListener());
	
	}
	
}
