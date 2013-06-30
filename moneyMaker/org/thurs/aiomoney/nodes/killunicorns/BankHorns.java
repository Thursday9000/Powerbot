package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.thurs.aiomoney.resources.Variables;

public class BankHorns extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.HORN_ID) >= 28;
	}

	@Override
	public void execute() {
		Variables.status = "Banking...";
		if (!Bank.isOpen()) {
			Bank.open();
			Bank.depositInventory();
		}
		Bank.close();
	}
}