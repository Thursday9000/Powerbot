package org.thurs.aiomoney.nodes.openkits;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class BankKits extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Inventory.getCount() == 28
				&& SceneEntities
						.getLoaded(var.VARROCK_BANKER) != null
				&& !Inventory.contains(var.HUNTER_KIT)
				&& var.VARROCK_BANK.contains(Players
						.getLocal())
				&& var.openKits == true;
	}

	@Override
	public void execute() {
		var.status = "Banking...";
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
		if (!Inventory.contains(var.HUNTER_KIT)
				&& Bank.isOpen()
				&& Bank.getItem(var.HUNTER_KIT) != null) {
			Bank.withdraw(var.HUNTER_KIT, 4);
		}

		Bank.close();
		while (t.isRunning() && !Bank.isOpen()) {
			Task.sleep(12);
		}
	}
}