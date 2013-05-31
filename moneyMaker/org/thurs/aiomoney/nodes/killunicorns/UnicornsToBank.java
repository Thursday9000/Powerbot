package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.tab.Inventory;

public class UnicornsToBank extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.HORN_ID) == 28
				&& org.thurs.aiomoney.resources.Variables.unicornKill;

	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Walking...";
		Walking.newTilePath(org.thurs.aiomoney.resources.Variables.UNICORNS_TO_BANK)
				.traverse();
	}

}