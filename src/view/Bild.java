package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Bild extends JPanel{

	BufferedImage bild;
	
	public Bild(){
		//
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.fillRect(0, 0, 250, 800);
		//g.drawImage(bild, 0, 0, 250, 800, null);
		g.drawImage(bild, 0, 0, ifNullWidth(), ifNullHeight(), null);
		
		
		repaint();
	}
	
	public void setBild(BufferedImage pBild){
		bild = new BufferedImage(pBild.getWidth()/3, pBild.getHeight()/3 , BufferedImage.TYPE_INT_RGB);
		bild.getGraphics().drawImage(pBild, 0, 0,pBild.getWidth()/3, pBild.getHeight()/3, null);
	}
	
	public int ifNullWidth(){
		try{
			return bild.getWidth();
		}catch(NullPointerException e){
			return 0;
		}
	}
	
	public int ifNullHeight(){
		try{
			return bild.getHeight();
		}catch(NullPointerException e){
			return 0;
		}
	}
}
