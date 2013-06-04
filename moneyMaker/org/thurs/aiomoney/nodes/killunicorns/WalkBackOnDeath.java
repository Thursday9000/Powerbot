package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.thurs.aiomoney.resources.Variables;

public class WalkBackOnDeath extends Node {
	

	@Override
	public boolean activate() {
		return !Variables.UNICORN_AREA.contains(Players.getLocal())
				&& !Variables.HORN_BANK.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		Walking.newTilePath(Variables.LUMBY_TO_UNICORNS).traverse();
	}

}