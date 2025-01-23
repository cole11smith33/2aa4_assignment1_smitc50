package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Player player1 = new Player();
        


        Options options = new Options();
        Option flag = Option.builder("i")
            .hasArg()
            .required() //requires that a argument be given
            .desc("Filepath to the maze")
            .build();

        options.addOption(flag); // add flag option
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args); //parse command line
            logger.info("** Starting Maze Runner");
            String filepath = commandLine.getOptionValue("i");





            try {
                logger.info("**** Reading the maze from file " + filepath);
                BufferedReader reader = new BufferedReader(new FileReader(filepath));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            logger.trace("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            logger.trace("PASS ");
                        }
                    }
                    logger.trace(System.lineSeparator());
                }
            } catch(Exception e) {
                logger.error("/!\\ An error has occured /!\\");
            }
            Maze maze = new Maze(filepath);
            maze.createMaze();
            maze.printMaze();
            
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        logger.info("**** Computing path");
        logger.warn("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
