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
        this.movementPositions = new char[] {'→','↓','←','↑'}; // possible positions
        this.size = 4; 
        this.currentDirection = 0;
        this.currX = 0;
        this.currY = leftHandMazeOpening;
    }  

    public void rotateLeft(List<List<Character>> mazeMap){ // rotate through the movement position list, which is a circular array
        currentDirection--;// as you index to the left, the character will rotate left
        if(currentDirection == -1){
            currentDirection = 3;
        }
        mazeMap.get(currY).set(currX, movementPositions[currentDirection]);  // set the new player position in the maze
        logger.trace(movementPositions[currentDirection]);
    }

    public void rotateRight(List<List<Character>> mazeMap){ //rotate through the movement position list, which is a circular array
        currentDirection++; // as you index to the right, the character will rotate right
        if(currentDirection == 4){
            currentDirection = 0;
        }
        mazeMap.get(currY).set(currX, movementPositions[currentDirection]); // set the new player position in the maze
        logger.trace(movementPositions[currentDirection]);
    }

    public List<List<Character>> placePlayer(List<List<Character>> mazeMap){ //place the current player icon at a given (x,y) in the maze
        saveNewPosition(mazeMap, currX, currY);
        return mazeMap;
    }

    private boolean isValidMove(List<List<Character>> mazeMap, int x, int y){
        return mazeMap.get(y).get(x) != '#'; //check if current square is a #, which represents a wall, making the move invalid
    }

    public List<List<Character>> moveForward(List<List<Character>> mazeMap){
        int nextX = currX;
        int nextY = currY;

        switch(currentDirection){ //switch case to perform the approipriate position change
            case 0 -> nextX = moveRight(nextX);
            case 1 -> nextY = moveDown(nextY);
            case 2 -> nextX = moveLeft(nextX);
            case 3 -> nextY = moveUp(nextY);
        }

        if(isValidMove(mazeMap, nextX, nextY)) { // if move is valid, save as current (x,y) and rewrite the position to the board
            deletePreviousPosition(mazeMap, currX, currY); //remove previous position
            currX = nextX;
            currY = nextY;
            saveNewPosition(mazeMap, currX, currY); //save new position
        }
        return mazeMap;
    }

    private int moveUp(int nextY){ //list manipulation to move up
        nextY--;
        logger.trace("Moving Up");
        return nextY;
    }

    private int moveDown(int nextY){ //list manipulation to move down
        nextY++;
        logger.trace("Moving Down");
        return nextY;
    }

    private int moveRight(int nextX){ //list manipulation to move right
        nextX++;
        logger.trace("Moving Right");
        return nextX;
    }

    private int moveLeft(int nextX){ //list manipulation to move left
        nextX--;
        logger.trace("Moving Left");
        return nextX;
    }

    public boolean canMoveRight(List<List<Character>> mazeMap){ // check if the player can move right

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
        // see if this move has a valid character in its place
        if (mazeMap.get(nextY).get(nextX) == ' '){
            nextX = currX;
            nextY = currY;
            return true;
        }
        else{ //character in the position is invalid, so revert the changes
            nextX = currX;
            nextY = currY;
            return false;
        }
    }

    private List<List<Character>> deletePreviousPosition(List<List<Character>> mazeMap, int x, int y){ //delete the player position in the maze
        mazeMap.get(y).set(x, ' ');
        return mazeMap;
    }

    private List<List<Character>> saveNewPosition(List<List<Character>> mazeMap, int x, int y){ // set the player position in the maze to current position
        mazeMap.get(y).set(x, movementPositions[currentDirection]);
        return mazeMap;
    }

}
