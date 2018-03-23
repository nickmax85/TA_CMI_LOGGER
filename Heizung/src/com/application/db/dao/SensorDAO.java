package com.application.db.dao;

import java.util.List;

import com.application.db.dto.SensorDTO;
import com.application.db.util.DAOException;

public interface SensorDAO {

	public List<SensorDTO> getSensoren() throws DAOException;


}