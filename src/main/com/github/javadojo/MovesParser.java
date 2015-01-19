package com.github.javadojo;

import java.util.ArrayList;
import java.util.List;

public class MovesParser {
	private final String movesToParse;
	
	public MovesParser(String movesToParse) {
		this.movesToParse = movesToParse;
	}

	public static Move parseASingleMoves(String move) {
		return MovesFactory.create(move.charAt(0));
	}

	public List<Move> parseMultipleMoves() {
		final List<Move> parsedMoves = new ArrayList<Move>();
		Move parseASingleMoves;
		for (int i = 0; i < movesToParse.length(); i++) {
			parseASingleMoves = parseASingleMoves(movesToParse.charAt(i) + "");
			parsedMoves.add(parseASingleMoves);
		}
		return parsedMoves;
	}
}
