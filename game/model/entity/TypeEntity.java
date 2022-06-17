package model.entity;

import model.Model;


public class TypeEntity {

	public static final int Allié = 1;
	public static final int Ennemi = 2;
	public static final int Neutre = 3;
	public static final int Obstacle = 4;
	public static final int Item = 5;
	// ajouter un type particulier pour les boss ?

	int m_type; // de 1 à 5, allié, ennemi, neutre ou obstacle
	int m_typeOrigine; // type à la création de l'entité


	public TypeEntity (int ID) {

		switch (ID) {
			case Model.COWBOY_ID:
			case Model.J1_ID:
			case Model.J2_ID:
				m_type = Allié;
				break;
			case Model.BLOON_ID:
			case Model.SKELETON_ID:
			case Model.DART_MONKEY_ID:
				m_type = Ennemi;
				break;
			case Model.BAT_ID:
				m_type = Neutre;
				break;

			//			case Model:
			//				m_type = Obstacle;
			//				break;

			case Model.TORCH_ID:
				m_type = Item;
				break;

			default:
				throw new RuntimeException("erreur création TypeEntity, type non reconnu");
		}
		m_typeOrigine = m_type;
	}

	public TypeEntity (String type) {
		type = type.toLowerCase();

		switch (type) {
			case "ally":
				m_type = Allié;
				break;
			case "ennemy":
				m_type = Ennemi;
				break;
			case "neutral":
				m_type = Neutre;
				break;
			case "obstacle":
				m_type = Obstacle;
				break;
			case "item":
				m_type = Item;
				break;
			default:
				throw new RuntimeException("erreur création TypeEntity, string non reconnue");
		}
		m_typeOrigine = m_type;
	}

	public int getType () {
		return m_type;
	}

	public void devientGentil () {
		m_type = Allié;
	}

	public void restaureType () {
		m_type = m_typeOrigine;
	}
}
