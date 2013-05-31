package org.thurs.aiomoney.nodes.cowhides;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.thurs.aiomoney.resources.Variables;

public class AntiBan extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return var.pickupHides
				&& var.COW_HIDE_ANTI_BAN.contains(Players
						.getLocal());
	}

	@Override
	public void execute() {
		Walking.newTilePath(var.ANTI_BAN_WALK).traverse();
	}
}