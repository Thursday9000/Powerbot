package org.thurs.aiomoney.nodes.eatpie;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;

public class EatThePie extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.APPLE_PIE) == 28
				&& org.thurs.aiomoney.resources.Variables.VARROCK_BANK.contains(Players
						.getLocal()) && org.thurs.aiomoney.resources.Variables.eatPie
				&& Players.getLocal().isIdle();
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Eating Pie";
		if (org.thurs.aiomoney.resources.Variables.VARROCK_BANK.contains(Players.getLocal())
				&& Widgets.get(548).getChild(172).visible()) {
			for (int x = 0; x < 28; x++) {
				Widgets.get(679, 0).getChild(x)
						.interact("Eat", "Apple Pie");
				Timer t = new Timer(1490);
				while (t.isRunning()) {
					Task.sleep(10);
				}
				Widgets.get(679, 0).getChild(x)
						.interact("Eat", "Apple Pie");
				while (t.isRunning()) {
					Task.sleep(10);
				}
			}
		}
	}
}