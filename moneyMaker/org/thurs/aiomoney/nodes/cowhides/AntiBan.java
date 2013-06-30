package org.thurs.aiomoney.nodes.cowhides;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.thurs.aiomoney.resources.Variables;

public class AntiBan extends Node {
	

	@Override
	public boolean activate() {
		return Variables.COW_HIDE_ANTI_BAN.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		Walking.newTilePath(Variables.ANTI_BAN_WALK).traverse();
	}
}