package com.rea.robot.util;

import java.util.HashMap;
import java.util.Map;

public enum Movement {

	NORTH(0), EAST(1), SOUTH(2), WEST(3);
	private int movementIndex;
	private static Map<Integer, Movement> map = new HashMap<Integer, Movement>();

	static {
		for (Movement movementEnum : Movement.values()) {
			map.put(movementEnum.movementIndex, movementEnum);
		}
	}

	private Movement(int movement) {
		this.movementIndex = movement;
	}

	public static Movement valueOf(int movementVal) {
		return map.get(movementVal);
	}

	public Movement leftMovement() {
		return turn(-1);
	}

	public Movement rightMovement() {
		return turn(1);
	}

	private Movement turn(int move) {

		int newMove = (this.movementIndex + move) < 0 ? map.size() - 1 : (this.movementIndex + move) % map.size();

		return Movement.valueOf(newMove);
	}

}
