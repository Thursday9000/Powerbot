package org.thurs.aiomoney.nodes.fillvials;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;

public class InitialVialBank extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.VIALS) == 0
				&& org.thurs.aiomoney.resources.Variables.fillVials;
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Banking...";
		Bank.open();
		Timer t = new Timer(500);
		while (t.isRunning()) {
			Task.sleep(10);
		}
		if (!Inventory.contains(org.thurs.aiomoney.resources.Variables.VIALS) && Bank.isOpen()
				&& Bank.getItem(org.thurs.aiomoney.resources.Variables.VIALS) != null) {
			Bank.withdraw(org.thurs.aiomoney.resources.Variables.VIALS, 28);
			Bank.close();
		}
	}
}