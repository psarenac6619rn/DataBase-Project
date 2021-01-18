package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.MainFrame;
import workspace.model.Entity;


public class Tabs extends JPanel{

	private JTable table;
	private ToolBar toolBar;
	private JScrollPane scroll;
	private TableModel tableModel;
	private String name;
	
	public Tabs(TableModel tm) {
		this.tableModel = tm;
		initElements();
		addElements();
	}
	
	private void initElements() {
		
		table = new JTable(tableModel);
		toolBar = new ToolBar();
	}
	
	public void addElements() {
		scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(570,220));
        
        this.add(scroll,BorderLayout.NORTH);
        this.add(toolBar,BorderLayout.SOUTH);

	}	

	public void removeToolbar() {
		this.remove(toolBar);
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(ToolBar toolBar) {
		this.toolBar = toolBar;
	}


	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public TableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
