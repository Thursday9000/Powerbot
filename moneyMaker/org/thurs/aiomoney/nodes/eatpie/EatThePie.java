package org.thurs.aiomoney.nodes.eatpie;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class EatThePie extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.APPLE_PIE) == 28
				&& Variables.VARROCK_BANK.contains(Players.getLocal())
				&& Players.getLocal().isIdle();
	}

	@Override
	public void execute() {
		Variables.status = "Eating Pie";
		if (Variables.VARROCK_BANK.contains(Players.getLocal())
				&& Widgets.get(548).getChild(172).visible()) {
			for (int x = 0; x < 28; x++) {
				Widgets.get(679, 0).getChild(x).interact("Eat", "Apple Pie");
				Timer t = new Timer(2000);
				while (t.isRunning() && Inventory.getCount(Variables.APPLE_PIE) <= 28) {
					Task.sleep(50);
				}
				Timer ti = new Timer(2000);
				Widgets.get(679, 0).getChild(x).interact("Eat", "Apple Pie");
				while (ti.isRunning() && Inventory.getCount(Variables.HALF_PIE) >= 0) {
					Task.sleep(50);
				}
			}
		}
	}
}