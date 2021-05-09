package de.timsander.jframe_pages;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Dialog.ModalityType;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import de.timsander.main.City;
import de.timsander.main.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Browser extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Browser() {
		setTitle("Impfstoffe / Impfstellen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1032, 775);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 685, 702);
		panel.setBackground(new Color(204, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTpferrechner = new JLabel("Saarland Impftermin Bot");
		lblTpferrechner.setBackground(new Color(255, 182, 193));
		lblTpferrechner.setHorizontalAlignment(SwingConstants.CENTER);
		lblTpferrechner.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblTpferrechner.setBounds(130, 333, 403, 81);
		panel.add(lblTpferrechner);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Startseite.class.getResource("/images/calendar.png")));
		label.setBounds(269, 180, 153, 199);
		panel.add(label);
		
		JLabel lblAOpenSource = new JLabel("An Open Source Project by Luca Henn and Tim Sander");
		lblAOpenSource.setHorizontalAlignment(SwingConstants.CENTER);
		lblAOpenSource.setFont(new Font("Consolas", Font.PLAIN, 19));
		lblAOpenSource.setBackground(new Color(255, 182, 193));
		lblAOpenSource.setBounds(0, 640, 673, 62);
		panel.add(lblAOpenSource);
		
		Button button_2 = new Button("Browser Einstellungen");
		button_2.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		button_2.setForeground(new Color(255, 255, 255));
		button_2.setBackground(new Color(0, 153, 255));
		button_2.setBounds(703, 10, 301, 110);
		contentPane.add(button_2);
		
		Button button_3 = new Button("Zurück");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		button_3.setBackground(new Color(0, 153, 255));
		button_3.setBounds(703, 605, 301, 110);
		contentPane.add(button_3);
		
		DefaultListModel<String> model = new DefaultListModel<>();
		JList<String> list = new JList<String>(model);
		list.setBackground(new Color(204, 255, 255));
		list.setFont(new Font("Consolas", Font.PLAIN, 15));
		
		model.addElement("Firefox");
		model.addElement("Chrome");
		
		list.setToolTipText("Wählen Sie einen Browser");
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(709, 126, 293, 120);
		contentPane.add(list);
		
		Button button_2_1 = new Button("Speichern");
		button_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String select = list.getSelectedValue();
				String msg = "Sie müssen zuerst einen Browser auswählen!";
				
				if (select != null) {
					
					Main.browser = select;
					msg = "Browser wurde gespeichert! (" + select + ")";
					
				}
				
				try {
					SaveDialog dialog = new SaveDialog(msg);
					dialog.setLocationRelativeTo(button_2_1);
					dialog.setModalityType(ModalityType.APPLICATION_MODAL);
					dialog.setVisible(true);
				} catch (Exception y) {
					y.printStackTrace();
				}
				
			}
		});
		button_2_1.setForeground(Color.WHITE);
		button_2_1.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		button_2_1.setBackground(new Color(0, 153, 153));
		button_2_1.setBounds(703, 250, 301, 52);
		contentPane.add(button_2_1);
	}

}
