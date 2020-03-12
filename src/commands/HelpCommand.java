package commands;

import app.Application;
import app.StudyGroup;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда выводит доступные команды
 */
public class HelpCommand extends Command {
    private Application application;

    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        this.application = application;
        System.out.println("Доступные команды: ");
        System.out.println(new HelpCommand().getCommandInfo());
        System.out.println(new InfoCommand().getCommandInfo());
        System.out.println(new ShowCommand().getCommandInfo());
        System.out.println(new AddCommand().getCommandInfo());
        System.out.println(new UpdateCommand().getCommandInfo());
        System.out.println(new RemoveCommand().getCommandInfo());
        System.out.println(new ClearCommand().getCommandInfo());
        System.out.println(new SaveCommand().getCommandInfo());
        System.out.println(new ExecuteScriptCommand().getCommandInfo());
        System.out.println(new ExitCommand().getCommandInfo());
        System.out.println(new AddIfMaxCommand().getCommandInfo());
        System.out.println(new AddIfMinCommand().getCommandInfo());
        System.out.println(new HistoryCommand().getCommandInfo());
        System.out.println(new SumOfStudentsCountCommand().getCommandInfo());
        System.out.println(new FilterStartsWithNameCommand().getCommandInfo());
        System.out.println(new FilterGreaterThanStudentsCountCommand().getCommandInfo());
    }

    @Override
    public LinkedHashSet<StudyGroup> getCollection() {
        return application.getCollection();
    }

    @Override
    public HashSet<Long> getIdList() {
        return application.getIdList();
    }

    @Override
    String getCommandInfo() {
        return "help : выводит справку по доступным командам";
    }

    @Override
    public String toString() {
        return "help";
    }

    @Override
    public boolean withArgument() {
        return false;
    }
}
