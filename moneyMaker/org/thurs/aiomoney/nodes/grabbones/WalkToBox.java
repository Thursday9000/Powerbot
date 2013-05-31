package org.thurs.aiomoney.nodes.grabbones;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.tab.Inventory;
import org.thurs.aiomoney.resources.Variables;

public class WalkToBox extends Node {
	Variables var = new Variables();
	@Override
	public boolean activate() {
		return Inventory.getCount(var.BONE_ID) == 28
				&& var.pickupBones;
	}

	@Override
	public void execute() {
		Walking.newTilePath(var.BONES_TO_BOX).traverse();
		var.status = "Walking...";
	}

}