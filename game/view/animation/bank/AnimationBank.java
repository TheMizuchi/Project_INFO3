package view.animation.bank;

import model.Model;

/* Singleton définissant les banques d'animations pour chacune des entités */


public class AnimationBank {

	private static EntityAnimationBank EAB[];


	private AnimationBank () {
		EAB = new EntityAnimationBank[Model.ENTITY_NUMBER];
		EAB[Model.COWBOY_ID] = CowboyBank.getInstance();
		//		EAB[J1ID] = new J1Bank();
		//		EAB[J2ID] = new J2Bank();
		//		EAB[BLOONID] = new BloonBank();
		//		EAB[ZOMBIEID] = new ZombieBank();
		//		EAB[BATID] = new BatBank();
		//		EAB[DARTMONKEYID] = new DartMonkeyBank();

	}


	private static AnimationBank INSTANCE = null;


	public static AnimationBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new AnimationBank();
		}
		return INSTANCE;
	}

	public static EntityAnimationBank getAnimationBank (int ID) {
		if (ID < 0 || ID >= Model.ENTITY_NUMBER)
			return null;
		return EAB[ID];

	}
}
