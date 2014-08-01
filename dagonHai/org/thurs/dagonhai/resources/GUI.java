package org.thurs.dagonhai.resources;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

//GUI
@SuppressWarnings("serial")
public class GUI extends JFrame {

	public GUI() {
		initComponents();
	}

	private void comboBox1ActionPerformed(ActionEvent e) {
		return;
	}

	private void button1ActionPerformed(ActionEvent e) {
		return;
	}

	private void initComponents() {

		comboBox1 = new JComboBox<>();
		button1 = new JButton();

		// Main window
		setTitle("Thurs's Dagon'hai Monk Killer");
		setFont(new Font("Dialog", Font.PLAIN, 14));
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		setPreferredSize(new Dimension(310, 83));
		pack();
		setLocationRelativeTo(null);

		// Combobox
		comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
				"Loot nothing!", "Loot Runes!" }

		));
		comboBox1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox1ActionPerformed(e);
			}
		});
		contentPane.add(comboBox1);
		comboBox1.setBounds(new Rectangle(new Point(15, 15), comboBox1
				.getPreferredSize()));
		comboBox1.setSize(new Dimension(200, 20));

		// start button
		button1.setText("Start");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1ActionPerformed(e);
				String chosen = comboBox1.getSelectedItem().toString();
				if (chosen.equals("Loot Runes!")) {
					Variables.profit = true;
				}

				setVisible(false);
			}
		});
		contentPane.add(button1);
		button1.setBounds(new Rectangle(new Point(220, 14), button1
				.getPreferredSize()));
		button1.setSize(new Dimension(57, 22));

	}

	private JComboBox<String> comboBox1;
	private JButton button1;
}
