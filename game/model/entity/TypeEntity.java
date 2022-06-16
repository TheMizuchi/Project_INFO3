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
			case 1:
			case 2:
				m_type = 1;
				break;
			case 3:
			case 4:
			case 6:
				m_type = 2;
				break;
			case 5:
				m_type = 3;
				break;
			/*
			 * case block m_type = 4;
			 * 
			 * case torche m_type = 5;
			 */
			default:
				throw new RuntimeException("erreur création TypeEntity, type non reconnu");
		}
		m_typeOrigine = m_type;
	}

	public TypeEntity (String type) {
		type = type.toLowerCase();

		switch (type) {
			case "ally":
				m_type = 1;
				break;
			case "ennemy":
				m_type = 2;
				break;
			case "neutral":
				m_type = 3;
				break;
			case "obstacle":
				m_type = 4;
				break;
			case "item":
				m_type = 5;
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
		m_type = 1;
	}

	public void restaureType () {
		m_type = m_typeOrigine;
	}
}
