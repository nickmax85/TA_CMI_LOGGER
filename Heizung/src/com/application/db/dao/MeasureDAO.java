package com.application.db.dao;

import com.application.db.dto.MeasureDTO;
import com.application.db.util.DAOException;

public interface MeasureDAO {

	public void insertMeasure(MeasureDTO data) throws DAOException;

}