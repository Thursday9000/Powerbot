package org.thurs.aiomoney.nodes.cowhidekill;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.thurs.aiomoney.resources.Variables;

public class KillCows extends Node {
	GroundItem hides = GroundItems.getNearest(Variables.COW_HIDES);
	NPC cow = NPCs.getNearest(Variables.COW_ID);

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.COW_HIDES) < 28 && cow != null
				&& !Players.getLocal().isInCombat()
				&& !Variables.COW_AREA.contains(hides);
	}

	@Override
	public void execute() {
		NPC cows = NPCs.getNearest(Variables.COW_ID);
		if (cows.isOnScreen()) {
			Variables.status = "Killing Cows...";
			Camera.turnTo(cow);
			cows.interact("Attack", cow.getName());
			Timer t = new Timer(3100);
			while (t.isRunning() && !Players.getLocal().isInCombat()) {
				Task.sleep(30);
			}
		} else {
			Camera.turnTo(cow);
		}

	}
}
