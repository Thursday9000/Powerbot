package org.thurs.aiomoney.nodes.grabbones;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.methods.widget.DepositBox;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.thurs.aiomoney.resources.Variables;

public class BoneBank extends Node {
	Timer t = new Timer(1750);

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.BONE_ID) == 28
				&& SceneEntities.getLoaded(Variables.LUMBY_BOX) != null;
	}

	@Override
	public void execute() {
		SceneObject box = SceneEntities.getNearest(Variables.LUMBY_BOX);
		if (box.isOnScreen()) {
			Variables.status = "Banking...";
			box.interact("Deposit");
			while (t.isRunning() && !DepositBox.isOpen()) {
				Task.sleep(15);
			}
			if (DepositBox.isOpen()) {
				DepositBox.depositInventory();
				DepositBox.close();
			}
		} else {
			Camera.turnTo(box);
			Camera.setPitch(3);

		}

	}

}
