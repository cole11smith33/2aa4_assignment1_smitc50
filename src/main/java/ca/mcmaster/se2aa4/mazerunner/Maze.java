package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;

public class Maze {

    private static final Logger logger = LogManager.getLogger();
    private String filepath;
    private BufferedReader reader;
    private int entranceY;
    private int exitY;

    private List<List<Character>> mazeMap;

    //Constructor for maze class
    public Maze (String filepath){
        this.mazeMap = new ArrayList<>();
        this.filepath = filepath;
        try {
            this.reader = new BufferedReader(new FileReader(filepath));
            createMaze();
            this.entranceY = findEntrance();
            this.exitY = findExit();
        }
        catch (IOException e){
            logger.error("Unable to open file:" + filepath, e);
        } 
    }

    public List<List<Character>> getMaze(){
        return mazeMap;
    }

    public void setMaze(List<List<Character>> mazeMap){
        this.mazeMap = mazeMap;
    }

    public void createMaze(){
        String line;
        try {
            while((line = reader.readLine()) != null) { //adapted from provided code in main.java
                List<Character> mazeRow = new ArrayList<>();
                for (int idx = 0; idx < line.length(); idx++){
                    if (line.charAt(idx) == '#' || line.charAt(idx) == ' '){
                        mazeRow.add(line.charAt(idx));
                    }
                }
                mazeMap.add(mazeRow);
            }
        } catch (IOException e) {
            logger.error("/!\\ A bad error has occured /!\\");
        }
    }

    public void printMaze(){
        for(List<Character> row : mazeMap){
            logger.info(row.toString());
        }
    }

    public int  findEntrance(){
        int y = 0;
        for(List<Character> row: mazeMap) {
            if (row.get(0) == ' '){
                logger.trace(y);
                return y;
            }
            else{
                y++;
            }
        }
        return y;
    }

    public int findExit(){
        int y = 0;
        for(List<Character> row: mazeMap) {
            if (row.get(row.size()-1) == ' '){ //checks last character in each row
                logger.trace(y);
                return y;
            }
            else{
                y++;
            }
        }
        return y;
    }
}
