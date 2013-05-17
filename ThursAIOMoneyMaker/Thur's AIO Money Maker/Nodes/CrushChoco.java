package Nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class CrushChoco {

	// Bank
	public static class InitialBankBars extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(Resources.Variables.CHOCOLATE_DUST) == 0
					&& Inventory.getCount(Resources.Variables.CHOCOLATE_BAR) == 0
					&& Resources.Variables.VARROCK_BANK.contains(Players
							.getLocal().getLocation())
					&& SceneEntities
							.getLoaded(Resources.Variables.VARROCK_BANKER) != null
					&& Resources.Variables.crushBars == true;
		}

		@Override
		public void execute() {
			Resources.Variables.status = "Banking...";
			NPC banker = NPCs.getNearest(Resources.Variables.VARROCK_BANKER);
			if (banker.isOnScreen()) {
				Bank.open();
				if (Bank.isOpen()) {
					Timer t = new Timer(1350);
					while (t.isRunning()) {
						Task.sleep(1000, 1500);
					}
					if (!Inventory.contains(Resources.Variables.CHOCOLATE_BAR)
							&& Bank.isOpen()
							&& Bank.getItem(Resources.Variables.CHOCOLATE_BAR) != null) {
						Bank.withdraw(Resources.Variables.CHOCOLATE_BAR, 28);

					}
				}
				Bank.close();
			}

		}

	}

	public static class BankBars extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(Resources.Variables.CHOCOLATE_DUST) == 28
					&& Resources.Variables.VARROCK_BANK.contains(Players
							.getLocal().getLocation())
					&& SceneEntities
							.getLoaded(Resources.Variables.VARROCK_BANKER) != null
					&& Resources.Variables.crushBars == true;
		}

		@Override
		public void execute() {
			Resources.Variables.status = "Banking...";
			NPC banker = NPCs.getNearest(Resources.Variables.VARROCK_BANKER);
			if (banker.isOnScreen()) {
				Bank.open();
				Timer t = new Timer(1350);
				while (t.isRunning()) {
					Task.sleep(1000, 1500);
				}
				if (Bank.isOpen()) {
					Bank.deposit(Resources.Variables.CHOCOLATE_DUST, 28);
					if (!Inventory.contains(Resources.Variables.CHOCOLATE_BAR)
							&& Bank.isOpen()
							&& Bank.getItem(Resources.Variables.CHOCOLATE_BAR) != null) {
						Bank.withdraw(Resources.Variables.CHOCOLATE_BAR, 28);
					}
				}
				Bank.close();
			}
		}

	}

	// Drink
	public static class CrushBars extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(Resources.Variables.CHOCOLATE_BAR) <= 28
					&& Resources.Variables.VARROCK_BANK.contains(Players
							.getLocal().getLocation())
					&& Resources.Variables.crushBars == true
					&& Players.getLocal().isIdle();
		}

		@Override
		public void execute() {
			Resources.Variables.status = "Crushing bars...";
			if (Resources.Variables.VARROCK_BANK.contains(Players.getLocal()
					.getLocation()) && Widgets.get(548).getChild(172).visible()) {
				for (int x = 1; x < 28; x++) {
					Widgets.get(679, 0).getChild(x)
							.interact("Powder", "Chocolate bar");
					Timer t = new Timer(1560);
					while (t.isRunning()) {
						Task.sleep(1200, 1800);
					}
					Widgets.get(1371, 44).getChild(2).interact("Select");
					Timer ti = new Timer(535);
					while (ti.isRunning()) {
						Task.sleep(500, 550);
					}
					Widgets.get(1370, 35).getChild(0)
							.interact("Make 28 Chocolate dust");
					Timer tim = new Timer(2350);
					while (tim.isRunning()) {
						Task.sleep(2000, 2500);
					}
				}
			}
		}
	}
}
