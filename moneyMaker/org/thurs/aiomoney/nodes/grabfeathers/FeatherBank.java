package org.thurs.aiomoney.nodes.grabfeathers;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.DepositBox;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.thurs.aiomoney.resources.Variables;

public class FeatherBank extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Inventory.getCount(var.FEATHER_ID) == 28
				&& SceneEntities.getLoaded(var.LUMBY_BOX) != null
				&& var.pickupFeathers;
	}

	@Override
	public void execute() {
		SceneObject box = SceneEntities
				.getNearest(var.LUMBY_BOX);
		if (box.isOnScreen()) {
			var.status = "Banking...";
			box.interact("Deposit");
			Timer t = new Timer(1000);
			while (t.isRunning()) {
				Task.sleep(10);
			}

			DepositBox.close();

		} else {
			Camera.turnTo(box);
			Camera.setPitch(3);

		}
	}
}
