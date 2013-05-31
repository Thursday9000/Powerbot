package org.thurs.aiomoney.nodes.grabfeathers;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.thurs.aiomoney.resources.Variables;

public class PickupFeathers extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Players.getLocal().isIdle()
				&& var.FEATHER_AREA.contains(Players
						.getLocal())
				&& GroundItems.getLoaded(var.FEATHER_ID) != null
				&& var.pickupFeathers;
	}

	@Override
	public void execute() {
		var.status = "Taking Feathers...";
		GroundItem feathers = GroundItems
				.getNearest(new Filter<GroundItem>() {

					public boolean accept(GroundItem e) {
						return e.getId() == var.FEATHER_ID;
					}

				});
		if (feathers != null) {
			if (feathers.isOnScreen()) {
				feathers.interact("Take", "Feather");
				Timer t = new Timer(1850);
				while (t.isRunning()) {
					Task.sleep(10);
				}
			} else {
				Camera.turnTo(feathers);
			}
		}
	}
}