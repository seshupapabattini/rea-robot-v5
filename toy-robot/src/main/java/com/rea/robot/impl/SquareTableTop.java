package com.rea.robot.impl;

import com.rea.robot.util.Status;

public class SquareTableTop {

	int rows;
	int columns;

	public SquareTableTop(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}

	public boolean isValidPosition(Status status) {
		return !(status.getxPosition() > this.columns || status.getxPosition() < 0 || status.getyPosition() > this.rows
				|| status.getyPosition() < 0);
	}

}
