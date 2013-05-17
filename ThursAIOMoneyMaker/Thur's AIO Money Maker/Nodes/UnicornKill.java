package Nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
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
			return NPCs.getNearest(Resources.Variables.UNICORN_ID) != null
					&& Resources.Variables.UNICORN_AREA.contains(Players
							.getLocal().getLocation())
					&& Resources.Variables.unicornKill == true;
		}

		@Override
		public void execute() {

			if (!Players.getLocal().isInCombat()) {
				Resources.Variables.method = "Killing Unicorns...";
				NPC[] unicorns = NPCs.getLoaded(Resources.Variables.UNICORN_ID);
				for (int i = 0; i < unicorns.length - 1; i++) {
					if (unicorns[i].isInCombat()) {
						unicorns[i] = null;
					}
				}
				NPC unicorn = npcGetNearest(unicorns);
				if (unicorn != null)
					if (!unicorn.isOnScreen()) {
						Camera.turnTo(unicorn);
						Camera.setPitch(false);
					} else {
						Resources.Variables.method = "Killing Unicorns...";
						unicorn.interact("Attack", "Unicorn");
						Timer t = new Timer(3100);
						while (t.isRunning()) {
							Task.sleep(3000, 3200);
						}
					}
			}
			if (GroundItems.getLoaded(Resources.Variables.HORN_ID) != null
					&& Resources.Variables.UNICORN_AREA.contains(Players
							.getLocal().getLocation())
					&& Players.getLocal().isIdle()) {
				Camera.setPitch(32);
				GroundItem horn = GroundItems
						.getNearest(new Filter<GroundItem>() {

							public boolean accept(GroundItem e) {
								return e.getId() == Resources.Variables.HORN_ID;
							}

						});
				if (horn != null) {
					if (horn.isOnScreen()) {
						Resources.Variables.status = "Picking up Horns...";
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

	private static NPC npcGetNearest(NPC[] array) {
		int best = 0;
		double bestDistance = 100000;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] != null
					&& Calculations.distanceTo(array[i]) < bestDistance) {
				best = i;
				bestDistance = Calculations.distanceTo(array[i]);
			}
		}
		return array[best];
	}

	// Walking
	public static class BankToUnicorns extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(Resources.Variables.HORN_ID) == 0
					&& Resources.Variables.unicornKill == true
					&& !Resources.Variables.UNICORN_AREA.contains(Players
							.getLocal().getLocation());
		}

		@Override
		public void execute() {
			Resources.Variables.status = "Walking...";
			Walking.newTilePath(Resources.Variables.BANK_TO_UNICORNS)
					.traverse();
			{
			}
		}

	}

	public static class UnicornsToBank extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(Resources.Variables.HORN_ID) == 28
					&& Resources.Variables.unicornKill == true;

		}

		@Override
		public void execute() {
			Resources.Variables.status = "Walking...";
			Walking.newTilePath(Resources.Variables.UNICORNS_TO_BANK)
					.traverse();
			{
			}
		}

	}

	public static class WalkBackOnDeath extends Node {

		@Override
		public boolean activate() {
			return !Resources.Variables.UNICORN_AREA.contains(Players
					.getLocal().getLocation())
					&& !Resources.Variables.HORN_BANK.contains(Players
							.getLocal().getLocation())
					&& Resources.Variables.unicornKill == true;
		}

		@Override
		public void execute() {
			Walking.newTilePath(Resources.Variables.LUMBY_TO_UNICORNS)
					.traverse();
		}

	}

	// Banking
	public static class BankHorns extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(Resources.Variables.HORN_ID) >= 28
					&& SceneEntities.getLoaded(Resources.Variables.LUMBY_BOX) != null
					&& Resources.Variables.unicornKill == true;
		}

		@Override
		public void execute() {
			NPC banker = NPCs.getNearest(Resources.Variables.HORN_BANKER);
			if (banker.isOnScreen()) {
				Resources.Variables.status = "Banking...";
				Bank.open();
				Timer t = new Timer(4500);
				while (t.isRunning()) {
					Task.sleep(3000, 6000);
				}
				if (Bank.isOpen()) {
					Bank.depositInventory();
				}
				Bank.close();
			} else {
				Camera.turnTo(banker);
				Camera.setPitch(3);

			}

		}

	}

}
