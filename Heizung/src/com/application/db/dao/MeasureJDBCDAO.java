package com.application.db.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.application.db.dto.MeasureDTO;
import com.application.db.util.ConnectionManager;
import com.application.db.util.DAOException;

public class MeasureJDBCDAO implements MeasureDAO {

	private static final Logger logger = Logger.getLogger(MeasureJDBCDAO.class);

	private final static String INSERT_MEASURE = "INSERT INTO measure(value, sensor_id) VALUES (?, ?)";

	@Override
	public void insertMeasure(MeasureDTO measure) throws DAOException {

		PreparedStatement ps;

		try {
			ps = ConnectionManager.getInstance().getConnection().prepareStatement(INSERT_MEASURE);

			ps.setFloat(1, measure.getValue());
			ps.setInt(2, measure.getSensorId());
			ps.executeUpdate();
			
			logger.info("Messung gespeichert: " + measure);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);

		} finally {
			try {
				ConnectionManager.getInstance().getConnection().close();
			} catch (SQLException e) {

				e.printStackTrace();
				throw new DAOException(e);
			}
		}

	}

}
