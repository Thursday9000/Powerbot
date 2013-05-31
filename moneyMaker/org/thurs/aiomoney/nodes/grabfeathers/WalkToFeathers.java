package org.thurs.aiomoney.nodes.grabfeathers;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class WalkToFeathers extends Node {
	Variables var = new Variables();

	@Override
	public boolean activate() {
		return Inventory.getCount(var.FEATHER_ID) == 0;
	}

	@Override
	public void execute() {
		var.status = "Walking...";
		Walking.newTilePath(var.BANK_TO_FEATHERS).traverse();
		{
			if (SceneEntities.getLoaded(45206) != null
					&& var.FEATHER_AREA.contains(Players.getLocal())) {
				SceneEntities.getNearest(45206).interact("Open", "Gate");
				Timer t = new Timer(1350);
				while (t.isRunning() && SceneEntities.getLoaded(45206) == null) {
					Task.sleep(10);
				}
			}
		}
	}
}