package org.thurs.dagonhai.resources;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.thurs.dagonhai.tasks.surviving.Eat;
import org.thurs.dagonhai.tasks.surviving.Teleport;
import org.thurs.dagonhai.tasks.fighting.Attack;
import org.thurs.dagonhai.tasks.looting.Pickup;
import org.thurs.dagonhai.resources.FoodTypes;
import org.thurs.dagonhai.*;

//GUI
public class GUI extends ClientAccessor {

    public GUI(ClientContext ctx) {
        super(ctx);
		initComponents();
    }
	public JFrame frame = new JFrame("Thurs's Dagon'hai Monk Killer");

	private void comboBox1ActionPerformed(ActionEvent e) {
	}

	private void comboBox2ActionPerformed(ActionEvent e) {
	}

	private void button1ActionPerformed(ActionEvent e) {
	}

	private void initComponents() {

		comboBox1 = new JComboBox<>();
		final JComboBox comboBox2 = new JComboBox(FoodTypes.values());
		button1 = new JButton();


		// Main window
		frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setSize(new Dimension(310, 110));
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
		frame.add(comboBox1);
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
		frame.add(comboBox2);
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
					DagonHai.taskList.add(new Pickup(ctx));
				}	
				
				DagonHai.food = (FoodTypes) comboBox2.getSelectedItem();
				DagonHai.taskList.add(new Eat(ctx));
				DagonHai.taskList.add(new Attack(ctx));
				DagonHai.taskList.add(new Teleport(ctx));
				System.out.println("ID of food: " + DagonHai.food.value);
				frame.dispose();

			}
		});
		frame.add(button1);
		button1.setBounds(new Rectangle(new Point(220, 14), button1
				.getPreferredSize()));
		button1.setSize(new Dimension(57, 22));

	}

	private JComboBox<String> comboBox1;
	private JButton button1;

}
