package model.entity;


public abstract class Entity {
	
	private Hitbox hitbox ; 
	// Liste d'items 
	
	void move () {
		
	}
	
	abstract void attack();
	
	abstract void interact() ; 
	
	
}
