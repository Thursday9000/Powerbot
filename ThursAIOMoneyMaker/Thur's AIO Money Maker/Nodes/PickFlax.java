package Nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class PickFlax {
	// Pickup Flax

	public static class FlaxPicking extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount() < 28
					&& Players.getLocal().isIdle()
					&& Resources.Variables.FLAX_AREA.contains(Players
							.getLocal().getLocation())
					&& SceneEntities.getLoaded(Resources.Variables.FLAX_ID) != null
					&& Resources.Variables.flaxPicking == true;
		}

		@Override
		public void execute() {
			SceneObject flax = SceneEntities
					.getNearest(new Filter<SceneObject>() {

						public boolean accept(SceneObject e) {
							return e.getId() == Resources.Variables.FLAX_ID;
						}

					});
			if (flax != null) {
				if (flax.isOnScreen()) {
					flax.interact("Pick", "Flax");
					Timer t = new Timer(1850);
					while (t.isRunning()) {
						Task.sleep(1750, 2000);
					}
				} else {
					Camera.turnTo(flax);
				}
			}
			Resources.Variables.status = "Picking Flax...";
		}
	}

	// Banking
	public static class FlaxBanking extends Node {

		@Override
		public boolean activate() {

			return Inventory.getCount() == 28
					&& Resources.Variables.SEERS_BANK_AREA.contains(Players
							.getLocal().getLocation())
					&& SceneEntities
							.getLoaded(Resources.Variables.SEERS_BANKER) != null
					&& Resources.Variables.flaxPicking == true;
		}

		@Override
		public void execute() {

			NPC banker = NPCs.getNearest(Resources.Variables.SEERS_BANKER);
			if (banker.isOnScreen()) {
				Resources.Variables.status = "Banking...";
				Bank.open();
				Timer t = new Timer(1750);
				while (t.isRunning()) {
					Task.sleep(1500, 2000);
				}
				if (Bank.isOpen()) {
					Bank.deposit(Resources.Variables.INV_FLAX, 28);
				}
				Bank.close();
			}
		}

	}

	// Walk to bank
	public static void walkToBank() {
		Walking.newTilePath(Resources.Variables.FLAX_TO_BANK).traverse();
	}

	public static class ToBank extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount() == 28
					&& !Resources.Variables.SEERS_BANK_AREA.contains(Players
							.getLocal().getLocation())
					&& Resources.Variables.flaxPicking == true;
		}

		@Override
		public void execute() {
			walkToBank();
			Resources.Variables.status = "Walking to bank...";

		}

	}

	// Walk to flax
	public static void walkToFlax() {
		Walking.newTilePath(Resources.Variables.BANK_TO_FLAX).traverse();
	}

	public static class ToFlax extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount() == 0
					&& !Resources.Variables.FLAX_AREA.contains(Players
							.getLocal().getLocation())
					&& Resources.Variables.flaxPicking == true;
		}

		@Override
		public void execute() {
			walkToFlax();
			Resources.Variables.status = "Walking to flax...";
		}

	}

	public static class GlitchFix extends Node {

		@Override
		public boolean activate() {
			return Resources.Variables.GLITCH_AREA.contains(Players.getLocal()
					.getLocation());
		}

		@Override
		public void execute() {
			Walking.newTilePath(Resources.Variables.GLITCH_TO_BANK).traverse();
		}

	}
}
