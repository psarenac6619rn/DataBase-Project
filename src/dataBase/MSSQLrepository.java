package dataBase;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import app.MainFrame;
import dataBase.settings.Settings;
import view.TableModel;
import view.Tabs;

import workspace.enums.AttributeType;
import workspace.enums.ConstraintType;
import workspace.model.Attribute;
import workspace.model.AttributeConstraint;
import workspace.model.Entity;
import workspace.model.InformationResource;
import workspace.model.Row;

public class MSSQLrepository implements Repository{

    private Settings settings;
    private Connection connection;

    public MSSQLrepository(Settings settings) {
        this.settings = settings;
    }
    

    private void initConnection() throws SQLException, ClassNotFoundException{
    	String ip = (String) settings.getParameter("mssql_ip");
        String database = (String) settings.getParameter("mssql_database");
        String username = (String) settings.getParameter("mssql_username");
        String password = (String) settings.getParameter("mssql_password");
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        System.out.println("RADI");
        connection = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ip+"/"+database,username,password);
    }

    private void closeConnection(){
        try{
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connection = null;
        }
    }


    @Override
    public DefaultMutableTreeNode getSchema() {

        try{
            this.initConnection();

            DatabaseMetaData metaData = connection.getMetaData();
            InformationResource ir = new InformationResource("" + settings.getParameter("mssql_database"));

            String tableType[] = {"TABLE"};
            ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, tableType);

            while (tables.next()){

                String tableName = tables.getString("TABLE_NAME");
                
                if(tableName.startsWith("trace")) {
                	continue;
                }
                
                Entity newTable = new Entity(tableName, ir);
                ir.add(newTable);
                

                ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableName, null);

                while (columns.next()){

                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    int columnSize = Integer.parseInt(columns.getString("COLUMN_SIZE"));
                    Attribute attribute = new Attribute(columnName, newTable, AttributeType.valueOf(columnType.toUpperCase()), columnSize);
                    newTable.add(attribute);
                    
                    String isNullable = columns.getString("IS_NULLABLE");
                    AttributeConstraint isNul = new AttributeConstraint("NOT_NULL", attribute, ConstraintType.NOT_NULL);
                    if(isNullable.equals("NO"))
                    	attribute.add(isNul);
                    
                    ResultSet foreignKeys = metaData.getImportedKeys(connection.getCatalog(), null, newTable.getName());
                    ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, newTable.getName());
                    
                    while(primaryKeys.next()) {
                    	String keyName = primaryKeys.getString("COLUMN_NAME");
                    	AttributeConstraint attCon = new AttributeConstraint("PRIMARY_KEY", attribute, ConstraintType.PRIMARY_KEY);
                    	if(columnName.equals(keyName))
                    		attribute.add(attCon);
                    }
                    
                    while(foreignKeys.next()) {
                    	String keyName = foreignKeys.getString("FKCOLUMN_NAME");
                    	AttributeConstraint attCon = new AttributeConstraint("FOREIGN_KEY", attribute, ConstraintType.FOREIGN_KEY);
                    	if(columnName.equals(keyName))
                    		attribute.add(attCon);
                    }

                }

            }

            return ir;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeConnection();
        }

        return null;
    }

    @Override
    public List<Row> get(String from) {

        List<Row> rows = new ArrayList<>();


        try{
            this.initConnection();

            String query = "SELECT * FROM " + from;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){

                Row row = new Row();
                row.setName(from);

                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeConnection();
        }

        return rows;
    }

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	

	@Override
	public void addRow(String query) {
		try {
			initConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.execute();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Pogresno uneti parametri");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void remove(String query) {
		try {
			initConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Pogresno uneti parametri");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void update(String query) {
		try {
			initConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Pogresno uneti parametri");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public ArrayList<Row> filter(String query,Entity e) {
		
		ArrayList<Row> rows = new ArrayList<Row>();
		
		try {
			System.out.println("radi");
			initConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()){

                Row row = new Row();
                row.setName(e.getName());

                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);
			}
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return rows;
	}


	@Override
	public ArrayList<Row> sort(String query,Entity e) {
		
		ArrayList<Row> rows = new ArrayList<Row>();
		
		try {
			System.out.println("radi");
			initConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()){

                Row row = new Row();
                row.setName(e.getName());

                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);
			}
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Pogresno uneti parametri");
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return rows;
	}


	@Override
	public ArrayList<Row> count(String query,Entity e) {

		ArrayList<Row> rows = new ArrayList<Row>();
		
		try {
			System.out.println("radi");
			initConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()){

                Row row = new Row();
                row.setName(e.getName());

                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);
			}	
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return rows;
	}


	@Override
	public ArrayList<Row> avg(String query,Entity e) {
		ArrayList<Row> rows = new ArrayList<Row>();
		
		try {
			System.out.println("radi");
			initConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()){

                Row row = new Row();
                row.setName(e.getName());

                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);
			}	
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return rows;
	}
    
	@Override
	public ArrayList<Row> search(String query,Entity e) {
		
		ArrayList<Row> rows = new ArrayList<Row>();
		
		try {
			System.out.println("radi");
			initConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()){

                Row row = new Row();
                row.setName(e.getName());

                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);
			}
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Neispravno unet tekst");
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return rows;
	}
}
