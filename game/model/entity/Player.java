package model.entity;

import common.MyTimer;
import common.TimerListener;
import controller.RefAutomata;
import edu.polytech.oop.collections.IList;
import model.Camera;
import model.Model;
import model.entity.behavior.PlayerBehavior;
import model.map.TileType;


public abstract class Player extends Entity {

	public double getDetectionRange () {
		return 2.5;
	}


	protected static final long POSSESSION_CD = 30;
	protected final static double SLOW_TORCHE = 0.20;
	protected final static double POSSESSION_RANGE = 5;
	protected final static double SLOW_TORCHE_ATTAQUE = 1.3;

	long m_possessionCD;
	Mob m_possessing;
	PlayerBehavior m_pb;
	
	Hitbox m_possess_Before;

	double m_speedX, m_speedY;


	public Player (double x, double y, EntityProperties ep) {
		super(x, y, ep);
	}

	abstract public void onGround ();

	abstract public void onIce ();

	@Override
	public void update (long elapsed) {
		double centerX = this.m_hitbox.getCenterRealX();
		double centerY = this.m_hitbox.getCenterRealY();

		if (Model.getMap().getCases()[(int) centerX][(int) centerY].getType() == TileType.ICE) {
			this.onIce();
		} else {
			this.onGround();
		}
		m_pb.update(elapsed);
	}

	public void updateOnNormalGround (long elapsed) {

		if (m_possessing == null) {
			Torch torch = Torch.getInstance();
			Key key = Key.getInstance();
			// déplacement
			m_automata.step();

			if (m_cdAction == 0) {
				double speedX = super.m_vecDir.getX() * getSpeed();
				double speedY = super.m_vecDir.getY() * getSpeed();

				if (this.equals(torch.porteur)) {
					speedX *= (1 - SLOW_TORCHE);
					speedY *= (1 - SLOW_TORCHE);
				}

				if (Camera.getBlock()) {
					Entity autreJ = autreJ();
					Entity moi = getEntity();
					double m_angle = m_vecDir.getAngle();

					double distY = Math.abs(autreJ.m_hitbox.getP1().getY() - (moi.m_hitbox.getP1().getY() + (speedY * elapsed / 1000))); // distance future entre les 2 joueurs
					double distX = Math.abs(autreJ.m_hitbox.getP1().getX() - (moi.m_hitbox.getP1().getX() + (speedX * elapsed / 1000)));

					// haut
					if (m_angle < Math.PI && m_angle > 0 && distY > Camera.DISTANCE_MAX_Y) // si la distance sur cet axe est supérieur au max
						return;

					// bas
					if (m_angle > Math.PI && distY > Camera.DISTANCE_MAX_Y)
						return;

					// gauche
					if (m_angle > Math.PI / 2 && m_angle < 3 * Math.PI / 2 && distX > Camera.DISTANCE_MAX_X)
						return;

					// droite
					if ((m_angle < Math.PI / 2 || m_angle > 3 * Math.PI / 2) && distX > Camera.DISTANCE_MAX_X)
						return;

				}
				m_speedX = speedX * elapsed / 1000;
				m_speedY = speedY * elapsed / 1000;
				m_hitbox.move(m_speedX, m_speedY);
				if (this.equals(torch.porteur))
					torch.update(this);
				if (this.equals(key.porteur))
					key.update(this);
			}
		}

	}

	public void updateOnIce (long elapsed) {

		if (m_possessing == null) {
			Torch torch = Torch.getInstance();
			Key key = Key.getInstance();
			// déplacement
			m_automata.step();

			if (m_cdAction >= 0)
				m_cdAction -= elapsed;

			double speedX;
			double speedY;

			speedX = 0.5 * ENTITY_MAX_ACCELERATION * elapsed / 1000 * elapsed / 1000 * m_vecDir.getX();
			speedY = 0.5 * ENTITY_MAX_ACCELERATION * elapsed / 1000 * elapsed / 1000 * m_vecDir.getY();

			if (this.equals(torch.porteur)) {
				speedX *= (1 - SLOW_TORCHE);
				speedY *= (1 - SLOW_TORCHE);
			}

			speedX += m_speedX;
			speedY += m_speedY;

			if (Camera.getBlock()) {
				Entity autreJ = autreJ();
				Entity moi = getEntity();
				double m_angle = m_vecDir.getAngle();

				double distY = Math.abs(autreJ.m_hitbox.getP1().getY() - (moi.m_hitbox.getP1().getY() + (speedY * elapsed / 1000))); // distance future entre les 2 joueurs
				double distX = Math.abs(autreJ.m_hitbox.getP1().getX() - (moi.m_hitbox.getP1().getX() + (speedX * elapsed / 1000)));

				// haut
				if (m_angle < Math.PI && m_angle > 0 && distY > Camera.DISTANCE_MAX_Y) { // si la distance sur cet axe est supérieur au max
					return;
				}

				// bas
				if (m_angle > Math.PI && distY > Camera.DISTANCE_MAX_Y) {
					return;
				}

				// gauche
				if (m_angle > Math.PI / 2 && m_angle < 3 * Math.PI / 2 && distX > Camera.DISTANCE_MAX_X) {
					return;
				}

				// droite
				if ((m_angle < Math.PI / 2 || m_angle > 3 * Math.PI / 2) && distX > Camera.DISTANCE_MAX_X) {
					return;
				}

			}
			if (Math.abs(speedX) < 0.5)
				m_speedX = speedX;
			if (Math.abs(speedY) < 0.5)
				m_speedY = speedY;

			double p1x = m_hitbox.m_p1.getX();
			double p1y = m_hitbox.m_p1.getY();
			m_hitbox.move(m_speedX, m_speedY);

			if (p1x == m_hitbox.m_p1.getX()) {
				m_speedX = 0;
			}

			if (p1y == m_hitbox.m_p1.getY()) {
				m_speedY = 0;
			}

			Entity autreJ = autreJ();
			Entity moi = getEntity();
			double distY = Math.abs(autreJ.m_hitbox.getP1().getY() - (moi.m_hitbox.getP1().getY())); // distance future entre les 2 joueurs
			double distX = Math.abs(autreJ.m_hitbox.getP1().getX() - (moi.m_hitbox.getP1().getX()));

			if (distY > Camera.DISTANCE_MAX_Y || distX > Camera.DISTANCE_MAX_X) {
				m_hitbox.move(-m_speedX, -m_speedY);
				m_speedY = 0;
				m_speedX = 0;
			}
			if (this.equals(torch.porteur))
				torch.update(this);
			if (this.equals(key.porteur))
				key.update(this);

		}
	}

	public void setAutomata (RefAutomata a) {
		m_automata = a;
	}

	public void setPossessedMob (Mob m) {
		m_possessing = m;
	}

	private Entity autreJ () {

		if (this == J1.getInstance())
			return J2.getInstance().getEntity();
		return J1.getInstance().getEntity();
	}

	public Entity getEntity () {
		if (m_possessing != null)
			return m_possessing;
		return this;
	}

	public void pickTorch () {
		Torch torch = Torch.getInstance();
		Key key = Key.getInstance();

		if (distance(key) <= 2 && key.porteur == null) {
			key.porteur = this;
			key.hide();
		} else if (this.equals(torch.porteur)) {
			torch.m_ls.setRadius(Torch.GROUND_RADIUS);
			torch.porteur = null;
			torch.show();
		} else if (distance(torch) <= 2) {
			torch.m_ls.setRadius(Torch.HOLDED_RADIUS);
			torch.porteur = this;
			torch.hide();
		}
	}

	@Override
	public int getPv () {

		if (m_possessing != null) {
			return m_possessing.getPv();
		} else {
			return m_pv;
		}
	}

	@Override
	void takeDamages (int damages) {

		if (m_cdDmgTaken > 0)
			return;

		new InvulnerableTimer(this);

		super.takeDamages(damages);
	}

	@Override
	public void pop () {
		pick();
	}

	@Override
	public void wizz () {
		possession();
	}

	public void possession () {

		if (m_possessionCD == 0) {

			Mob closestTarget = (Mob) closest(true);

			if (closestTarget != null && distance(closestTarget) < POSSESSION_RANGE) {
				this.m_possess_Before = this.m_hitbox;
				
				closestTarget.devientGentil(m_entityProperties, m_vecDir.clone(), this);
				Point p = new Point(Double.MIN_VALUE, Double.MIN_VALUE);
				m_hitbox = new Hitbox(p, p, p, p, this);
				m_automata = new RefAutomata(this, true);
				m_possessing = closestTarget;
				m_tangible = false;
				hide();
				setCam(closestTarget);

				// On arrete l'animation de déplacement s'il y en a une
				if (m_vecDir.getX() != 0 || m_vecDir.getY() != 0) {
					m_ev.walk();
				}

				// On coupe la torche
				if (Torch.getInstance().porteur == this)
					Torch.getInstance().m_ls.setRadius(Torch.POSSESSED_RADIUS);
			}
		}
	}

	public boolean isPossessing () {
		if (m_possessing != null)
			return true;
		return false;
	}

	abstract void hide ();
	abstract void show ();
	abstract void setCam (Entity e);

	public Player finPossession (Mob m, int pv, Vector dir) {
		m_pv = pv;
		m_vecDir = dir;
		m_possessing = null;
		m_automata = new RefAutomata(this);
		show();
		new PossessionTimerCD(this);
		setCam(this);
		new IntangibleTimer(this);
		Hitbox hit = new Hitbox(m.m_hitbox.getP1(), m.m_hitbox.getP2(), m.m_hitbox.getP3(), m.m_hitbox.getP4(), m);
		if(Model.getMap().getCases()[(int) hit.getCenterX()][(int) hit.getCenterY()].getType() == TileType.VOID) {
			hit = this.m_possess_Before;
		}
		m_hitbox = hit;

		// Si besoin on lance l'animation de déplacement
		if (m_vecDir.getX() != 0 || m_vecDir.getY() != 0) {
			m_ev.walk();
		}

		// On rallume la torche
		if (Torch.getInstance().porteur == this)
			Torch.getInstance().m_ls.setRadius(Torch.HOLDED_RADIUS);

		return null;
	}


	private class IntangibleTimer implements TimerListener {

		Player m_p;


		IntangibleTimer (Player p) {
			m_p = p;
			m_p.m_possessionCD = POSSESSION_CD * 1000;
			m_p.m_tangible = false;
			MyTimer mt = MyTimer.getTimer();
			mt.setTimer(20, this);
		}

		@Override
		public void expired () {

			if (!contactPossession(m_p.m_hitbox.getP1(), m_p.m_hitbox.getP2(), m_p.m_hitbox.getP3(), m_p.m_hitbox.getP4())) {
				m_p.m_tangible = true;
				m_p.m_hitbox.m_e = m_p;
			} else {
				MyTimer mt = MyTimer.getTimer();
				mt.setTimer(20, this);
			}
		}

		private boolean contactPossession (Point new_p1, Point new_p2, Point new_p3, Point new_p4) {
			IList list = Model.getlistEntity();
			IList.Iterator it = list.iterator();

			//if (!m_e.isTanguible() && !m_e.isBloon())	
			//	return false;

			while (it.hasNext()) {
				Entity e = (Entity) it.next();

				if ((!e.equal(this.m_p) && (e.isTanguible())) || (e.isDoor() && e.isTanguible())) {

					if (e.getHibox().collides(new_p1, new_p2, new_p3, new_p4)) {
						return true;
					}
				}
			}
			return false;
		}

	}

	private class PossessionTimerCD implements TimerListener {

		Player m_p;
		long m_last;


		PossessionTimerCD (Player p) {
			m_p = p;
			m_p.m_possessionCD = POSSESSION_CD * 1000;
			MyTimer mt = MyTimer.getTimer();
			m_last = System.currentTimeMillis();
			mt.setTimer(100, this);
		}

		@Override
		public void expired () {
			long time = System.currentTimeMillis();
			m_p.m_possessionCD -= time - m_last;

			if (m_p.m_possessionCD <= 0) {
				m_p.m_possessionCD = 0;
			} else {
				MyTimer mt = MyTimer.getTimer();
				mt.setTimer(100, this);
				m_last = time;
			}
		}

	}
}
