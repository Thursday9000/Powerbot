package org.thurs.aiomoney.nodes.pickflax;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.thurs.aiomoney.resources.Variables;

public class FlaxBanking extends Node {
	
	@Override
	public boolean activate() {

		return Inventory.getCount() == 28
				&& Variables.SEERS_BANK_AREA.contains(Players
						.getLocal())
				&& SceneEntities
						.getLoaded(Variables.SEERS_BANKER) != null;
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
			Bank.deposit(Variables.INV_FLAX, 28);
		}
		Bank.close();
	}

}