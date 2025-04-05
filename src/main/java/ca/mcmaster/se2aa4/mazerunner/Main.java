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
import org.w3c.dom.UserDataHandler;

import ca.mcmaster.se2aa4.mazerunner.*;

public class Main{
    private final String filepath;
    private final String pathway;
    private final Maze maze;
    private final Player player1;
    private final PathfindingAlgorithm algorithm;
    private static final Logger logger = LogManager.getLogger();

    public Main (MazerunnerBuilder builder) {
        this.filepath = builder.filepath;
        this.pathway = builder.pathway;
        this.maze = builder.maze;
        this.player1 = builder.player1;
        this.algorithm = builder.algorithm;
    }

    public static class MazerunnerBuilder{   
        private String filepath;
        private String pathway;
        private Maze maze;
        private Player player1;
        private PathfindingAlgorithm algorithm;
        
        public MazerunnerBuilder(String filepath) {
            this.filepath = filepath;
        }

        public MazerunnerBuilder setPathway(String pathway) {
            this.pathway = pathway;
            return this;
        }

        public MazerunnerBuilder setMaze(Maze maze) {
            this.maze = maze;
            return this;
        }

        public MazerunnerBuilder setPlayer(Player player1) {
            this.player1 = player1;
            return this;
        }

        public MazerunnerBuilder setAlgorithm(PathfindingAlgorithm algorithm) {
            this.algorithm = algorithm;
            return this;
        }
        public Main build() {
            return new Main(this);
        }
    }

    private void run () {

            try {
                logger.info("**** Reading the maze from file " + filepath);
                BufferedReader reader = new BufferedReader(new FileReader(filepath));
                String line;
                while ((line = reader.readLine()) != null) {

                    for (int idx = 0; idx < line.length(); idx++) {

                        if (line.charAt(idx) == '#') {
                            logger.trace("WALL ");
                        }
                        else if (line.charAt(idx) == ' ') {
                            logger.trace("PASS ");
                        }
                    }

                    logger.trace(System.lineSeparator());
                }

            } catch(Exception e) {
                logger.error("/!\\ An error has occured /!\\");
            }

            player1.placePlayer(maze.getMaze()); //place player in maze            
            ConvertPath pathfinder = new ConvertPath(algorithm);
            pathfinder.pathfind(player1, maze); //find or validate the path depending on if -p is present (Liskov Substitution Principle shown here)
    }


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
            String pathway = commandLine.getOptionValue("p");

            PathfindingAlgorithm algorithm;
            if (commandLine.hasOption("p")){ //if path is given, then use the UserProvidedPath algorithm
                algorithm = new UserProvidedPath(pathway);
            }
            else { //if the path isn't given, the use the RightHandRule algorithm
                algorithm = new RightHandRule();
            }

            Maze maze = new Maze(filepath); //create maze object
            Player player1 = new Player(maze.findEntrance()); //create player object

            Main mazeRunner = new MazerunnerBuilder(filepath) //build a maze runner
                .setPathway(pathway)
                .setMaze(maze)
                .setPlayer(player1)
                .setAlgorithm(algorithm)
                .build();

            mazeRunner.run(); //run the maze

        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        logger.info("** End of MazeRunner");
    }
}

