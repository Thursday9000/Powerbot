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

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.INV_FLAX) == 27;
	}

	@Override
	public void execute() {
		Variables.status = "Walking to spinner";
		if (Variables.SEERS_BANK_AREA.contains(Players.getLocal())
				&& Inventory.getCount(Variables.INV_FLAX) == 27) {
			Walking.newTilePath(Variables.BANK_TO_SPINNER).traverse();
		}
		if (SceneEntities.getNearest(Variables.CLOSED_DOOR) != null
				&& Variables.SPINNER_AREA.contains(Players.getLocal())) {
			Variables.status = "Opening door...";
			SceneEntities.getNearest(Variables.CLOSED_DOOR).interact("Open",
					"Door");
			Timer t = new Timer(1740);
			while (t.isRunning()
					&& SceneEntities.getNearest(Variables.CLOSED_DOOR) != null) {
				Task.sleep(15);
			}
		}
		if (SceneEntities.getLoaded(25938) != null
				&& Inventory.getCount(Variables.INV_FLAX) == 27
				&& Variables.SPINNER_AREA.contains(Players.getLocal())) {
			SceneEntities.getNearest(Variables.LADDER_ID).interact("Climb-up",
					"Ladder");
			Timer t = new Timer(2350);
			while (t.isRunning()
					&& SceneEntities.getNearest(Variables.LADDER_ID) != null) {
				Task.sleep(20);
			}
			Variables.status = "Climbing ladder...";
		}
	}
}
