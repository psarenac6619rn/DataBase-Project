package dataBase;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import workspace.model.Entity;
import workspace.model.Row;


public class DatabaseImplementation implements Database {

    private Repository repository;
    
    public DatabaseImplementation(Repository repository) {
		super();
		this.repository = repository;
	}

	@Override
    public DefaultMutableTreeNode loadResource() {
        return repository.getSchema();
    }

    @Override
    public List<Row> readDataFromTable(String tableName) {
        return repository.get(tableName);
    }

	@Override
	public void addRow(String query) {
		this.repository.addRow(query);
		
	}

	@Override
	public void remove(String query) {
		this.repository.remove(query);
		
	}

	@Override
	public void update(String query) {
		this.repository.update(query);
		
	}

	@Override
	public ArrayList<Row> filter(String query, Entity e) {
		return repository.filter(query, e);
	}

	@Override
	public ArrayList<Row> sort(String query,Entity e) {
		return repository.sort(query,e);
	}

	@Override
	public ArrayList<Row> count(String query,Entity e) {
		return repository.count(query,e);
	}

	@Override
	public ArrayList<Row> avg(String query,Entity e) {
		return repository.avg(query,e);
	}
	
	@Override
	public ArrayList<Row> search(String query,Entity e){
		return repository.search(query,e);
	}
}
