package org.thurs.aiomoney.nodes.pickflax;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.thurs.aiomoney.resources.Variables;

public class ToBank extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Inventory.getCount() == 28
				&& !var.SEERS_BANK_AREA.contains(Players
						.getLocal()) && var.flaxPicking;
	}

	@Override
	public void execute() {
		Walking.newTilePath(var.FLAX_TO_BANK).traverse();
		var.status = "Walking to bank...";

	}

}
