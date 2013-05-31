package org.thurs.aiomoney.nodes.fillvials;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class InitialVialBank extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Inventory.getCount(var.VIALS) == 0
				&& var.fillVials;
	}

	@Override
	public void execute() {
		var.status = "Banking...";
		Bank.open();
		Timer t = new Timer(500);
		while (t.isRunning()) {
			Task.sleep(10);
		}
		if (!Inventory.contains(var.VIALS) && Bank.isOpen()
				&& Bank.getItem(var.VIALS) != null) {
			Bank.withdraw(var.VIALS, 28);
			Bank.close();
		}
	}
}