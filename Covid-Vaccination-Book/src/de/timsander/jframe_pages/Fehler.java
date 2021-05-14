package de.timsander.jframe_pages;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Fehler extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Fehler dialog = new Fehler();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Fehler() {
		setType(Type.POPUP);
		setTitle("Terminsuche fehlgeschlagen");
		setBounds(100, 100, 637, 341);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 0));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblAOpenSource = new JLabel("An Open Source Project by Luca Henn and Tim Sander");
		lblAOpenSource.setBounds(168, 640, 673, 62);
		lblAOpenSource.setHorizontalAlignment(SwingConstants.CENTER);
		lblAOpenSource.setForeground(Color.WHITE);
		lblAOpenSource.setFont(new Font("Consolas", Font.PLAIN, 19));
		lblAOpenSource.setBackground(new Color(255, 182, 193));
		panel.add(lblAOpenSource);
		
		Button button_2 = new Button("X");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_2.setBounds(540, 242, 54, 38);
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		button_2.setBackground(new Color(0, 102, 0));
		panel.add(button_2);
		
		JLabel lblEsKonntenLeider = new JLabel("Es konnte kein Termin gefunden werden!");
		lblEsKonntenLeider.setBounds(81, 97, 478, 62);
		lblEsKonntenLeider.setHorizontalAlignment(SwingConstants.CENTER);
		lblEsKonntenLeider.setForeground(Color.WHITE);
		lblEsKonntenLeider.setFont(new Font("Consolas", Font.BOLD, 19));
		lblEsKonntenLeider.setBackground(new Color(255, 182, 193));
		panel.add(lblEsKonntenLeider);
		
		JLabel lblVersuchenSieEs = new JLabel("Versuchen Sie es einfach erneut!");
		lblVersuchenSieEs.setBounds(12, 136, 595, 62);
		lblVersuchenSieEs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersuchenSieEs.setForeground(Color.WHITE);
		lblVersuchenSieEs.setFont(new Font("Consolas", Font.BOLD, 19));
		lblVersuchenSieEs.setBackground(new Color(255, 182, 193));
		panel.add(lblVersuchenSieEs);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Fehler.class.getResource("/images/dislike.png")));
		lblNewLabel.setBounds(265, 27, 70, 84);
		panel.add(lblNewLabel);
	}
}
