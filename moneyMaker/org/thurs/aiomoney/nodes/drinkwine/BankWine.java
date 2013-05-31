package org.thurs.aiomoney.nodes.drinkwine;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.thurs.aiomoney.resources.Variables;

public class BankWine extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.JUG) == 28
				&& Variables.VARROCK_BANK.contains(Players.getLocal())
				&& SceneEntities.getLoaded(Variables.VARROCK_BANKER) != null;
	}

	@Override
	public void execute() {
		Variables.status = "Banking...";
		Bank.open();
		if (Bank.isOpen()) {
			Bank.deposit(Variables.JUG, 28);
			if (!Inventory.contains(Variables.FULL_WINE) && Bank.isOpen()
					&& Bank.getItem(Variables.FULL_WINE) != null) {
				Bank.withdraw(Variables.FULL_WINE, 28);
			}
			Bank.close();
		}
	}
}