package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.tab.Inventory;
import org.thurs.aiomoney.resources.Variables;

public class UnicornsToBank extends Node {
	Variables var = new Variables();

	@Override
	public boolean activate() {
		return Inventory.getCount(var.HORN_ID) == 28;

	}

	@Override
	public void execute() {
		var.status = "Walking...";
		Walking.newTilePath(var.UNICORNS_TO_BANK).traverse();
	}

}