package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Player {
    private static final Logger logger = LogManager.getLogger();
    private final char[] movementPositions;
    private int size;
    private int currentDirection;
    private int currX;
    private int currY;

    public Player(int leftHandMazeOpening){
        this.movementPositions = new char[] {'→','↓','←','↑'};
        this.size = 4; //this is a magic number, remove when refactoring
        this.currentDirection = 0;
        this.currX = 0;
        this.currY = leftHandMazeOpening;
    }  

    public void rotateLeft(List<List<Character>> mazeMap){
        currentDirection--;
        if(currentDirection == -1){
            currentDirection = 3;
        }
        mazeMap.get(currY).set(currX, movementPositions[currentDirection]);
        logger.trace(movementPositions[currentDirection]);
    }

    public void rotateRight(List<List<Character>> mazeMap){
        currentDirection++;
        if(currentDirection == 4){
            currentDirection = 0;
        }
        mazeMap.get(currY).set(currX, movementPositions[currentDirection]);
        logger.trace(movementPositions[currentDirection]);
    }

    public List<List<Character>> placePlayer(List<List<Character>> mazeMap){
        saveNewPosition(mazeMap, currX, currY);
        return mazeMap;
    }


    public List<List<Character>> moveForward(List<List<Character>> mazeMap){
        deletePreviousPosition(mazeMap, currX, currY);
        switch(currentDirection){
            case 0 -> moveRight();
            case 1 -> moveDown();
            case 2 -> moveLeft();
            case 3 -> moveUp();
        }
        saveNewPosition(mazeMap, currX, currY);
        return mazeMap;
    }

    private void moveUp(){
        currY--;
        logger.trace("Moving Up");
    }

    private void moveDown(){
        currY++;
        logger.trace("Moving Down");
    }

    private void moveRight(){
        currX++;
        logger.trace("Moving Right");
    }

    private void moveLeft(){
        currX--;
        logger.trace("Moving Left");
    }

    private List<List<Character>> deletePreviousPosition(List<List<Character>> mazeMap, int x, int y){
        mazeMap.get(y).set(x, ' ');
        return mazeMap;
    }

    private List<List<Character>> saveNewPosition(List<List<Character>> mazeMap, int x, int y){
        mazeMap.get(y).set(x, movementPositions[currentDirection]);
        return mazeMap;
    }

}
