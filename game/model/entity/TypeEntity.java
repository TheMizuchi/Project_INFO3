package model.entity;

import model.Model;


public class TypeEntity {

	public static final int Allié = 1;
	public static final int Ennemi = 2;
	public static final int Neutre = 3;
	public static final int Obstacle = 4;
	public static final int Item = 5;
	// ajouter un type particulier pour les boss ?

	private int m_type; // de 1 à 5, allié, ennemi, neutre ou obstacle
	private int m_typeOrigine; // type à la création de l'entité
	private int m_intialPv;


	public TypeEntity (int ID) {

		switch (ID) {
			case Model.COWBOY_ID:
				m_type = Allié;
				m_intialPv = 1;
				break;
			case Model.J1_ID:
				m_type = Allié;
				m_intialPv = 1;
				break;
			case Model.J2_ID:
				m_type = Allié;
				m_intialPv = 1;
				break;
			case Model.BLOON_ID:
				m_type = Ennemi;
				m_intialPv = 1;
				break;
			case Model.ZOMBIE_ID:
				m_type = Ennemi;
				m_intialPv = 1;
				break;
			case Model.DART_MONKEY_ID:
				m_type = Ennemi;
				m_intialPv = 1;
				break;
			case Model.BAT_ID:
				m_type = Neutre;
				m_intialPv = 1;
				break;
			/*
			 * case block m_type = Obstacle;
			 * 
			 * case torche m_type = Item;
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

	public int getInitialPv () {
		return m_intialPv;
	}

	public void devientGentil () {
		m_type = Allié;
	}

	public void restaureType () {
		m_type = m_typeOrigine;
	}
}
