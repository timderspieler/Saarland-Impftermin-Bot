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

import de.timsander.main.Main;

public class Failure extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public Failure() {
		setTitle("Es konnte kein Termin gefunden werden");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1032, 775);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 990, 702);
		panel.setBackground(new Color(255, 0, 51));
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
		button_2.setBounds(434, 495, 93, 62);
		panel.add(button_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Failure.class.getResource("/images/sad.png")));
		lblNewLabel.setBounds(350, 75, 260, 274);
		panel.add(lblNewLabel);
		
		JLabel lblEsKonntenLeider = new JLabel("Es konnten leider keine Termine gefunden werden!");
		lblEsKonntenLeider.setHorizontalAlignment(SwingConstants.CENTER);
		lblEsKonntenLeider.setForeground(Color.WHITE);
		lblEsKonntenLeider.setFont(new Font("Consolas", Font.BOLD, 19));
		lblEsKonntenLeider.setBackground(new Color(255, 182, 193));
		lblEsKonntenLeider.setBounds(153, 351, 673, 62);
		panel.add(lblEsKonntenLeider);
		
		JLabel lblVersuchenSieEs = new JLabel("Versuchen Sie es doch einfach erneut!");
		lblVersuchenSieEs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersuchenSieEs.setForeground(Color.WHITE);
		lblVersuchenSieEs.setFont(new Font("Consolas", Font.BOLD, 19));
		lblVersuchenSieEs.setBackground(new Color(255, 182, 193));
		lblVersuchenSieEs.setBounds(153, 427, 673, 62);
		panel.add(lblVersuchenSieEs);
	}

}
