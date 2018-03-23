package com.application.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.application.db.dto.SensorDTO;
import com.application.db.util.ConnectionManager;
import com.application.db.util.DAOException;

public class SensorJDBCDAO implements SensorDAO {

	private static final Logger logger = Logger.getLogger(SensorJDBCDAO.class);

	private final static String GET_SENSOREN = "SELECT * FROM sensor";

	@Override
	public List<SensorDTO> getSensoren() throws DAOException {
		Statement statement = null;
		ResultSet rs = null;
		Connection con = null;

		List<SensorDTO> sensorList = new ArrayList<SensorDTO>();

		try {
			con = ConnectionManager.getInstance().getConnection();

			statement = con.createStatement();
			rs = statement.executeQuery(GET_SENSOREN);

			while (rs.next()) {
				SensorDTO sensor = new SensorDTO();
				sensor.setId(new Integer(rs.getInt("id")));
				sensor.setAdr(rs.getInt("adr"));
				sensor.setName(rs.getString("name"));
				sensor.setTimestamp(rs.getTimestamp("timestamp").toString());
				sensor.setUnitId(rs.getInt("unit_id"));
				sensor.setLocationId(rs.getInt("location_id"));

				sensorList.add(sensor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

		finally {
			try {
				ConnectionManager.getInstance().getConnection().close();
			} catch (SQLException e) {

				e.printStackTrace();
				throw new DAOException(e);
			}
		}

		return sensorList;
	}

}
