package view.animation.bank;

/* Singleton définissant les banques d'animations pour chacune des entités */

public class AnimationBank {
	
	public static final int COWBOYID = 0;
	public static final int J1ID = 1;
	public static final int J2ID = 2;
	public static final int BLOONID = 3;
	public static final int ZOMBIEID = 4;
	public static final int BATID = 5;
	public static final int DARTMONKEYID = 6;
	
	private static final int ENTITY_NUMBER = 7; 
	
	private static EntityAnimationBank EAB[] ;
	
	private AnimationBank() {
		EAB = new EntityAnimationBank[ENTITY_NUMBER];
		EAB[COWBOYID] = CowboyBank.getInstance();
//		EAB[J1ID] = new J1Bank();
//		EAB[J2ID] = new J2Bank();
//		EAB[BLOONID] = new BloonBank();
//		EAB[ZOMBIEID] = new ZombieBank();
//		EAB[BATID] = new BatBank();
//		EAB[DARTMONKEYID] = new DartMonkeyBank();
				
	}
	
	private static AnimationBank INSTANCE = null;
	
	public static AnimationBank getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AnimationBank();
		}
		return INSTANCE;
	}
	
	
	public static EntityAnimationBank getAnimationBank(int ID) {
		if(ID<0 || ID>=ENTITY_NUMBER) return null;
		return EAB[ID];
		
	}
}
