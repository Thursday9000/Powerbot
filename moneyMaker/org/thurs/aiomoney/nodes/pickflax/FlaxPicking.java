package org.thurs.aiomoney.nodes.pickflax;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.thurs.aiomoney.resources.Variables;

public class FlaxPicking extends Node {
	Variables var = new Variables();

	@Override
	public boolean activate() {
		return Inventory.getCount() < 28 && Players.getLocal().isIdle()
				&& var.FLAX_AREA.contains(Players.getLocal())
				&& SceneEntities.getLoaded(var.FLAX_ID) != null;
	}

	@Override
	public void execute() {
		int flaxCount = Inventory.getCount(var.FLAX_ID);
		SceneObject flax = SceneEntities.getNearest(new Filter<SceneObject>() {

			public boolean accept(SceneObject e) {
				return e.getId() == var.FLAX_ID;
			}

		});
		if (flax != null) {
			if (flax.isOnScreen()) {
				flax.interact("Pick", "Flax");
				Timer t = new Timer(1850);
				while (t.isRunning()
						&& Inventory.getCount(var.FLAX_ID) <= flaxCount) {
					Task.sleep(17);
				}
			} else {
				Camera.turnTo(flax);
			}
		}
		var.status = "Picking Flax...";
	}
}