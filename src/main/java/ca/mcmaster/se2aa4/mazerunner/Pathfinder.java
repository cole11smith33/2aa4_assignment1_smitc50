package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pathfinder {
    private static final Logger logger = LogManager.getLogger();
    private String pathway = "";
    private int index = 0;
    private List<List<Character>> mazeMap;

    public Pathfinder(List<List<Character>> mazeMap, int startX, int startY, int exitY) {
        this.mazeMap = mazeMap;
        this.index = index;
    }

    //refactor to move the findPathway function to here

    public Pathfinder(String pathway) {
        this.pathway = pathway;
        this.index = index;
    }
    
    public StringBuilder convertToFactorized(StringBuilder pathway) {
        StringBuilder factorizedPathway = new StringBuilder();
        int count = 1;
        //iterate through pathway and convert all movements
        for(int movement = 1; movement <= pathway.length(); movement++) {
            if(movement < pathway.length() && pathway.charAt(movement) == pathway.charAt(movement - 1)){ // if next character is the same as current character, increment counter
                count++;
            }
            else{ //formats a set of repetitive movements in the factorized form
                if(count > 1){ factorizedPathway.append(count); }
                factorizedPathway.append(pathway.charAt(movement - 1));
                count = 1; //reset count back to one
                factorizedPathway.append(' ');
            }
        }
        return factorizedPathway;
    }

    public void convertToStandard(String path){
        StringBuilder pathway = new StringBuilder(path);
        StringBuilder standard = new StringBuilder();
        int movement = 0;

        while (movement < pathway.length()){
            char character = pathway.charAt(movement);
            if (character == ' '){ //bug here that needs to be fixed in morning
                character = pathway.charAt(movement+1);
            }
            else if (Character.isDigit(character)){
                int count = Character.getNumericValue(character);
                if (movement + 1 < pathway.length()){
                    char move = pathway.charAt(movement + 1);
                    standard.append(String.valueOf(move).repeat(count));
                    movement = movement + 2;
                }
            }
            else{
                standard.append(character);
                movement++;
            }     
        }
        this.pathway = standard.toString();
    }

    public char readInput(){
        try { //index through maze until an index error appears, then you know you have traveled through the whole maze
            char currMove = pathway.charAt(index);
            index++;
            return currMove;
        } catch (Exception e) {
            return '0'; //end of pathway
        }
    }
    public void predefinedPath(Pathfinder pathfinder, String pathway, Player player, Maze maze){
        logger.trace(pathway);
        pathfinder = new Pathfinder(pathway);
        pathfinder.convertToStandard(pathway);
        maze.printMaze();
        boolean exit = false;

        while (true){
            switch (pathfinder.readInput()) {
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
            logger.info("** PATH VALID"); //path validation 
        }
    }
    public void rightHandRule(Pathfinder pathfinder, String pathway, Player player, Maze maze){
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
        logger.info("Valid Path: " + pathfinder.convertToFactorized(movementPath));
    }
}
