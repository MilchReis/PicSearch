package de.nartis.picsearch.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import de.nartis.picsearch.model.AppModel;

public class PaintPanel extends JPanel implements MouseMotionListener, Observer {
	private static final long serialVersionUID = 1L;

	private Point lastPos;
	private int xOffset=5, yOffset=5;
	private AppModel model;

	
	public PaintPanel(AppModel appmodel) {
		this.model = appmodel;
		super.setBackground(Colors.BACKGROUND);
		lastPos = new Point();
		addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		BufferedImage img = model.getImage();
		g.setColor(Colors.BACKGROUND_LIGHT);
		g.fillRect(xOffset, yOffset, img.getWidth(), img.getHeight());

		g.drawImage(img, xOffset, yOffset, null);
			
		Point pos = getMousePosition();
		if(pos != null) {
			int brushSize = model.getBrushSize();
			g.setColor(Color.WHITE);
			g.drawOval(pos.x-(brushSize/2)+xOffset, pos.y-(brushSize/2)+yOffset, brushSize, brushSize);
		}
		
		g.setColor(Color.BLACK);
		g.drawRect(xOffset, yOffset, img.getWidth(), img.getHeight());
	}

	private void drawLine(Point p1, Point p2, Color color) {
		int brushSize = model.getBrushSize();

		BufferedImage img = model.getImage();
		Graphics2D g2 = (Graphics2D) img.getGraphics();
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(
				RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND));
		g2.drawLine(p1.x, p1.y, p2.x, p2.y);
		g2.dispose();
		super.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		drawLine(lastPos, e.getPoint(), model.getColor());
		lastPos.x = e.getX();
		lastPos.y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		lastPos.x = e.getX();
		lastPos.y = e.getY();
		super.repaint();
	}

	@Override
	public void update(Observable o, Object arg) {
	}
}
