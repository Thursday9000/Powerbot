package org.thurs.aiomoney.nodes.drinkwine;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class DrinkWine extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.FULL_WINE) == 28
				&& org.thurs.aiomoney.resources.Variables.VARROCK_BANK.contains(Players
						.getLocal())
				&& org.thurs.aiomoney.resources.Variables.drinkWine
				&& Players.getLocal().isIdle();
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Drinking Wine";
		if (org.thurs.aiomoney.resources.Variables.VARROCK_BANK.contains(Players.getLocal())
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