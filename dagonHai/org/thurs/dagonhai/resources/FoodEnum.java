package org.thurs.dagonhai.resources;

public enum FoodEnum {
	Shrimp(315), Lobster(379), Swordfish(373), Monkfish(7946), Shark(385), Rocktail(
			15272);
	FoodEnum(int value) {
		this.value = value;
	}

	public final int value;

}
