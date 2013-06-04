package org.thurs.aiomoney.nodes.drinkwine;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class DrinkWine extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.FULL_WINE) == 28
				&& Variables.VARROCK_BANK.contains(Players.getLocal())
				&& Players.getLocal().isIdle();
	}

	@Override
	public void execute() {
		Variables.status = "Drinking Wine";
		if (Variables.VARROCK_BANK.contains(Players.getLocal())
				&& Widgets.get(548).getChild(172).visible()) {
			for (int x = 0; x < 28; x++) {
				Widgets.get(679, 0).getChild(x)
						.interact("Drink", "Jug of wine");
				Timer t = new Timer(2500);
				while (t.isRunning()
						&& Widgets.get(679, 0).getChild(x).isOnScreen()) {
					Task.sleep(2300, 2800);
				}
			}
		}
	}
}