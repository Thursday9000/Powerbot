package org.thurs.aiomoney.nodes.cowhides;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.thurs.aiomoney.resources.Variables;

//Pickup
public class PickupHides extends Node {
	Variables var = new Variables();
	int hideCount = Inventory
			.getCount(var.COW_HIDES);

	@Override
	public boolean activate() {
		return var.COW_AREA.contains(Players
				.getLocal())
				&& Players.getLocal().isIdle()
				&& GroundItems
						.getLoaded(var.COW_HIDES) != null
				&& Inventory
						.getCount(var.COW_HIDES) < 29
				&& var.pickupHides;
	}

	@Override
	public void execute() {
		Camera.setPitch(32);
		GroundItem hide = GroundItems.getNearest(new Filter<GroundItem>() {

			public boolean accept(GroundItem e) {
				return e.getId() == var.COW_HIDES;
			}
		});
		if (hide != null) {
			if (hide.isOnScreen()) {
				Camera.turnTo(hide);
				hide.interact("Take", "Cowhide");
				Timer t = new Timer(2000);
				while (t.isRunning() && hide != null) {
					Task.sleep(10);
				}

			} else {
				Camera.turnTo(hide);
			}

			var.status = "Picking up cowhides...";
		}
	}
}