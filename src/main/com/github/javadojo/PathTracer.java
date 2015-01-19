package com.github.javadojo;

import static com.github.javadojo.MarsRover.LINE_SEPARATOR;

import java.util.List;

public class PathTracer {
	private static final String VERTICAL_MOVE = "|";
	private static final String HORISONTAL_MOVE = "-";
	private static final char SOUTH = 'S';
	private static final char WEST = 'W';
	private static final char NORTH = 'N';
	private static final char EAST = 'E';
	private static final String LANDING_POSITION = "*";
	private static final String START_POSITION = "X";
	private final List<Move> path;
	private int lastLineLength = 0;
	private char direction = 'E';
	private String lastMove = "";

	public PathTracer(List<Move> path) {
		this.path = path;
	}

	public String printPath() {
		final StringBuilder printedValue = new StringBuilder();
		printedValue.append(START_POSITION);
		refreshDirection(path.get(0));
		for (int i = 1; i < path.size(); i++) {
			lastMove = printMove(path.get(i), direction, printedValue);
		}
		appendLandingPosition(printedValue);
		printedValue.append(LINE_SEPARATOR);
		return printedValue.toString();
	}

	private void refreshDirection(Move move) {
		if (Move.RIGHT.equals(move)) {
			switch (direction) {
			case EAST:
				direction = SOUTH;
				break;
			case SOUTH:
				direction = WEST;
				break;
			case WEST:
				direction = NORTH;
				break;
			default:
				direction = EAST;
			}
		} else if(Move.LEFT.equals(move)) {
			switch (direction) {
			case EAST:
				direction = NORTH;
				break;
			case NORTH:
				direction = WEST;
				break;
			case WEST:
				direction = SOUTH;
				break;

			default:
				direction = EAST;
			}
		}
	}

	private void appendLandingPosition(StringBuilder printedValue) {
		if (direction == EAST)
			printedValue.append(LANDING_POSITION);
		else if (direction == WEST)
			printedValue.insert(0, LANDING_POSITION);
		else if (direction == NORTH)
			printedValue.append("\n" + LANDING_POSITION);
		else
			printedValue.insert(0, LANDING_POSITION + "\n");
	}

	private String printMove(Move move, char direction, StringBuilder path) {
		if (Move.FORWARD.equals(move)) {
			if (direction == 'E' || direction == 'W') {
				path.append(HORISONTAL_MOVE);
				return HORISONTAL_MOVE;
			} else {
				path.append(VERTICAL_MOVE);
				return VERTICAL_MOVE;
			}
		} else {
			return turn(move, direction, path);
		}
	}

	private String turn(Move move, char direction, StringBuilder pathBuilder) {
		lastLineLength = pathBuilder.length() - lastLineLength;
		String moveToPrint = "";
		if (direction == 'E') {
			if (Move.RIGHT.equals(move)) {
				moveToPrint = "\n" + printSpace(lastLineLength) + VERTICAL_MOVE;
				pathBuilder.append("+");
				pathBuilder.append(moveToPrint);
				direction = 'N';
			} else {
				moveToPrint = printSpace(lastLineLength) + "|\n";
				pathBuilder.append("+");
				pathBuilder.insert(0, moveToPrint);
				direction = 'S';
			}

		} else if (direction == 'W') {
			if (Move.RIGHT.equals(move)) {
				moveToPrint = "\n" + printSpace(lastLineLength) + VERTICAL_MOVE;
				pathBuilder.append("+");
				pathBuilder.append(moveToPrint);
			} else {
				moveToPrint = "|\n";
				pathBuilder.append("+");
			}
			pathBuilder.insert(0, moveToPrint);
			return moveToPrint;
		}

		return null;
	}

	private String printSpace(int lastStraightPathLength) {
		StringBuilder spaces = new StringBuilder();
		for (int i = 0; i < lastStraightPathLength; i++) {
			spaces.append(" ");
		}
		return spaces.toString();
	}

}
