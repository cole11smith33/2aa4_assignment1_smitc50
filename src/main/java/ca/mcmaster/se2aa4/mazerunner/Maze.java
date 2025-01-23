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

    private List<List<Character>> mazeMap;

    //Constructor for maze class
    public Maze (String filepath){
        this.mazeMap = new ArrayList<>();
        this.filepath = filepath;
        try {
            this.reader = new BufferedReader(new FileReader(filepath));
        }
        catch (IOException e){
            logger.error("Unable to open file:" + filepath, e);
        }
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
            reader.close(); // close the FileReader
        } catch (IOException e) {
            logger.error("/!\\ An error has occured /!\\");
        }
    }

    public void printMaze(){
        for(List<Character> row : mazeMap){
            logger.info(row.toString());
        }
    }

    //method to find the entrance to the maze
}
