package org.thursBoner;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingUtilities;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.thursBoner.resources.GUI;
import org.thursBoner.resources.Task;
import org.thursBoner.tasks.BankBones;
import org.thursBoner.tasks.BuryBones;

@Script.Manifest(name = "Thurs's Boner", description = "AIO Bone Burrier")
public class ThursBoner extends PollingScript<ClientContext> implements
		PaintListener {
	
	private List<Task> taskList = new ArrayList<Task>();

	
	
	public void start() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GUI().setVisible(true);
			}

		});

		taskList.add(new BankBones(ctx));
		taskList.add(new BuryBones(ctx));
		return;
	}
	
	
	
	@Override
	public void repaint(Graphics arg0) {
		
	}

	@Override
	public void poll() {
		for (Task task : taskList) {
			if (task.activate()) {
				task.execute();
			}
		}
		return;		
	}

}