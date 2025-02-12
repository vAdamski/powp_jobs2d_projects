package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexCommand implements ICompoundCommand {
    private List<DriverCommand> listOfCommands;
    private String name;

    public ComplexCommand(List<DriverCommand> listOfCommands, String name) {
        this.listOfCommands = listOfCommands;
        this.name = name;
    }

    @Override
    public void execute(Job2dDriver driver) {
        this.iterator().forEachRemaining(command -> command.execute(driver));
    }

    @Override
    public Iterator<DriverCommand> iterator() {
        return listOfCommands.iterator();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public ComplexCommand clone() {
        ComplexCommand complexCommand;
        try {
            complexCommand = (ComplexCommand) super.clone();
            complexCommand.listOfCommands = new ArrayList<>();
            complexCommand.name = this.name;
            for (DriverCommand cmd : this.listOfCommands) {
                complexCommand.listOfCommands.add(cmd.clone());
            }
        } catch (CloneNotSupportedException e) {
            complexCommand = new ComplexCommand(this.listOfCommands, this.name);
        }
        return complexCommand;
    }

    public List<DriverCommand> getListOfCommands() {
        return listOfCommands;
    }

    public String getName() {
        return name;
    }
}
