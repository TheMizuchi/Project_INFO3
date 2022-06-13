package model.entity;

import java.awt.Polygon;

public class Hitbox {
	
	Polygon m_forme ;
	
	
	public boolean collision (Polygon hitbox) {
		int[] Xpoints = m_forme.xpoints;
		int[] Ypoints = m_forme.ypoints;
		for(int i =0; i< m_forme.npoints ; i++) {
			if(hitbox.contains(Xpoints[i], Ypoints[i]))
				return true ;
		}
		Xpoints = hitbox.xpoints;
		Ypoints = hitbox.ypoints;
		for(int i =0; i< m_forme.npoints ; i++) {
			if(m_forme.contains(Xpoints[i], Ypoints[i]))
				return true ;
		}
		return false ; 
	}
	
	public void move (int d_x, int d_y) {
		 
	}
	
	
	

}
