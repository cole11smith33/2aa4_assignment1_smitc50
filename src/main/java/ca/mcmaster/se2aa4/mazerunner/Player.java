package ca.mcmaster.se2aa4.mazerunner;
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

    private boolean isValidMove(List<List<Character>> mazeMap, int x, int y){
        return mazeMap.get(y).get(x) != '#';
    }

    public List<List<Character>> moveForward(List<List<Character>> mazeMap){
        int nextX = currX;
        int nextY = currY;

        switch(currentDirection){
            case 0 -> nextX = moveRight(nextX);
            case 1 -> nextY = moveDown(nextY);
            case 2 -> nextX = moveLeft(nextX);
            case 3 -> nextY = moveUp(nextY);
        }

        if(isValidMove(mazeMap, nextX, nextY)) {
            deletePreviousPosition(mazeMap, currX, currY);
            currX = nextX;
            currY = nextY;
            saveNewPosition(mazeMap, currX, currY);
        }
        return mazeMap;
    }

    private int moveUp(int nextY){
        nextY--;
        logger.trace("Moving Up");
        return nextY;
    }

    private int moveDown(int nextY){
        nextY++;
        logger.trace("Moving Down");
        return nextY;
    }

    private int moveRight(int nextX){
        nextX++;
        logger.trace("Moving Right");
        return nextX;
    }

    private int moveLeft(int nextX){
        nextX--;
        logger.trace("Moving Left");
        return nextX;
    }

    public boolean canMoveRight(List<List<Character>> mazeMap){

        int nextX = currX;
        int nextY = currY;

        rotateRight(mazeMap);
        //check tile in front
        switch(currentDirection){ //move current direction
            case 0 -> nextX = moveRight(nextX);
            case 1 -> nextY = moveDown(nextY);
            case 2 -> nextX = moveLeft(nextX);
            case 3 -> nextY = moveUp(nextY);
        }
        // see if this move has a valid character in its place
        if (mazeMap.get(nextY).get(nextX) == ' '){
            nextX = currX;
            nextY = currY;
            rotateLeft(mazeMap);
            return true;
        }
        else{ //no valid character, so move back to where we were before
            nextX = currX;
            nextY = currY;
            rotateLeft(mazeMap);
            return false;
        }
    }

    public boolean canMoveForward(List<List<Character>> mazeMap){ // checks if player would be able to move directly forward in regards to current direction
        int nextX = currX; 
        int nextY = currY;

        //check tile in front
        switch(currentDirection){
            case 0 -> nextX = moveRight(nextX);
            case 1 -> nextY = moveDown(nextY);
            case 2 -> nextX = moveLeft(nextX);
            case 3 -> nextY = moveUp(nextY);
        }

        if (mazeMap.get(nextY).get(nextX) == ' '){
            nextX = currX;
            nextY = currY;
            return true;
        }
        else{
            nextX = currX;
            nextY = currY;
            return false;
        }
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
