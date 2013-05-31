package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.thurs.aiomoney.resources.Variables;

public class BankToUnicorns extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Inventory.getCount(var.HORN_ID) == 0
				&& !var.UNICORN_AREA.contains(Players
						.getLocal());
	}

	@Override
	public void execute() {
		var.status = "Walking...";
		Walking.newTilePath(var.BANK_TO_UNICORNS)
				.traverse();
	}

}