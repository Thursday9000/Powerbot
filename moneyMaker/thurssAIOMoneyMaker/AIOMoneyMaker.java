import java.awt.*;

import java.util.LinkedList;

import javax.swing.*;


import nodes.CowHides.*;
import nodes.CrushChoco.*;
import nodes.DrinkTheBrews.*;
import nodes.EatPie.*;
import nodes.FillVials.*;
import nodes.GrabBones.*;
import nodes.GrabFeathers.*;
import nodes.OpenTheKits.*;
import nodes.PickFlax.*;
import nodes.SpinFlax.*;
import nodes.SwampTar.*;
import nodes.UnicornKill.*;
import nodes.WineDrink.*;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.util.Random;

import resources.GUI;

@Manifest(authors = { "Thurs" }, name = "Thurs's AIO Money Maker", description = "Makes money so you don't have to!", version = 1)
public class AIOMoneyMaker extends ActiveScript implements PaintListener {

	// onStart
	@Override
	public void onStart() {
		Mouse.setSpeed(Speed.VERY_FAST);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GUI().setVisible(true);
			}

		});
		// Tar
		NODES.add(new Pickup());
		// Flax
		NODES.add(new FlaxPicking());
		NODES.add(new FlaxBanking());
		NODES.add(new ToFlax());
		NODES.add(new ToBank());
		// Kit opening
		NODES.add(new BankKits());
		NODES.add(new OpenKits());
		NODES.add(new BankKitsOnStart());
		// Flax spinning
		NODES.add(new Spinning());
		NODES.add(new StringBanking());
		NODES.add(new ToBank1());
		NODES.add(new ToSpinner());
		NODES.add(new ClimbLadder());
		NODES.add(new ClimbDownLadder());
		NODES.add(new Withdraw());
		NODES.add(new GlitchFix());
		// Drink brews
		NODES.add(new BankBrewsOnStart());
		NODES.add(new BankBrews());
		NODES.add(new DrinkBrews());
		// pickup bones
		NODES.add(new BoneBank());
		NODES.add(new PickBones());
		NODES.add(new WalkToBones());
		NODES.add(new WalkToLadder());
		NODES.add(new FixDaGlitch());
		// Cow hide pickup
		NODES.add(new HideBank());
		NODES.add(new PickupHides());
		NODES.add(new WalkToHides());
		NODES.add(new HidesToBank());
		NODES.add(new AntiBan());
		// Feather pickup
		NODES.add(new PickupFeathers());
		NODES.add(new FeatherBank());
		NODES.add(new WalkToFeathers());
		NODES.add(new FeathersToBank());
		// Unicorns
		NODES.add(new KillUnicorns());
		NODES.add(new BankToUnicorns());
		NODES.add(new UnicornsToBank());
		NODES.add(new BankHorns());
		NODES.add(new WalkBackOnDeath());
		// Vial Filling
		NODES.add(new VialFiller());
		NODES.add(new VialBanker());
		NODES.add(new InitialVialBank());
		// Crushing Choc bars
		NODES.add(new CrushBars());
		NODES.add(new BankBars());
		NODES.add(new InitialBankBars());
		// Wine Drinking
		NODES.add(new DrinkWine());
		NODES.add(new BankWine());
		NODES.add(new BankWineOnStart());
		// Pie Eating
		NODES.add(new BankPiesOnStart());
		NODES.add(new BankPie());
		NODES.add(new EatThePie());

	}

	// Paint
	private final Color color1 = new Color(255, 255, 255);

	private final Font font1 = new Font("Arial", 1, 13);
	private final Font font2 = new Font("Arial", 0, 13);

	public void onRepaint(Graphics g1) {
		resources.Variables.runTime.toElapsedString();
		Graphics2D g = (Graphics2D) g1;
		g.setFont(font1);
		g.setColor(color1);
		g.drawString("Thurs's Money Maker", 25, 75);
		g.setFont(font2);
		g.drawString(
				"Run Time: " + resources.Variables.runTime.toElapsedString(),
				25, 100);
		g.drawString("Method: " + resources.Variables.method, 25, 125);
		g.drawString("Status: " + resources.Variables.status, 25, 150);

		g.setColor(Color.RED);
		g.drawLine(Mouse.getX() - 5, Mouse.getY() - 5, Mouse.getX() + 5,
				Mouse.getY() + 5);
		g.drawLine(Mouse.getX() - 5, Mouse.getY() + 5, Mouse.getX() + 5,
				Mouse.getY() - 5);
	}

	// Node
	private static final LinkedList<Node> NODES = new LinkedList<Node>();

	@Override
	public int loop() {
		for (final Node job :(new Node[NODES.size()])) {
			if (job.activate()) {
				getContainer().submit(job);
				job.join();
			}
		}
		return Random.nextInt(150, 250);

	}

}