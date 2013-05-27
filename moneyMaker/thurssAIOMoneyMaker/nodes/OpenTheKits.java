package nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;

public class OpenTheKits {
	// Banking

	public static class BankKitsOnStart extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount() == 0
					&& resources.Variables.VARROCK_BANK.contains(Players
							.getLocal())
					&& SceneEntities
							.getLoaded(resources.Variables.VARROCK_BANKER) != null
					&& resources.Variables.drinkBrews == true;
		}

		@Override
		public void execute() {
			resources.Variables.status = "Banking...";
			Bank.open();
			Timer t = new Timer(1750);
			while (t.isRunning()) {
				Task.sleep(1500, 2000);
			}
			if (Bank.isOpen()) {
				Bank.withdraw(resources.Variables.HUNTER_KIT, 4);
				Timer ti = new Timer(1750);
				while (ti.isRunning()) {
					Task.sleep(1500, 2000);
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
							.getLoaded(resources.Variables.VARROCK_BANKER) != null
					&& !Inventory.contains(resources.Variables.HUNTER_KIT)
					&& resources.Variables.VARROCK_BANK.contains(Players
							.getLocal())
					&& resources.Variables.openKits == true;
		}

		@Override
		public void execute() {
			resources.Variables.status = "Banking...";
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
			if (!Inventory.contains(resources.Variables.HUNTER_KIT)
					&& Bank.isOpen()
					&& Bank.getItem(resources.Variables.HUNTER_KIT) != null) {
				Bank.withdraw(resources.Variables.HUNTER_KIT, 4);
			}

			Bank.close();
			while (t.isRunning()) {
				Task.sleep(1200, 1800);
			}
		}
	}

	// Opening
	public static class OpenKits extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.HUNTER_KIT) == 4
					&& resources.Variables.VARROCK_BANK.contains(Players
							.getLocal())
					&& resources.Variables.openKits == true;
		}

		@Override
		public void execute() {
			resources.Variables.status = "Opening Kits...";
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
