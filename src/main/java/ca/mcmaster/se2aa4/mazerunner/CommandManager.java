package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<Command> commandArchive = new ArrayList<Command>();
    
    public void executeCommand(Command command) {
        command.execute();
        commandArchive.add(command);
    } 
}
