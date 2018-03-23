package com.application.db.dto;

public class MeasureDTO {

	private int id;
	private float value;

	private String timestamp;

	private int sensorId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

	@Override
	public String toString() {
		return "MeasureDTO [id=" + id + ", value=" + value + ", sensorId=" + sensorId + "]";
	}

}
