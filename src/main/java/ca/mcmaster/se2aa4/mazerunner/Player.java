package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Player {
    private static final Logger logger = LogManager.getLogger();
    private String[] movementPositions;
    private int size;
    private int currentDirection;

    public Player(){
        this.movementPositions = new String[] {"→","↓","←","↑"};
        this.size = 4; // 
        this.currentDirection = 0;
    }  

    public void rotateLeft(){
        currentDirection--;
        if(currentDirection == -1){
            currentDirection = 3;
        }
        logger.trace(movementPositions[currentDirection]);
    }

    public void rotateRight(){
        currentDirection++;
        if(currentDirection == 4){
            currentDirection = 0;
        }
        logger.trace(movementPositions[currentDirection]);
    }

}
