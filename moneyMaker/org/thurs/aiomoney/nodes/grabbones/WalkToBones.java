package org.thurs.aiomoney.nodes.grabbones;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.tab.Inventory;

public class WalkToBones extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(org.thurs.aiomoney.resources.Variables.BONE_ID) == 0
				&& org.thurs.aiomoney.resources.Variables.pickupBones;
	}

	@Override
	public void execute() {
		Walking.newTilePath(org.thurs.aiomoney.resources.Variables.BOX_TO_BONES).traverse();
		org.thurs.aiomoney.resources.Variables.status = "Walking...";
	}

}
