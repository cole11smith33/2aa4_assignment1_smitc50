package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class TurnLeftCommand implements Command{
    private Player player;
    private List<List<Character>> mazeMap;

    public TurnLeftCommand(Player player, List<List<Character>> mazeMap){
        this.player = player;
        this.mazeMap = mazeMap;
    }

    @Override
    public void execute(){
        player.rotateLeft(mazeMap);
    }

    @Override
    public void undo(){
        player.rotateRight(mazeMap);
    }

    @Override
    public void redo(){
        player.rotateLeft(mazeMap);
    }
    
}
