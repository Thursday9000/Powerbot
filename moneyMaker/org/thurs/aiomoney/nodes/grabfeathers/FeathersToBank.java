package org.thurs.aiomoney.nodes.grabfeathers;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;

public class FeathersToBank extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.FEATHER_ID) == 28
				&& org.thurs.aiomoney.resources.Variables.pickupFeathers;

	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Walking...";
		Walking.newTilePath(org.thurs.aiomoney.resources.Variables.FEATHER_TO_BANK).traverse();
		{
			if (SceneEntities.getLoaded(45206) != null
					&& org.thurs.aiomoney.resources.Variables.FEATHER_AREA.contains(Players
							.getLocal())) {
				SceneEntities.getNearest(45206).interact("Open", "Gate");
				Timer t = new Timer(1350);
				while (t.isRunning() && SceneEntities.getLoaded(45206) == null) {
					Task.sleep(10);
				}
			}
		}
	}
}