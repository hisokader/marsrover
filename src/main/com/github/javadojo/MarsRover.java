package com.github.javadojo;

import java.util.List;

/**
 * The Mars rover is programmed to drive around Mars. Its programming is very
 * simple. The commands are the following:
 * <dl>
 * <dt>s</dt>
 * <dd>drive in a straight line</dd>
 * <dt>r</dt>
 * <dd>turn right</dd>
 * <dt>l</dt>
 * <dd>turn left</dd>
 * </dl>
 *
 * Note that the Mars rover always land at the <code>X</code> and starts by
 * facing east.
 * 
 * The Mars rover can send a 2D string representation of its path back to
 * Mission Control. The following character are used with the following
 * meanings:
 * <dl>
 * <dt>X</dt>
 * <dd>where the Mars rover landed</dd>
 * <dt>*</dt>
 * <dd>current position of the Mars rover</dd>
 * <dt>-</dt>
 * <dd>path in the west-east direction</dd>
 * <dt>|</dt>
 * <dd>path in the north-south direction</dd>
 * <dt>+</dt>
 * <dd>a place where the Mars rover turned or a crossroad</dd>
 * <dt>S</dt>
 * <dd>a place where a sample was taken</dd>
 * </dl>
 */
public class MarsRover {

	static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private final List<Move> moves;

	public MarsRover(String operations) {
		MovesParser parser = new MovesParser(operations);
		moves = parser.parseMultipleMoves();
	}

	public String path() {
		PathTracer path = new PathTracer(moves);
		return path.printPath();
	}

	public MarsRover turnLeft() {
		moves.add(Move.LEFT);
		return this;
	}

	public MarsRover turnRight() {
		moves.add(Move.RIGHT);
		return this;
	}

	public MarsRover moveForward() {
		moves.add(Move.FORWARD);
		return this;
	}

	public MarsRover takeSample() {
		moves.add(Move.TAKEN_SIMPLE);
		return this;
	}
}
