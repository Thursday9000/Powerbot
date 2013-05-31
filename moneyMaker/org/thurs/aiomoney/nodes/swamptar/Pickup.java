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

public class Pickup extends Node {

	@Override
	public boolean activate() {
		return Players.getLocal().isIdle()
				&& org.thurs.aiomoney.resources.Variables.TAR_LOCATION.contains(Players
						.getLocal())
				&& GroundItems.getLoaded(org.thurs.aiomoney.resources.Variables.TAR_ID) != null
				&& org.thurs.aiomoney.resources.Variables.tarPickup;
	}

	@Override
	public void execute() {
		GroundItem tar = GroundItems.getNearest(new Filter<GroundItem>() {

			public boolean accept(GroundItem e) {
				return e.getId() == org.thurs.aiomoney.resources.Variables.TAR_ID;
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
		org.thurs.aiomoney.resources.Variables.status = "Taking Tar...";
	}

}