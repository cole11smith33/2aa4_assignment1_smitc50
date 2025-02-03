package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandRule implements PathfindingAlgorithm{
    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public void findPath(Player player, Maze maze){
        
        StringBuilder movementPath = new StringBuilder();
        logger.info("**** Computing path");
        while (true) { 
            maze.printMaze();
            if(player.canMoveRight(maze.getMaze())){
                player.rotateRight(maze.getMaze());
                player.moveForward(maze.getMaze());
                movementPath.append("RF");
            }
            else if(player.canMoveForward(maze.getMaze())){
                player.moveForward(maze.getMaze());
                movementPath.append("F");
            }
            else{
                player.rotateLeft(maze.getMaze());
                movementPath.append("L");
            }
            if (maze.gameOver() == true){
                logger.info("** PATH VALID");
                break; //path validation 
            }
        }
        ConvertPath convertPath = new ConvertPath(this);
        logger.info("Valid Path: " + convertPath.convertToFactorized(movementPath));
    }
}
