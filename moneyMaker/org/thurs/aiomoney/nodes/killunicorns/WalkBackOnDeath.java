package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.thurs.aiomoney.resources.Variables;

public class WalkBackOnDeath extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return !var.UNICORN_AREA.contains(Players
				.getLocal())
				&& !var.HORN_BANK.contains(Players
						.getLocal())
				&& var.unicornKill;
	}

	@Override
	public void execute() {
		Walking.newTilePath(var.LUMBY_TO_UNICORNS)
				.traverse();
	}

}