package com.github.javadojo;

public class MovesFactory {
	public static Move create(char move) {
		switch (move) {
		case 's':
			return Move.FORWARD;
		case 'l':
			return Move.LEFT;
		case 'r':
			return Move.RIGHT;
		default:
			return null;
		}
	}
}
