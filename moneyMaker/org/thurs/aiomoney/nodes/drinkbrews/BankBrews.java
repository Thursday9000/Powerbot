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
	

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.SARA_BREW_DRANK) == 28
				&& Variables.VARROCK_BANK.contains(Players.getLocal())
				&& SceneEntities.getLoaded(Variables.VARROCK_BANKER) != null;
	}

	@Override
	public void execute() {
		Variables.status = "Banking...";

		Bank.open();
		Timer t = new Timer(1740);
		while (t.isRunning()) {
			Task.sleep(10);
		}
		if (Bank.isOpen()) {
			Bank.deposit(Variables.SARA_BREW_DRANK, 28);
			if (!Inventory.contains(Variables.FULL_WINE) && Bank.isOpen()
					&& Bank.getItem(Variables.SARA_BREW_FULL) != null) {
				Bank.withdraw(Variables.SARA_BREW_FULL, 28);
				Bank.close();
			}
		}
	}
}