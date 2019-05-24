package com.rea.robot.util;

import com.rea.robot.exception.RobotException;

public class Status {

	int xPosition;
	int yPosition;
	Movement movement;

	public Status(Status status) {
		this.xPosition = status.getxPosition();
		this.yPosition = status.getyPosition();
		this.movement = status.getMovement();
	}

	public Status(int x, int y, Movement movement) {
		this.xPosition = x;
		this.yPosition = y;
		this.movement = movement;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public Movement getMovement() {
		return this.movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	public void change(int x, int y) {
		this.xPosition = this.xPosition + x;
		this.yPosition = this.yPosition + y;
	}

	public Status getNextStatus() throws RobotException {
		if (this.movement == null)
			throw new RobotException("Invalid robot Movement");

		Status newstatus = new Status(this);
		switch (this.movement) {
		case NORTH:
			newstatus.change(0, 1);
			break;
		case EAST:
			newstatus.change(1, 0);
			break;
		case SOUTH:
			newstatus.change(0, -1);
			break;
		case WEST:
			newstatus.change(-1, 0);
			break;
		}
		return newstatus;
	}

}
