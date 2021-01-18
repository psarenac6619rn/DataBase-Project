package app;


import java.util.ArrayList;

import dataBase.Database;
import dataBase.DatabaseImplementation;
import dataBase.MSSQLrepository;
import dataBase.settings.Settings;
import dataBase.settings.SettingsImplementation;
import observer.Notification;
import observer.enums.NotificationCode;
import observer.implementation.PublisherImplementation;
import view.TableModel;
import workspace.model.Attribute;
import workspace.model.Entity;
import workspace.model.InformationResource;

public class AppCore extends PublisherImplementation {

    private Database database;
    private Settings settings;
    private TableModel tableModel;
    private InformationResource ir;
    

    public AppCore() {
        this.settings = initSettings();
        this.database = new DatabaseImplementation(new MSSQLrepository(settings));
        tableModel = new TableModel();
        loadResource();
    }

    private Settings initSettings() {
        Settings settingsImplementation = new SettingsImplementation();
        settingsImplementation.addParameter("mssql_ip", workspace.enums.Constants.MSSQL_IP);
        settingsImplementation.addParameter("mssql_database", workspace.enums.Constants.MSSQL_DATABASE);
        settingsImplementation.addParameter("mssql_username", workspace.enums.Constants.MSSQL_USERNAME);
        settingsImplementation.addParameter("mssql_password", workspace.enums.Constants.MSSQL_PASSWORD);
        return settingsImplementation;
    }


    public void loadResource(){
        ir = (InformationResource) this.database.loadResource();
        this.notifySubscribers(new Notification(NotificationCode.RESOURCE_LOADED,ir));
    }

    public void readDataFromTable(String fromTable,TableModel tableModel){

        tableModel.setRows(this.database.readDataFromTable(fromTable));
        //MainFrame.getInstance().getAppCore().getTableModel().setRows(this.database.readDataFromTable(fromTable));
        //MainFrame.getInstance().getTabGornji().setTable(new JTable(MainFrame.getInstance().getAppCore().getTableModel()));

        //Zasto ova linija moze da ostane zakomentarisana?
        //this.notifySubscribers(new Notification(NotificationCode.DATA_UPDATED, this.getTableModel()));
    }

	public void uradi() {
    	for(int i = 0; i < MainFrame.getInstance().getAppCore().getIr().getEntities().size(); i++) {
    		Entity e = MainFrame.getInstance().getAppCore().getIr().getEntities().get(i);
    		for(int j = 0; j < e.getAttributes().size(); j++) {
    			e.getAttributes().get(j).napraviRelaciju();
    		}
    	}
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public InformationResource getIr() {
		return ir;
	}

	public void setIr(InformationResource ir) {
		this.ir = ir;
	}
	
	public void filter(String query, TableModel tableModel,Entity e) {
		System.out.println(this.database.filter(query, e));
		tableModel.setRows(this.database.filter(query,e));
	}
	
	public void sort(String query,TableModel tableModel,Entity e) {
		tableModel.setRows(this.database.sort(query,e));
	}
	
	public void count(String query, TableModel tableModel,Entity e) {
		System.out.println(this.database.count(query, e));
		tableModel.setRows(this.database.count(query,e));
	}

	public void avg(String query, TableModel tableModel,Entity e) {
		System.out.println(this.database.avg(query, e));
		tableModel.setRows(this.database.avg(query,e));
	}
	
	public void search(String query,TableModel tableModel,Entity e) {
		System.out.println(this.database.search(query, e));
		tableModel.setRows(this.database.search(query,e));
	}
}
