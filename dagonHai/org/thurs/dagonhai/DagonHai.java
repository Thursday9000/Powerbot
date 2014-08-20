package org.thurs.dagonhai;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.SwingUtilities;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Skills;
import org.thurs.dagonhai.tasks.*;

import org.thurs.dagonhai.resources.GUI;

@Script.Manifest(name = "Dagon'hai Monk Killer", description = "Kills dagonhai monks for 150k exp ph", properties = "topic=1204565")
public class DagonHai extends PollingScript<ClientContext> implements
		PaintListener {
	public int startExp = ctx.skills.experience(ctx.skills
			.experience(Skills.ATTACK)
			+ ctx.skills.experience(Skills.DEFENSE)
			+ ctx.skills.experience(Skills.STRENGTH)
			+ ctx.skills.experience(Skills.MAGIC)
			+ ctx.skills.experience(Skills.RANGE));

	public void start() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GUI(ctx).frame.setVisible(true);
			}

		});

	}

	private String runTime() {
		final int sec = (int) (getTotalRuntime() / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
		return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":"
				+ (s < 10 ? "0" + s : s);
	}

	private final Color color1 = new Color(255, 255, 255);

	private final Font font1 = new Font("Arial", 1, 12);

	public void repaint(final Graphics g1) {
		int expNow = ctx.skills.experience(Skills.ATTACK)
				+ ctx.skills.experience(Skills.DEFENSE)
				+ ctx.skills.experience(Skills.STRENGTH)
				+ ctx.skills.experience(Skills.MAGIC)
				+ ctx.skills.experience(Skills.RANGE);
		int expGained = expNow - startExp;
		int kills = expGained / 226;
		int killsPH = (int) ((kills * 3600000D) / (getTotalRuntime()));
		int xpPH = (int) ((expGained * 3600000D) / (getTotalRuntime()));

		Graphics2D g = (Graphics2D) g1;
		g.setFont(font1);
		g.setColor(color1);
		g.drawString("Runtime: " + runTime(), 0, 25);
		g.drawString("Kills:" + kills, 0, 50);
		g.drawString("Kills P/H:" + killsPH, 0, 75);
		g.drawString("XP:" + expGained, 0, 100);
		g.drawString("XP P/H:" + xpPH, 0, 124);

		g.setColor(Color.RED);
		final Point p = ctx.input.getLocation();
		g.drawLine(p.x - 5, p.y - 5, p.x + 5, p.y + 5);
		g.drawLine(p.x - 5, p.y + 5, p.x + 5, p.y - 5);
	}

	public void poll() {
		for (Task task : GUI.taskList) {
			if (task.activate()) {
				task.execute();
			}
		}
	}
}