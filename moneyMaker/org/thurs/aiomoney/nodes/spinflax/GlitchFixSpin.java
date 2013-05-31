package org.thurs.aiomoney.nodes.spinflax;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.thurs.aiomoney.resources.Variables;


public class GlitchFixSpin extends Node {
	
	@Override
	public boolean activate() {

		return Variables.GLITCH_AREA.contains(Players
				.getLocal())
				&& Inventory.getCount() == 27;
	}

	@Override
	public void execute() {
		Walking.newTilePath(Variables.GLITCH_TO_BANK)
				.traverse();
	}
}