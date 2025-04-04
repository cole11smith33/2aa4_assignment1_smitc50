package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger(); //initialize logger
    private String filepath; //filepath to the maze
    private BufferedReader reader; //reader for each line of maze
    private int entranceY; // y value of entrance on east side of maze
    private int exitY; // y value of exit on west side of maze
    private List<List<Character>> mazeMap;

    //Constructor for maze class
    public Maze (String filepath){
        this.mazeMap = new ArrayList<>();
        this.filepath = filepath;
        try {
            this.reader = new BufferedReader(new FileReader(filepath)); //read each line of file
            createMaze(); //initialize maze
            this.entranceY = findEntrance(); //find entrance of the maze
            this.exitY = findExit(); //find exit of the maze
        }
        catch (IOException e){
            logger.error("Unable to open file:" + filepath, e);
        }
    }

    public List<List<Character>> getMaze(){ //getter method for maze
        return mazeMap;
    }

    public void setMaze(List<List<Character>> mazeMap){ //setter method for maze
        this.mazeMap = mazeMap;
    }

    public void createMaze(){ //method to initialize maze
        String line;
        try {
            while((line = reader.readLine()) != null) { //store each row as a new item in an array list
                List<Character> mazeRow = new ArrayList<>();
                for (int idx = 0; idx < line.length(); idx++){
                    if (line.charAt(idx) == '#' || line.charAt(idx) == ' '){
                        mazeRow.add(line.charAt(idx)); // store each element in the row as a column
                    }
                }
                mazeMap.add(mazeRow);
            }
        } catch (IOException e) {
            logger.error("/!\\ A bad error has occured /!\\");
        }
    }

    public void printMaze(){ //output the maze to logger
        //this function is only used for the purpose of debugging
        for(List<Character> row : mazeMap){
            logger.info(row.toString()); //turn each row of the maze to a string and output
        
        }
        logger.info("\n"); 
    }

    public int  findEntrance(){ //find the y value of the entrance of the maze
        int y = 0;
        for(List<Character> row: mazeMap) { //index until a ' ' is found in the first column
            if (row.get(0) == ' '){
                logger.trace(y);
                return y;
            }
            else{
                y++;
            }
        }
        y = this.exitY;
        return y;
    }

    public int findExit(){ //find y value of the exit of the maze
        int y = 0;
        for(List<Character> row: mazeMap) {
            if (row.get(row.size()-1) == ' '){ //checks last character in each row looking for a ' '
                logger.trace(y);
                return y;
            }
            else{
                y++;
            }
        }
        return y;
    }

    public boolean gameOver(){ //this is the path validation to see if we are at the end of the maze
        if(mazeMap.get(exitY).get(mazeMap.get(0).size()-1) != ' ' && mazeMap.get(exitY).get(mazeMap.get(0).size()-1) != '#'){ // checks if the character is a player character at the x,y coordinate at the end of the maze
            return true;
        }
        else{ return false; }
    }
}
