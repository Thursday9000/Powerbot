package org.thursBoner.resources;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

//GUI
@SuppressWarnings("serial")
public class GUI extends JFrame {
	public static int boneID;

	public GUI() {
		initComponents();
	}

	private void comboBox1ActionPerformed(ActionEvent e) {
	}

	private void button1ActionPerformed(ActionEvent e){
	}

	private void initComponents() {

		textBox1 = new JTextField();
		button1 = new JButton();

		//Main window
		setTitle("Thurs's Boner");
		setFont(new Font("Dialog", Font.PLAIN, 14));
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		setPreferredSize(new Dimension(310, 83));
		pack();
		setLocationRelativeTo(null);

		//Textbox
		textBox1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox1ActionPerformed(e);
			}
		});
		contentPane.add(textBox1);
		textBox1.setBounds(new Rectangle(new Point(15, 15), textBox1
				.getPreferredSize()));
		textBox1.setSize(new Dimension(200, 20));

		//Start
		button1.setText("Start");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1ActionPerformed(e);
				String chosen = textBox1.getText();
				boneID = Integer.parseInt(chosen);
				setVisible(false);
			}
		});
		contentPane.add(button1);
		button1.setBounds(new Rectangle(new Point(220, 14), button1
				.getPreferredSize()));
		button1.setSize(new Dimension(57, 22));

	}

	private JTextField textBox1;
	private JButton button1;
}
