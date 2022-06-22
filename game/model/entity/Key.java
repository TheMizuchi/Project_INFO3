package model.entity;

public class Key extends Entity {

	static Key m_instance;


	public Key (double x, double y) {
		super(x, y, EntityProperties.KEY);
		// TODO Auto-generated constructor stub
	}

	public static Key getInstance (double x, double y) {
		if (m_instance == null)
			m_instance = new Key(x, y);
		return m_instance;
	}

	public static Key getInstance () {
		if (m_instance == null)
			throw new RuntimeException("Key not yet created");
		return m_instance;
	}

}
