package org.thurs.aiomoney.nodes.spinflax;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.thurs.aiomoney.resources.Variables;

public class StringBanking extends Node {

	@Override
	public boolean activate() {

		return Inventory.getCount(Variables.BOW_STRING) == 27
				&& Variables.SEERS_BANK_AREA.contains(Players.getLocal())
				&& SceneEntities.getLoaded(Variables.SEERS_BANKER) != null;
	}

	@Override
	public void execute() {

		Variables.status = "Banking...";
		Bank.open();
		Bank.depositInventory();

		Bank.withdraw(Variables.INV_FLAX, 27);
	}
}