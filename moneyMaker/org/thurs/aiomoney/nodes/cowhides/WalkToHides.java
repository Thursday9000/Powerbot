package org.thurs.aiomoney.nodes.cowhides;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class WalkToHides extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Inventory.getCount(var.COW_HIDES) == 0
				&& var.pickupHides;
	}

	@Override
	public void execute() {
		var.status = "Walking...";
		Walking.newTilePath(var.DEPOSITBOX_TO_HIDES)
				.traverse();
		{
			if (SceneEntities.getLoaded(45212) != null
					&& var.COW_AREA.contains(Players
							.getLocal())) {
				SceneEntities.getNearest(45212).interact("Open", "Gate");
				Timer t = new Timer(1340);
				while (t.isRunning()
						&& SceneEntities.getLoaded(45212) == null) {
					Task.sleep(10);
				}
			}
		}
	}
}
