package org.thurs.aiomoney.nodes.grabbones;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.thurs.aiomoney.resources.Variables;

public class WalkToBones extends Node {
	
	GroundItem bone = GroundItems.getNearest(Variables.BONE_ID);
	@Override
	public boolean activate() {
		return Inventory.getCount(Variables.BONE_ID) == 0 && !bone.isOnScreen();
	}

	@Override
	public void execute() {
		Walking.newTilePath(Variables.BOX_TO_BONES).traverse();
		Variables.status = "Walking...";
	}

}
