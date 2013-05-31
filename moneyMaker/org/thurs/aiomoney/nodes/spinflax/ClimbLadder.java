package org.thurs.aiomoney.nodes.spinflax;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;

public class ClimbLadder extends Node {

	@Override
	public boolean activate() {
		return SceneEntities.getLoaded(25938) != null
				&& SceneEntities.getLoaded(25819) != null
				&& Inventory.getCount(org.thurs.aiomoney.resources.Variables.INV_FLAX) == 27
				&& org.thurs.aiomoney.resources.Variables.spinFlax
				&& org.thurs.aiomoney.resources.Variables.SPINNER_AREA.contains(Players
						.getLocal());
	}

	@Override
	public void execute() {

		SceneEntities.getNearest(org.thurs.aiomoney.resources.Variables.LADDER_ID).interact(
				"Climb-up", "Ladder");
		Timer t = new Timer(2350);
		while (t.isRunning()
				&& SceneEntities.getNearest(org.thurs.aiomoney.resources.Variables.LADDER_ID) == null) {
			Task.sleep(20);
		}
		org.thurs.aiomoney.resources.Variables.status = "Climbing ladder...";
	}
}
