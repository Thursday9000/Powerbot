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

import org.thurs.aiomoney.AIOMoneyMaker;
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
	AIOMoneyMaker node = new AIOMoneyMaker();
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
					var.method = "Swamp Tar Picking";
					node.NODES.add(new Pickup());
				} else if (chosen.equals("Flax Picking")) {
					var.method = "Picking Flax";
					node.NODES.add(new FlaxPicking());
					node.NODES.add(new FlaxBanking());
					node.NODES.add(new ToFlax());
					node.NODES.add(new ToBank());
				} else if (chosen.equals("Wine Drinking")) {
					var.method = "Drinking Wine";
					node.NODES.add(new DrinkWine());
					node.NODES.add(new BankWine());
					node.NODES.add(new BankWineOnStart());
				} else if (chosen.equals("Pie Eating")) {
					var.method = "Eating Pie";
					node.NODES.add(new BankPiesOnStart());
					node.NODES.add(new BankPie());
					node.NODES.add(new EatThePie());
				} else if (chosen.equals("Hunter Kits")) {
					var.method = "Hunter Kits";
					node.NODES.add(new BankKits());
					node.NODES.add(new OpenKits());
					node.NODES.add(new BankKitsOnStart());
				} else if (chosen.equals("Spin Flax")) {
					var.method = "Flax Spinning";
					node.NODES.add(new Spinning());
					node.NODES.add(new StringBanking());
					node.NODES.add(new ToBankSpin());
					node.NODES.add(new ToSpinner());
					node.NODES.add(new ClimbLadder());
					node.NODES.add(new ClimbDownLadder());
					node.NODES.add(new Withdraw());
					node.NODES.add(new GlitchFix());
				} else if (chosen.equals("Sara Brews")) {
					var.method = "Drinking Sara Brews";
					node.NODES.add(new BankBrewsOnStart());
					node.NODES.add(new BankBrews());
					node.NODES.add(new DrinkBrews());
				} else if (chosen.equals("Pickup Bones")) {
					var.method = "Picking Up Bones";
					node.NODES.add(new BoneBank());
					node.NODES.add(new PickBones());
					node.NODES.add(new WalkToBones());
					node.NODES.add(new WalkToBox());
					node.NODES.add(new FixDaGlitch());
				} else if (chosen.equals("Cow Hides")) {
					var.method = "Picking Up Cow Hides";
					node.NODES.add(new HideBank());
					node.NODES.add(new PickupHides());
					node.NODES.add(new WalkToHides());
					node.NODES.add(new HidesToBank());
					node.NODES.add(new AntiBan());
				} else if (chosen.equals("Pickup Feathers")) {
					var.method = "Picking Up Feathers";
					node.NODES.add(new PickupFeathers());
					node.NODES.add(new FeatherBank());
					node.NODES.add(new WalkToFeathers());
					node.NODES.add(new FeathersToBank());
				} else if (chosen.equals("Unicorn Horns")) {
					var.method = "Gathering Unicorn Horns";
					node.NODES.add(new KillUnicorns());
					node.NODES.add(new BankToUnicorns());
					node.NODES.add(new UnicornsToBank());
					node.NODES.add(new BankHorns());
					node.NODES.add(new WalkBackOnDeath());
				} else if (chosen.equals("Fill Vials")) {
					var.method = "Filling Vials";
					node.NODES.add(new VialFiller());
					node.NODES.add(new VialBanker());
					node.NODES.add(new InitialVialBank());
				} else if (chosen.equals("Crush Chocolate Bars")) {
					var.method = "Crushing Chocolate Bars";
					node.NODES.add(new CrushBars());
					node.NODES.add(new BankBars());
					node.NODES.add(new InitialBankBars());
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