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
        CommandManager commandManager = new CommandManager();
        convertPath.convertToStandard(pathway);

        maze.printMaze();
        boolean exit = false;

        while (true){
            switch (convertPath.readInput()) { //move according to provided path
                case 'F' -> commandManager.executeCommand(new MoveForwardCommand(player, maze.getMaze()));
                case 'R' -> commandManager.executeCommand(new TurnRightCommand(player, maze.getMaze()));
                case 'L' -> commandManager.executeCommand(new TurnLeftCommand(player, maze.getMaze()));
                case '0' -> {exit = true; break; } //end of input pathway reached
            }
            if (exit == true){ //end of input pathway has been reached so end traversal
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
