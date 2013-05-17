package Nodes;

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

public class CowHides {

	// Pickup
	public static class PickupHides extends Node {

		@Override
		public boolean activate() {
			return Resources.Variables.COW_AREA.contains(Players.getLocal()
					.getLocation())
					&& Players.getLocal().isIdle()
					&& GroundItems.getLoaded(Resources.Variables.COW_HIDES) != null
					&& Inventory.getCount(Resources.Variables.COW_HIDES) < 29
					&& Resources.Variables.pickupHides == true;
		}

		@Override
		public void execute() {
			Camera.setPitch(32);
			GroundItem hide = GroundItems.getNearest(new Filter<GroundItem>() {

				public boolean accept(GroundItem e) {
					return e.getId() == Resources.Variables.COW_HIDES;
				}
			});
			if (hide != null) {
				if (hide.isOnScreen()) {
					Camera.turnTo(hide);
					hide.interact("Take", "Cowhide");
					{
						Timer t = new Timer(299);
						while (t.isRunning()) {
							Task.sleep(1750, 2500);
						}
					}
				} else {
					Camera.turnTo(hide);
				}
			}
			Resources.Variables.status = "Picking up cowhides...";
		}
	}

	// Bank
	public static class HideBank extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(Resources.Variables.COW_HIDES) == 28
					&& SceneEntities.getLoaded(Resources.Variables.LUMBY_BOX) != null
					&& Resources.Variables.pickupHides == true;
		}

		@Override
		public void execute() {
			SceneObject box = SceneEntities
					.getNearest(Resources.Variables.LUMBY_BOX);
			if (box.isOnScreen()) {
				Resources.Variables.status = "Banking...";
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

	public static class WalkToHides extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(Resources.Variables.COW_HIDES) == 0
					&& Resources.Variables.pickupHides == true;
		}

		@Override
		public void execute() {
			Resources.Variables.status = "Walking...";
			Walking.newTilePath(Resources.Variables.DEPOSITBOX_TO_HIDES)
					.traverse();
			{
				if (SceneEntities.getLoaded(45212) != null
						&& Resources.Variables.COW_AREA.contains(Players
								.getLocal().getLocation())) {
					SceneEntities.getNearest(45212).interact("Open", "Gate");
					Timer t = new Timer(1350);
					while (t.isRunning()) {
						Task.sleep(1000, 1500);
					}
				}
			}
		}
	}

	public static class HidesToBank extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(Resources.Variables.COW_HIDES) == 28
					&& Resources.Variables.pickupHides == true;

		}

		@Override
		public void execute() {
			Resources.Variables.status = "Walking...";
			Walking.newTilePath(Resources.Variables.HIDES_TO_DEPOSITBOX)
					.traverse();
			{
				if (SceneEntities.getLoaded(45212) != null
						&& Resources.Variables.COW_AREA.contains(Players
								.getLocal().getLocation())) {
					SceneEntities.getNearest(45212).interact("Open", "Gate");
					Timer t = new Timer(1350);
					while (t.isRunning()) {
						Task.sleep(1000, 1500);
					}
				}
			}
		}
	}

	public static class AntiBan extends Node {

		@Override
		public boolean activate() {
			return Resources.Variables.pickupHides == true
					&& Resources.Variables.COW_HIDE_ANTI_BAN.contains(Players
							.getLocal().getLocation());
		}

		@Override
		public void execute() {
			Walking.newTilePath(Resources.Variables.ANTI_BAN_WALK).traverse();
		}
	}
}