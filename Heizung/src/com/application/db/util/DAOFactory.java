package com.application.db.util;

import com.application.db.dao.MeasureDAO;
import com.application.db.dao.MeasureJDBCDAO;
import com.application.db.dao.SensorDAO;
import com.application.db.dao.SensorJDBCDAO;

public class DAOFactory {

	private SensorDAO sensorDAO;
	private MeasureDAO measureDAO;

	public DAOFactory(EDAOType eDAOType) {

		if (eDAOType == EDAOType.JDBC) {
			sensorDAO = new SensorJDBCDAO();
			measureDAO = new MeasureJDBCDAO();
		}

		if (eDAOType == EDAOType.MEMORY) {

		}

	}

	public SensorDAO getSensorDAO() {
		return sensorDAO;
	}

	public MeasureDAO getMeasureDAO() {
		return measureDAO;
	}

}
