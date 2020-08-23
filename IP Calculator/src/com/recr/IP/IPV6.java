package com.recr.IP;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;
public class IPV6 {
	private JPanel[] panel;
	private JLabel ipname;
	private JTextField ip;
	private JTextArea info;
	private JScrollPane barra;
	private JButton returno,calculate;
	private InetAddress IPV6;
	
	
	
	public IPV6(JFrame frame, JPanel previous) {
		panel = new JPanel[3];
		
		for(int i=0;i<3;i++) {
			panel[i]=new JPanel();
		}
		
		calculate = new JButton("calculate");
		calculate.setSize(100, 50);
		
		returno = new JButton("returno");
		returno.setSize(100, 50);
		
		info= new JTextArea(15,20);
		info.setDisabledTextColor(Color.BLACK);
		info.setEnabled(false);
		barra=new JScrollPane(info);
		
		ip=new JTextField(20);
		ip.setText("");
		ip.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(Character.digit(e.getKeyChar(),16)==-1 && !(e.getKeyChar()==':')) {
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		ipname = new JLabel("IPV6:");
		
		panel[1].add(ipname);
		panel[1].add(ip);
		panel[1].setBackground(Color.LIGHT_GRAY);
		
		panel[2].add(returno);
		panel[2].add(calculate);
		panel[2].setBackground(Color.DARK_GRAY);
		
		panel[0].setLayout(new BoxLayout(panel[0],BoxLayout.PAGE_AXIS));
		panel[0].add(panel[1]);
		panel[0].add(barra);
		panel[0].add(panel[2]);
		panel[0].setBackground(Color.LIGHT_GRAY);
		calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				info.setText("");
				String[] prueba=analize(ip.getText());
				
				info.append("IP: ");
				for(int i=0;i<prueba.length;i++) {
				
					if(!(i==(prueba.length-1))) {
						info.append(prueba[i]+":");
					}else {
						info.append(prueba[i]+"\n");
					}
					
				}
				try {
					IPV6= Inet6Address.getByName(ip.getText());
					info.append("IP identified:"+IPV6.getHostAddress().toUpperCase()+"\n");			
					if(IPV6.isAnyLocalAddress()) {
						info.append("Type: Anycast\n");
					}else if(IPV6.isLoopbackAddress()) {
						info.append("Type: LoopBack\n");
					}else if(IPV6.isMCGlobal()) {
						info.append("Type: Multicast Global\n");
					}else if(IPV6.isMCLinkLocal()) {
						info.append("Type: Multicast LocalLink\n");
					}else if(IPV6.isMCNodeLocal()) {
						info.append("Type: Multicast NodeLocal");
					}else if(IPV6.isMCOrgLocal()) {
						info.append("Type: Multicast Organization Local");
					}else if(IPV6.isMCSiteLocal()) {
						info.append("Type: Multicast Site-Local");
					}else if(IPV6.isMulticastAddress()) {
						info.append("Type: Multicast");
					}else {
						info.append("Type: -");
					}
					
				} catch (UnknownHostException e1) {
					JOptionPane.showMessageDialog(frame, "Invalid IP");
				}
			}
			
		});
		
		returno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setContentPane(previous);
				frame.setTitle("IPV4");
				frame.revalidate();
				
			}
			
		});
		
	}
	
	public JPanel getPanel() {
		return this.panel[0];
	}
	
	public String[] analize(String ip) {
		String[] hex = new String[8];
		
		int i;
		hex = ip.split(":");
		
		for(i=0;i<hex.length;i++) {
			
			
			if(hex[i].length()==2) {
				
				if(hex[i].substring(0, 1).equalsIgnoreCase("0")) 
					hex[i]=hex[i].substring(1);
				
			}else if(hex[i].length()==3) {

				if(hex[i].substring(0, 2).equalsIgnoreCase("00")) { 
					hex[i]=hex[i].substring(2);
				}else if(hex[i].substring(0, 1).equalsIgnoreCase("0")) {
					hex[i]=hex[i].substring(1);
					}

			}else if(hex[i].length()==4) {
				
				if(hex[i].substring(0, 3).equalsIgnoreCase("000")) { 
				
					hex[i]=hex[i].substring(3);
					
				}else if(hex[i].substring(0, 2).equalsIgnoreCase("00")) {
				
					hex[i]=hex[i].substring(2);
				}else if(hex[i].substring(0, 1).equalsIgnoreCase("0")) {
				
					hex[i]=hex[i].substring(1);
					}
				}
		
			}
		
		for(i=0;i<hex.length;i++) {
			if(hex[i].isBlank()) {
				
			}
		}
		
		return hex;
		
	}
}