package model;

import model.entity.Entity;
import model.entity.EntityInterface;
import view.Viewport;


public class Camera {

	private static Camera m_instance = null;

	public Viewport vp;
	private EntityInterface j1;
	private EntityInterface j2;
	static boolean bloquer = false;
	static double scale;

	public static final double SCALE_MIN = 0.75;
	private static final double DISTANT_MAX_X_SCALE_1 = 13.0;
	private static final double DISTANT_MAX_Y_SCALE_1 = 5.0;
	public static final double DISTANCE_MAX_X = DISTANT_MAX_X_SCALE_1 / SCALE_MIN;
	public static final double DISTANCE_MAX_Y = DISTANT_MAX_Y_SCALE_1 / SCALE_MIN;


	private Camera (Viewport vp, Entity j1, Entity j2, double x, double y) {
		this(vp, x, y);
		this.j1 = j1;
		this.j2 = j2;
	}

	private Camera (Viewport vp, double x, double y) {
		this.vp = vp;
		this.setPosition(x, y, 1);
	}

	static Camera getInstance (Viewport vp, Entity j1, Entity j2, double x, double y) {
		if (m_instance == null)
			m_instance = new Camera(vp, j1, j2, x, y);
		return m_instance;
	}

	static Camera getInstance (Viewport vp, double x, double y) {
		if (m_instance == null)
			m_instance = new Camera(vp, x, y);
		return m_instance;
	}

	public static Camera getInstance () {
		if (m_instance == null)
			throw new RuntimeException("Tentative d'initialisation d'une deuxième caméra");
		return m_instance;
	}

	public void update () {
		double dx = Math.abs(j1.getPosX() - j2.getPosX());
		double dy = Math.abs(j1.getPosY() - j2.getPosY());
		scale = Math.min(Math.min(DISTANT_MAX_X_SCALE_1 / Math.max(dx, DISTANT_MAX_X_SCALE_1), DISTANT_MAX_Y_SCALE_1 / Math.max(dy, DISTANT_MAX_Y_SCALE_1)), 1);
		scale = Math.max(scale, SCALE_MIN);

		if (scale <= SCALE_MIN) {
			bloquer = true;
		} else {
			bloquer = false;
		}
		this.setPosition((double) (j1.getPosX() + j2.getPosX()) / 2, (double) (j1.getPosY() + j2.getPosY()) / 2, scale);
	}

	public void setj1 (Entity j) {
		this.j1 = j;
	}

	public void setj2 (Entity j) {
		this.j2 = j;
	}

	private void setPosition (double x, double y, double scale) {
		this.vp.setPosition(x, y, scale);
	}

	public static boolean getBlock () {
		return bloquer;
	}

	public static double getScale () {
		return scale;
	}
}
