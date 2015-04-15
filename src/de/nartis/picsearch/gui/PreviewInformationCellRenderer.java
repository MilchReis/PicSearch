package de.nartis.picsearch.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import de.nartis.picsearch.model.Picture;

public class PreviewInformationCellRenderer implements ListCellRenderer<Picture> {

	@Override
	public Component getListCellRendererComponent( JList list, Picture value,
			int index, boolean isSelected, boolean cellHasFocus ) {

		Picture p = (Picture) value;

		// Panel for a row
		JPanel panel = new JPanel( new BorderLayout() );
		JPanel infoPanel = new JPanel( new BorderLayout() );
		infoPanel.setOpaque( false );

		// Preview
		JLabel preview = new JLabel( new ImageIcon( p.getPreview() ) );
		preview.setBorder( BorderFactory.createEmptyBorder( 0, 0, 0, 3 ) );
		preview.setVerticalAlignment( JLabel.TOP );
		preview.setForeground(list.getForeground());
		
		// Path
		JLabel path = new JLabel( p.getPath() );
		path.setForeground(list.getForeground());
		
		// Size
		JLabel simi = new JLabel( "Similarity: " + p.getSimilarity()*100.0f);
		JLabel size = new JLabel( p.getWidth() + "x" + p.getHeight() );
		simi.setForeground(list.getForeground());
		
		size.setForeground(list.getForeground());
		
		infoPanel.add( path, BorderLayout.NORTH );
		infoPanel.add( simi, BorderLayout.CENTER );
		infoPanel.add( size, BorderLayout.SOUTH );
		
		panel.add( preview, BorderLayout.WEST );
		panel.add( infoPanel, BorderLayout.CENTER );

		if( isSelected ) {
			panel.setBackground( list.getSelectionBackground() );
			panel.setForeground( list.getSelectionForeground() );
		} else {
			panel.setBackground( list.getBackground() );
			panel.setForeground( list.getForeground() );
		}

		panel.setEnabled( list.isEnabled() );
		panel.setFont( list.getFont() );
		panel.setOpaque( true );
		panel.setBorder( BorderFactory.createEmptyBorder( 0, 0, 3, 0 ) );
		return panel;
	}
}
