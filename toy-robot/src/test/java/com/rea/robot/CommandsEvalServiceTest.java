package com.rea.robot;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.rea.robot.exception.RobotException;
import com.rea.robot.impl.SquareTableTop;

public class CommandsEvalServiceTest {

	final int BOARD_ROWS = 5;
	final int BOARD_COLUMNS = 5;
	@Rule
	public org.junit.rules.ExpectedException thrown = ExpectedException.none();


	@Test
	public void testEval() throws RobotException {

		SquareTableTop board = new SquareTableTop(BOARD_COLUMNS, BOARD_ROWS);
		ToyRobotService toyRobotService = new ToyRobotService();
		CommandsEvalService commandsEvalService = new CommandsEvalService(board, toyRobotService);

		commandsEvalService.evaluate("PLACE 0,0,NORTH");
		Assert.assertEquals("0,0,NORTH", commandsEvalService.evaluate("REPORT"));

		commandsEvalService.evaluate("MOVE");
		commandsEvalService.evaluate("RIGHT");
		commandsEvalService.evaluate("MOVE");
		Assert.assertEquals("1,1,EAST", commandsEvalService.evaluate("REPORT"));

		// if it goes out of the board it ignores the command
		for (int i = 0; i < 10; i++)
			commandsEvalService.evaluate("MOVE");
		Assert.assertEquals("5,1,EAST", commandsEvalService.evaluate("REPORT"));

		// rotate on itself
		for (int i = 0; i < 4; i++)
			commandsEvalService.evaluate("LEFT");
		Assert.assertEquals("5,1,EAST", commandsEvalService.evaluate("REPORT"));

		// invalid commands
		thrown.expect(RobotException.class);
		Assert.assertEquals("Invalid command", commandsEvalService.evaluate("PLACE12NORTH"));
		thrown.expect(RobotException.class);
		Assert.assertEquals("Invalid command", commandsEvalService.evaluate("LEFFT"));
		thrown.expect(RobotException.class);
		Assert.assertEquals("Invalid command", commandsEvalService.evaluate("RIGHTT"));
	}

}
