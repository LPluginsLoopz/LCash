package org.loopz.lcash;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

	
	private Main plugin;
	public DataBase(Main plugin) {
		this.plugin = plugin;
	}
	
	public void createTable() {
		PreparedStatement stm;
		try {
			stm = plugin.Conexao.getConnection()
					.prepareStatement("CREATE TABLE IF NOT EXISTS LCash (NAME varChar(18), CASH DOUBLE);");
			stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  boolean exists(String NAME) {
		PreparedStatement stm;
		try {
			stm = plugin.Conexao.getConnection().prepareStatement("SELECT * FROM LCash WHERE NAME = ?");
			stm.setString(1, NAME);
			ResultSet rs = stm.executeQuery();
			boolean resultado = rs.next();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	public  void createPlayer(String NAME) {
		PreparedStatement stm;
		try {
			stm = plugin.Conexao.getConnection().prepareStatement("INSERT INTO LCash (`NAME`, `CASH`) VALUES (?,?)");
			stm.setString(1, NAME);
			stm.setDouble(2, 0);
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public double getCash(String NAME) {
		PreparedStatement stm;
		try {
			stm = plugin.Conexao.getConnection().prepareStatement("SELECT * FROM LCash WHERE NAME = ?");
			stm.setString(1, NAME);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				return rs.getDouble("CASH");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return 0;
	
	}

    public  List<String> getCashTop() {
        List<String> tops = new ArrayList<String>();
         PreparedStatement stm;
        try {
            stm = plugin.Conexao.getConnection().prepareStatement("SELECT * FROM LCash ORDER BY CASH DESC LIMIT 10");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) 
            	tops.add(rs.getString("NAME")); 
            
        } catch (Exception e) {
            e.printStackTrace();

        }
        return tops;
    }
	
	public  void definirCash(String p, Double quantia) {
		PreparedStatement prepareStat;
		try {

			prepareStat = plugin.Conexao.getConnection()
					.prepareStatement("UPDATE LCash SET CASH = ? WHERE NAME = ?");
			prepareStat.setDouble(1, quantia);
			prepareStat.setString(2, p);
			prepareStat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}

}
	
	public  void removerCash(String p, Double quantia) {
		PreparedStatement prepareStat;
			try {

				prepareStat = plugin.Conexao.getConnection()
						.prepareStatement("UPDATE LCash SET CASH = ? WHERE NAME = ?");
				prepareStat.setDouble(1, getCash(p) - quantia);
				prepareStat.setString(2, p);
				prepareStat.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();

			}

	}

	public  void adicionarCash(String args, Double quantia) {
		PreparedStatement prepareStat;
			try {

				prepareStat = plugin.Conexao.getConnection()
						.prepareStatement("UPDATE LCash SET CASH = ? WHERE NAME = ?");
				prepareStat.setDouble(1, quantia + getCash(args));
				prepareStat.setString(2, args);
				prepareStat.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
	
			}

	}



}
