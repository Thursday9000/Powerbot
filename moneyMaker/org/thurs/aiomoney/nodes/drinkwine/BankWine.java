package org.thurs.aiomoney.nodes.drinkwine;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

public class BankWine extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.JUG) == 28
				&& org.thurs.aiomoney.resources.Variables.VARROCK_BANK.contains(Players
						.getLocal())
				&& SceneEntities
						.getLoaded(org.thurs.aiomoney.resources.Variables.VARROCK_BANKER) != null
				&& org.thurs.aiomoney.resources.Variables.drinkWine;
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Banking...";
		Bank.open();
		Timer t = new Timer(1750);
		while (t.isRunning()) {
			Task.sleep(15);
		}
		if (Bank.isOpen()) {
			Bank.deposit(org.thurs.aiomoney.resources.Variables.JUG, 28);
			if (!Inventory.contains(org.thurs.aiomoney.resources.Variables.FULL_WINE)
					&& Bank.isOpen()
					&& Bank.getItem(org.thurs.aiomoney.resources.Variables.FULL_WINE) != null) {
				Bank.withdraw(org.thurs.aiomoney.resources.Variables.FULL_WINE, 28);
			}
			Bank.close();
		}
	}

}