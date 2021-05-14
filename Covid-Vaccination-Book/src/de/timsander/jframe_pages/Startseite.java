package de.timsander.jframe_pages;

import java.awt.Button;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import de.timsander.main.City;
import de.timsander.main.Main;
import de.timsander.main.Main.Task;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.net.URI;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import java.awt.SystemColor;

public class Startseite extends JFrame {

	private JPanel contentPane;
	
	public static JTextField txtEmailTelefon;
	public static JLabel suche_panel;
	public static JCheckBox auto_login;
	public static JProgressBar progressBar;
	public static JToggleButton start_button;
	
	/**
	 * Create the frame. Test
	 */
	public Startseite() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Startseite.class.getResource("/images/coronavirus.png")));
		
		setTitle("Saarland Impftermin Bot");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 926, 527);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 554, 480);
		panel.setBackground(new Color(204, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTpferrechner = new JLabel("Saarland Impftermin Bot");
		lblTpferrechner.setBackground(new Color(255, 182, 193));
		lblTpferrechner.setHorizontalAlignment(SwingConstants.CENTER);
		lblTpferrechner.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		lblTpferrechner.setBounds(75, 201, 403, 81);
		panel.add(lblTpferrechner);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Startseite.class.getResource("/images/calendar.png")));
		label.setBounds(201, 86, 138, 154);
		panel.add(label);
		
		JLabel lblAOpenSource = new JLabel("An Open Source Project by Luca Henn & Tim Sander");
		lblAOpenSource.setHorizontalAlignment(SwingConstants.CENTER);
		lblAOpenSource.setFont(new Font("Consolas", Font.PLAIN, 19));
		lblAOpenSource.setBackground(new Color(255, 182, 193));
		lblAOpenSource.setBounds(0, 253, 560, 62);
		panel.add(lblAOpenSource);
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(false);
		progressBar.setBounds(12, 430, 530, 28);
		panel.add(progressBar);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openWebpage("https://timderspieler.gitbook.io/saarland-impftermin-bot/");
			}
		});
		btnNewButton_1.setBounds(12, 13, 65, 47);
		panel.add(btnNewButton_1);
		btnNewButton_1.setBackground(SystemColor.info);
		btnNewButton_1.setIcon(new ImageIcon(Startseite.class.getResource("/images/information (2).png")));
		btnNewButton_1.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 16));
		
		suche_panel = new JLabel();
		suche_panel.setText("(Suche inaktiv)");
		suche_panel.setHorizontalAlignment(SwingConstants.CENTER);
		suche_panel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		suche_panel.setBackground(new Color(255, 182, 193));
		suche_panel.setBounds(36, 401, 482, 28);
		panel.add(suche_panel);
		
		Button button_1 = new Button("Browser Einstellungen");
		button_1.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setBackground(new Color(0, 153, 255));
		button_1.setBounds(560, 11, 342, 64);
		contentPane.add(button_1);
		
		Button button_2 = new Button("Impfzentren / Impfstoffe");
		button_2.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		button_2.setForeground(new Color(255, 255, 255));
		button_2.setBackground(new Color(0, 153, 255));
		button_2.setBounds(560, 119, 342, 67);
		contentPane.add(button_2);
		
		JButton btnNewButton = new JButton(" Spenden");
		btnNewButton.setBounds(721, 384, 181, 87);
		contentPane.add(btnNewButton);
		btnNewButton.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWebpage("https://www.paypal.com/donate?business=sander.tim%40t-online.de&currency_code=EUR");
			}
		});
		btnNewButton.setIcon(new ImageIcon(Startseite.class.getResource("/images/paypal.png")));
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Consolas", Font.BOLD, 15));
		
		for (City all : City.values()) {
			comboBox.addItem(all.getName() + ": (" + all.getVaccinationType() + ")");
		}
		
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(560, 192, 342, 32);
		contentPane.add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("Chrome");
		comboBox_1.addItem("Firefox");
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setFont(new Font("Consolas", Font.BOLD, 15));
		comboBox_1.setBounds(560, 81, 342, 32);
		contentPane.add(comboBox_1);
		
		start_button = new JToggleButton("");
		start_button.setToolTipText("Starte / Stoppe die Suche");
		start_button.setIcon(new ImageIcon(Startseite.class.getResource("/images/switch-off.png")));
		start_button.setSelectedIcon(new ImageIcon(Startseite.class.getResource("/images/switch-on.png")));
		start_button.setBounds(560, 384, 149, 87);
		
		start_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (start_button.isSelected()) {
					
					Main.c = City.getByName(((String) comboBox.getSelectedItem()).split(":")[0]);
					Main.browser = (String) comboBox_1.getSelectedItem();
					Main.times = 0;
					
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
						
						progressBar.setIndeterminate(true);
						
						Task t = new Main.Task();
						t.start();
						
					}
					
				} else {
					
					Main.times = Main.max_times;
					
				}
				
			}
		});
		
		contentPane.add(start_button);
		
		txtEmailTelefon = new JTextField("0170 12345678");
		txtEmailTelefon.setBounds(560, 303, 342, 39);
		contentPane.add(txtEmailTelefon);
		contentPane.add(comboBox_1);
		
		Button button_2_1 = new Button("E-Mail / Telefon");
		button_2_1.setForeground(Color.WHITE);
		button_2_1.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		button_2_1.setBackground(new Color(0, 153, 255));
		button_2_1.setBounds(560, 230, 342, 67);
		contentPane.add(button_2_1);
		
		auto_login = new JCheckBox("Auto Login");
		auto_login.setBackground(Color.WHITE);
		auto_login.setFont(new Font("Consolas", Font.BOLD, 18));
		auto_login.setBounds(562, 350, 342, 35);
		contentPane.add(auto_login);
		
	}
	
	public static void failure(Startseite seite) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Failure frame = new Failure();
//					frame.setLocation(seite.getLocation());
//					frame.setVisible(true);
					
					Fehler frame = new Fehler();
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
//					Success frame = new Success();
//					frame.setLocation(seite.getLocation());
//					frame.setVisible(true);
					
					Bestaetigung fr = new Bestaetigung();
					fr.setLocation(seite.getLocation());
					fr.setVisible(true);
					
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
