package org.thurs.aiomoney.nodes.cowhides;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;

public class AntiBan extends Node {

	@Override
	public boolean activate() {
		return org.thurs.aiomoney.resources.Variables.pickupHides
				&& org.thurs.aiomoney.resources.Variables.COW_HIDE_ANTI_BAN.contains(Players
						.getLocal());
	}

	@Override
	public void execute() {
		Walking.newTilePath(org.thurs.aiomoney.resources.Variables.ANTI_BAN_WALK).traverse();
	}
}