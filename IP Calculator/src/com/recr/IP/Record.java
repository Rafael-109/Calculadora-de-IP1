package com.recr.IP;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Record {
	private JPanel panel;
	private JTable table;
	private JButton button;
	String[][] data;
	public Record(JFrame frame, JPanel previous) {
		String[] columnNames = {"IP","State","APIPA","Reservada","Class","BroadCast","Gateway","Range","Direction","Cast","Mask"};
		
		DB db = DB.getInstances();
		
		data = new String[db.quantity()][11];
		
		
		db.dbStatement("select * from ip", data);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		table = new JTable(data,columnNames);
		table.setEnabled(false);
		table.setSize(300,200);
		
		button = new JButton("Return");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setSize(350, 400);
				frame.setTitle("IPv4");
				frame.setContentPane(previous);
				frame.revalidate();				
				}			
		});
		
		panel.add(table, BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
		panel.setVisible(true);
		
	}
	
	JPanel getPanel() {
		return this.panel;
		}
	
	void setPanel(JPanel panel) {
		this.panel=panel;
		}
}