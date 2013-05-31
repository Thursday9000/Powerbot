package org.thurs.aiomoney.nodes.spinflax;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;

public class StringBanking extends Node {

	@Override
	public boolean activate() {

		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.BOW_STRING) == 27
				&& org.thurs.aiomoney.resources.Variables.SEERS_BANK_AREA.contains(Players
						.getLocal())
				&& SceneEntities
						.getLoaded(org.thurs.aiomoney.resources.Variables.SEERS_BANKER) != null
				&& org.thurs.aiomoney.resources.Variables.spinFlax;
	}

	@Override
	public void execute() {

		org.thurs.aiomoney.resources.Variables.status = "Banking...";
		Bank.open();
		Timer t = new Timer(5000);
		while (t.isRunning() && Bank.isOpen()) {
			Task.sleep(30);
		}
		if (Bank.isOpen()) {
			Bank.depositInventory();

			Bank.withdraw(org.thurs.aiomoney.resources.Variables.INV_FLAX, 27);
			Timer ti = new Timer(1350);
			while (ti.isRunning()
					&& Inventory.getCount(org.thurs.aiomoney.resources.Variables.INV_FLAX) == 27) {
				Task.sleep(10);
			}
		}
	}
}