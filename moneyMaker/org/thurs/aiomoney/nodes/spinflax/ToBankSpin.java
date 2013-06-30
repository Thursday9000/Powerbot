package org.thurs.aiomoney.nodes.spinflax;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class ToBankSpin extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.BOW_STRING) == 27;
	}

	@Override
	public void execute() {
		if (SceneEntities.getNearest(Variables.CLOSED_DOOR) != null
				&& Inventory.getCount(Variables.BOW_STRING) == 27
				&& Variables.SPINNER_AREA.contains(Players.getLocal())) {
			SceneEntities.getNearest(Variables.CLOSED_DOOR).interact("Open",
					"Door");
			Timer t = new Timer(1500);
			while (t.isRunning()
					&& SceneEntities.getNearest(Variables.CLOSED_DOOR) != null) {
				Task.sleep(10);
			}
		}
		if (Variables.SPINNER_AREA.contains(Players.getLocal())
				&& Inventory.getCount(Variables.BOW_STRING) <= 27) {
			Walking.newTilePath(Variables.SPINNER_TO_BANK).traverse();
			Variables.status = "Walking to bank...";
		}
		if (Inventory.getCount(Variables.BOW_STRING) == 27
				&& SceneEntities.getLoaded(25939) != null) {
			Camera.setPitch(93);
			SceneEntities.getNearest(Variables.LADDER_ID2).interact(
					"Climb-down", "Ladder");
			Timer t = new Timer(2350);
			while (t.isRunning()
					&& SceneEntities.getNearest(Variables.LADDER_ID2) != null) {
				Task.sleep(20);
				Variables.status = "Climbing ladder...";
			}
		}
	}
}
