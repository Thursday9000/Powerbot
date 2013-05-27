package nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class PickFlax {
	// Pickup Flax

	public static class FlaxPicking extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount() < 28
					&& Players.getLocal().isIdle()
					&& resources.Variables.FLAX_AREA.contains(Players
							.getLocal())
					&& SceneEntities.getLoaded(resources.Variables.FLAX_ID) != null
					&& resources.Variables.flaxPicking == true;
		}

		@Override
		public void execute() {
			SceneObject flax = SceneEntities
					.getNearest(new Filter<SceneObject>() {

						public boolean accept(SceneObject e) {
							return e.getId() == resources.Variables.FLAX_ID;
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
			resources.Variables.status = "Picking Flax...";
		}
	}

	// Banking
	public static class FlaxBanking extends Node {

		@Override
		public boolean activate() {

			return Inventory.getCount() == 28
					&& resources.Variables.SEERS_BANK_AREA.contains(Players
							.getLocal())
					&& SceneEntities
							.getLoaded(resources.Variables.SEERS_BANKER) != null
					&& resources.Variables.flaxPicking == true;
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
				Bank.deposit(resources.Variables.INV_FLAX, 28);
			}
			Bank.close();
		}

	}

	// Walk to bank
	public static void walkToBank() {
		Walking.newTilePath(resources.Variables.FLAX_TO_BANK).traverse();
	}

	public static class ToBank extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount() == 28
					&& !resources.Variables.SEERS_BANK_AREA.contains(Players
							.getLocal())
					&& resources.Variables.flaxPicking == true;
		}

		@Override
		public void execute() {
			walkToBank();
			resources.Variables.status = "Walking to bank...";

		}

	}

	// Walk to flax
	public static void walkToFlax() {
		Walking.newTilePath(resources.Variables.BANK_TO_FLAX).traverse();
	}

	public static class ToFlax extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount() == 0
					&& !resources.Variables.FLAX_AREA.contains(Players
							.getLocal())
					&& resources.Variables.flaxPicking == true;
		}

		@Override
		public void execute() {
			walkToFlax();
			resources.Variables.status = "Walking to flax...";
		}

	}

	public static class GlitchFix extends Node {

		@Override
		public boolean activate() {
			return resources.Variables.GLITCH_AREA.contains(Players.getLocal());
		}

		@Override
		public void execute() {
			Walking.newTilePath(resources.Variables.GLITCH_TO_BANK).traverse();
		}

	}
}
