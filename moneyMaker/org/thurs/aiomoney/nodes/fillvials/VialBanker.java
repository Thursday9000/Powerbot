package org.thurs.aiomoney.nodes.fillvials;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

public class VialBanker extends Node {

	@Override
	public boolean activate() {

		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.WATER_VIALS) == 28
				&& org.thurs.aiomoney.resources.Variables.fillVials;
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Banking...";
		Bank.open();
		{
			while (org.thurs.aiomoney.resources.Variables.Sleeping.isRunning()) {
				Task.sleep(3000, 6000);
			}
		}
		Bank.deposit(org.thurs.aiomoney.resources.Variables.WATER_VIALS, 28);
		Bank.withdraw(org.thurs.aiomoney.resources.Variables.VIALS, 28);
		Bank.close();
	}
}
