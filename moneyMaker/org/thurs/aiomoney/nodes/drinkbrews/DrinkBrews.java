package org.thurs.aiomoney.nodes.drinkbrews;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class DrinkBrews extends Node {
	Variables var = new Variables();

	@Override
	public boolean activate() {
		return Inventory.getCount(var.SARA_BREW_FULL) == 28
				&& var.VARROCK_BANK.contains(Players.getLocal())
				&& Players.getLocal().isIdle();
	}

	@Override
	public void execute() {
		if (var.VARROCK_BANK.contains(Players.getLocal())
				&& Widgets.get(548).getChild(172).visible()) {
			for (int x = 0; x < 28; x++) {
				Widgets.get(679, 0).getChild(x)
						.interact("Drink", "Saradomin brew (2)");
				Timer t = new Timer(1740);
				while (t.isRunning()) {
					Task.sleep(10);
				}
			}
		}
		var.status = "Drinking brews...";
	}

}
