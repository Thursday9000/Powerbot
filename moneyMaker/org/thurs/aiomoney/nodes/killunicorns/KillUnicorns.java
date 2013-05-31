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

public class KillUnicorns extends Node {

	@Override
	public boolean activate() {
		return NPCs.getNearest(org.thurs.aiomoney.resources.Variables.UNICORN_ID) != null
				&& org.thurs.aiomoney.resources.Variables.UNICORN_AREA.contains(Players
						.getLocal())
				&& org.thurs.aiomoney.resources.Variables.unicornKill;
	}

	@Override
	public void execute() {
		int hornCount = Inventory.getCount(org.thurs.aiomoney.resources.Variables.UNICORN_ID);

		if (!Players.getLocal().isInCombat()) {
			org.thurs.aiomoney.resources.Variables.method = "Killing Unicorns...";
			NPC[] unicorns = NPCs.getLoaded(org.thurs.aiomoney.resources.Variables.UNICORN_ID);
			for (int i = 0; i < unicorns.length - 1; i++) {
				if (unicorns[i].isInCombat()) {
					unicorns[i] = null;
				}
			}
			NPC unicorn = NPCs.getNearest(org.thurs.aiomoney.resources.Variables.UNICORN_ID);
			if (unicorn != null) {
				if (!unicorn.isOnScreen()) {
					Camera.turnTo(unicorn);
					Camera.setPitch(false);

				} else {
					org.thurs.aiomoney.resources.Variables.method = "Killing Unicorns...";
					unicorn.interact("Attack", "Unicorn");
					Timer t = new Timer(3100);
					while (t.isRunning()) {
						Task.sleep(30);
					}
				}
			}
		}
		if (GroundItems.getLoaded(org.thurs.aiomoney.resources.Variables.HORN_ID) != null
				&& org.thurs.aiomoney.resources.Variables.UNICORN_AREA.contains(Players
						.getLocal()) && Players.getLocal().isIdle()) {
			Camera.setPitch(32);
			GroundItem horn = GroundItems
					.getNearest(new Filter<GroundItem>() {

						public boolean accept(GroundItem e) {
							return e.getId() == org.thurs.aiomoney.resources.Variables.HORN_ID;
						}

					});
			if (horn != null) {
				if (horn.isOnScreen()) {
					org.thurs.aiomoney.resources.Variables.status = "Picking up Horns...";
					Camera.turnTo(horn);
					horn.interact("Take", "Unicorn horn");
					Timer t = new Timer(2000);
					while (t.isRunning()
							&& Inventory
									.getCount(org.thurs.aiomoney.resources.Variables.HORN_ID) <= hornCount) {
						Task.sleep(17);
					}
				} else {
					Camera.turnTo(horn);
				}
			}
		}
	}
}