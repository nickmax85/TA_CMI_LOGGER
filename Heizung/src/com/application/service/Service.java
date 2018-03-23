package com.application.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.application.db.dao.MeasureDAO;
import com.application.db.dao.SensorDAO;
import com.application.db.dto.MeasureDTO;
import com.application.db.dto.SensorDTO;
import com.application.db.util.DAOException;
import com.application.db.util.DAOFactory;
import com.application.db.util.EDAOType;

public class Service {

	private static final Logger logger = Logger.getLogger(Service.class);

	private static Service instance;
	private final static EDAOType SOURCE = EDAOType.JDBC;

	private DAOFactory daoFactory;

	private boolean errorStatus = false;

	private SensorDAO sensorDAO;
	private MeasureDAO measureDAO;

	private Service() {

		daoFactory = new DAOFactory(SOURCE);

		sensorDAO = daoFactory.getSensorDAO();
		measureDAO = daoFactory.getMeasureDAO();
	}

	public synchronized static Service getInstance() {

		if (instance == null) {
			instance = new Service();
		}

		return instance;

	}

	public void insertMeasure(MeasureDTO measure) {

		try {
			measureDAO.insertMeasure(measure);
			errorStatus = false;
		} catch (DAOException e) {

			e.printStackTrace();
			showExceptionMessage(e);
		}

	}

	public List<SensorDTO> getSensoren() {

		List<SensorDTO> sensoren = null;

		try {
			sensoren = sensorDAO.getSensoren();
			errorStatus = false;
		} catch (DAOException e) {

			e.printStackTrace();
			showExceptionMessage(e);
		}
		return sensoren;

	}

	public boolean isErrorStatus() {
		return errorStatus;
	}

	private void showExceptionMessage(DAOException e) {
		errorStatus = true;
		showExceptionAlertDialog(e);
	}

	private void showExceptionAlertDialog(DAOException e) {
		if (logger.isInfoEnabled())
			logger.info(e.getMessage());

	}

}
