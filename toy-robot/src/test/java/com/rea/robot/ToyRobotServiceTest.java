package com.rea.robot;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.rea.robot.exception.RobotException;
import com.rea.robot.util.Movement;
import com.rea.robot.util.Status;

public class ToyRobotServiceTest {

	@Rule
	public org.junit.rules.ExpectedException thrown = ExpectedException.none();

	@Test
	public void testMovement() throws RobotException {

		ToyRobotService toyRobotService = new ToyRobotService(new Status(0, 0, Movement.NORTH));

		Assert.assertTrue(toyRobotService.move());
		Assert.assertEquals(0, toyRobotService.getStatus().getxPosition());
		Assert.assertEquals(1, toyRobotService.getStatus().getyPosition());
		Assert.assertEquals(Movement.NORTH, toyRobotService.getStatus().getMovement());

		toyRobotService.setPosition(new Status(1, 2, Movement.EAST));
		toyRobotService.move();
		toyRobotService.move();
		toyRobotService.rotateLeft();
		toyRobotService.move();

		Assert.assertEquals(3, toyRobotService.getStatus().getxPosition());
		Assert.assertEquals(3, toyRobotService.getStatus().getyPosition());
		Assert.assertEquals(Movement.NORTH, toyRobotService.getStatus().getMovement());

	}

}
