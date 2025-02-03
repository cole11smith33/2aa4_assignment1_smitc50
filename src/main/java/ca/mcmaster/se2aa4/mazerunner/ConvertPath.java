package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConvertPath {
    private PathfindingAlgorithm algorithm;
    private static final Logger logger = LogManager.getLogger();
    private String pathway = "";
    private int index = 0;

    public ConvertPath(PathfindingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void pathfind(Player player, Maze maze){
        algorithm.findPath(player, maze);
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
}
