package com.application.db.dto;

public class SensorDTO {

	private int id;
	private int adr;
	private String name;
	private String timestamp;

	private int unitId;
	private int locationId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAdr() {
		return adr;
	}

	public void setAdr(int adr) {
		this.adr = adr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	@Override
	public String toString() {
		return "SensorDTO [id=" + id + ", adr=" + adr + ", name=" + name + ", timestamp=" + timestamp + ", unitId="
				+ unitId + ", locationId=" + locationId + "]";
	}

	

}
