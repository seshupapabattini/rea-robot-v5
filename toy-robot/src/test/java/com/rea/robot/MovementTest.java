package com.rea.robot;

import org.junit.Assert;
import org.junit.Test;

import com.rea.robot.util.Movement;

public class MovementTest {
	@Test
    public void testRotate() throws Exception {
        Movement movement = Movement.EAST;

        movement = movement.leftMovement();
        Assert.assertEquals(movement, Movement.NORTH);

        movement = movement.leftMovement();
        Assert.assertEquals(movement, Movement.WEST);

        movement = movement.leftMovement();
        Assert.assertEquals(movement, Movement.SOUTH);

        movement = movement.leftMovement();
        Assert.assertEquals(movement, Movement.EAST);

        movement = movement.leftMovement();
        Assert.assertEquals(movement, Movement.NORTH);

        movement = movement.rightMovement();
        Assert.assertEquals(movement, Movement.EAST);

        movement = movement.rightMovement();
        Assert.assertEquals(movement, Movement.SOUTH);

        movement = movement.rightMovement();
        Assert.assertEquals(movement, Movement.WEST);

        movement = movement.rightMovement();
        Assert.assertEquals(movement, Movement.NORTH);

        movement = movement.rightMovement();
        Assert.assertEquals(movement, Movement.EAST);
    }
}
