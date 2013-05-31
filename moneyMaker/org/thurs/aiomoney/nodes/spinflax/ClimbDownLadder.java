package org.thurs.aiomoney.nodes.spinflax;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class ClimbDownLadder extends Node {
	

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.BOW_STRING) == 27
				&& SceneEntities.getLoaded(25939) != null;
	}

	@Override
	public void execute() {
		Camera.setPitch(93);
		SceneEntities.getNearest(Variables.LADDER_ID2).interact("Climb-down",
				"Ladder");
		Timer t = new Timer(2350);
		while (t.isRunning()
				&& SceneEntities.getNearest(Variables.LADDER_ID2) != null) {
			Task.sleep(20);
			Variables.status = "Climbing ladder...";
		}

	}
}
