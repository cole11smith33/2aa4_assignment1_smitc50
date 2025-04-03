package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class TurnRightCommand implements Command {
    private Player player;
    private List<List<Character>> mazeMap;

    public TurnRightCommand(Player player, List<List<Character>> mazeMap){
        this.player = player;
        this.mazeMap = mazeMap;
    }

    @Override
    public void execute(){
        player.rotateRight(mazeMap);
    }
    
}
