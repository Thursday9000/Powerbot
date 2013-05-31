package org.thurs.aiomoney.nodes.openkits;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;

public class OpenKits extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.HUNTER_KIT) == 4
				&& org.thurs.aiomoney.resources.Variables.VARROCK_BANK.contains(Players
						.getLocal())
				&& org.thurs.aiomoney.resources.Variables.openKits == true;
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Opening Kits...";
		for (int x = 0; x < 5; x++) {
			Widgets.get(679, 0).getChild(3).interact("Open", "Hunter kit");
			Timer t = new Timer(1750);
			while (t.isRunning()) {
				Task.sleep(15);
			}
		}
	}
}