package org.thurs.aiomoney.nodes.drinkwine;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.thurs.aiomoney.resources.Variables;

public class DrinkWine extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Inventory.getCount(var.FULL_WINE) == 28
				&& var.VARROCK_BANK.contains(Players
						.getLocal())
				&& var.drinkWine
				&& Players.getLocal().isIdle();
	}

	@Override
	public void execute() {
		var.status = "Drinking Wine";
		if (var.VARROCK_BANK.contains(Players.getLocal())
				&& Widgets.get(548).getChild(172).visible()) {
			for (int x = 0; x < 28; x++) {
				Widgets.get(679, 0).getChild(x)
						.interact("Drink", "Jug of wine");
				Timer t = new Timer(2500);
				while (t.isRunning()) {
					Task.sleep(2300, 2800);
				}
			}
		}
	}
}