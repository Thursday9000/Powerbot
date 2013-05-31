package org.thurs.aiomoney.nodes.chocdust;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;

public class InitialBankBars extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.CHOCOLATE_DUST) == 0
				&& Inventory.getCount(org.thurs.aiomoney.resources.Variables.CHOCOLATE_BAR) == 0
				&& org.thurs.aiomoney.resources.Variables.VARROCK_BANK.contains(Players
						.getLocal())
				&& SceneEntities
						.getLoaded(org.thurs.aiomoney.resources.Variables.VARROCK_BANKER) != null
				&& org.thurs.aiomoney.resources.Variables.crushBars;
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Banking...";

		Bank.open();
		if (Bank.isOpen()) {
			Timer t = new Timer(1340);
			while (t.isRunning()) {
				Task.sleep(10);
			}
			if (!Inventory.contains(org.thurs.aiomoney.resources.Variables.CHOCOLATE_BAR)
					&& Bank.isOpen()
					&& Bank.getItem(org.thurs.aiomoney.resources.Variables.CHOCOLATE_BAR) != null) {
				Bank.withdraw(org.thurs.aiomoney.resources.Variables.CHOCOLATE_BAR, 28);

			}
			Bank.close();
		}

	}

}
