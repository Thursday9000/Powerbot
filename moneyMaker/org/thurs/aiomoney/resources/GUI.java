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

import org.thurs.aiomoney.nodes.chocdust.BankBars;
import org.thurs.aiomoney.nodes.chocdust.CrushBars;
import org.thurs.aiomoney.nodes.chocdust.InitialBankBars;
import org.thurs.aiomoney.nodes.cowhides.AntiBan;
import org.thurs.aiomoney.nodes.cowhides.HideBank;
import org.thurs.aiomoney.nodes.cowhides.HidesToBank;
import org.thurs.aiomoney.nodes.cowhides.PickupHides;
import org.thurs.aiomoney.nodes.cowhides.WalkToHides;
import org.thurs.aiomoney.nodes.drinkbrews.BankBrews;
import org.thurs.aiomoney.nodes.drinkbrews.BankBrewsOnStart;
import org.thurs.aiomoney.nodes.drinkbrews.DrinkBrews;
import org.thurs.aiomoney.nodes.drinkwine.BankWine;
import org.thurs.aiomoney.nodes.drinkwine.BankWineOnStart;
import org.thurs.aiomoney.nodes.drinkwine.DrinkWine;
import org.thurs.aiomoney.nodes.eatpie.BankPie;
import org.thurs.aiomoney.nodes.eatpie.BankPiesOnStart;
import org.thurs.aiomoney.nodes.eatpie.EatThePie;
import org.thurs.aiomoney.nodes.fillvials.InitialVialBank;
import org.thurs.aiomoney.nodes.fillvials.VialBanker;
import org.thurs.aiomoney.nodes.fillvials.VialFiller;
import org.thurs.aiomoney.nodes.grabbones.BoneBank;
import org.thurs.aiomoney.nodes.grabbones.FixDaGlitch;
import org.thurs.aiomoney.nodes.grabbones.PickBones;
import org.thurs.aiomoney.nodes.grabbones.WalkToBones;
import org.thurs.aiomoney.nodes.grabbones.WalkToBox;
import org.thurs.aiomoney.nodes.grabfeathers.FeatherBank;
import org.thurs.aiomoney.nodes.grabfeathers.FeathersToBank;
import org.thurs.aiomoney.nodes.grabfeathers.PickupFeathers;
import org.thurs.aiomoney.nodes.grabfeathers.WalkToFeathers;
import org.thurs.aiomoney.nodes.killunicorns.BankHorns;
import org.thurs.aiomoney.nodes.killunicorns.BankToUnicorns;
import org.thurs.aiomoney.nodes.killunicorns.KillUnicorns;
import org.thurs.aiomoney.nodes.killunicorns.UnicornsToBank;
import org.thurs.aiomoney.nodes.killunicorns.WalkBackOnDeath;
import org.thurs.aiomoney.nodes.openkits.BankKits;
import org.thurs.aiomoney.nodes.openkits.BankKitsOnStart;
import org.thurs.aiomoney.nodes.openkits.OpenKits;
import org.thurs.aiomoney.nodes.pickflax.FlaxBanking;
import org.thurs.aiomoney.nodes.pickflax.FlaxPicking;
import org.thurs.aiomoney.nodes.pickflax.GlitchFix;
import org.thurs.aiomoney.nodes.pickflax.ToBank;
import org.thurs.aiomoney.nodes.pickflax.ToFlax;
import org.thurs.aiomoney.nodes.spinflax.ClimbDownLadder;
import org.thurs.aiomoney.nodes.spinflax.ClimbLadder;
import org.thurs.aiomoney.nodes.spinflax.Spinning;
import org.thurs.aiomoney.nodes.spinflax.StringBanking;
import org.thurs.aiomoney.nodes.spinflax.ToBankSpin;
import org.thurs.aiomoney.nodes.spinflax.ToSpinner;
import org.thurs.aiomoney.nodes.spinflax.Withdraw;
import org.thurs.aiomoney.nodes.swamptar.Pickup;

//GUI
@SuppressWarnings("serial")
public class GUI extends JFrame {
	Variables var = new Variables();
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
					var.tarPickup = true;
					var.method = "Swamp Tar Picking";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new Pickup());
				} else if (chosen.equals("Flax Picking")) {
					var.flaxPicking = true;
					var.method = "Picking Flax";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new FlaxPicking());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new FlaxBanking());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new ToFlax());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new ToBank());
				} else if (chosen.equals("Wine Drinking")) {
					var.drinkWine = true;
					var.method = "Drinking Wine";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new DrinkWine());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new BankWine());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new BankWineOnStart());
				} else if (chosen.equals("Pie Eating")) {
					var.eatPie = true;
					var.method = "Eating Pie";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new BankPiesOnStart());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new BankPie());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new EatThePie());
				} else if (chosen.equals("Hunter Kits")) {
					var.openKits = true;
					var.method = "Hunter Kits";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new BankKits());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new OpenKits());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new BankKitsOnStart());
				} else if (chosen.equals("Spin Flax")) {
					var.spinFlax = true;
					var.method = "Flax Spinning";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new Spinning());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new StringBanking());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new ToBankSpin());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new ToSpinner());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new ClimbLadder());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new ClimbDownLadder());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new Withdraw());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new GlitchFix());
				} else if (chosen.equals("Sara Brews")) {
					var.drinkBrews = true;
					var.method = "Drinking Sara Brews";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new BankBrewsOnStart());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new BankBrews());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new DrinkBrews());
				} else if (chosen.equals("Pickup Bones")) {
					var.pickupBones = true;
					var.method = "Picking Up Bones";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new BoneBank());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new PickBones());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new WalkToBones());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new WalkToBox());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new FixDaGlitch());
				} else if (chosen.equals("Cow Hides")) {
					var.pickupHides = true;
					var.method = "Picking Up Cow Hides";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new HideBank());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new PickupHides());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new WalkToHides());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new HidesToBank());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new AntiBan());
				} else if (chosen.equals("Pickup Feathers")) {
					var.pickupFeathers = true;
					var.method = "Picking Up Feathers";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new PickupFeathers());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new FeatherBank());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new WalkToFeathers());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new FeathersToBank());
				} else if (chosen.equals("Unicorn Horns")) {
					var.unicornKill = true;
					var.method = "Gathering Unicorn Horns";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new KillUnicorns());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new BankToUnicorns());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new UnicornsToBank());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new BankHorns());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new WalkBackOnDeath());
				} else if (chosen.equals("Fill Vials")) {
					var.fillVials = true;
					var.method = "Filling Vials";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new VialFiller());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new VialBanker());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new InitialVialBank());
				} else if (chosen.equals("Crush Chocolate Bars")) {
					var.crushBars = true;
					var.method = "Crushing Chocolate Bars";
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new CrushBars());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new BankBars());
					org.thurs.aiomoney.AIOMoneyMaker.NODES.add(new InitialBankBars());
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