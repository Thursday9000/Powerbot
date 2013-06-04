package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.thurs.aiomoney.resources.Variables;

public class KillUnicorns extends Node {

	@Override
	public boolean activate() {
		return NPCs.getLoaded(Variables.UNICORN_ID) != null
				&& GroundItems.getNearest(Variables.HORN_ID) == null
				&& !Players.getLocal().isInCombat();
	}

	@Override
	public void execute() {
		Variables.method = "Killing Unicorns...";
		NPC unicorn = NPCs.getNearest(Variables.UNICORN_ID);
		if (unicorn.isOnScreen()) {
			unicorn.interact("Attack", "Unicorn");
			Timer t = new Timer(3100);
			while (t.isRunning() && !Players.getLocal().isInCombat()) {
				Task.sleep(30);
			}

		} else {
			Camera.turnTo(unicorn);
			Camera.setPitch(false);
		}

	}
}
