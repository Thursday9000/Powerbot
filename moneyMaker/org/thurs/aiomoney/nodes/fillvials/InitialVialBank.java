package org.thurs.aiomoney.nodes.fillvials;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class InitialVialBank extends Node {
	

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.VIALS) == 0;
	}

	@Override
	public void execute() {
		Variables.status = "Banking...";
		Bank.open();
		Timer t = new Timer(500);
		while (t.isRunning()) {
			Task.sleep(10);
		}
		if (!Inventory.contains(Variables.VIALS) && Bank.isOpen()
				&& Bank.getItem(Variables.VIALS) != null) {
			Bank.withdraw(Variables.VIALS, 28);
			Bank.close();
		}
	}
}