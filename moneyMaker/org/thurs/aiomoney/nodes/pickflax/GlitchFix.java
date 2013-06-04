package org.thurs.aiomoney.nodes.pickflax;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.thurs.aiomoney.resources.Variables;

public class GlitchFix extends Node {
	
	@Override
	public boolean activate() {
		return Variables.GLITCH_AREA.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		Walking.newTilePath(Variables.GLITCH_TO_BANK).traverse();
	}

}