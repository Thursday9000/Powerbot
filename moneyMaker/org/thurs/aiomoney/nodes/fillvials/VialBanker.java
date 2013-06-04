package org.thurs.aiomoney.nodes.fillvials;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.thurs.aiomoney.resources.Variables;

public class VialBanker extends Node {

	@Override
	public boolean activate() {

		return Inventory.getCount(Variables.WATER_VIALS) == 28;
	}

	@Override
	public void execute() {
		Variables.status = "Banking...";
		Bank.open();
		if (Bank.isOpen()) {
			Bank.deposit(Variables.WATER_VIALS, 28);
			Bank.withdraw(Variables.VIALS, 28);
			Bank.close();
		}
	}
}
