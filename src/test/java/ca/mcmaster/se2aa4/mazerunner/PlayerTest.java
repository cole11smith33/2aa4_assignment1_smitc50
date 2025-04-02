package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;
    private Maze maze;
    private final String MazeFilepath = "examples/regular.maz.txt";

    @BeforeEach
    void setUp(){
        String mazeFilepath = "examples/small.maz.txt";
        maze = new Maze(mazeFilepath);
        player = new Player(maze.findEntrance());
    }

    @Test
    void test_rotateLeft(){
        player.rotateLeft(maze.getMaze());
        int currentDirection = player.getCurrentDirection();

        assertEquals(3, currentDirection); // representing the up arrow (↑)
    }

    @Test
    void test_rotateRight(){
        player.rotateRight(maze.getMaze());
        int currentDirection = player.getCurrentDirection();

        assertEquals(1, currentDirection); // representing the up arrow (↑)
    }
    
}
