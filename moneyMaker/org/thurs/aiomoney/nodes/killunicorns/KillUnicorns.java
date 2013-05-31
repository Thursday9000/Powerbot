package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.thurs.aiomoney.resources.Variables;

public class KillUnicorns extends Node {
	

	@Override
	public boolean activate() {
		return NPCs.getNearest(Variables.UNICORN_ID) != null
				&& Variables.UNICORN_AREA.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		if (!Players.getLocal().isInCombat()) {
			Variables.method = "Killing Unicorns...";
			NPC[] unicorns = NPCs.getLoaded(Variables.UNICORN_ID);
			for (int i = 0; i < unicorns.length - 1; i++) {
				if (unicorns[i].isInCombat()) {
					unicorns[i] = null;
				}
			}
			NPC unicorn = NPCs.getNearest(Variables.UNICORN_ID);
			if (unicorn != null) {
				if (!unicorn.isOnScreen()) {
					Camera.turnTo(unicorn);
					Camera.setPitch(false);

				} else {
					Variables.method = "Killing Unicorns...";
					unicorn.interact("Attack", "Unicorn");
					Timer t = new Timer(3100);
					while (t.isRunning() && !Players.getLocal().isInCombat()) {
						Task.sleep(30);
					}
				}
			}
		}
		if (GroundItems.getLoaded(Variables.HORN_ID) != null
				&& Variables.UNICORN_AREA.contains(Players.getLocal())
				&& Players.getLocal().isIdle()) {
			Camera.setPitch(32);
			GroundItem horn = GroundItems.getNearest(new Filter<GroundItem>() {

				public boolean accept(GroundItem e) {
					return e.getId() == Variables.HORN_ID;
				}

			});
			if (horn != null) {
				if (horn.isOnScreen()) {
					Variables.status = "Picking up Horns...";
					Camera.turnTo(horn);
					horn.interact("Take", "Unicorn horn");
					Timer t = new Timer(2000);
					while (t.isRunning()
							&& horn != null) {
						Task.sleep(17);
					}
				} else {
					Camera.turnTo(horn);
				}
			}
		}
	}
}