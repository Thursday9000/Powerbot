package org.thurs.aiomoney.nodes.spinflax;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;

public class Withdraw extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount() == 0
				&& org.thurs.aiomoney.resources.Variables.spinFlax
				&& org.thurs.aiomoney.resources.Variables.SEERS_BANK_AREA.contains(Players
						.getLocal())
				&& SceneEntities
						.getLoaded(org.thurs.aiomoney.resources.Variables.SEERS_BANKER) != null;
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Banking...";
		Bank.open();
		if (Bank.isOpen()) {
			Timer t = new Timer(1350);
			while (t.isRunning() && Bank.isOpen()) {
				Task.sleep(15);
			}
			Bank.withdraw(org.thurs.aiomoney.resources.Variables.INV_FLAX, 27);
			while (t.isRunning()
					&& Inventory.getCount(org.thurs.aiomoney.resources.Variables.INV_FLAX) == 27) {
				Task.sleep(15);
			}
			Bank.close();
		}
	}
}