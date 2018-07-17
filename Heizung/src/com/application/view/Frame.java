package com.application.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JList<String> list;
	private static DefaultListModel<String> listModel;

	public Frame() {

		setTitle("Heizung (TA_CMI_LOGGING by Markus Thaler - 2016)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10, 10));
		setSize(new Dimension(600, 400));
		setLocationRelativeTo(null);

		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setFont(new Font("Arial", Font.PLAIN, 13));

		JScrollPane sp = new JScrollPane(list);
		sp.setAutoscrolls(true);

		getContentPane().add(sp, BorderLayout.CENTER);

		setVisible(true);
	}

	public void updateList(String text) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

				listModel.add(0, simpleDateFormat.format(date) + ": " + text);

			}
		});
	}

}
