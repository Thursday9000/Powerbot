package org.thurs.aiomoney.nodes.cowhides;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;

public class Fixer extends Node {

	@Override
	public boolean activate() {
		return Inventory.contains(2132);
	}

	@Override
	public void execute() {
		Inventory.getItem(2132).getWidgetChild().interact("Drop");
	}

}
