package org.thurs.aiomoney.nodes.cowhides;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;

public class WalkToHides extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.COW_HIDES) == 0
				&& org.thurs.aiomoney.resources.Variables.pickupHides;
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Walking...";
		Walking.newTilePath(org.thurs.aiomoney.resources.Variables.DEPOSITBOX_TO_HIDES)
				.traverse();
		{
			if (SceneEntities.getLoaded(45212) != null
					&& org.thurs.aiomoney.resources.Variables.COW_AREA.contains(Players
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
