package view.animation.bank;

import model.entity.EntityProperties;

/* Singleton définissant les banques d'animations pour chacune des entités */


public class AnimationBank {

	private static EntityAnimationBank EAB[];


	private AnimationBank () {
		EAB = new EntityAnimationBank[EntityProperties.ENTITY.getID()];
		EAB[EntityProperties.COWBOY.getID()] = CowboyBank.getInstance();
		EAB[EntityProperties.J1.getID()] = J1Bank.getInstance();
		EAB[EntityProperties.J2.getID()] = J2Bank.getInstance();
		EAB[EntityProperties.BLOON.getID()] = BloonBank.getInstance();
		EAB[EntityProperties.SKELETON.getID()] = SkeletonBank.getInstance();
		EAB[EntityProperties.BAT.getID()] = BatBank.getInstance();
		//		EAB[DARTMONKEYID] = new DartMonkeyBank();
		EAB[EntityProperties.TORCH.getID()] = TorchBank.getInstance();

	}


	private static AnimationBank INSTANCE = null;


	public static AnimationBank getInstance () {

		if (INSTANCE == null) {
			INSTANCE = new AnimationBank();
		}
		return INSTANCE;
	}

	public static EntityAnimationBank getAnimationBank (int ID) {
		if (ID < 0 || ID >= EntityProperties.ENTITY.getID())
			return null;
		return EAB[ID];

	}
}
