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
				GUI gui = new GUI();
				gui.setVisible(true);

			}

		});
		// Tar
		nodes.add(new Pickup());
		// Flax
		nodes.add(new FlaxPicking());
		nodes.add(new FlaxBanking());
		nodes.add(new ToFlax());
		nodes.add(new ToBank());
		// Kit opening
		nodes.add(new BankKits());
		nodes.add(new OpenKits());
		nodes.add(new BankKitsOnStart());
		// Flax spinning
		nodes.add(new Spinning());
		nodes.add(new StringBanking());
		nodes.add(new ToBank1());
		nodes.add(new ToSpinner());
		nodes.add(new ClimbLadder());
		nodes.add(new ClimbDownLadder());
		nodes.add(new Withdraw());
		nodes.add(new GlitchFix());
		// Drink brews
		nodes.add(new BankBrewsOnStart());
		nodes.add(new BankBrews());
		nodes.add(new DrinkBrews());
		// pickup bones
		nodes.add(new BoneBank());
		nodes.add(new PickBones());
		nodes.add(new WalkToBones());
		nodes.add(new WalkToLadder());
		nodes.add(new FixDaGlitch());
		// Cow hide pickup
		nodes.add(new HideBank());
		nodes.add(new PickupHides());
		nodes.add(new WalkToHides());
		nodes.add(new HidesToBank());
		nodes.add(new AntiBan());
		// Feather pickup
		nodes.add(new PickupFeathers());
		nodes.add(new FeatherBank());
		nodes.add(new WalkToFeathers());
		nodes.add(new FeathersToBank());
		// Unicorns
		nodes.add(new KillUnicorns());
		nodes.add(new BankToUnicorns());
		nodes.add(new UnicornsToBank());
		nodes.add(new BankHorns());
		nodes.add(new WalkBackOnDeath());
		// Vial Filling
		nodes.add(new VialFiller());
		nodes.add(new VialBanker());
		nodes.add(new InitialVialBank());
		// Crushing Choc bars
		nodes.add(new CrushBars());
		nodes.add(new BankBars());
		nodes.add(new InitialBankBars());
		// Wine Drinking
		nodes.add(new DrinkWine());
		nodes.add(new BankWine());
		nodes.add(new BankWineOnStart());
		// Pie Eating
		nodes.add(new BankPiesOnStart());
		nodes.add(new BankPie());
		nodes.add(new EatThePie());

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
	final LinkedList<Node> nodes = new LinkedList<Node>();

	@Override
	public int loop() {
		if (nodes == null || nodes.size() <= 0)
			return Random.nextInt(150, 250);
		for (final Node job : nodes.toArray(new Node[nodes.size()])) {
			if (job.activate()) {
				getContainer().submit(job);
				job.join();
			}
		}
		return Random.nextInt(150, 250);

	}

}