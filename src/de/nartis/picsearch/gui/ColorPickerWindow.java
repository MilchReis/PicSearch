package de.nartis.picsearch.gui;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import com.bric.swing.ColorPicker;

import de.nartis.picsearch.model.AppModel;

public class ColorPickerWindow extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	private AppModel model;
	private ColorPicker picker;
	
	public ColorPickerWindow(AppModel appmodel) {
		super();
		super.setSize(200, 200);
		
		super.setAlwaysOnTop( true );
		super.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		super.setUndecorated(true);	
		super.addMouseListener(this);
		super.setBackground(Colors.BACKGROUND);
		
		this.model = appmodel;
		
		picker = new ColorPicker(false,false);
		picker.setBackground(Colors.BACKGROUND);
		picker.setForeground(Colors.BACKGROUND);
		picker.setColor(model.getColor());
		picker.setRGBControlsVisible(false);
		picker.setHexControlsVisible(false);
		picker.setPreviewSwatchVisible(false);
		picker.setHSBControlsVisible(false);
		picker.setVisible(true);

		Point mouse = MouseInfo.getPointerInfo().getLocation();
		super.setLocation(mouse.x - 20, mouse.y - 20);

		super.add(picker);
		super.setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		model.setColor(picker.getColor());
		super.setVisible(false);
		dispose();
	}

	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
}
