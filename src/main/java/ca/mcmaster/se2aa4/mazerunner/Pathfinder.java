package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pathfinder {
    private static final Logger logger = LogManager.getLogger();
    private String pathway = "FLFRFFLFFFFFFRFFFFRFFLFFRFFLF"; //Found manually through tracing maze
    private int index = 0;

    public Pathfinder(){
        this.pathway = pathway;
        this.index = index;
    }

    public Pathfinder(String pathway){
        this.pathway = pathway;
        this.index = index;
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
