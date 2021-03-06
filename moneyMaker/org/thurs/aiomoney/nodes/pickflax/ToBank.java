package org.thurs.aiomoney.nodes.pickflax;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.thurs.aiomoney.resources.Variables;

public class ToBank extends Node {
	

	@Override
	public boolean activate() {
		return Inventory.getCount() == 28
				&& !Variables.SEERS_BANK_AREA.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		Walking.newTilePath(Variables.FLAX_TO_BANK).traverse();
		Variables.status = "Walking to bank...";

	}

}
