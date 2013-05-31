package org.thurs.aiomoney.nodes.openkits;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class OpenKits extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Inventory.getCount(var.HUNTER_KIT) == 4
				&& var.VARROCK_BANK.contains(Players
						.getLocal())
				&& var.openKits == true;
	}

	@Override
	public void execute() {
		var.status = "Opening Kits...";
		for (int x = 0; x < 5; x++) {
			Widgets.get(679, 0).getChild(3).interact("Open", "Hunter kit");
			Timer t = new Timer(1750);
			while (t.isRunning()) {
				Task.sleep(15);
			}
		}
	}
}