package com.rea.robot;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.rea.robot.exception.RobotException;
import com.rea.robot.impl.SquareTableTop;

public class ToyRobot {

	final static Logger logger = Logger.getLogger(ToyRobot.class.getName());

	// TODO can change the file location
	private static String fileName = System.getProperty("user.home") + File.separator + "rea-robot.txt";

	public static void main(String[] args) {

		FileHandler fh;

		try {
			// TODO can change the log location
			fh = new FileHandler(System.getProperty("user.home") + File.separator + "rea.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

		} catch (SecurityException e) {
			logger.warning(e.getMessage());
		} catch (IOException e) {
			logger.warning(e.getMessage());
		}

		Console console = System.console();
		logger.warning("console" + console);

		if (console == null) {
			logger.warning("No console.");
			System.exit(1);
		}

		SquareTableTop squareTableTop = new SquareTableTop(5, 5);
		ToyRobotService toyRobotService = new ToyRobotService();
		CommandsEvalService commandsEvalService = new CommandsEvalService(squareTableTop, toyRobotService);

		logger.info("Toy Robot Simulator");
		logger.info("Enter a command, Valid commands are:");
		logger.info("\'PLACE X,Y,F\', MOVE, LEFT, RIGHT, REPORT or EXIT or FILE");

		boolean keepRunning = true;
		while (keepRunning) {
			String inputString = console.readLine(": ");
			logger.warning("inputString : " + inputString);
			if ("FILE".equalsIgnoreCase(inputString)) {
				String outputVal = null;
				try {
					File file;
					BufferedReader br = null;
					try {
						file = new File(fileName);
						if (!file.exists()) {
							logger.warning(" ******* Please create the file in home dir ["
									+ System.getProperty("user.home")
									+ "] with name rea-robot.txt and valid input(refer README.md) in it ******");
							continue;
						}
						br = new BufferedReader(new FileReader(file));
						String st;
						while ((st = br.readLine()) != null) {
							logger.info(st);
							outputVal = commandsEvalService.evaluate(st);
							logger.info(outputVal);
						}
					} catch (IOException e) {
						logger.warning(e.getMessage());
						keepRunning = false;
					} finally {
						try {
							if (br != null)
								br.close();
						} catch (IOException e) {
							logger.warning(e.getMessage());
							keepRunning = false;
						}
					}

				} catch (RobotException e) {
					logger.info(e.getMessage());
					keepRunning = false;
				}
			} else if ("EXIT".equals(inputString)) {
				keepRunning = false;
			} else {
				try {
					String outputVal = commandsEvalService.evaluate(inputString);
					logger.info(outputVal);
				} catch (RobotException e) {
					logger.info(e.getMessage());
				}
			}
		}
	}

}
