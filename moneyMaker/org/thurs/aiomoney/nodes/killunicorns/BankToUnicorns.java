package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class BankToUnicorns extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.HORN_ID) == 0
				&& org.thurs.aiomoney.resources.Variables.unicornKill
				&& !org.thurs.aiomoney.resources.Variables.UNICORN_AREA.contains(Players
						.getLocal());
	}

	@Override
	public void execute() {
		org.thurs.aiomoney.resources.Variables.status = "Walking...";
		Walking.newTilePath(org.thurs.aiomoney.resources.Variables.BANK_TO_UNICORNS)
				.traverse();
	}

}