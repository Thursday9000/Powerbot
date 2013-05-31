package org.thurs.aiomoney.nodes.drinkbrews;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class BankBrewsOnStart extends Node {
	Variables var = new Variables();

	@Override
	public boolean activate() {
		return Inventory.getCount() == 0
				&& var.VARROCK_BANK.contains(Players.getLocal())
				&& SceneEntities.getLoaded(var.VARROCK_BANKER) != null;
	}

	@Override
	public void execute() {
		var.status = "Banking...";
		Bank.open();
		Timer t = new Timer(1740);
		while (t.isRunning()) {
			Task.sleep(10);
		}
		if (Bank.isOpen()) {
			Bank.withdraw(var.SARA_BREW_FULL, 28);
			Timer ti = new Timer(1740);
			while (ti.isRunning()
					& Inventory.getCount(var.SARA_BREW_FULL) == 28) {
				Task.sleep(10);
			}
		}
		Bank.close();
	}
}