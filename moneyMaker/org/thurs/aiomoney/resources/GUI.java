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
					Variables.method = "Swamp Tar Picking";
					AIOMoneyMaker.NODES.add(new Pickup());
				} else if (chosen.equals("Flax Picking")) {
					Variables.method = "Picking Flax";
					AIOMoneyMaker.NODES.add(new FlaxPicking());
					AIOMoneyMaker.NODES.add(new FlaxBanking());
					AIOMoneyMaker.NODES.add(new ToFlax());
					AIOMoneyMaker.NODES.add(new ToBank());
				} else if (chosen.equals("Wine Drinking")) {
					Variables.method = "Drinking Wine";
					AIOMoneyMaker.NODES.add(new DrinkWine());
					AIOMoneyMaker.NODES.add(new BankWine());
					AIOMoneyMaker.NODES.add(new BankWineOnStart());
				} else if (chosen.equals("Pie Eating")) {
					Variables.method = "Eating Pie";
					AIOMoneyMaker.NODES.add(new BankPiesOnStart());
					AIOMoneyMaker.NODES.add(new BankPie());
					AIOMoneyMaker.NODES.add(new EatThePie());
				} else if (chosen.equals("Hunter Kits")) {
					Variables.method = "Hunter Kits";
					AIOMoneyMaker.NODES.add(new BankKits());
					AIOMoneyMaker.NODES.add(new OpenKits());
					AIOMoneyMaker.NODES.add(new BankKitsOnStart());
				} else if (chosen.equals("Spin Flax")) {
					Variables.method = "Flax Spinning";
					AIOMoneyMaker.NODES.add(new Spinning());
					AIOMoneyMaker.NODES.add(new StringBanking());
					AIOMoneyMaker.NODES.add(new ToBankSpin());
					AIOMoneyMaker.NODES.add(new ToSpinner());
					AIOMoneyMaker.NODES.add(new ClimbLadder());
					AIOMoneyMaker.NODES.add(new ClimbDownLadder());
					AIOMoneyMaker.NODES.add(new Withdraw());
					AIOMoneyMaker.NODES.add(new GlitchFix());
				} else if (chosen.equals("Sara Brews")) {
					Variables.method = "Drinking Sara Brews";
					AIOMoneyMaker.NODES.add(new BankBrewsOnStart());
					AIOMoneyMaker.NODES.add(new BankBrews());
					AIOMoneyMaker.NODES.add(new DrinkBrews());
				} else if (chosen.equals("Pickup Bones")) {
					Variables.method = "Picking Up Bones";
					AIOMoneyMaker.NODES.add(new BoneBank());
					AIOMoneyMaker.NODES.add(new PickBones());
					AIOMoneyMaker.NODES.add(new WalkToBones());
					AIOMoneyMaker.NODES.add(new WalkToBox());
					AIOMoneyMaker.NODES.add(new FixDaGlitch());
				} else if (chosen.equals("Cow Hides")) {
					Variables.method = "Picking Up Cow Hides";
					AIOMoneyMaker.NODES.add(new HideBank());
					AIOMoneyMaker.NODES.add(new PickupHides());
					AIOMoneyMaker.NODES.add(new WalkToHides());
					AIOMoneyMaker.NODES.add(new HidesToBank());
					AIOMoneyMaker.NODES.add(new AntiBan());
				} else if (chosen.equals("Pickup Feathers")) {
					Variables.method = "Picking Up Feathers";
					AIOMoneyMaker.NODES.add(new PickupFeathers());
					AIOMoneyMaker.NODES.add(new FeatherBank());
					AIOMoneyMaker.NODES.add(new WalkToFeathers());
					AIOMoneyMaker.NODES.add(new FeathersToBank());
				} else if (chosen.equals("Unicorn Horns")) {
					Variables.method = "Gathering Unicorn Horns";
					AIOMoneyMaker.NODES.add(new KillUnicorns());
					AIOMoneyMaker.NODES.add(new BankToUnicorns());
					AIOMoneyMaker.NODES.add(new UnicornsToBank());
					AIOMoneyMaker.NODES.add(new BankHorns());
					AIOMoneyMaker.NODES.add(new WalkBackOnDeath());
				} else if (chosen.equals("Fill Vials")) {
					Variables.method = "Filling Vials";
					AIOMoneyMaker.NODES.add(new VialFiller());
					AIOMoneyMaker.NODES.add(new VialBanker());
					AIOMoneyMaker.NODES.add(new InitialVialBank());
				} else if (chosen.equals("Crush Chocolate Bars")) {
					Variables.method = "Crushing Chocolate Bars";
					AIOMoneyMaker.NODES.add(new CrushBars());
					AIOMoneyMaker.NODES.add(new BankBars());
					AIOMoneyMaker.NODES.add(new InitialBankBars());
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