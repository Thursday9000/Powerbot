package org.thurs.aiomoney.resources;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
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
		setTitle("Thurs's AIO Money Maker");
		setFont(new Font("Dialog", Font.PLAIN, 14));
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		// Comboboc
		comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
				"Cow Hides", "Swamp Tar", "Flax Picking", "Spin Flax",
				"Wine Drinking", "Sara Brews", "Pie Eating", "Hunter Kits",
				"Pickup Bones", "Pickup Feathers", "Unicorn Horns",
				"Fill Vials", "Crush Chocolate Bars" }

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
				if (chosen.equals("Swamp Tar")) {
					org.thurs.aiomoney.resources.Variables.tarPickup = true;
					org.thurs.aiomoney.resources.Variables.method = "Swamp Tar Picking";
				} else if (chosen.equals("Flax Picking")) {
					org.thurs.aiomoney.resources.Variables.flaxPicking = true;
					org.thurs.aiomoney.resources.Variables.method = "Picking Flax";
				} else if (chosen.equals("Wine Drinking")) {
					org.thurs.aiomoney.resources.Variables.drinkWine = true;
					org.thurs.aiomoney.resources.Variables.method = "Drinking Wine";
				} else if (chosen.equals("Pie Eating")) {
					org.thurs.aiomoney.resources.Variables.eatPie = true;
					org.thurs.aiomoney.resources.Variables.method = "Eating Pie";
				} else if (chosen.equals("Hunter Kits")) {
					org.thurs.aiomoney.resources.Variables.openKits = true;
					org.thurs.aiomoney.resources.Variables.method = "Hunter Kits";
				} else if (chosen.equals("Spin Flax")) {
					org.thurs.aiomoney.resources.Variables.spinFlax = true;
					org.thurs.aiomoney.resources.Variables.method = "Flax Spinning";
				} else if (chosen.equals("Sara Brews")) {
					org.thurs.aiomoney.resources.Variables.drinkBrews = true;
					org.thurs.aiomoney.resources.Variables.method = "Drinking Sara Brews";
				} else if (chosen.equals("Pickup Bones")) {
					org.thurs.aiomoney.resources.Variables.pickupBones = true;
					org.thurs.aiomoney.resources.Variables.method = "Picking Up Bones";
				} else if (chosen.equals("Cow Hides")) {
					org.thurs.aiomoney.resources.Variables.pickupHides = true;
					org.thurs.aiomoney.resources.Variables.method = "Picking Up Cow Hides";
				} else if (chosen.equals("Pickup Feathers")) {
					org.thurs.aiomoney.resources.Variables.pickupFeathers = true;
					org.thurs.aiomoney.resources.Variables.method = "Picking Up Feathers";
				} else if (chosen.equals("Unicorn Horns")) {
					org.thurs.aiomoney.resources.Variables.unicornKill = true;
					org.thurs.aiomoney.resources.Variables.method = "Gathering Unicorn Horns";
				} else if (chosen.equals("Fill Vials")) {
					org.thurs.aiomoney.resources.Variables.fillVials = true;
					org.thurs.aiomoney.resources.Variables.method = "Filling Vials";
				} else if (chosen.equals("Crush Chocolate Bars")) {
					org.thurs.aiomoney.resources.Variables.crushBars = true;
					org.thurs.aiomoney.resources.Variables.method = "Crushing Chocolate Bars";
				}
				setVisible(false);
			}
		});
		contentPane.add(button1);
		button1.setBounds(new Rectangle(new Point(220, 14), button1
				.getPreferredSize()));
		button1.setSize(new Dimension(57, 22));

		{
			Dimension preferredSize = new Dimension();
			for (int i = 0; i < contentPane.getComponentCount(); i++) {
				preferredSize.width = 289;
				preferredSize.height = 45;
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
	}

	private JComboBox<String> comboBox1;
	private JButton button1;

}