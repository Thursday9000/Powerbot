package org.thurs.aiomoney.nodes.grabbones;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.thurs.aiomoney.resources.Variables;

public class PickBones extends Node {
	GroundItem bone = GroundItems.getNearest(Variables.BONE_ID);

	@Override
	public boolean activate() {
		return Variables.BONE_AREA.contains(Players.getLocal())
				&& Players.getLocal().isIdle()
				&& GroundItems.getLoaded(Variables.BONE_ID) != null
				&& Inventory.getCount(Variables.BONE_ID) != 28
				&& Variables.BONE_AREA.contains(bone);
	}

	@Override
	public void execute() {
		Camera.setPitch(32);
		GroundItem bones = GroundItems.getNearest(new Filter<GroundItem>() {

			public boolean accept(GroundItem e) {
				return e.getId() == Variables.BONE_ID;
			}

		});
		if (bones.isOnScreen()) {
			Camera.turnTo(bones);
			bones.interact("Take", "Bones");
			Timer t = new Timer(1000);
			while (t.isRunning() && bones.validate()) {
				Task.sleep(10);
			}

		} else {
			Camera.turnTo(bones);
			Walking.walk(bones);
		}
		Variables.status = "Picking up bones...";
	}

}