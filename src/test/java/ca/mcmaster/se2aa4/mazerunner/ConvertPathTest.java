package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConvertPathTest {
    private ConvertPath convertPath;

    
    @BeforeEach
    void setUp(){
        convertPath = new ConvertPath(new RightHandRule());
    }

    @Test
    void test_convertToFactorized(){ //test factorizing the small maze pathway
        StringBuilder pathway = new StringBuilder("FRFLLFFRFFRFFLLFFFFRFFRFFFFLLFFRFFFFRFFRFFLLFFLFFLFFFFRFFRFFLLFFFFRFFRFFLLFFRFFRFFFFRFFLFFRFFLF");
        StringBuilder output = convertPath.convertToFactorized(pathway);
        String expectedOutput = "FRF2L2FR2FR2F2L4FR2FR4F2L2FR4FR2FR2F2L2FL2FL4FR2FR2F2L4FR2FR2F2L2FR2FR4FR2FL2FR2FLF";
        assertEquals(expectedOutput, output.toString());
    }
}
