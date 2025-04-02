package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;
    private Maze maze;

    @BeforeEach
    void setUp(){
        String mazeFilepath = "examples/tiny.maz.txt";
        maze = new Maze(mazeFilepath);
        player = new Player(maze.findEntrance());
    }

    @Test
    void test_rotateLeft(){
        player.rotateLeft(maze.getMaze());
        int currentDirection = player.getCurrentDirection();

        assertEquals(3, currentDirection); 
    }

    @Test
    void test_rotateRight(){
        player.rotateRight(maze.getMaze());
        int currentDirection = player.getCurrentDirection();

        assertEquals(1, currentDirection); 
    }

    @Test
    void test_placePlayer(){
        player.placePlayer(maze.getMaze());
        List<List<Character>> mazeMap = maze.getMaze();

        List<List<Character>> expectedMaze = List.of(
        List.of('#', '#', '#', '#', '#', '#', '#'),
        List.of('#', ' ', ' ', ' ', ' ', ' ', ' '),
        List.of('#', '#', '#', ' ', '#', '#', '#'),
        List.of('#', ' ', ' ', ' ', ' ', ' ', '#'),
        List.of('#', '#', '#', ' ', '#', '#', '#'),
        List.of('→', ' ', ' ', ' ', ' ', ' ', '#'),
        List.of('#', '#', '#', '#', '#', '#', '#')
        );

        assertEquals(expectedMaze, mazeMap);
    }

    @Test 
    void test_moveForward(){
        player.moveForward(maze.getMaze());
        List<List<Character>> expectedMaze = List.of(
            List.of('#', '#', '#', '#', '#', '#', '#'),
            List.of('#', ' ', ' ', ' ', ' ', ' ', ' '),
            List.of('#', '#', '#', ' ', '#', '#', '#'),
            List.of('#', ' ', ' ', ' ', ' ', ' ', '#'),
            List.of('#', '#', '#', ' ', '#', '#', '#'),
            List.of(' ', '→', ' ', ' ', ' ', ' ', '#'),
            List.of('#', '#', '#', '#', '#', '#', '#')
        );
        List<List<Character>> mazeMap = maze.getMaze();    

        assertEquals(expectedMaze, mazeMap); 
    }

    @Test
    void test_isValidMove_false(){
        boolean validity = player.isValidMove(maze.getMaze(), 0, 4); //case where next move is directly above the starting position
        assertEquals(false, validity);
    }

    @Test
    void test_isValidMove_true(){
        boolean validity = player.isValidMove(maze.getMaze(), 1, 5); //case where next move is directly forward from starting position
        assertEquals(true, validity);
    }

    
}
