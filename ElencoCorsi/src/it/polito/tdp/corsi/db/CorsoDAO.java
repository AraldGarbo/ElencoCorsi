package it.polito.tdp.corsi.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Statistiche;

public class CorsoDAO {

	public List<Corso> listByPD(int pd) {
		String sql = "select codins, crediti, nome, pd\r\n" + 
				"from corso where pd=?";
		
		List<Corso> result = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pd);
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Corso c = new Corso(res.getString("codins"),
						res.getInt("crediti"),
						res.getString("nome"),
						res.getInt("pd"));
				result.add(c);
			}
			
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
		return result;
	}
	/**
	 * ritorna tutti gli elementi della tabella CORSO
	 * @return
	 */
	public List<Corso> listAll() {
		
		String sql = "select codins, crediti, nome, pd\r\n" + 
				"from corso";
		
		List<Corso> result = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Corso c = new Corso(res.getString("codins"),
						res.getInt("crediti"),
						res.getString("nome"),
						res.getInt("pd"));
				result.add(c);
			}
			
			conn.close();
			
		} catch (SQLException e) {
			return null;
		}		
		return result;
	}
	public Statistiche getStatisticheByCodins(String codIns) {

		String sql = "select cds, count(cds) as count\r\n" + 
				"from studente as s, iscrizione as i\r\n" + 
				"where s.matricola = i.matricola and i.codins = ? and cds <> \"\" \r\n" + 
				"group by cds ";
		
		Statistiche stat = new Statistiche();
		try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, codIns);
		ResultSet res = st.executeQuery();
		while (res.next()) {
			 stat.getMappaCDS().put(res.getString("cds"), res.getInt("count"));
		}
		conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return stat;
	}

	

}
