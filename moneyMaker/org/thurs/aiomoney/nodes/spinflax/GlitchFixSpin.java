package org.thurs.aiomoney.nodes.spinflax;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;


public class GlitchFixSpin extends Node {

	@Override
	public boolean activate() {

		return org.thurs.aiomoney.resources.Variables.GLITCH_AREA.contains(Players
				.getLocal())
				&& Inventory.getCount() == 27
				&& org.thurs.aiomoney.resources.Variables.spinFlax;
	}

	@Override
	public void execute() {
		Walking.newTilePath(org.thurs.aiomoney.resources.Variables.GLITCH_TO_BANK)
				.traverse();
	}
}