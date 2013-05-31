package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

public class BankHorns extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.HORN_ID) >= 28
				&& SceneEntities.getLoaded(org.thurs.aiomoney.resources.Variables.LUMBY_BOX) != null
				&& org.thurs.aiomoney.resources.Variables.unicornKill;
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Banking...";
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