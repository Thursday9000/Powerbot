package nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;

public class DrinkTheBrews {
	// Bank
	public static class BankBrewsOnStart extends Node {

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
				Bank.withdraw(resources.Variables.SARA_BREW_FULL, 28);
				Timer ti = new Timer(1750);
				while (ti.isRunning()) {
					Task.sleep(1500, 2000);
				}
			}
			Bank.close();
		}
	}

	public static class BankBrews extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.SARA_BREW_DRANK) == 28
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
				Bank.deposit(resources.Variables.SARA_BREW_DRANK, 28);
				if (!Inventory.contains(resources.Variables.FULL_WINE)
						&& Bank.isOpen()
						&& Bank.getItem(resources.Variables.SARA_BREW_FULL) != null) {
					Bank.withdraw(resources.Variables.SARA_BREW_FULL, 28);
					Bank.close();
				}
			}
		}
	}

	// Drink
	public static class DrinkBrews extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.SARA_BREW_FULL) == 28
					&& resources.Variables.VARROCK_BANK.contains(Players
							.getLocal())
					&& resources.Variables.drinkBrews == true
					&& Players.getLocal().isIdle();
		}

		@Override
		public void execute() {
			if (resources.Variables.VARROCK_BANK.contains(Players.getLocal())
					&& Widgets.get(548).getChild(172).visible()) {
				for (int x = 0; x < 28; x++) {
					Widgets.get(679, 0).getChild(x)
							.interact("Drink", "Saradomin brew (2)");
					Timer t = new Timer(1750);
					while (t.isRunning()) {
						Task.sleep(1500, 2000);
					}
				}
			}
			resources.Variables.status = "Drinking brews...";
		}

	}

}
