package org.thurs.aiomoney.nodes.eatpie;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.thurs.aiomoney.resources.Variables;

public class BankPiesOnStart extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount() == 0
				&& Variables.VARROCK_BANK.contains(Players.getLocal())
				&& SceneEntities.getLoaded(Variables.VARROCK_BANKER) != null;
	}

	@Override
	public void execute() {
		Variables.status = "Banking...";
		Bank.open();
		if (Bank.isOpen()) {
			Bank.withdraw(Variables.APPLE_PIE, 28);
			Bank.close();
		}
	}
}
