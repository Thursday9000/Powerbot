package Resources;

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
					Resources.Variables.tarPickup = true;
					Resources.Variables.method = "Swamp Tar Picking";
				} else if (chosen.equals("Flax Picking")) {
					Resources.Variables.flaxPicking = true;
					Resources.Variables.method = "Picking Flax";
				} else if (chosen.equals("Wine Drinking")) {
					Resources.Variables.drinkWine = true;
					Resources.Variables.method = "Drinking Wine";
				} else if (chosen.equals("Pie Eating")) {
					Resources.Variables.eatPie = true;
					Resources.Variables.method = "Eating Pie";
				} else if (chosen.equals("Hunter Kits")) {
					Resources.Variables.openKits = true;
					Resources.Variables.method = "Hunter Kits";
				} else if (chosen.equals("Spin Flax")) {
					Resources.Variables.spinFlax = true;
					Resources.Variables.method = "Flax Spinning";
				} else if (chosen.equals("Sara Brews")) {
					Resources.Variables.drinkBrews = true;
					Resources.Variables.method = "Drinking Sara Brews";
				} else if (chosen.equals("Pickup Bones")) {
					Resources.Variables.pickupBones = true;
					Resources.Variables.method = "Picking Up Bones";
				} else if (chosen.equals("Cow Hides")) {
					Resources.Variables.pickupHides = true;
					Resources.Variables.method = "Picking Up Cow Hides";
				} else if (chosen.equals("Pickup Feathers")) {
					Resources.Variables.pickupFeathers = true;
					Resources.Variables.method = "Picking Up Feathers";
				} else if (chosen.equals("Unicorn Horns")) {
					Resources.Variables.unicornKill = true;
					Resources.Variables.method = "Gathering Unicorn Horns";
				} else if (chosen.equals("Fill Vials")) {
					Resources.Variables.fillVials = true;
					Resources.Variables.method = "Filling Vials";
				} else if (chosen.equals("Crush Chocolate Bars")) {
					Resources.Variables.crushBars = true;
					Resources.Variables.method = "Crushing Chocolate Bars";
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