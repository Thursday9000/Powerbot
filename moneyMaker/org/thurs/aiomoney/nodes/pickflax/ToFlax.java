package org.thurs.aiomoney.nodes.pickflax;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class ToFlax extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount() == 0
				&& !org.thurs.aiomoney.resources.Variables.FLAX_AREA.contains(Players.getLocal())
				&& org.thurs.aiomoney.resources.Variables.flaxPicking;
	}

	@Override
	public void execute() {
		Walking.newTilePath(org.thurs.aiomoney.resources.Variables.BANK_TO_FLAX).traverse();
		org.thurs.aiomoney.resources.Variables.status = "Walking to flax...";
	}

}
