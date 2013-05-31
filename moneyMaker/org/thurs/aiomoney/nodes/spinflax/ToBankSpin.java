package org.thurs.aiomoney.nodes.spinflax;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class ToBankSpin extends Node {
	
	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.BOW_STRING) == 27
				&& Variables.SPINNER_AREA
						.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		SceneEntities.getNearest(Variables.CLOSED_DOOR).interact(
				"Open", "Door");
		Timer t = new Timer(1500);
		while (t.isRunning()
				&& SceneEntities.getNearest(Variables.CLOSED_DOOR) != null) {
			Task.sleep(10);
		}
		Walking.newTilePath(Variables.SPINNER_TO_BANK).traverse();
		Variables.status = "Walking to bank...";

	}
}
