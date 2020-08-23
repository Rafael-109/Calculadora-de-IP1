package com.recr.IP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Host {
	private JPanel panel;
	private JTextField num;
	private JLabel etiqueta, answer;
	private JButton returno, calculate;
	public Host(JFrame frame, JPanel previous) {
		panel = new JPanel();
		
		num = new JTextField(4);
		num.setBounds(110, 10,100,20);
		num.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});
		
		etiqueta = new JLabel("Num. de Hosts:");
		etiqueta.setBounds(10, 10,100,20);
		
		answer = new JLabel("-->Result");
		answer.setBounds(230, 10,100,20);
		
		returno = new JButton("returno");
		returno.setBounds(50, 50, 100, 20);
		returno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setSize(350, 400);
				frame.setTitle("IPv4");
				frame.setContentPane(previous);
				
			}
			
		});
		
		calculate = new JButton("calculate");
		calculate.setBounds(160, 50, 100, 20);
		calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int numero;
				if(num.getText().isBlank()) {
					numero=0;
				}else {
					numero=Integer.parseInt(num.getText());
				}
				
				int mask = (int) (+32-(Math.log(numero+2)/Math.log(2)));
				
	
				if(mask<=8) {
					answer.setText("-->"+"10.0.0.0/"+mask);
				}else if(mask<=16) {
					answer.setText("-->"+"172.16.0.0/"+mask);
				}else if(mask<=24) {
					answer.setText("-->"+"192.168.1.0/"+mask);
				}else if(mask<=30){
					answer.setText("-->"+"224.0.0.0/"+mask);
				}else {
					answer.setText("-->"+"Invalid:"+mask);
				}
			}
			
		});
		
		
		panel.setLayout(null);
		panel.add(etiqueta);
		panel.add(num);
		panel.add(answer);
		panel.add(returno);
		panel.add(calculate);
	}

	public JPanel getPanel() {
		return this.panel;
	}
}