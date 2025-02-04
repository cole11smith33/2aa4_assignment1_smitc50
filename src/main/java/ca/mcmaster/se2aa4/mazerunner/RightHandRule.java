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
            if(player.canMoveRight(maze.getMaze())){ //see if the player can turn right
                player.rotateRight(maze.getMaze());
                player.moveForward(maze.getMaze());
                movementPath.append("RF"); //add to path string for future output
            }
            else if(player.canMoveForward(maze.getMaze())){ //move forward if turning right isn't an option
                player.moveForward(maze.getMaze());
                movementPath.append("F");//add to path string for future output
            }
            else{ //turn left because stuck
                player.rotateLeft(maze.getMaze());
                movementPath.append("L");//add to path string for future output
            }
            if (maze.gameOver() == true){ //check if the player is in the exit position of the maze
                logger.info("** PATH VALID");
                break; //path validation 
            }
        }
        ConvertPath convertPath = new ConvertPath(this);
        System.out.println(convertPath.convertToFactorized(movementPath)); //convert the path to factorized form and output
    }
}
