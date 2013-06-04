package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.thurs.aiomoney.resources.Variables;

public class PickupHorns extends Node {

	@Override
	public boolean activate() {
		return GroundItems.getLoaded(Variables.HORN_ID) != null
				&& Variables.UNICORN_AREA.contains(Players.getLocal())
				&& !Players.getLocal().isInCombat();
	}

	@Override
	public void execute() {
		Camera.setPitch(32);
		GroundItem horn = GroundItems.getNearest(Variables.HORN_ID);
		if (horn != null) {
			Variables.status = "Picking up Horns...";
			horn.interact("Take", "Unicorn horn");
			Timer t = new Timer(2000);
			while (t.isRunning() && horn != null) {
				Task.sleep(17);
			}

		} else {
			Camera.turnTo(horn);
		}
	}
}
