package de.nartis.picsearch.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import de.nartis.picsearch.model.AppModel;

public class CommandPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private AppModel model;

	private NButton source;
	private NButton search;

	private String imgPath;

	public CommandPanel(AppModel appmodel) {

		this.model = appmodel;

		super.setBackground(Colors.BACKGROUND);
		super.setPreferredSize(new Dimension(0, 40));

		source = new NButton("Image directory");
		search = new NButton("Search");

		source.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(CommandPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					imgPath = chooser.getSelectedFile().getAbsolutePath();
					model.setSourcePath(imgPath);
					System.out.println("Imagepath selected: " + imgPath);
				}
			}
		});

		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CommandPanel.this.model.startSearch();
			}
		});

		JPanel pane = new JPanel(new GridLayout(1, 0, 5, 5));
		pane.setBackground(Colors.BACKGROUND);

		pane.add(source);
		pane.add(search);

		add(pane);
	}
}
