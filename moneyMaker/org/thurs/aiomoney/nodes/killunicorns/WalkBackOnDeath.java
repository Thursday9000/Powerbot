package org.thurs.aiomoney.nodes.killunicorns;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;

public class WalkBackOnDeath extends Node {

	@Override
	public boolean activate() {
		return !org.thurs.aiomoney.resources.Variables.UNICORN_AREA.contains(Players
				.getLocal())
				&& !org.thurs.aiomoney.resources.Variables.HORN_BANK.contains(Players
						.getLocal())
				&& org.thurs.aiomoney.resources.Variables.unicornKill;
	}

	@Override
	public void execute() {
		Walking.newTilePath(org.thurs.aiomoney.resources.Variables.LUMBY_TO_UNICORNS)
				.traverse();
	}

}