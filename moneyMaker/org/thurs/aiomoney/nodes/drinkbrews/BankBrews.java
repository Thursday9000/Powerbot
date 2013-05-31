package org.thurs.aiomoney.nodes.drinkbrews;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class BankBrews extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Inventory.getCount(var.SARA_BREW_DRANK) == 28
				&& var.VARROCK_BANK.contains(Players
						.getLocal())
				&& SceneEntities
						.getLoaded(var.VARROCK_BANKER) != null
				&& var.drinkBrews;
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
			Bank.deposit(var.SARA_BREW_DRANK, 28);
			if (!Inventory.contains(var.FULL_WINE)
					&& Bank.isOpen()
					&& Bank.getItem(var.SARA_BREW_FULL) != null) {
				Bank.withdraw(var.SARA_BREW_FULL, 28);
				Bank.close();
			}
		}
	}
}