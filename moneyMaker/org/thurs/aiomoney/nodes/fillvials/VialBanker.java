package org.thurs.aiomoney.nodes.fillvials;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.thurs.aiomoney.resources.Variables;

public class VialBanker extends Node {
	Variables var = new Variables();

	@Override
	public boolean activate() {

		return Inventory.getCount(var.WATER_VIALS) == 28;
	}

	@Override
	public void execute() {
		var.status = "Banking...";
		Bank.open();
		{
			while (var.Sleeping.isRunning()) {
				Task.sleep(3000, 6000);
			}
		}
		Bank.deposit(var.WATER_VIALS, 28);
		Bank.withdraw(var.VIALS, 28);
		Bank.close();
	}
}
