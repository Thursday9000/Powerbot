package nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;

public class UnicornKill {
	// Kill them all!
	public static class KillUnicorns extends Node {

		@Override
		public boolean activate() {
			return NPCs.getNearest(resources.Variables.UNICORN_ID) != null
					&& resources.Variables.UNICORN_AREA.contains(Players
							.getLocal())
					&& resources.Variables.unicornKill == true;
		}

		@Override
		public void execute() {

			if (!Players.getLocal().isInCombat()) {
				resources.Variables.method = "Killing Unicorns...";
				NPC[] unicorns = NPCs.getLoaded(resources.Variables.UNICORN_ID);
				for (int i = 0; i < unicorns.length - 1; i++) {
					if (unicorns[i].isInCombat()) {
						unicorns[i] = null;
					}
				}
				NPC unicorn = NPCs.getNearest(resources.Variables.UNICORN_ID);
				if (unicorn != null)
					if (!unicorn.isOnScreen()) {
						Camera.turnTo(unicorn);
						Camera.setPitch(false);
					} else {
						resources.Variables.method = "Killing Unicorns...";
						unicorn.interact("Attack", "Unicorn");
						Timer t = new Timer(3100);
						while (t.isRunning()) {
							Task.sleep(3000, 3200);
						}
					}
			}
			if (GroundItems.getLoaded(resources.Variables.HORN_ID) != null
					&& resources.Variables.UNICORN_AREA.contains(Players
							.getLocal()) && Players.getLocal().isIdle()) {
				Camera.setPitch(32);
				GroundItem horn = GroundItems
						.getNearest(new Filter<GroundItem>() {

							public boolean accept(GroundItem e) {
								return e.getId() == resources.Variables.HORN_ID;
							}

						});
				if (horn != null) {
					if (horn.isOnScreen()) {
						resources.Variables.status = "Picking up Horns...";
						Camera.turnTo(horn);
						horn.interact("Take", "Unicorn horn");
						Timer t = new Timer(2000);
						while (t.isRunning()) {
							Task.sleep(1750, 2500);
						}
					} else {
						Camera.turnTo(horn);
					}
				}
			}
		}
	}

	// Walking
	public static class BankToUnicorns extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.HORN_ID) == 0
					&& resources.Variables.unicornKill == true
					&& !resources.Variables.UNICORN_AREA.contains(Players
							.getLocal());
		}

		@Override
		public void execute() {
			resources.Variables.status = "Walking...";
			Walking.newTilePath(resources.Variables.BANK_TO_UNICORNS)
					.traverse();
			{
			}
		}

	}

	public static class UnicornsToBank extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.HORN_ID) == 28
					&& resources.Variables.unicornKill == true;

		}

		@Override
		public void execute() {
			resources.Variables.status = "Walking...";
			Walking.newTilePath(resources.Variables.UNICORNS_TO_BANK)
					.traverse();
			{
			}
			Mouse.click(true);
			Task.sleep(100);
		}

	}

	public static class WalkBackOnDeath extends Node {

		@Override
		public boolean activate() {
			return !resources.Variables.UNICORN_AREA.contains(Players
					.getLocal())
					&& !resources.Variables.HORN_BANK.contains(Players
							.getLocal())
					&& resources.Variables.unicornKill == true;
		}

		@Override
		public void execute() {
			Walking.newTilePath(resources.Variables.LUMBY_TO_UNICORNS)
					.traverse();
		}

	}

	// Banking
	public static class BankHorns extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.HORN_ID) >= 28
					&& SceneEntities.getLoaded(resources.Variables.LUMBY_BOX) != null
					&& resources.Variables.unicornKill == true;
		}

		@Override
		public void execute() {
			resources.Variables.status = "Banking...";
			Bank.open();
			Timer t = new Timer(4500);
			while (t.isRunning()) {
				Task.sleep(3000, 6000);
			}
			if (Bank.isOpen()) {
				Bank.depositInventory();
			}
			Bank.close();

		}

	}

}
