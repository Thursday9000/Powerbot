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

public class OpenTheKits {
	// Banking

	public static class BankKitsOnStart extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount() == 0
					&& Resources.Variables.VARROCK_BANK.contains(Players
							.getLocal().getLocation())
					&& SceneEntities
							.getLoaded(Resources.Variables.VARROCK_BANKER) != null
					&& Resources.Variables.drinkBrews == true;
		}

		@Override
		public void execute() {
			Resources.Variables.status = "Banking...";
			NPC banker = NPCs.getNearest(Resources.Variables.VARROCK_BANKER);
			if (banker.isOnScreen()) {
				Bank.open();
				Timer t = new Timer(1750);
				while (t.isRunning()) {
					Task.sleep(1500, 2000);
				}
				if (Bank.isOpen()) {
					Bank.withdraw(Resources.Variables.HUNTER_KIT, 4);
					Timer ti = new Timer(1750);
					while (ti.isRunning()) {
						Task.sleep(1500, 2000);
					}
				}
				Bank.close();
			}
		}
	}

	public static class BankKits extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount() == 28
					&& SceneEntities
							.getLoaded(Resources.Variables.VARROCK_BANKER) != null
					&& !Inventory.contains(Resources.Variables.HUNTER_KIT)
					&& Resources.Variables.VARROCK_BANK.contains(Players
							.getLocal().getLocation())
					&& Resources.Variables.openKits == true;
		}

		@Override
		public void execute() {
			Resources.Variables.status = "Banking...";
			NPC banker = NPCs.getNearest(Resources.Variables.VARROCK_BANKER);
			if (banker.isOnScreen()) {
				Bank.open();
				Timer ti = new Timer(3350);
				while (ti.isRunning()) {
					Task.sleep(3000, 3500);
				}
				Widgets.get(762).getChild(34).interact("Deposit carried items");
				Timer t = new Timer(750);
				while (t.isRunning()) {
					Task.sleep(1200, 1800);
				}
				if (!Inventory.contains(Resources.Variables.HUNTER_KIT)
						&& Bank.isOpen()
						&& Bank.getItem(Resources.Variables.HUNTER_KIT) != null) {
					Bank.withdraw(Resources.Variables.HUNTER_KIT, 4);
				}

				Bank.close();
				while (t.isRunning()) {
					Task.sleep(1200, 1800);
				}
			}
		}

	}

	// Opening
	public static class OpenKits extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(Resources.Variables.HUNTER_KIT) == 4
					&& Resources.Variables.VARROCK_BANK.contains(Players
							.getLocal().getLocation())
					&& Resources.Variables.openKits == true;
		}

		@Override
		public void execute() {
			Resources.Variables.status = "Opening Kits...";
			for (int x = 0; x < 5; x++) {
				Widgets.get(679, 0).getChild(3).interact("Open", "Hunter kit");
				Timer t = new Timer(1750);
				while (t.isRunning()) {
					Task.sleep(1500, 2000);
				}
			}
		}

	}
}
