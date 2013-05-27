package nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.DepositBox;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class GrabFeathers {

	public static class PickupFeathers extends Node {

		@Override
		public boolean activate() {
			return Players.getLocal().isIdle()
					&& resources.Variables.FEATHER_AREA.contains(Players
							.getLocal())
					&& GroundItems.getLoaded(resources.Variables.FEATHER_ID) != null
					&& resources.Variables.pickupFeathers == true;
		}

		@Override
		public void execute() {
			resources.Variables.status = "Taking Feathers...";
			GroundItem feathers = GroundItems
					.getNearest(new Filter<GroundItem>() {

						public boolean accept(GroundItem e) {
							return e.getId() == resources.Variables.FEATHER_ID;
						}

					});
			if (feathers != null) {
				if (feathers.isOnScreen()) {
					feathers.interact("Take", "Feather");
					Timer t = new Timer(1850);
					while (t.isRunning()) {
						Task.sleep(1750, 2000);
					}
				} else {
					Camera.turnTo(feathers);
				}
			}
		}
	}

	// Bank
	public static class FeatherBank extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.FEATHER_ID) == 28
					&& SceneEntities.getLoaded(resources.Variables.LUMBY_BOX) != null
					&& resources.Variables.pickupFeathers == true;
		}

		@Override
		public void execute() {
			SceneObject box = SceneEntities
					.getNearest(resources.Variables.LUMBY_BOX);
			if (box.isOnScreen()) {
				resources.Variables.status = "Banking...";
				box.interact("Deposit");
				Timer t = new Timer(5000);
				while (t.isRunning()) {
					Task.sleep(3000, 6000);
				}
				if (DepositBox.isOpen()) {
					Timer ti = new Timer(1500);
					while (ti.isRunning()) {
						Task.sleep(1000, 2000);
					}
					DepositBox.close();
				}
			} else {
				Camera.turnTo(box);
				Camera.setPitch(3);

			}
		}
	}

	public static class WalkToFeathers extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.FEATHER_ID) == 0
					&& resources.Variables.pickupFeathers == true;
		}

		@Override
		public void execute() {
			resources.Variables.status = "Walking...";
			Walking.newTilePath(resources.Variables.BANK_TO_FEATHERS)
					.traverse();
			{
				if (SceneEntities.getLoaded(45206) != null
						&& resources.Variables.FEATHER_AREA.contains(Players
								.getLocal())) {
					SceneEntities.getNearest(45206).interact("Open", "Gate");
					Timer t = new Timer(1350);
					while (t.isRunning()) {
						Task.sleep(1000, 1500);
					}
				}
			}
		}
	}

	public static class FeathersToBank extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.FEATHER_ID) == 28
					&& resources.Variables.pickupFeathers == true;

		}

		@Override
		public void execute() {
			resources.Variables.status = "Walking...";
			Walking.newTilePath(resources.Variables.FEATHER_TO_BANK).traverse();
			{
				if (SceneEntities.getLoaded(45206) != null
						&& resources.Variables.FEATHER_AREA.contains(Players
								.getLocal())) {
					SceneEntities.getNearest(45206).interact("Open", "Gate");
					Timer t = new Timer(1350);
					while (t.isRunning()) {
						Task.sleep(1000, 1500);
					}
				}
			}
		}
	}
}
