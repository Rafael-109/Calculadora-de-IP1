package com.recr.IP;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class IPV4 {
	private JFrame frame;
	private JPanel panel[];
	private JButton calculate, record,IPV6,host;
	private JLabel tag[];
	private JTextField ip[],mask[];
	private JTextArea datos;
	private JScrollPane datosS;
	private KeyListener k;
	private int i,ip_octeto[],mask_octeto[],bc[],dr[];
	private static int ancho=350,alto=400;
	private String paquete[];
	public IPV4() {
	
		panel = new JPanel[4];
		tag = new JLabel[10];
		ip = new JTextField[4];
		mask = new JTextField[4];
		paquete=new String[11];
		ip_octeto= new int[4];
		mask_octeto=new int[4];
		bc=new int[4];
		dr=new int[4];
		
		frame= new JFrame("Calculadora: IPV4");
		frame.setBounds(500, 100, ancho, alto);
		frame.setResizable(false);
		
		calculate = new JButton("calculate");
		calculate.setSize(100, 50);
		
		IPV6 = new JButton("IPV6");
		IPV6.setSize(100, 50);
		IPV6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IPV6 ip6 = new IPV6(frame,panel[0]);
				frame.setTitle("IPV6");
				frame.setContentPane(ip6.getPanel());
				frame.revalidate();
				}
			
		});
		
		host = new JButton("#Host");
		host.setSize(100, 50);
		host.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Host host = new Host(frame, panel[0]);
				frame.setTitle("#Host");;
				frame.setContentPane(host.getPanel());
				frame.setSize(ancho, alto/3);
				frame.revalidate();
			}
			
		});
		
		record = new JButton("Record");
		record.setSize(100, 50);
		record.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Record hist = new Record(frame,panel[0]);
				frame.setTitle("Record");
				frame.setContentPane(hist.getPanel());
				frame.setSize(ancho*3, alto);
				frame.revalidate();
			}
			
		});
		
		
		datos= new JTextArea();
		datos.setDisabledTextColor(Color.BLACK);
		datos.setEnabled(false);		
		datosS=new JScrollPane(datos);
		
		k = new KeyListener() {
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
		};
		
		
		for(i=0;i<8;i++) {
			tag[i]=new JLabel(".");
		}
		tag[8]=new JLabel("-----IP:");
		tag[9]=new JLabel("Mask:");
		
		for(i=0;i<4;i++) {			
			ip[i]=new JTextField(3);
			ip[i].setText("0");
			ip[i].setHorizontalAlignment(JTextField.RIGHT);
			ip[i].addKeyListener(k);
			mask[i]=new JTextField(3);
			mask[i].setHorizontalAlignment(JTextField.RIGHT);
			mask[i].setText("0");
			mask[i].addKeyListener(k);
			panel[i]=new JPanel();
		}
	
		ip[0].setText("192");
		ip[1].setText("168");
		ip[2].setText("1");
		ip[3].setText("0");
		
	
		mask[0].setText("255");
		mask[1].setText("255");
		mask[2].setText("255");
		
		panel[1].add(tag[8]);
		panel[2].add(tag[9]);
		for(i=0;i<4;i++) {
			panel[1].add(ip[i]);
			panel[1].add(tag[i]);
			
			panel[2].add(mask[i]);
			panel[2].add(tag[i+4]);
		}
		
		panel[3].add(IPV6);
		panel[3].add(calculate);
		panel[3].add(record);
		panel[3].add(host);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		
		panel[0].setLayout(new GridBagLayout());
		panel[0].add(panel[1], c);
		
		c.gridy = 1;
		
		panel[0].add(panel[2],c);
		
		c.gridy = 6;
		
		panel[0].add(panel[3],c);
		
		c.gridy = 2;
		c.gridheight = 3;
		c.ipady = 200;
		panel[0].add(datosS,c);
		
		panel[0].setBackground(Color.GREEN);
		panel[1].setBackground(Color.RED);
		panel[2].setBackground(Color.RED);
		panel[3].setBackground(Color.LIGHT_GRAY);
		frame.add(panel[0]);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
	
			
				for(i=0;i<4;i++) {
					if(!ip[i].getText().equalsIgnoreCase("")) {
						ip_octeto[i]=Integer.parseInt(ip[i].getText());
					}else {
						ip_octeto[i]=0;
					}
				}
				
			
				for(i=0;i<4;i++) {
					if(!mask[i].getText().equalsIgnoreCase("")) {
						mask_octeto[i]=Integer.parseInt(mask[i].getText());
					}else {
						mask_octeto[i]=0;
					}
				}
				
				paquete[0]=ip[0].getText()+"."+ip[1].getText()+"."+ip[2].getText()+"."+ip[3].getText();
				paquete[1]=mask[0].getText()+"."+mask[1].getText()+"."+mask[2].getText()+"."+mask[3].getText();
				
		
				boolean a,b,c;
				a=ip_octeto[0]>255 || ip_octeto[1]>255 || ip_octeto[2]>255 || ip_octeto[3]>255;  
				b=ip_octeto[0]<0 || ip_octeto[1]<0 || ip_octeto[2]<0 || ip_octeto[3]<0;
				
				if(!a||b) {
					datos.setText("");
					
			
					a=ip_octeto[0]==10;
					b=ip_octeto[0]==172 && ip_octeto[1]>=16 && ip_octeto[1]<32;
					c=ip_octeto[0]==192 && ip_octeto[1]==168;
					
					if(a||b||c) {
						datos.append("state: Private\n");
						paquete[3]="Private";
					}else {
						datos.append("state: Public\n");
						paquete[3]="Public";
					}
					
				
					if(ip_octeto[0]<128) {
						datos.append("class: A\n");
						paquete[6]="A";
					}else if(ip_octeto[0]<192) {
						datos.append("class: B\n");
						paquete[6]="B";
					}else if(ip_octeto[0]<224) {
						datos.append("class: C\n");
						paquete[6]="C";
					}else if(ip_octeto[0]<240) {
						datos.append("class: D\n");
						paquete[6]="D";
					}else if(ip_octeto[0]<=255) {
						datos.append("class: E\n");
						paquete[6]="E";}
					
				
					if(ip_octeto[0]==169 && ip_octeto[1]==254 && ip_octeto[3]>=1 && ip_octeto[3]<=254) {
						datos.append("APIPA: Pertenece\n");
						paquete[4]="Pertenece";
					}else {
						datos.append("APIPA: No pertenece\n");
						paquete[4]="No pertenece";
					}	
					
			
					if(ip_octeto[0]==0) {
						
						datos.append("Reservada: This host on this network");
						paquete[5]="This host on this network\n";
						
					}else if(ip_octeto[0]==10) {
						
						datos.append("Reservada: Private-Use\n");
						paquete[5]="Private-Use";
						
					}else if(ip_octeto[0]==100 && ip_octeto[1]>=64 && ip_octeto[1]<=127) {
						
						datos.append("Reservada: Shared Address Space \n");
						paquete[5]="Shared Address Space";
						
					}else if(ip_octeto[0]==127) {
						
						datos.append("Reservada: Loopback \n");
						paquete[5]="Loopback";
						
					}else if(ip_octeto[0]==169 && ip_octeto[1]==254) {
						
						datos.append("Reservada: Link Local\n");
						paquete[5]="Link Local";
						
					}else if(ip_octeto[0]==172 && ip_octeto[1]>=16 && ip_octeto[1]<=31) {
						
						datos.append("Reservada: Private-Use\n");
						paquete[5]="Private-Use";
						
					}else if(ip_octeto[0]==192 && ip_octeto[1]==0 && ip_octeto[2]==0) {
						
						datos.append("Reservada: DS-Lite\n");
						paquete[5]="DS-Lite";
						
					}else if(ip_octeto[0]==192 && ip_octeto[1]==0 && ip_octeto[2]==2) {
						
						datos.append("Reservada: Documentation (TEST-NET-1)\n");
						paquete[5]="Documentation (TEST-NET-1)";
						
					}else if(ip_octeto[0]==192 && ip_octeto[1]==88 && ip_octeto[2]==99) {
						
						datos.append("Reservada: 6to4 Relay Anycast\n");
						paquete[5]="6to4 Relay Anycast";
						
					}else if(ip_octeto[0]==192 && ip_octeto[1]==168) {
						
						datos.append("Reservada: Private-Use\n");
						paquete[5]="Private-Use";
						
					}else if(ip_octeto[0]==198 && ip_octeto[1]>=18 && ip_octeto[1]<=19) {
						
						datos.append("Reservada: Benchmarking\n");
						paquete[5]="Benchmarking";
						
					}else if(ip_octeto[0]==198 && ip_octeto[1]==51 && ip_octeto[2]==100) {
						
						datos.append("Reservada: Documentation (TEST-NET-2)\n");
						paquete[5]="Documentation (TEST-NET-2)";
						
					}else if(ip_octeto[0]==203 && ip_octeto[1]==0 && ip_octeto[2]==113) {
						
						datos.append("Reservada: Documentation (TEST-NET-3)\n");
						paquete[5]="Documentation (TEST-NET-3)";
						
					}else if(ip_octeto[0]==255 && ip_octeto[1]==255 && ip_octeto[2]==255 && ip_octeto[3]==255) {
						
						datos.append("Reservada: Limited Brodcast\n");
						paquete[5]="Limited Brodcast";
						
					}else if(ip_octeto[0]>=240) {
						
						datos.append("Reservada: Reserved for Future Use\n");
						paquete[5]="Reserved for Future Use";
						
					}else  {
						datos.append("Reservada: No\n");
						paquete[5]="No";
					}
					
					for(i=0;i<4;i++) {
						dr[i] = ip_octeto[i]&mask_octeto[i];
					}
					datos.append("Direccion de red:"+dr[0]+"."+dr[1]+"."+dr[2]+"."+dr[3]+"\n");
					paquete[2]= dr[0]+"."+dr[1]+"."+dr[2]+"."+dr[3];
					
										
					for(i=0;i<4;i++) {
						bc[i] = ip_octeto[i]|(255-mask_octeto[i]);
					}
					datos.append("Broadcast:"+bc[0]+"."+bc[1]+"."+bc[2]+"."+bc[3]+"\n");
					paquete[7]=bc[0]+"."+bc[1]+"."+bc[2]+"."+bc[3];

					
					datos.append("Gateway:"+dr[0]+"."+dr[1]+"."+dr[2]+"."+(dr[3]+1)+"\n");
					paquete[8]=dr[0]+"."+dr[1]+"."+dr[2]+"."+(dr[3]+1);
					
					datos.append("range de ip´s: "+dr[0]+"."+dr[1]+"."+dr[2]+"."+(dr[3]+1)+" - "
								 +bc[0]+"."+bc[1]+"."+bc[2]+"."+(bc[3]-1)+"\n");
					
					paquete[9]=dr[0]+"."+dr[1]+"."+dr[2]+"."+(dr[3]+1)+" - "
							 +bc[0]+"."+bc[1]+"."+bc[2]+"."+(bc[3]-1);
					
					if(ip_octeto[0]==bc[0] && ip_octeto[1]==bc[1] && ip_octeto[2]==bc[2] && ip_octeto[3]==bc[3]) {
						datos.append("Cast:Broadcast\n");
						paquete[10]="Broadcast";
					}else if(paquete[6].equals("D")) {
						datos.append("Cast:Multicast\n"); 
						paquete[10]="Multicast";
					}else if(paquete[4].equals("Pertenece")) {
						datos.append("Cast:Unicast\n"); 
						paquete[10]="Unicast";
					}else {
						datos.append("Cast: -\n");
						paquete[10]="-";
					}
					
					DB db = DB.getInstances();
					String query = "insert into ip(ip , mask, red, state, apipa, reserva, "
							+ "class, broadcast, gateway, range, multicast) values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					db.dbPrepareStatement(query, paquete);
					
				}else {
					JOptionPane.showMessageDialog(frame, "Invalid IP");
				}
			
			}
			
		});
			
		
	}
	
}