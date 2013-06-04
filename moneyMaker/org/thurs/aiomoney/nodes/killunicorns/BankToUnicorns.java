package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.thurs.aiomoney.resources.Variables;

public class BankToUnicorns extends Node {
	
	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.HORN_ID) == 0
				&& !Variables.UNICORN_AREA.contains(Players
						.getLocal());
	}

	@Override
	public void execute() {
		Variables.status = "Walking...";
		Walking.newTilePath(Variables.BANK_TO_UNICORNS)
				.traverse();
	}

}