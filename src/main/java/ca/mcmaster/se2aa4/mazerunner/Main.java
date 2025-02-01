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
        


        Options options = new Options();
        Option flag = Option.builder("i")
            .hasArg()
            .required() //requires that a argument be given
            .desc("Filepath to the maze")
            .build();

        Option pFlag = Option.builder("p")
            .hasArg()
            .desc("Path through maze")
            .build();

        options.addOption(flag); // add filepath flag option
        options.addOption(pFlag); // add path flag option
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args); //parse command line
            logger.info("** Starting Maze Runner");
            String filepath = commandLine.getOptionValue("i");
            Pathfinder pathfinder;
            if (commandLine.hasOption("p")){
                String pathway = commandLine.getOptionValue("p");
                logger.trace(pathway);
                pathfinder = new Pathfinder(pathway);
            }
            else{
                pathfinder = new Pathfinder();
            }


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
            Player player1 = new Player(maze.findEntrance());
            player1.placePlayer(maze.getMaze());
            maze.printMaze();
            boolean exit = false;

            while (true){
                switch (pathfinder.readInput()) {
                    case 'F' -> player1.moveForward(maze.getMaze());
                    case 'R' -> player1.rotateRight(maze.getMaze());
                    case 'L' -> player1.rotateLeft(maze.getMaze());
                    case '0' -> {exit = true; break; }
                }
                if (exit == true){
                    break;
                }
                maze.printMaze();
                logger.info("\n");
            }


            
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        logger.info("**** Computing path");
        logger.warn("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
