package de.timsander.jframe_pages;

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
import de.timsander.main.Main.Task;

import javax.swing.JProgressBar;

public class Progress extends JFrame {

	private JPanel contentPane;
	
	public static JProgressBar progressBar = new JProgressBar(0, Main.max_times);
	public static JLabel progressString = new JLabel();
	
	/**
	 * Create the frame.
	 */
	public Progress() {
		setTitle("Impfstoffe / Impfstellen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1032, 775);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 990, 702);
		panel.setBackground(new Color(204, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		progressString.setText("Suche nach freien Terminen...");
		progressString.setBackground(new Color(255, 182, 193));
		progressString.setHorizontalAlignment(SwingConstants.CENTER);
		progressString.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		progressString.setBounds(57, 398, 921, 81);
		panel.add(progressString);
		
		JLabel lblAOpenSource = new JLabel("An Open Source Project by Luca Henn and Tim Sander");
		lblAOpenSource.setHorizontalAlignment(SwingConstants.CENTER);
		lblAOpenSource.setFont(new Font("Consolas", Font.PLAIN, 19));
		lblAOpenSource.setBackground(new Color(255, 182, 193));
		lblAOpenSource.setBounds(168, 640, 673, 62);
		panel.add(lblAOpenSource);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Progress.class.getResource("/images/deadline.png")));
		lblNewLabel.setBounds(382, 129, 237, 291);
		panel.add(lblNewLabel);
		
		progressBar.setBounds(320, 464, 403, 62);
		progressBar.setStringPainted(true);
		panel.add(progressBar);
		
		Button button_2 = new Button("X");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Main.times = Main.max_times;
				
				dispose();
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Startseite s = new Startseite();
							s.setLocation(getLocation());
							s.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		button_2.setBackground(new Color(255, 0, 0));
		button_2.setBounds(473, 532, 93, 62);
		panel.add(button_2);
		
		Task t = new Main.Task();
		t.start();
		
	}
}
