package org.thurs.aiomoney.nodes.openkits;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.thurs.aiomoney.resources.Variables;

public class BankKits extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount() == 28
				&& SceneEntities.getLoaded(Variables.VARROCK_BANKER) != null
				&& !Inventory.contains(Variables.HUNTER_KIT)
				&& Variables.VARROCK_BANK.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		Variables.status = "Banking...";
		Bank.open();
		if (Bank.isOpen()) {
			Bank.depositInventory();
			if (!Inventory.contains(Variables.HUNTER_KIT) && Bank.isOpen()
					&& Bank.getItem(Variables.HUNTER_KIT) != null) {
				Bank.withdraw(Variables.HUNTER_KIT, 4);
			}
			Bank.close();
		}
	}
}