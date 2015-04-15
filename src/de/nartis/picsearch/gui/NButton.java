package de.nartis.picsearch.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class NButton extends JButton {
	private static final long serialVersionUID = 1L;
	
	private Color background = new Color( 42, 42, 42 );
	private Font font = new Font( "Sans", Font.BOLD, 12 );
	
	public NButton( String label ) {
		super( label );
		setFont( font );
		setBorder( BorderFactory.createEmptyBorder( 5, 3, 5, 3 ) );
	}

	@Override
	protected void paintComponent( Graphics g ) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setFont( font );
		int radius = 5;
		FontMetrics fm =g2d.getFontMetrics();
		
		g2d.setColor(background);
		g2d.fillRect(0, 0, this.getWidth(), getHeight());
		
		if( getModel().isRollover() && !getModel().isPressed() ) {
			g2d.setColor( new Color( 30, 30, 30 ) );
			g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.5f ) );
			g2d.fillRoundRect( 0, 0, getWidth(), getHeight(), radius, radius );
			GradientPaint gradient = new GradientPaint( getWidth()/2, 0, new Color( 95, 95, 95 ), getWidth()/2, getHeight(), new Color( 61, 61, 61 ) );
			g2d.setPaint( gradient );
			g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f ) );
			g2d.fillRoundRect( 1, 1, getWidth()-2, getHeight()-2, radius, radius );
			
			g2d.setColor( new Color( 30, 30, 30 ) );
			int width = fm.getStringBounds( getText(), g2d ).getBounds().width;
			g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.5f ) );
			g2d.drawString( getText(), ( getWidth()/2 ) - (width/2)-2, (getHeight()/2)+(12/2)-2 );
			g2d.setColor( Color.WHITE );
			g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f ) );
			g2d.drawString( getText(), ( getWidth()/2 ) - (width/2)+1-2, (getHeight()/2)+(12/2)-1 );

		} else if( getModel().isPressed() || getModel().isArmed() ) {
			g2d.setColor( new Color( 30, 30, 30 ) );
			g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.5f ) );
			g2d.fillRoundRect( 0, 0, getWidth(), getHeight(), radius, radius );
			GradientPaint gradient = new GradientPaint( getWidth()/2, 0, new Color( 53, 53, 53 ), getWidth()/2, 
					getHeight(), new Color( 70, 70, 70 ) );
			g2d.setPaint( gradient );
			g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f ) );
			g2d.fillRoundRect( 1, 1, getWidth()-2, getHeight()-2, radius, radius );
			
			g2d.setColor( new Color( 30, 30, 30 ) );
			int width = fm.getStringBounds( getText(), g2d ).getBounds().width;
			g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.5f ) );
			g2d.drawString( getText(), ( getWidth()/2 ) - (width/2)-2, (getHeight()/2)+(12/2)-2 );
			g2d.setColor( Color.WHITE );
			g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f ) );
			g2d.drawString( getText(), ( getWidth()/2 ) - (width/2)+1-2, (getHeight()/2)+(12/2)-1 );

		} else {
			g2d.setColor( new Color( 30, 30, 30 ) );
			g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.5f ) );
			g2d.fillRoundRect( 0, 0, getWidth(), getHeight(), radius, radius );
			GradientPaint gradient = new GradientPaint( getWidth()/2, 0, new Color( 87, 87, 87 ), getWidth()/2, getHeight(), new Color( 53, 53, 53 ) );
			g2d.setPaint( gradient );
			g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f ) );
			g2d.fillRoundRect( 1, 1, getWidth()-2, getHeight()-2, radius, radius );
			
			g2d.setColor( new Color( 40, 40, 40 ) );
			int width = fm.getStringBounds( getText(), g2d ).getBounds().width;
			g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.5f ) );
			g2d.drawString( getText(), ( getWidth()/2 ) - (width/2)-2, (getHeight()/2)+(12/2)-2 );
			g2d.setColor( Color.WHITE );
			g2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1.0f ) );
			g2d.drawString( getText(), ( getWidth()/2 ) - (width/2)+1-2, (getHeight()/2)+(12/2)-1 );
		}
	}
}