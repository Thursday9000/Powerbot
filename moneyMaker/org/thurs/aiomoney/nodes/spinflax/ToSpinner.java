package org.thurs.aiomoney.nodes.spinflax;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;

public class ToSpinner extends Node {
	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.INV_FLAX) == 27
				&& org.thurs.aiomoney.resources.Variables.SEERS_BANK_AREA.contains(Players
						.getLocal()) && org.thurs.aiomoney.resources.Variables.spinFlax;
	}

	@Override
	public void execute() {
		SceneEntities.getNearest(org.thurs.aiomoney.resources.Variables.CLOSED_DOOR).interact(
				"Open", "Door");
		Timer t = new Timer(1740);
		while (t.isRunning()
				&& SceneEntities.getNearest(org.thurs.aiomoney.resources.Variables.CLOSED_DOOR) == null) {
			Task.sleep(15);
		}
		Walking.newTilePath(org.thurs.aiomoney.resources.Variables.BANK_TO_SPINNER).traverse();
		org.thurs.aiomoney.resources.Variables.status = "Opening door...";
	}

}
