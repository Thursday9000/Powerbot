package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.thurs.aiomoney.resources.Variables;

public class KillUnicorns extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return NPCs.getNearest(var.UNICORN_ID) != null
				&& var.UNICORN_AREA.contains(Players
						.getLocal())
				&& var.unicornKill;
	}

	@Override
	public void execute() {
		int hornCount = Inventory.getCount(var.UNICORN_ID);

		if (!Players.getLocal().isInCombat()) {
			var.method = "Killing Unicorns...";
			NPC[] unicorns = NPCs.getLoaded(var.UNICORN_ID);
			for (int i = 0; i < unicorns.length - 1; i++) {
				if (unicorns[i].isInCombat()) {
					unicorns[i] = null;
				}
			}
			NPC unicorn = NPCs.getNearest(var.UNICORN_ID);
			if (unicorn != null) {
				if (!unicorn.isOnScreen()) {
					Camera.turnTo(unicorn);
					Camera.setPitch(false);

				} else {
					var.method = "Killing Unicorns...";
					unicorn.interact("Attack", "Unicorn");
					Timer t = new Timer(3100);
					while (t.isRunning()) {
						Task.sleep(30);
					}
				}
			}
		}
		if (GroundItems.getLoaded(var.HORN_ID) != null
				&& var.UNICORN_AREA.contains(Players
						.getLocal()) && Players.getLocal().isIdle()) {
			Camera.setPitch(32);
			GroundItem horn = GroundItems
					.getNearest(new Filter<GroundItem>() {

						public boolean accept(GroundItem e) {
							return e.getId() == var.HORN_ID;
						}

					});
			if (horn != null) {
				if (horn.isOnScreen()) {
					var.status = "Picking up Horns...";
					Camera.turnTo(horn);
					horn.interact("Take", "Unicorn horn");
					Timer t = new Timer(2000);
					while (t.isRunning()
							&& Inventory
									.getCount(var.HORN_ID) <= hornCount) {
						Task.sleep(17);
					}
				} else {
					Camera.turnTo(horn);
				}
			}
		}
	}
}