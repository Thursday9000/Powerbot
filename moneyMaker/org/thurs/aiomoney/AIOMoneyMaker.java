package org.thurs.aiomoney;


import java.awt.*;

import java.util.LinkedList;

import javax.swing.*;

import org.thurs.aiomoney.nodes.cowhides.*;
import org.thurs.aiomoney.nodes.chocdust.*;
import org.thurs.aiomoney.nodes.eatpie.*;
import org.thurs.aiomoney.nodes.fillvials.*;
import org.thurs.aiomoney.nodes.grabbones.*;
import org.thurs.aiomoney.nodes.grabfeathers.*;
import org.thurs.aiomoney.nodes.openkits.*;
import org.thurs.aiomoney.nodes.pickflax.*;
import org.thurs.aiomoney.nodes.spinflax.*;
import org.thurs.aiomoney.nodes.swamptar.*;
import org.thurs.aiomoney.nodes.killunicorns.*;
import org.thurs.aiomoney. nodes.drinkbrews.*;
import org.thurs.aiomoney.nodes.drinkwine.*;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.input.Mouse.Speed;
import org.powerbot.game.api.util.Random;

import org.thurs.aiomoney.resources.GUI;

@Manifest(authors = { "Thurs" }, name = "Thurs's AIO Money Maker", description = "Makes money so you don't have to!", version = 1)
public class AIOMoneyMaker extends ActiveScript implements PaintListener {

	// onStart
	@Override
	public void onStart() {
		Mouse.setSpeed(Speed.VERY_FAST);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GUI().setVisible(true);
			}

		});

	}

	// Paint
	private final Color color1 = new Color(255, 255, 255);

	private final Font font1 = new Font("Arial", 1, 13);
	private final Font font2 = new Font("Arial", 0, 13);

	public void onRepaint(Graphics g1) {
		org.thurs.aiomoney.resources.Variables.runTime.toElapsedString();
		Graphics2D g = (Graphics2D) g1;
		g.setFont(font1);
		g.setColor(color1);
		g.drawString("Thurs's Money Maker", 25, 75);
		g.setFont(font2);
		g.drawString(
				"Run Time: " + org.thurs.aiomoney.resources.Variables.runTime.toElapsedString(),
				25, 100);
		g.drawString("Method: " + org.thurs.aiomoney.resources.Variables.method, 25, 125);
		g.drawString("Status: " + org.thurs.aiomoney.resources.Variables.status, 25, 150);

		g.setColor(Color.RED);
		g.drawLine(Mouse.getX() - 5, Mouse.getY() - 5, Mouse.getX() + 5,
				Mouse.getY() + 5);
		g.drawLine(Mouse.getX() - 5, Mouse.getY() + 5, Mouse.getX() + 5,
				Mouse.getY() - 5);
	}

	// Node
	public static final LinkedList<Node> NODES = new LinkedList<Node>();

	@Override
	public int loop() {
		for (final Node job : NODES.toArray(new Node[NODES.size()])) {
			if (job.activate()) {
				getContainer().submit(job);
				job.join();
			}
		}
		return Random.nextInt(150, 250);

	}

}