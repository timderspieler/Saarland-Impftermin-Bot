package de.timsander.jframe_pages;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Success extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public Success() {
		setTitle("Terminbestätigung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 428);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 754, 381);
		panel.setBackground(new Color(51, 204, 0));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAOpenSource = new JLabel("An Open Source Project by Luca Henn and Tim Sander");
		lblAOpenSource.setForeground(new Color(255, 255, 255));
		lblAOpenSource.setHorizontalAlignment(SwingConstants.CENTER);
		lblAOpenSource.setFont(new Font("Consolas", Font.PLAIN, 19));
		lblAOpenSource.setBackground(new Color(255, 182, 193));
		lblAOpenSource.setBounds(168, 640, 673, 62);
		panel.add(lblAOpenSource);
		
		Button button_2 = new Button("X");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		button_2.setBackground(Color.GRAY);
		button_2.setBounds(690, 334, 54, 38);
		panel.add(button_2);
		
		JLabel lblEsKonntenLeider = new JLabel("Es wurde ein Termin gefunden!");
		lblEsKonntenLeider.setHorizontalAlignment(SwingConstants.CENTER);
		lblEsKonntenLeider.setForeground(Color.WHITE);
		lblEsKonntenLeider.setFont(new Font("Consolas", Font.BOLD, 19));
		lblEsKonntenLeider.setBackground(new Color(255, 182, 193));
		lblEsKonntenLeider.setBounds(39, 128, 673, 62);
		panel.add(lblEsKonntenLeider);
		
		JLabel lblVersuchenSieEs = new JLabel("Öffnen Sie bitte das Browser Fenster und fahren dort fort!");
		lblVersuchenSieEs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersuchenSieEs.setForeground(Color.WHITE);
		lblVersuchenSieEs.setFont(new Font("Consolas", Font.BOLD, 19));
		lblVersuchenSieEs.setBackground(new Color(255, 182, 193));
		lblVersuchenSieEs.setBounds(39, 186, 673, 62);
		panel.add(lblVersuchenSieEs);
		
		JButton btnNewButton = new JButton(" Spenden");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Startseite.openWebpage("https://www.paypal.com/donate?business=sander.tim%40t-online.de&currency_code=EUR");
			}
		});
		btnNewButton.setIcon(new ImageIcon(Success.class.getResource("/images/paypal.png")));
		btnNewButton.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 16));
		btnNewButton.setBounds(12, 295, 222, 77);
		panel.add(btnNewButton);
	}

}
