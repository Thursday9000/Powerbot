package Nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.GroundItem;

public class GrabFeathers {

	public static class PickupFeathers extends Node {

		@Override
		public boolean activate() {
			return Players.getLocal().isIdle()
					&& Resources.Variables.FEATHER_AREA.contains(Players
							.getLocal().getLocation())
					&& GroundItems.getLoaded(Resources.Variables.FEATHER_ID) != null
					&& Resources.Variables.pickupFeathers == true;
		}

		@Override
		public void execute() {
			Resources.Variables.status = "Taking Feathers...";
			GroundItem feathers = GroundItems
					.getNearest(new Filter<GroundItem>() {

						public boolean accept(GroundItem e) {
							return e.getId() == Resources.Variables.FEATHER_ID;
						}

					});
			if (feathers != null) {
				if (feathers.isOnScreen()) {
					feathers.interact("Take", "Feather");
					Timer t = new Timer(1850);
					while (t.isRunning()) {
						Task.sleep(1750, 2000);
					}
				} else {
					Camera.turnTo(feathers);
				}
			}
		}
	}
}
