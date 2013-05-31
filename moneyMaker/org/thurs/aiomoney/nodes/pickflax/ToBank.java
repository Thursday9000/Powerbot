package org.thurs.aiomoney.nodes.pickflax;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class ToBank extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount() == 28
				&& !org.thurs.aiomoney.resources.Variables.SEERS_BANK_AREA.contains(Players
						.getLocal()) && org.thurs.aiomoney.resources.Variables.flaxPicking;
	}

	@Override
	public void execute() {
		Walking.newTilePath(org.thurs.aiomoney.resources.Variables.FLAX_TO_BANK).traverse();
		org.thurs.aiomoney.resources.Variables.status = "Walking to bank...";

	}

}
