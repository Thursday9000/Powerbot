package org.thurs.dagonhai.resources;

public enum FoodType {
	SHRIMP(315),
	LOBSTER(379),
	SWORDFISH(373),
	MONKFISH(7946),
	SHARK(385),
	ROCKTAIL(15272);
	
	public final int value;
	public final String name;
	
	FoodType(int value) {
		this.value = value;
		this.name = super.name().charAt(0) + super.name().substring(1).toLowerCase();
	}
	public String toString() {
		return name;
	}
}
