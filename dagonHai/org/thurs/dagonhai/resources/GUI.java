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

import org.thurs.dagonhai.tasks.surviving.Eat;
import org.thurs.dagonhai.tasks.looting.Pickup;
import org.thurs.dagonhai.resources.FoodEnum;

//GUI
public class GUI extends JFrame {
	public static FoodEnum food = null;

	
	public GUI() {
		initComponents();
	}

	private void comboBox1ActionPerformed(ActionEvent e) {
	}

	private void comboBox2ActionPerformed(ActionEvent e) {
	}

	private void button1ActionPerformed(ActionEvent e) {
	}

	private void initComponents() {

		comboBox1 = new JComboBox<>();
		final JComboBox comboBox2 = new JComboBox(FoodEnum.values());
		button1 = new JButton();

		// Main window
		setTitle("Thurs's Dagon'hai Monk Killer");
		setFont(new Font("Dialog", Font.PLAIN, 14));
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		setPreferredSize(new Dimension(310, 110));
		pack();
		setLocationRelativeTo(null);

		// Combobox 1
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

		// Combobox 2
		comboBox2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox2ActionPerformed(e);
			}
		});
		contentPane.add(comboBox2);
		comboBox2.setBounds(new Rectangle(new Point(15, 45), comboBox2
				.getPreferredSize()));
		comboBox2.setSize(new Dimension(200, 20));

		// start button
		button1.setText("Start");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1ActionPerformed(e);
				String chosen = comboBox1.getSelectedItem().toString();
				if (chosen.equals("Loot Runes!")) {
					Pickup.profit = true;
				}	
				
				food = (FoodEnum) comboBox2.getSelectedItem();
				
				System.out.println("ID of food: " + GUI.food.value);
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
