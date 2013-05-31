package org.thurs.aiomoney.nodes.eatpie;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;

public class BankPiesOnStart extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount() == 0
				&& org.thurs.aiomoney.resources.Variables.VARROCK_BANK.contains(Players
						.getLocal())
				&& SceneEntities
						.getLoaded(org.thurs.aiomoney.resources.Variables.VARROCK_BANKER) != null
				&& org.thurs.aiomoney.resources.Variables.eatPie;
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Banking...";
		Bank.open();
		Timer t = new Timer(1740);
		while (t.isRunning()) {
			Task.sleep(10);
		}
		if (Bank.isOpen()) {
			Bank.withdraw(org.thurs.aiomoney.resources.Variables.APPLE_PIE, 28);
			Timer ti = new Timer(1740);
			while (ti.isRunning()
					&& Inventory.getCount(org.thurs.aiomoney.resources.Variables.APPLE_PIE) == 28) {
				Task.sleep(10);
			}

			Bank.close();
		}
	}
}
