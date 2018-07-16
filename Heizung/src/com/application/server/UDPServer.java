package com.application.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.application.db.dto.MeasureDTO;
import com.application.db.dto.SensorDTO;
import com.application.service.Service;
import com.application.util.CMIDataConverter;
import com.application.view.Frame;

public class UDPServer {

	private static final Logger logger = Logger.getLogger(UDPServer.class);

	private Frame frame;

	public static void main(String[] args) {
		new UDPServer();
	}

	public UDPServer() {

		DatagramSocket socket;
		DatagramPacket packet;
		try {
			socket = new DatagramSocket(5441);
			packet = new DatagramPacket(new byte[14], 14);
			while (true) {

				// Warte auf Client
				logger.info("Warte auf Daten an Port: " + socket.getLocalPort());
				frame.updateList("Warte auf Daten an Port: " + socket.getLocalPort());
				socket.receive(packet);
				read(packet);
			}

		} catch (SocketException e) {
			logger.error(e.getMessage());
			frame.updateList(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			frame.updateList(e.getMessage());
			e.printStackTrace();
		}
	}

	public UDPServer(Frame frame) {

		this.frame = frame;

		DatagramSocket socket;
		DatagramPacket packet;
		try {
			socket = new DatagramSocket(5441);
			packet = new DatagramPacket(new byte[14], 14);
			while (true) {

				// Warte auf Client
				logger.info("Warte auf Daten an Port:" + socket.getLocalPort());
				frame.updateList("Warte auf Daten an Port: " + socket.getLocalPort());
				socket.receive(packet);
				read(packet);
			}

		} catch (SocketException e) {
			logger.error(e.getMessage());
			frame.updateList(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			frame.updateList(e.getMessage());
			e.printStackTrace();
		}

	}

	private void read(DatagramPacket packet) {

		// Empfänger auslesen
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		int len = packet.getLength();
		byte[] data = packet.getData();
		logger.info("Adresse: " + address + "; Port:" + port + "; Length: " + len);
		frame.updateList("Adresse: " + address + "; Port:" + port + "; Length: " + len);

		logger.info("Empfangsdaten hex: ");
		frame.updateList("Empfangsdaten hex: ");
		String text = new String();
		for (int i = 0; i < data.length; i++) {
			text += String.format("%02X", data[i]) + " ";
		}
		logger.info(text);
		frame.updateList(text);

		logger.info(Calendar.getInstance().getTime());
		frame.updateList(Calendar.getInstance().getTime().toString());

		int adr = Integer.parseInt(String.format("%02X", data[0]), 16);
		logger.info("Knoten: " + adr);
		frame.updateList("Knoten: " + adr);

		float data1 = CMIDataConverter.getInstance().convert(data[2], data[3]);
		logger.info("Messung: " + data1);
		frame.updateList("Messung: " + data1);

		MeasureDTO measure = new MeasureDTO();
		int id = findSensorAdr(adr);

		measure.setSensorId(id);
		measure.setValue(data1);

		if (data1 < 1000) {
			Service.getInstance().insertMeasure(measure);
		} else {
			logger.info("Wert unplausibel:" + data1);
			frame.updateList("Wert unplausibel:" + data1);
		}
	}

	private int findSensorAdr(int knoten) {

		for (SensorDTO sensor : Service.getInstance().getSensoren()) {

			if (sensor.getAdr() == knoten) {
				return sensor.getId();
			}

		}
		logger.info("Sensoradresse nicht konfiguriert: " + knoten);
		frame.updateList("Sensoradresse nicht konfiguriert: " + knoten);
		return 0;

	}

}