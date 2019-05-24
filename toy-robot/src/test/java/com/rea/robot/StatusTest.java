package com.rea.robot;

import org.junit.Assert;
import org.junit.Test;

import com.rea.robot.util.Movement;
import com.rea.robot.util.Status;

public class StatusTest {
	@Test
	public void testGetNextPosition() throws Exception {
		Status status = new Status(0, 0, Movement.EAST);

		Status newStatus = status.getNextStatus();
		Assert.assertEquals(newStatus.getxPosition(), 1);
		Assert.assertEquals(newStatus.getyPosition(), 0);
		Assert.assertEquals(newStatus.getMovement(), Movement.EAST);

	}
}
