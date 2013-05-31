package org.thurs.aiomoney.nodes.openkits;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class BankKitsOnStart extends Node {
	
	@Override
	public boolean activate() {
		return Inventory.getCount() == 0
				&& Variables.VARROCK_BANK.contains(Players
						.getLocal())
				&& SceneEntities
						.getLoaded(Variables.VARROCK_BANKER) != null;
	}

	@Override
	public void execute() {
		Variables.status = "Banking...";
		Bank.open();
		Timer t = new Timer(1750);
		while (t.isRunning()) {
			Task.sleep(15);
		}
		if (Bank.isOpen()) {
			Bank.withdraw(Variables.HUNTER_KIT, 4);
			Timer ti = new Timer(1750);
			while (ti.isRunning()
					&& Inventory.getCount(Variables.HUNTER_KIT) == 4) {
				Task.sleep(15);
			}
			Bank.close();
		}
	}
}
