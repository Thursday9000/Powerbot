package nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class SpinFlax {
	// Withdraw Flax On Start
	public static class Withdraw extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount() == 0
					&& resources.Variables.spinFlax == true
					&& resources.Variables.SEERS_BANK_AREA.contains(Players
							.getLocal().getLocation())
					&& SceneEntities
							.getLoaded(resources.Variables.SEERS_BANKER) != null;
		}

		@Override
		public void execute() {
			NPC banker = NPCs.getNearest(resources.Variables.SEERS_BANKER);
			if (banker.isOnScreen()) {
				resources.Variables.status = "Banking...";
				Bank.open();
				if (Bank.isOpen()) {
					Timer t = new Timer(1350);
					while (t.isRunning()) {
						Task.sleep(1500, 2000);
					}
					Bank.withdraw(resources.Variables.INV_FLAX, 27);
					while (t.isRunning()) {
						Task.sleep(1500, 2000);
					}
				}
			}
		}
	}

	// Spinning
	public static class Spinning extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.INV_FLAX) > 24
					&& SceneEntities.getLoaded(resources.Variables.SPINNER) != null
					&& resources.Variables.spinFlax == true;
		}

		@Override
		public void execute() {
			resources.Variables.status = "Spinning...";
			Camera.setPitch(3);
			SceneObject spinner = SceneEntities
					.getNearest(resources.Variables.SPINNER);
			if (spinner.isOnScreen() && SceneEntities.getLoaded(resources.Variables.SPINNER) != null) {
				spinner.interact("Spin", "Spinning wheel");
				Timer t = new Timer(1500);
				while (t.isRunning()) {
					Task.sleep(2000, 3000);
				}
				if (Widgets.get(1370).getChild(38).visible()) {
					Widgets.get(1370).getChild(38)
							.interact("Make 27 Bow string");
					Timer ti = new Timer(40000);
					while (ti.isRunning()) {
						Task.sleep(40000, 45000);
					}
				}
			} else {
				Camera.turnTo(spinner);
			}
		}
	}

	// Banking
	public static class StringBanking extends Node {

		@Override
		public boolean activate() {

			return Inventory.getCount(resources.Variables.BOW_STRING) == 27
					&& resources.Variables.SEERS_BANK_AREA.contains(Players
							.getLocal().getLocation())
					&& SceneEntities
							.getLoaded(resources.Variables.SEERS_BANKER) != null
					&& resources.Variables.spinFlax == true;
		}

		@Override
		public void execute() {

			NPC banker = NPCs.getNearest(resources.Variables.SEERS_BANKER);
			if (banker.isOnScreen()) {
				resources.Variables.status = "Banking...";
				Bank.open();
				Timer t = new Timer(5000);
				while (t.isRunning()) {
					Task.sleep(3000, 6000);
				}
				if (Bank.isOpen()) {
					Bank.depositInventory();

					Bank.withdraw(resources.Variables.INV_FLAX, 27);
					Timer ti = new Timer(1350);
					while (ti.isRunning()) {
						Task.sleep(1000, 1500);
					}
				}
			} else {
				Camera.setPitch(3);
				Camera.turnTo(banker);

			}
		}
	}

	// Walk to bank
	public static void WalkToBank1() {
		Walking.newTilePath(resources.Variables.SPINNER_TO_BANK).traverse();
	}

	public static class ToBank1 extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.BOW_STRING) == 27
					&& resources.Variables.SPINNER_AREA.contains(Players
							.getLocal().getLocation())
					&& resources.Variables.spinFlax == true;
		}

		@Override
		public void execute() {
			SceneEntities.getNearest(resources.Variables.CLOSED_DOOR).interact(
					"Open", "Door");
			Timer t = new Timer(1500);
			while (t.isRunning()) {
				Task.sleep(1000, 2000);
			}
			WalkToBank1();
			Timer ti = new Timer(750);
			while (ti.isRunning()) {
				Task.sleep(500, 1000);
			}
			resources.Variables.status = "Walking to bank...";

		}
	}

	// Walk To Spinner

	public static void walkToSpinner() {
		Walking.newTilePath(resources.Variables.BANK_TO_SPINNER).traverse();
	}

	public static class ToSpinner extends Node {
		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.INV_FLAX) == 27
					&& resources.Variables.SEERS_BANK_AREA.contains(Players
							.getLocal().getLocation())
					&& resources.Variables.spinFlax == true;
		}

		@Override
		public void execute() {
			SceneEntities.getNearest(resources.Variables.CLOSED_DOOR).interact(
					"Open", "Door");
			Timer t = new Timer(1740);
			while (t.isRunning()) {
				Task.sleep(1500, 2000);
			}
			walkToSpinner();
			resources.Variables.status = "Opening door...";
		}

	}

	// Ladders
	public static class ClimbLadder extends Node {

		@Override
		public boolean activate() {
			return SceneEntities.getLoaded(25938) != null
					&& SceneEntities.getLoaded(25819) != null
					&& Inventory.getCount(resources.Variables.INV_FLAX) == 27
					&& resources.Variables.spinFlax == true
					&& resources.Variables.SPINNER_AREA.contains(Players
							.getLocal().getLocation());
		}

		@Override
		public void execute() {

			SceneEntities.getNearest(resources.Variables.LADDER_ID).interact(
					"Climb-up", "Ladder");
			Timer t = new Timer(2350);
			while (t.isRunning()) {
				Task.sleep(2000, 2500);
			}
			resources.Variables.status = "Climbing ladder...";
		}
	}

	public static class ClimbDownLadder extends Node {

		@Override
		public boolean activate() {
			return resources.Variables.spinFlax == true
					&& Inventory.getCount(resources.Variables.BOW_STRING) == 27
					&& SceneEntities.getLoaded(25939) != null;
		}

		@Override
		public void execute() {
			Camera.setPitch(93);
			SceneEntities.getNearest(resources.Variables.LADDER_ID2).interact(
					"Climb-down", "Ladder");
			Timer t = new Timer(2350);
			while (t.isRunning()) {
				Task.sleep(2000, 2500);
				resources.Variables.status = "Climbing ladder...";
			}

		}

		public static class GlitchFix extends Node {

			@Override
			public boolean activate() {

				return resources.Variables.GLITCH_AREA.contains(Players
						.getLocal().getLocation())
						&& Inventory.getCount() == 27
						&& resources.Variables.spinFlax == true;
			}

			@Override
			public void execute() {
				Walking.newTilePath(resources.Variables.GLITCH_TO_BANK)
						.traverse();
				Timer t = new Timer(5350);
				while (t.isRunning()) {
					Task.sleep(5000, 5500);
				}
			}
		}
	}
}
