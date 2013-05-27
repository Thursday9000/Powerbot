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

public class GrabBones {

	// pickup
	public static class PickBones extends Node {

		@Override
		public boolean activate() {
			return resources.Variables.BONE_AREA.contains(Players.getLocal()
					)
					&& Players.getLocal().isIdle()
					&& GroundItems.getLoaded(resources.Variables.BONE_ID) != null
					&& Inventory.getCount(resources.Variables.BONE_ID) < 28
					&& resources.Variables.pickupBones == true;
		}

		@Override
		public void execute() {
			Camera.setPitch(32);
			GroundItem bones = GroundItems.getNearest(new Filter<GroundItem>() {

				public boolean accept(GroundItem e) {
					return e.getId() == resources.Variables.BONE_ID;
				}

			});
			if (bones != null) {
				if (bones.isOnScreen()) {
					Camera.turnTo(bones);
					bones.interact("Take", "Bones");
					Timer t = new Timer(1000);
					while (t.isRunning()) {
						Task.sleep(1000, 1500);
					}

				} else {
					Camera.turnTo(bones);
				}
			}
			resources.Variables.status = "Picking up bones...";
		}

	}

	// Bank
	public static class BoneBank extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.BONE_ID) == 28
					&& SceneEntities.getLoaded(resources.Variables.LUMBY_BOX) != null
					&& resources.Variables.pickupBones == true;
		}

		@Override
		public void execute() {
			SceneObject box = SceneEntities
					.getNearest(resources.Variables.LUMBY_BOX);
			if (box.isOnScreen()) {
				resources.Variables.status = "Banking...";
				box.interact("Deposit");
				Timer t = new Timer(1000);
				while (t.isRunning()) {
					Task.sleep(1000, 1500);
				}
				if (DepositBox.isOpen()) {
					DepositBox.depositInventory();
					while (t.isRunning()) {
						Task.sleep(1000, 1500);
					}
					DepositBox.close();
				}
			} else {
				Camera.turnTo(box);
				Camera.setPitch(3);

			}

		}

	}

	public static class WalkToBones extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.BONE_ID) == 0
					&& resources.Variables.pickupBones == true;
		}

		@Override
		public void execute() {
			Walking.newTilePath(resources.Variables.BOX_TO_BONES).traverse();
			resources.Variables.status = "Walking...";
		}

	}

	public static class WalkToLadder extends Node {

		@Override
		public boolean activate() {
			return Inventory.getCount(resources.Variables.BONE_ID) == 28
					&& resources.Variables.pickupBones == true;
		}

		@Override
		public void execute() {
			Walking.newTilePath(resources.Variables.BONES_TO_BOX).traverse();
			resources.Variables.status = "Walking...";
		}

	}

	public static class FixDaGlitch extends Node {

		@Override
		public boolean activate() {
			return resources.Variables.BONE_COW_AREA.contains(Players
					.getLocal())
					&& resources.Variables.pickupBones == true;
		}

		@Override
		public void execute() {
			Walking.newTilePath(resources.Variables.BONE_COW_FIX).traverse();
			if (SceneEntities.getLoaded(45212) != null
					&& resources.Variables.COW_AREA.contains(Players
							.getLocal())) {
				SceneEntities.getNearest(45212).interact("Open", "Gate");
				Timer t = new Timer(1350);
				while (t.isRunning()) {
					Task.sleep(1000, 1500);
				}
			}
		}

	}
}
