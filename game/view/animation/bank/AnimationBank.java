package view.animation.bank;

import model.Model;

/* Singleton définissant les banques d'animations pour chacune des entités */


public class AnimationBank {

	private static EntityAnimationBank EAB[];


	private AnimationBank () {
		EAB = new EntityAnimationBank[Model.ENTITY_NUMBER];
		EAB[Model.COWBOY_ID] = CowboyBank.getInstance();
		EAB[Model.J1_ID] = J1Bank.getInstance();
		EAB[Model.J2_ID] = J2Bank.getInstance();
		EAB[Model.BLOON_ID] = BloonBank.getInstance();
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
