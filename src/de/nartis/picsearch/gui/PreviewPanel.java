package de.nartis.picsearch.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.nartis.picsearch.model.AppModel;
import de.nartis.picsearch.model.Picture;

public class PreviewPanel extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	private JList list;
	private DefaultListModel listModel;
	private AppModel model;
	
	public PreviewPanel(AppModel model) {
		super(new BorderLayout());
		super.setBackground(Colors.BACKGROUND);

		this.model = model;
		
		listModel = new DefaultListModel();
		
		list = new JList( listModel );
		list.setBackground(Colors.BACKGROUND_LIGHT);
		list.setForeground(Color.WHITE);
		list.setSelectionBackground(Colors.BACKGROUND);
		list.setCellRenderer(new PreviewInformationCellRenderer());
		list.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				if( e.getClickCount() == 2 ) {
					int index = list.locationToIndex(e.getPoint());
					Picture p = (Picture) listModel.get(index);
					try {
						Desktop.getDesktop().open(new File(p.getPath()));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		} );
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		add( scroll, BorderLayout.CENTER );
		setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		List<Picture> previews = model.getPreviews();
		listModel.clear();
		for(Picture p : previews)
			listModel.addElement(p);
		
		list.invalidate();
	}
}
