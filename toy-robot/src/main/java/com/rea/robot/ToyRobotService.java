package com.rea.robot;

import com.rea.robot.exception.RobotException;
import com.rea.robot.util.Status;

public class ToyRobotService {

	private Status status;

	public ToyRobotService() {
	}

	public ToyRobotService(Status status) {
		this.status = status;
	}

	public boolean setPosition(Status status) {
		if (status == null)
			return false;

		this.status = status;
		return true;
	}

	public boolean move() throws RobotException {
		return move(status.getNextStatus());
	}

	public boolean move(Status newStatus) {
		if (newStatus == null)
			return false;

		this.status = newStatus;
		return true;
	}

	public Status getStatus() {
		return this.status;
	}

	public boolean rotateLeft() {
		if (this.status.getMovement() == null)
			return false;

		this.status.setMovement(this.status.getMovement().leftMovement());
		return true;
	}

	public boolean rotateRight() {
		if (this.status.getMovement() == null)
			return false;

		this.status.setMovement(this.status.getMovement().rightMovement());
		return true;
	}

}
