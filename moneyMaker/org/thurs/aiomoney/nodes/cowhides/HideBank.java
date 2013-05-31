package org.thurs.aiomoney.nodes.cowhides;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.DepositBox;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.thurs.aiomoney.resources.Variables;

//Bank
public class HideBank extends Node {
	Variables var = new Variables();

	@Override
	public boolean activate() {
		return Inventory.getCount(var.COW_HIDES) == 28
				&& SceneEntities.getLoaded(var.LUMBY_BOX) != null;
	}

	@Override
	public void execute() {
		SceneObject box = SceneEntities.getNearest(var.LUMBY_BOX);
		if (box.isOnScreen()) {
			var.status = "Banking...";
			box.interact("Deposit");
			Timer t = new Timer(3000);
			while (t.isRunning()) {
				Task.sleep(10);
			}
			if (DepositBox.isOpen()) {
				DepositBox.depositInventory();
				Timer ti = new Timer(1490);
				while (ti.isRunning() && Inventory.getCount() == 0) {
					Task.sleep(10);
				}
				DepositBox.close();
			}
		} else {
			Camera.turnTo(box);
			Camera.setPitch(3);

		}
	}
}