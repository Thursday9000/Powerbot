package org.thurs.aiomoney.nodes.spinflax;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class ToSpinner extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Inventory.getCount(var.INV_FLAX) == 27
				&& var.SEERS_BANK_AREA.contains(Players
						.getLocal());
	}

	@Override
	public void execute() {
		SceneEntities.getNearest(var.CLOSED_DOOR).interact(
				"Open", "Door");
		Timer t = new Timer(1740);
		while (t.isRunning()
				&& SceneEntities.getNearest(var.CLOSED_DOOR) == null) {
			Task.sleep(15);
		}
		Walking.newTilePath(var.BANK_TO_SPINNER).traverse();
		var.status = "Opening door...";
	}

}
