package org.thurs.aiomoney.nodes.openkits;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;

public class BankKits extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount() == 28
				&& SceneEntities
						.getLoaded(org.thurs.aiomoney.resources.Variables.VARROCK_BANKER) != null
				&& !Inventory.contains(org.thurs.aiomoney.resources.Variables.HUNTER_KIT)
				&& org.thurs.aiomoney.resources.Variables.VARROCK_BANK.contains(Players
						.getLocal())
				&& org.thurs.aiomoney.resources.Variables.openKits == true;
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Banking...";
		Bank.open();
		Timer ti = new Timer(3350);
		while (ti.isRunning()) {
			Task.sleep(30);
		}
		Bank.depositInventory();
		Timer t = new Timer(750);
		while (t.isRunning() && Inventory.getCount() == 0) {
			Task.sleep(12);
		}
		if (!Inventory.contains(org.thurs.aiomoney.resources.Variables.HUNTER_KIT)
				&& Bank.isOpen()
				&& Bank.getItem(org.thurs.aiomoney.resources.Variables.HUNTER_KIT) != null) {
			Bank.withdraw(org.thurs.aiomoney.resources.Variables.HUNTER_KIT, 4);
		}

		Bank.close();
		while (t.isRunning() && !Bank.isOpen()) {
			Task.sleep(12);
		}
	}
}