package com.balitechy.whatsmyip;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WhatsMyIp extends JFrame {

	private JPanel contentPane;
	private JLabel status;
	private JLabel myIP;
	private URL url;
	private final String server = "http://automation.whatismyip.com/n09230945.asp";
	private String current_ip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WhatsMyIp frame = new WhatsMyIp();
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

	/**
	 * Create the frame.
	 */
	public WhatsMyIp() {
		super("Whats My IP");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMyExternalIp = new JLabel("My external IP Address is:");
		lblMyExternalIp.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMyExternalIp.setBounds(87, 33, 324, 27);
		contentPane.add(lblMyExternalIp);
		
		myIP = new JLabel("000.000.000.000");
		myIP.setFont(new Font("Dialog", Font.PLAIN, 40));
		myIP.setBounds(52, 60, 350, 48);
		contentPane.add(myIP);
		
		status = new JLabel();
		status.setFont(new Font("Dialog", Font.PLAIN, 12));
		status.setBounds(12, 124, 420, 15);
		status.setText("Fetching data from http://whatismyip.com...");
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
						hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:12.0) Gecko/20100101 Firefox/12.0");
						
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
