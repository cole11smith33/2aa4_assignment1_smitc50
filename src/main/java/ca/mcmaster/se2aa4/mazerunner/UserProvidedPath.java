package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserProvidedPath implements PathfindingAlgorithm{
    private static final Logger logger = LogManager.getLogger();
    private String pathway;

    public UserProvidedPath(String pathway){
        this.pathway = pathway;
    }

    @Override
    public void findPath(Player player, Maze maze){
        ConvertPath convertPath = new ConvertPath(this);
        convertPath.convertToStandard(pathway);

        maze.printMaze();
        boolean exit = false;

        while (true){
            switch (convertPath.readInput()) {
                case 'F' -> player.moveForward(maze.getMaze());
                case 'R' -> player.rotateRight(maze.getMaze());
                case 'L' -> player.rotateLeft(maze.getMaze());
                case '0' -> {exit = true; break; }
            }
            if (exit == true){
                break;
            }
            maze.printMaze();
            logger.trace("\n");
        }
        if (maze.gameOver() == true){
            System.out.println("correct path"); //path validation 
        }
        else{
            System.out.println("incorrect path"); 
        }
    }
    
}
