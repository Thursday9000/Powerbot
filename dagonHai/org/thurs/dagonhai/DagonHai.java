package org.thurs.dagonhai;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Skills;
import org.thurs.dagonhai.resources.*;
import org.thurs.dagonhai.tasks.*;

@Script.Manifest(name = "DagonHai monk killer", description = "Kills dagonhai monks for 150k exp ph", properties = "topic=1234")
public class DagonHai extends PollingScript<ClientContext> implements
		PaintListener {

	private List<Task> taskList = new ArrayList<Task>();

	public void start() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GUI().setVisible(true);
			}

		});

		taskList.add(new Eat(ctx));
		taskList.add(new Attack(ctx));
		taskList.add(new Pickup(ctx));
		taskList.add(new Teleport(ctx));
		return;
	}

	long startTime = System.currentTimeMillis();
	private final Color color1 = new Color(255, 255, 255);

	private final Font font1 = new Font("Arial", 1, 12);

	public void repaint(final Graphics g1) {
		Variables.expGained = (ctx.skills.experience(Skills.ATTACK)
				+ ctx.skills.experience(Skills.DEFENSE)
				+ ctx.skills.experience(Skills.STRENGTH)
				+ ctx.skills.experience(Skills.MAGIC) + ctx.skills
					.experience(Skills.RANGE)) - Variables.startingExperience;
		int kills = Variables.expGained / 226;
		int killsPH = (int) ((kills * 3600000D) / (System.currentTimeMillis() - startTime));
		int xpPH = (int) ((Variables.expGained * 3600000D) / (System
				.currentTimeMillis() - startTime));

		Graphics2D g = (Graphics2D) g1;
		g.setFont(font1);
		g.setColor(color1);
		g.drawString("Runtime: " + getTotalRuntime(), 0, 25);
		g.drawString("Kills:" + kills, 0, 50);
		g.drawString("Kills P/H:" + killsPH, 0, 75);
		g.drawString("XP:" + Variables.expGained, 0, 100);
		g.drawString("XP P/H:" + xpPH, 0, 124);
		

		g.setColor(Color.RED);
		final Point p = ctx.input.getLocation();
		g.drawLine(p.x - 5, p.y - 5, p.x + 5, p.y + 5);
		g.drawLine(p.x - 5, p.y + 5, p.x + 5, p.y - 5);
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