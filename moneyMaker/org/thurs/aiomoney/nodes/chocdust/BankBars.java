package org.thurs.aiomoney.nodes.chocdust;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.thurs.aiomoney.resources.Variables;

public class BankBars extends Node {

	@Override
	public boolean activate() {
		return (Inventory.getCount(Variables.CHOCOLATE_DUST) == 28
				|| Bank.isOpen())
				&& Variables.VARROCK_BANK.contains(Players.getLocal())
				&& SceneEntities.getLoaded(Variables.VARROCK_BANKER) != null;
	}

	@Override
	public void execute() {
		Variables.status = "Banking...";
		if (!Bank.isOpen()) {
			Bank.open();
		} else {
					if (Inventory.getCount(Variables.CHOCOLATE_BAR) != 28) {
						if (Inventory.getCount() != 0) {
							Bank.depositInventory();
						} else {
							Bank.withdraw(Variables.CHOCOLATE_BAR, 0);
						}
					} else {
						Bank.close();
					}
		}
		Task.sleep(200, 500);
	}

}
