package dataBase;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import workspace.model.Entity;
import workspace.model.Row;



public interface Database{

    DefaultMutableTreeNode loadResource();

    List<Row> readDataFromTable(String tableName);

    void addRow(String query);
    
    void remove(String query);
    
    void update(String query);
    
    ArrayList<Row> filter(String query,Entity e);
    
    ArrayList<Row> sort(String query,Entity e);
    
    ArrayList<Row> count(String query,Entity e);
    
    ArrayList<Row> avg(String query,Entity e);
    
    ArrayList<Row> search(String query,Entity e);
}
