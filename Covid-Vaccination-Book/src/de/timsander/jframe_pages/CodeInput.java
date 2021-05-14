package de.timsander.jframe_pages;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import de.timsander.main.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class CodeInput extends JDialog {
	private JTextField txtHbanlt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CodeInput dialog = new CodeInput();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CodeInput() {
		setTitle("Terminbestätigung");
		setBounds(100, 100, 637, 341);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 51));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblAOpenSource = new JLabel("An Open Source Project by Luca Henn and Tim Sander");
		lblAOpenSource.setBounds(168, 640, 673, 62);
		lblAOpenSource.setHorizontalAlignment(SwingConstants.CENTER);
		lblAOpenSource.setForeground(Color.WHITE);
		lblAOpenSource.setFont(new Font("Consolas", Font.PLAIN, 19));
		lblAOpenSource.setBackground(new Color(255, 182, 193));
		panel.add(lblAOpenSource);
		
		JLabel lblEsKonntenLeider = new JLabel("Code:");
		lblEsKonntenLeider.setBounds(75, 61, 478, 62);
		lblEsKonntenLeider.setHorizontalAlignment(SwingConstants.CENTER);
		lblEsKonntenLeider.setForeground(Color.WHITE);
		lblEsKonntenLeider.setFont(new Font("Consolas", Font.BOLD, 25));
		lblEsKonntenLeider.setBackground(new Color(255, 182, 193));
		panel.add(lblEsKonntenLeider);
		
		JLabel lblVersuchenSieEs = new JLabel("Geben Sie den Code ein, den Sie soeben per E-Mail erhalten haben");
		lblVersuchenSieEs.setBounds(32, 176, 560, 32);
		lblVersuchenSieEs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersuchenSieEs.setForeground(Color.WHITE);
		lblVersuchenSieEs.setFont(new Font("Consolas", Font.BOLD, 14));
		lblVersuchenSieEs.setBackground(new Color(255, 182, 193));
		panel.add(lblVersuchenSieEs);
		
		txtHbanlt = new JTextField();
		txtHbanlt.setFont(new Font("Consolas", Font.BOLD, 25));
		txtHbanlt.setHorizontalAlignment(SwingConstants.CENTER);
		txtHbanlt.setBounds(180, 112, 266, 62);
		panel.add(txtHbanlt);
		txtHbanlt.setColumns(10);
		
		JButton btnNewButton = new JButton("Abschicken");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if (txtHbanlt.getText().length() == 0) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								
								Warnung fr = new Warnung("Ungültiger Code.");
								fr.setVisible(true);
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} else {
					
					Main.insertCode(Main.driver, txtHbanlt.getText());
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Main.login(Main.driver);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if (!Main.isCodeValid(Main.driver)) {
						
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									
									Warnung fr = new Warnung("Ungültiger Code.");
									fr.setVisible(true);
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
					}
					
				}
				
			}
		});
		btnNewButton.setBounds(318, 221, 128, 32);
		panel.add(btnNewButton);
		
		JButton btnNeuenCode = new JButton("Neuer Code");
		btnNeuenCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Main.sendNewCode(Main.driver);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNeuenCode.setBounds(180, 221, 124, 32);
		panel.add(btnNeuenCode);
	}
}
