package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {
    private final String MazeFilepath = "examples/regular.maz.txt";
    private Maze maze;

    @BeforeEach
    void formMaze(){
        maze = new Maze(MazeFilepath);
    }

    @Test
    void test_createMaze(){
        assertNotNull(maze);
    }

    @Test
    void test_findEntrance() {
        int entranceY = maze.findEntrance();
        // Test the entrance of the maze
        assertEquals(33, entranceY);
    }

    @Test
    void test_findExit() {
        int exitY = maze.findExit();
        // Test the entrance of the maze
        assertEquals(27, exitY);
    }

    @Test
    void test_gameOver(){
        boolean gameState = maze.gameOver();
        // Test the game state
        assertEquals(false, gameState);
    }
}
