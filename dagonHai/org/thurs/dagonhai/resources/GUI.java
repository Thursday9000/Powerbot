package org.thurs.dagonhai.resources;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;
import org.thurs.dagonhai.tasks.Task;
import org.thurs.dagonhai.tasks.surviving.Eat;
import org.thurs.dagonhai.tasks.surviving.Teleport;
import org.thurs.dagonhai.tasks.fighting.Attack;
import org.thurs.dagonhai.tasks.looting.Pickup;
import org.thurs.dagonhai.resources.FoodType;

//GUI
public class GUI extends ClientAccessor {
	public static FoodType food = null;

	public static List<Task> taskList = new ArrayList<Task>();

	public GUI(ClientContext ctx) {
		super(ctx);
		initComponents();
	}

	public JFrame frame = new JFrame("Thurs's Dagon'hai Monk Killer");

	private void comboBox2ActionPerformed(ActionEvent e) {
	}

	private void button1ActionPerformed(ActionEvent e) {
	}

	private void initComponents() {
		final JRadioButton loot = new JRadioButton("Loot runes ");

		JPanel content = new JPanel();
		frame.add(content);
		comboBox1 = new JComboBox<>();
		final JComboBox comboBox2 = new JComboBox(FoodType.values());
		button1 = new JButton();

		// Main window
		frame.setResizable(false);
		frame.setSize(225, 90);
		frame.validate();
		frame.setLocationRelativeTo(null);

		content.add(loot);

		// Combobox 2
		comboBox2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox2ActionPerformed(e);
			}
		});
		content.add(comboBox2);

		// start button
		content.setLayout(new FlowLayout());
		content.add(button1);
		button1.setText("Start");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1ActionPerformed(e);
				if (loot.isEnabled()) {
					taskList.add(new Pickup(ctx));
				}

				food = (FoodType) comboBox2.getSelectedItem();
				taskList.add(new Eat(ctx));
				taskList.add(new Attack(ctx));
				taskList.add(new Teleport(ctx));
				System.out.println("ID of food: " + food.value);
				frame.dispose();

			}
		});

	}

	private JComboBox<String> comboBox1;
	private JButton button1;
}
