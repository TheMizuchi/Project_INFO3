package controller.condition;

import controller.BotDirection;
import controller.ICondition;
import model.entity.Entity;
import model.entity.EntityType;
import model.entity.Point;
import model.entity.Vector;


public class BotCell implements ICondition {

	BotDirection m_dir;
	String m_cat;


	public BotCell (String dir, String cat) {
		m_dir = new BotDirection(dir);
		m_cat = cat;
	}

	@Override
	public boolean eval (Entity e) {

		if (m_dir.getSel())
			return e.cell(e.getDirVector(), EntityType.OBSTACLE);
		
		long environElapsed = 60;
		
		
		if (m_dir.getAngle() != 0)
			System.out.println();
		Vector zer = e.getDirVector().clone();
		zer.setAngle(zer.getAngle() + m_dir.getAngle());
		System.out.println("les dx | dy de Cell");
		System.out.println(zer.getX());
		System.out.println(zer.getY());
		double x = Math.cos(zer.getAngle());
		double y = Math.sin(zer.getAngle());
		zer.setX(x);
		zer.setY(-y);
		System.out.println(zer.getX());
		System.out.println(zer.getY());

		double dx = zer.getX() * e.getMobSpeed() * environElapsed / 1000 ;
		double dy = zer.getY() * e.getMobSpeed() * environElapsed / 1000;
		//System.out.println("x puis y");
		//System.out.println(dx);
		//System.out.println(dy);
		
		Point p1 = new Point(e.m_hitbox.getP1().getX() + dx, e.m_hitbox.getP1().getY() + dy);
		Point p2 = new Point(e.m_hitbox.getP2().getX() + dx, e.m_hitbox.getP2().getY() + dy);
		Point p3 = new Point(e.m_hitbox.getP3().getX() + dx, e.m_hitbox.getP3().getY() + dy);
		Point p4 = new Point(e.m_hitbox.getP4().getX() + dx, e.m_hitbox.getP4().getY() + dy);
		
		System.out.println("je suis en " + e.m_hitbox.getP1().getX() + "," + e.m_hitbox.getP2().getY());
		double trdbds = e.m_hitbox.getP1().getX() + dx, grzag = e.m_hitbox.getP1().getY() + dy;
		System.out.println("j'essaie d'aller en " + trdbds + "," + grzag);
		System.out.println("je suis en " + e.m_hitbox.getP4().getX() + "," + e.m_hitbox.getP4().getY());
		double tbezh = e.m_hitbox.getP4().getX() + dx, uffd = e.m_hitbox.getP4().getY() + dy;
		System.out.println("j'essaie d'aller en " + tbezh + "," + uffd);
		
		if (e.getHitbox().deplacementValide(p1, p2, p3, p4)) {
			System.out.println("circulez citoyen");
			return true;
		}
		System.out.println("halte là !");
		return false;
	}

}
