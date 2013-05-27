package nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;

public class EatPie {
	// Bank

	public static class BankPiesOnStart extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount() == 0
					&& resources.Variables.VARROCK_BANK.contains(Players
							.getLocal())
					&& SceneEntities
							.getLoaded(resources.Variables.VARROCK_BANKER) != null
					&& resources.Variables.eatPie == true;
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
				Bank.withdraw(resources.Variables.APPLE_PIE, 28);
				Timer ti = new Timer(1750);
				while (ti.isRunning()) {
					Task.sleep(1500, 2000);
				}

				Bank.close();
			}
		}
	}

	public static class BankPie extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.PIE_DISH) < 25
					&& resources.Variables.VARROCK_BANK.contains(Players
							.getLocal())
					&& SceneEntities
							.getLoaded(resources.Variables.VARROCK_BANKER) != null
					&& resources.Variables.eatPie == true;
		}

		@Override
		public void execute() {
			resources.Variables.status = "Banking...";
			Bank.open();
			Timer t = new Timer(3350);
			while (t.isRunning()) {
				Task.sleep(3000, 3500);
			}
			if (Bank.isOpen()) {
				Bank.deposit(resources.Variables.PIE_DISH, 28);
				if (!Inventory.contains(resources.Variables.APPLE_PIE)
						&& Bank.isOpen()
						&& Bank.getItem(resources.Variables.APPLE_PIE) != null) {
					Bank.withdraw(resources.Variables.APPLE_PIE, 28);
				}
				Bank.close();
			}
		}
	}

	// Drink
	public static class EatThePie extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.APPLE_PIE) == 28
					&& resources.Variables.VARROCK_BANK.contains(Players
							.getLocal()) && resources.Variables.eatPie == true
					&& Players.getLocal().isIdle();
		}

		@Override
		public void execute() {
			resources.Variables.status = "Eating Pie";
			if (resources.Variables.VARROCK_BANK.contains(Players.getLocal())
					&& Widgets.get(548).getChild(172).visible()) {
				for (int x = 0; x < 28; x++) {
					Widgets.get(679, 0).getChild(x)
							.interact("Eat", "Apple Pie");
					Timer t = new Timer(1500);
					while (t.isRunning()) {
						Task.sleep(1200, 1800);
					}
					Widgets.get(679, 0).getChild(x)
							.interact("Eat", "Apple Pie");
					while (t.isRunning()) {
						Task.sleep(1200, 1800);
					}
				}
			}
		}
	}
}