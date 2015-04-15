package de.nartis.picsearch.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import de.nartis.picsearch.model.AppModel;

public class ToolPanel extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	private NButton colorchooser;
	private NButton brushGrow;
	private NButton brushShrink;
	
	private AppModel model;
	
	public ToolPanel(AppModel appmodel) {
		this.model = appmodel;
		
		colorchooser = new NButton("Choose Color");
		brushGrow = new NButton("Brush +");
		brushShrink = new NButton("Brush -");
		
		colorchooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ColorPickerWindow(model);
			}
		});
		
		brushGrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.increaseBrush();
			}
		});
		
		brushShrink.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.decreaseBrush();
			}
		});
		
		super.setBackground(Colors.BACKGROUND);
		super.setPreferredSize( new Dimension( 0, 40 ) );
		
		JPanel pane = new JPanel(new GridLayout( 1, 0, 5, 5 ));
		pane.setBackground(Colors.BACKGROUND);
		
		pane.add(colorchooser);
		pane.add(brushGrow);
		pane.add(brushShrink);
	
		add(pane);
	}

	@Override
	public void update(Observable o, Object arg) {
	}
}
