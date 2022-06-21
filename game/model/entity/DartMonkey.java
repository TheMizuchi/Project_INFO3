package model.entity;

public class DartMonkey extends Mob {
	//DartMonkeyView m_dmv;

	public DartMonkey (double x, double y) {
		super(x, y, EntityProperties.DART_MONKEY);
		//m_dmv = new DartMonkeyView(this);
		//m_ev = m_dmv;
		//MyCanvas.getInstance().createEntityView(m_dmv);
	}

	//Constructeur pour créer entité sans view
	public DartMonkey (double x, double y, Object o) {
		super(x, y, EntityProperties.DART_MONKEY);
	}
}