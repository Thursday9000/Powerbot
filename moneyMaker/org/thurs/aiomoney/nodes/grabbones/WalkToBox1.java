package org.thurs.aiomoney.nodes.grabbones;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.thurs.aiomoney.resources.Variables;

public class WalkToBox1 extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.BONE_ID) == 28
				&& !Variables.BONE_BANK_AREA.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		Walking.newTilePath(Variables.BONES_TO_BOX).traverse();
		Variables.status = "Walking...";
	}

}