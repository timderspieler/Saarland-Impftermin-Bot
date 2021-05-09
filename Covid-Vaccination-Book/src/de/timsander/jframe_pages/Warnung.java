package de.timsander.jframe_pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Warnung extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public Warnung(String message) {
		setFont(new Font("Consolas", Font.BOLD, 18));
		setTitle("Warnung!");
		setBounds(100, 100, 668, 207);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JTextArea txtrHelp = new JTextArea();
			txtrHelp.setBackground(SystemColor.inactiveCaptionBorder);
			txtrHelp.setFont(new Font("Consolas", Font.BOLD, 27));
			txtrHelp.setText(message);
			txtrHelp.setForeground(new Color(0, 0, 0));
			contentPanel.add(txtrHelp);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Okay");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
