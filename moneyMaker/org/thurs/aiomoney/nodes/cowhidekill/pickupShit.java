package org.thurs.aiomoney.nodes.cowhidekill;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.thurs.aiomoney.resources.Variables;

public class pickupShit extends Node {
	GroundItem hides = GroundItems.getNearest(Variables.COW_HIDES);
	NPC cow = NPCs.getNearest(Variables.COW_ID);

	@Override
	public boolean activate() {
		return Variables.COW_AREA.contains(Players.getLocal()) && hides != null
				&& !Players.getLocal().isInCombat()
				&& Inventory.getCount(Variables.COW_HIDES) < 28;
	}

	@Override
	public void execute() {
		if (hides.isOnScreen()) {
			Camera.turnTo(hides);
			GroundItem hide = GroundItems.getNearest(Variables.COW_HIDES);
			Variables.status = "Picking up Hides...";
			hide.interact("Take", "Cowhide");
			Timer t = new Timer(2000);
			while (t.isRunning() && hide.validate()) {
				Task.sleep(17);
			}
		} else {
			Camera.turnTo(hides);

		}
	}
}
