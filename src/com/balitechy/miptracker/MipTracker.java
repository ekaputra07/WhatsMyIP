package com.balitechy.miptracker;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class MipTracker extends JFrame {
	
	private JPanel contentPane;
	private JLabel status;
	private JLabel myIP;
	private URL url;
	private final String server = "http://miptracker-app.appspot.com/plain";
	private String current_ip;
	private JMenu mnFile;
	private JMenuItem mntmExit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MipTracker frame = new MipTracker();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					Thread.sleep(500);
					frame.startThread();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	MipTracker() {
		super("Miptracker");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 180);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMyExternalIp = new JLabel("My external IP Address is:");
		lblMyExternalIp.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMyExternalIp.setBounds(81, 12, 324, 27);
		contentPane.add(lblMyExternalIp);
		
		myIP = new JLabel("000.000.000.000");
		myIP.setFont(new Font("Dialog", Font.PLAIN, 40));
		myIP.setBounds(54, 36, 350, 48);
		contentPane.add(myIP);
		
		status = new JLabel();
		status.setFont(new Font("Dialog", Font.PLAIN, 12));
		status.setBounds(12, 103, 420, 15);
		status.setText("Fetching data from http://miptracker-app.appspot.com...");
		contentPane.add(status);
	}
	
	public void startThread(){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					
					InputStreamReader is;
					try {
						url = new URL(server);
						URLConnection hc = url.openConnection();
						hc.setRequestProperty("User-Agent", "Miptracker Desktop - JavaSE 1.6");
						
						is = new InputStreamReader(hc.getInputStream());
						BufferedReader bf = new BufferedReader(is);
						
						current_ip = bf.readLine();
						myIP.setText(current_ip);
						status.setText("Success fetching IP address.");
						
					} catch (IOException e1) {
						status.setText("Failed fetching IP Address!");
					}
					
				}
			}
		);
	}
}
