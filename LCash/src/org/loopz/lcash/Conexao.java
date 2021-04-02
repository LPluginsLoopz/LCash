package org.loopz.lcash;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;


public class Conexao {
  public Connection con;
  
public DataBase DataBase;
	
public boolean isConnected() {
	return (con == null ? false : true);
}

  
  public void openConnectionSQLITE() {
		this.DataBase = new DataBase(Main.getPlugin(Main.class));
	
			 
		       
    try {
    	
	      File file = new File(Main.getPlugin(Main.class).getDataFolder(), "database.db");
      Class.forName("org.sqlite.JDBC");
      con = DriverManager.getConnection("jdbc:sqlite:" + file);
      Bukkit.getConsoleSender().sendMessage("§a[LCash] - Conexão com SQLite foi um sucesso.");
      DataBase.createTable();
    	
    } catch (Exception e) {
      Bukkit.getConsoleSender().sendMessage("§c[LCash] - Não foi possivel se conectar a o SQLite, desligando plugin.");
      Main.getPlugin(Main.class).getPluginLoader().disablePlugin((Plugin)Main.getPlugin(Main.class));
    }
    } 

  
  
  
  public void disconnect() {
		if(isConnected()) {
			try {
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public Connection getConnection() {
		return con;
	}
  
}
