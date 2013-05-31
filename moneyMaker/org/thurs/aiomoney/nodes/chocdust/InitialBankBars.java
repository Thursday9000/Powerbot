package org.thurs.aiomoney.nodes.chocdust;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class InitialBankBars extends Node {
	Variables var = new Variables();

	@Override
	public boolean activate() {
		return Inventory.getCount(var.CHOCOLATE_DUST) == 0
				&& Inventory.getCount(var.CHOCOLATE_BAR) == 0
				&& var.VARROCK_BANK.contains(Players.getLocal())
				&& SceneEntities.getLoaded(var.VARROCK_BANKER) != null;
	}

	@Override
	public void execute() {
		var.status = "Banking...";

		Bank.open();
		if (Bank.isOpen()) {
			Timer t = new Timer(1340);
			while (t.isRunning()) {
				Task.sleep(10);
			}
			if (!Inventory.contains(var.CHOCOLATE_BAR) && Bank.isOpen()
					&& Bank.getItem(var.CHOCOLATE_BAR) != null) {
				Bank.withdraw(var.CHOCOLATE_BAR, 28);

			}
			Bank.close();
		}

	}

}
