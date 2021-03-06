package org.thurs.aiomoney.nodes.cowhidekill;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class CowsToBank extends Node {
	

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.COW_HIDES) == 28;

	}

	@Override
	public void execute() {
		Variables.status = "Walking...";
		Walking.newTilePath(Variables.HIDES_TO_DEPOSITBOX).traverse();
		if (SceneEntities.getLoaded(45212) != null
				&& Variables.COW_AREA.contains(SceneEntities.getLoaded(45212))
				&& Variables.COW_AREA.contains(Players.getLocal())) {
			SceneEntities.getNearest(45212).interact("Open", "Gate");
			Timer t = new Timer(1340);
			while (t.isRunning() && SceneEntities.getLoaded(45212) != null) {
				Task.sleep(10);
			}
		}
	}

}