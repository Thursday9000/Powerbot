package org.thurs.aiomoney.nodes.swamptar;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.thurs.aiomoney.resources.Variables;

public class Pickup extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Players.getLocal().isIdle()
				&& var.TAR_LOCATION.contains(Players
						.getLocal())
				&& GroundItems.getLoaded(var.TAR_ID) != null
				&& var.tarPickup;
	}

	@Override
	public void execute() {
		GroundItem tar = GroundItems.getNearest(new Filter<GroundItem>() {

			public boolean accept(GroundItem e) {
				return e.getId() == var.TAR_ID;
			}

		});
		if (tar != null) {
			if (tar.isOnScreen()) {
				tar.interact("Take", "Swamp Tar");
				Timer t = new Timer(850);
				while (t.isRunning() && tar != null) {
					Task.sleep(10);
				}
			} else {
				Camera.turnTo(tar);
			}
		}
		if (Widgets.get(572).getChild(16).visible()) {
			Widgets.get(572).getChild(16).interact("No");
			Timer t = new Timer(1700);
			while (t.isRunning() && Widgets.get(572).getChild(16).visible()) {
				Task.sleep(12);
			}
		}
		var.status = "Taking Tar...";
	}

}