package nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class WineDrink {
	// Bank
	
	
	
	
	public static class BankWineOnStart extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount() == 0
					&& resources.Variables.VARROCK_BANK.contains(Players
							.getLocal().getLocation())
					&& SceneEntities
							.getLoaded(resources.Variables.VARROCK_BANKER) != null
					&& resources.Variables.drinkWine == true;
		}

		@Override
		public void execute() {
			resources.Variables.status = "Banking...";
			NPC banker = NPCs.getNearest(resources.Variables.VARROCK_BANKER);
			if (banker.isOnScreen()) {
				Bank.open();
				Timer t = new Timer(1750);
				while (t.isRunning()) {
					Task.sleep(1500, 2000);
				}
				if (Bank.isOpen()) {
					Bank.withdraw(resources.Variables.FULL_WINE, 28);
					Timer ti = new Timer(1750);
					while (ti.isRunning()) {
						Task.sleep(1500, 2000);
					}
				}
				Bank.close();
			}
		}
	}
	
	
	
	
	
	public static class BankWine extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.JUG) == 28
					&& resources.Variables.VARROCK_BANK.contains(Players
							.getLocal().getLocation())
					&& SceneEntities
							.getLoaded(resources.Variables.VARROCK_BANKER) != null
					&& resources.Variables.drinkWine == true;
		}

		@Override
		public void execute() {
			resources.Variables.status = "Banking...";
			NPC banker = NPCs.getNearest(resources.Variables.VARROCK_BANKER);
			if (banker.isOnScreen()) {
				Bank.open();
				Timer t = new Timer(1750);
				while (t.isRunning()) {
					Task.sleep(1500, 2000);
				}
				if (Bank.isOpen()) {
					Bank.deposit(resources.Variables.JUG, 28);
					if (!Inventory.contains(resources.Variables.FULL_WINE)
							&& Bank.isOpen()
							&& Bank.getItem(resources.Variables.FULL_WINE) != null) {
						Bank.withdraw(resources.Variables.FULL_WINE, 28);
					}
					Bank.close();
				}
			}
		}

	}

	// Drink
	public static class DrinkWine extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.FULL_WINE) == 28
					&& resources.Variables.VARROCK_BANK.contains(Players
							.getLocal().getLocation())
					&& resources.Variables.drinkWine == true
					&& Players.getLocal().isIdle();
		}

		@Override
		public void execute() {
			resources.Variables.status = "Drinking Wine";
			if (resources.Variables.VARROCK_BANK.contains(Players.getLocal()
					.getLocation()) && Widgets.get(548).getChild(172).visible()) {
				for (int x = 0; x < 28; x++) {
					Widgets.get(679, 0).getChild(x)
							.interact("Drink", "Jug of wine");
					Timer t = new Timer(2500);
					while (t.isRunning()) {
						Task.sleep(2300, 2800);
					}
				}
			}
		}
	}
}
