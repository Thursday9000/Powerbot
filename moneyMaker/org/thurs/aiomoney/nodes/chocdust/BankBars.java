package org.thurs.aiomoney.nodes.chocdust;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class BankBars extends Node {
	

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.CHOCOLATE_DUST) == 28
				&& Variables.VARROCK_BANK.contains(Players.getLocal())
				&& SceneEntities.getLoaded(Variables.VARROCK_BANKER) != null;
	}

	@Override
	public void execute() {
		Variables.status = "Banking...";
		Bank.open();
		Timer t = new Timer(1340);
		while (t.isRunning()) {
			Task.sleep(10);
		}
		if (Bank.isOpen()) {
			Bank.deposit(Variables.CHOCOLATE_DUST, 28);
			if (!Inventory.contains(Variables.CHOCOLATE_BAR) && Bank.isOpen()
					&& Bank.getItem(Variables.CHOCOLATE_BAR) != null) {
				Bank.withdraw(Variables.CHOCOLATE_BAR, 28);
			}
		}
		Bank.close();
	}
}