package com.rea.robot;

import com.rea.robot.exception.RobotException;
import com.rea.robot.impl.SquareTableTop;
import com.rea.robot.util.Commands;
import com.rea.robot.util.Movement;
import com.rea.robot.util.Status;

public class CommandsEvalService {

	SquareTableTop squareTableTop;
	ToyRobotService toyRobotService;

	public CommandsEvalService(SquareTableTop squareTableTop, ToyRobotService toyRobotService) {
		this.squareTableTop = squareTableTop;
		this.toyRobotService = toyRobotService;
	}

	public boolean placeToyRobot(Status status) throws RobotException {

		if (squareTableTop == null)
			throw new RobotException("Invalid SquareTableTop object");

		if (status == null)
			throw new RobotException("Invalid status object");

		if (status.getMovement() == null)
			throw new RobotException("Invalid movement value");

		if (!squareTableTop.isValidPosition(status))
			return false;

		toyRobotService.setPosition(status);
		return true;
	}

	public String evaluate(String inputString) throws RobotException {
		String[] args = inputString.split(" ");

		Commands command;
		try {
			command = Commands.valueOf(args[0]);
		} catch (IllegalArgumentException e) {
			throw new RobotException("Invalid command");
		}
		if (command == Commands.PLACE && args.length < 2) {
			throw new RobotException("Invalid command");
		}

		String[] params;
		int x = 0;
		int y = 0;
		Movement commandDirection = null;
		if (command == Commands.PLACE) {
			params = args[1].split(",");
			try {
				x = Integer.parseInt(params[0]);
				y = Integer.parseInt(params[1]);
				commandDirection = Movement.valueOf(params[2]);
			} catch (Exception e) {
				throw new RobotException("Invalid command");
			}
		}

		String output;

		switch (command) {
		case PLACE:
			output = String.valueOf(placeToyRobot(new Status(x, y, commandDirection)));
			break;
		case MOVE:
			Status newStatus = toyRobotService.getStatus().getNextStatus();
			if (!squareTableTop.isValidPosition(newStatus))
				output = String.valueOf(false);
			else
				output = String.valueOf(toyRobotService.move(newStatus));
			break;
		case LEFT:
			output = String.valueOf(toyRobotService.rotateLeft());
			break;
		case RIGHT:
			output = String.valueOf(toyRobotService.rotateRight());
			break;
		case REPORT:
			output = report();
			break;
		default:
			throw new RobotException("Invalid command");
		}

		return output;
	}

	public String report() {
		if (toyRobotService.getStatus() == null)
			return null;

		return toyRobotService.getStatus().getxPosition() + "," + toyRobotService.getStatus().getyPosition() + ","
				+ toyRobotService.getStatus().getMovement().toString();
	}

}
