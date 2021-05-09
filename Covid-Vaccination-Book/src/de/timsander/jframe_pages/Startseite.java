package de.timsander.jframe_pages;

import java.awt.Button;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import de.timsander.main.Main;

import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Startseite extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Startseite() {
		setTitle("Saarland Impftermin Bot");
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
		
		JLabel lblAOpenSource = new JLabel("An Open Source Project by Luca Henn & Tim Sander");
		lblAOpenSource.setHorizontalAlignment(SwingConstants.CENTER);
		lblAOpenSource.setFont(new Font("Consolas", Font.PLAIN, 19));
		lblAOpenSource.setBackground(new Color(255, 182, 193));
		lblAOpenSource.setBounds(61, 640, 560, 62);
		panel.add(lblAOpenSource);
		
		Button button = new Button("Weitere Informationen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWebpage("https://timderspieler.gitbook.io/saarland-impftermin-bot/");
			}
		});
		button.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		button.setForeground(new Color(255, 255, 255));
		button.setBackground(new Color(0, 153, 255));
		button.setBounds(703, 242, 301, 110);
		contentPane.add(button);
		
		Button button_1 = new Button("Browser Einstellungen");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Browser frame = new Browser();
							frame.setLocation(getLocation());
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button_1.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setBackground(new Color(0, 153, 255));
		button_1.setBounds(703, 126, 301, 110);
		contentPane.add(button_1);
		
		Button button_2 = new Button("Impfstoffe / Impfstellen");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Impfstellen frame = new Impfstellen();
							frame.setLocation(getLocation());
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button_2.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		button_2.setForeground(new Color(255, 255, 255));
		button_2.setBackground(new Color(0, 153, 255));
		button_2.setBounds(703, 10, 301, 110);
		contentPane.add(button_2);
		
		Button button_3 = new Button("Starten");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				if (Main.c == null || Main.browser.equalsIgnoreCase("none")) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Warnung frame = new Warnung(Main.c == null ? "Bitte wählen Sie eine Impfstelle aus!" : "Bitte wählen Sie einen Browser aus!");
								frame.setLocation(getLocation());
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} else {
					
					dispose();
					
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Main.p = new Progress();
								Main.p.setLocation(getLocation());
								Main.p.setVisible(true);
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					
				}
				
			}
		});
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		button_3.setBackground(new Color(0, 153, 255));
		button_3.setBounds(703, 605, 301, 110);
		contentPane.add(button_3);
		
		JButton btnNewButton = new JButton(" Spenden");
		btnNewButton.setBounds(768, 493, 181, 87);
		contentPane.add(btnNewButton);
		btnNewButton.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWebpage("https://www.paypal.com/donate?business=sander.tim%40t-online.de&currency_code=EUR");
			}
		});
		btnNewButton.setIcon(new ImageIcon(Startseite.class.getResource("/images/paypal.png")));
		
		JLabel lblImpfstoff = new JLabel("Impfstoff: " + (Main.c == null ? "Bitte wählen." : Main.c.getVaccinationType()));
		lblImpfstoff.setHorizontalAlignment(SwingConstants.LEFT);
		lblImpfstoff.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblImpfstoff.setBackground(new Color(255, 182, 193));
		lblImpfstoff.setBounds(703, 363, 299, 39);
		contentPane.add(lblImpfstoff);
		
		JLabel lblImpfstelle = new JLabel("Impfstelle: " + (Main.c == null ? "Bitte wählen." : Main.c.getName()));
		lblImpfstelle.setHorizontalAlignment(SwingConstants.LEFT);
		lblImpfstelle.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblImpfstelle.setBackground(new Color(255, 182, 193));
		lblImpfstelle.setBounds(703, 396, 299, 39);
		contentPane.add(lblImpfstelle);
		
		JLabel lblImpfstoff_1_1 = new JLabel("Browser: " + (Main.browser.equalsIgnoreCase("none") ? "Bitte wählen." : Main.browser));
		lblImpfstoff_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblImpfstoff_1_1.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblImpfstoff_1_1.setBackground(new Color(255, 182, 193));
		lblImpfstoff_1_1.setBounds(703, 430, 299, 39);
		contentPane.add(lblImpfstoff_1_1);
	}
	
	public static void failure(Startseite seite) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Failure frame = new Failure();
					frame.setLocation(seite.getLocation());
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public static void success(Startseite seite) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Success frame = new Success();
					frame.setLocation(seite.getLocation());
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public static void openWebpage(String url)
	{
		try
		{
			URI uri = new URL(url).toURI();

			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
				desktop.browse(uri);
		}
		catch (Exception e)
		{
			/*
			 *  I know this is bad practice but we don't want to do anything clever for a specific error
			 */
			e.printStackTrace();

			// Copy URL to the clipboard so the user can paste it into their browser
			StringSelection stringSelection = new StringSelection(url);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}
	}
	
}
