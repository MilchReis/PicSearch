package de.nartis.picsearch.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.nartis.picsearch.model.AppModel;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	private PaintPanel painting;
	private ToolPanel tools;
	private PreviewPanel preview;
	private CommandPanel command;
	
	private AppModel model;
	
	public Window(AppModel appmodel) {
		super("PicSearch");
		setSize(815, 600);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setBackground(Colors.BACKGROUND);
		
		this.model = appmodel;
		
		preview = new PreviewPanel(model);
		painting = new PaintPanel(model);
		tools = new ToolPanel(model);
		command = new CommandPanel(model);

		model.addObserver( preview );
		model.addObserver( tools );
		model.addObserver( painting );

		painting.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JPanel mid = new JPanel();
		mid.setBackground(Colors.BACKGROUND);
		mid.setLayout(new GridLayout(1, 2));
		mid.add(painting);
		mid.add(preview);
		
		add(tools, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(command, BorderLayout.SOUTH);
	}
}
