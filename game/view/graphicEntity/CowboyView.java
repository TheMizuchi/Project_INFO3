package view.graphicEntity;

import java.awt.Color;
import java.awt.Graphics;

import view.EntityView;

public class CowboyView extends EntityView{
	
	public CowboyView(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public void paint (Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(this.x, this.y, 50, 25);
		
	}
	
}
