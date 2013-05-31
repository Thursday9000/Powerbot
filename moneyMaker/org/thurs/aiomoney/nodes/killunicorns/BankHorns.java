package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.thurs.aiomoney.resources.Variables;

public class BankHorns extends Node {
	Variables var = new Variables();

	@Override
	public boolean activate() {
		return Inventory.getCount(var.HORN_ID) >= 28
				&& SceneEntities.getLoaded(var.LUMBY_BOX) != null;
	}

	@Override
	public void execute() {
		var.status = "Banking...";
		if (!Bank.isOpen()) {
			Bank.open();

			Timer t = new Timer(4500);
			while (t.isRunning()) {
				Task.sleep(30);
			}
			Bank.depositInventory();
		}
		Bank.close();
	}
}