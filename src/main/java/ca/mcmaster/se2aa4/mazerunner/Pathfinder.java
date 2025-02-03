package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pathfinder {
    private static final Logger logger = LogManager.getLogger();
    private String pathway = "FLFRFFLFFFFFFRFFFFRFFLFFRFFLF";
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
