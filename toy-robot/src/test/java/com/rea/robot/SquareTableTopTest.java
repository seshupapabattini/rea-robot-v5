package com.rea.robot;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import com.rea.robot.impl.SquareTableTop;
import com.rea.robot.util.Status;


public class SquareTableTopTest {
	@Test
    public void testIsValidPosition() throws Exception {
        Status mockPosition = mock(Status.class);
        when(mockPosition.getxPosition()).thenReturn(6);
        when(mockPosition.getyPosition()).thenReturn(7);

        SquareTableTop board = new SquareTableTop(4, 5);
        Assert.assertFalse(board.isValidPosition(mockPosition));


        when(mockPosition.getxPosition()).thenReturn(1);
        when(mockPosition.getyPosition()).thenReturn(1);
        Assert.assertTrue(board.isValidPosition(mockPosition));


        when(mockPosition.getxPosition()).thenReturn(-1);
        when(mockPosition.getyPosition()).thenReturn(-1);
        Assert.assertFalse(board.isValidPosition(mockPosition));
    }
}
