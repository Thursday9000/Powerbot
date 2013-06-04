package org.thurs.aiomoney.nodes.openkits;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class OpenKits extends Node {
	
	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.HUNTER_KIT) == 4
				&& Variables.VARROCK_BANK.contains(Players
						.getLocal());
	}

	@Override
	public void execute() {
		Variables.status = "Opening Kits...";
		for (int x = 0; x < 4; x++) {
			Widgets.get(679, 0).getChild(3).interact("Open", "Hunter kit");
			Timer t = new Timer(1750);
			while (t.isRunning() && Inventory.getCount() <= 28) {
				Task.sleep(15);
			}
		}
	}
}