package org.thurs.aiomoney.nodes.drinkwine;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.thurs.aiomoney.resources.Variables;

public class BankWine extends Node {
	Variables var = new Variables();

	@Override
	public boolean activate() {
		return Inventory.getCount(var.JUG) == 28
				&& var.VARROCK_BANK.contains(Players.getLocal())
				&& SceneEntities.getLoaded(var.VARROCK_BANKER) != null;
	}

	@Override
	public void execute() {
		var.status = "Banking...";
		Bank.open();
		Timer t = new Timer(1750);
		while (t.isRunning()) {
			Task.sleep(15);
		}
		if (Bank.isOpen()) {
			Bank.deposit(var.JUG, 28);
			if (!Inventory.contains(var.FULL_WINE) && Bank.isOpen()
					&& Bank.getItem(var.FULL_WINE) != null) {
				Bank.withdraw(var.FULL_WINE, 28);
			}
			Bank.close();
		}
	}

}