package app;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;

import observer.Notification;
import observer.Subscriber;
import observer.enums.NotificationCode;
import workspace.model.InformationResource;
import workspace.model.WorkspaceModel;
import workspace.view.WorkspaceView;

public class MainFrame extends JFrame implements Subscriber{

	private static MainFrame instance = null;
	
	private JSplitPane horizontal;
	private JSplitPane vertical;
	private JTabbedPane tabbedPane;
	private JTabbedPane tabbedDonji;
	private AppCore appCore;
	
	private WorkspaceView workspace;
	private WorkspaceModel workspacemodel;
	
	
	private MainFrame() {
		//initElements();
		//addElements();
	}
	
	public void addElements() {
		// TODO Auto-generated method stub
		setTitle("Baze_Podataka_Projekat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(900,610));
		horizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		horizontal.setEnabled(false);
		vertical =new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vertical.setPreferredSize(new Dimension(600,600));
		vertical.setEnabled(true);

		tabbedPane = new JTabbedPane();
		JScrollPane scroll = new JScrollPane(workspace);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setMinimumSize(new Dimension(300, 600));
        
        tabbedDonji = new JTabbedPane();
        horizontal.setLeftComponent(scroll);
        
        vertical.setLeftComponent(tabbedPane);
        vertical.setRightComponent(tabbedDonji);
        vertical.setDividerLocation(280);
        horizontal.setRightComponent(vertical);
        
        this.add(horizontal);
		
	}
	
	public void initElements() {
		workspace = new WorkspaceView();
		workspacemodel = new WorkspaceModel();
		workspace.setModel(workspacemodel);
	}

	public static MainFrame getInstance() {
		if (instance == null)
			instance = new MainFrame();
		return instance;
	}


	public WorkspaceView getWorkspace() {
		return workspace;
	}

	public void setWorkspace(WorkspaceView workspace) {
		this.workspace = workspace;
	}

	public WorkspaceModel getWorkspacemodel() {
		return workspacemodel;
	}

	public void setWorkspacemodel(WorkspaceModel workspacemodel) {
		this.workspacemodel = workspacemodel;
	}

	public JSplitPane getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(JSplitPane horizontal) {
		this.horizontal = horizontal;
	}

	public JSplitPane getVertical() {
		return vertical;
	}

	public void setVertical(JSplitPane vertical) {
		this.vertical = vertical;
	}
	
	public AppCore getAppCore() {
    	return appCore;
    }
    
    public void setAppCore(AppCore appCore) {
        this.appCore = appCore;
        this.appCore.addSubscriber(this);
    }

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabs) {
		this.tabbedPane = tabs;
	}

	public JTabbedPane getTabbedDonji() {
		return tabbedDonji;
	}

	public void setTabbedDonji(JTabbedPane tabbedDonji) {
		this.tabbedDonji = tabbedDonji;
	}

	@Override
	public void update(Notification notification) {

        if (notification.getCode() == NotificationCode.RESOURCE_LOADED){
            System.out.println((InformationResource)notification.getData());
        }

        else{
            //table.setModel((TableModel) notification.getData());
	}
	
        
        
	}
}
