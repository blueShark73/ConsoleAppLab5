package commands;

import app.Application;
import app.StudyGroup;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда добавляет элемент, если он меньше минимального элемента коллекции
 */
public class AddIfMinCommand extends AddCommand {
    private HashSet<Long> idList;
    private LinkedHashSet<StudyGroup> collection;

    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        collection = application.getCollection();
        idList = application.getIdList();
        StudyGroup studyGroup = executeInitialization(application, argument, scanner);
        if (studyGroup.compareTo(application.getMinStudyGroup()) < 0) {
            collection.add(studyGroup);
            idList.add(studyGroup.getId());
            System.out.println("Элемент добавлен");
        } else System.out.println("Элемент не добавлен");
    }

    @Override
    public LinkedHashSet<StudyGroup> getCollection() {
        return collection;
    }

    @Override
    public HashSet<Long> getIdList() {
        return idList;
    }

    @Override
    String getCommandInfo() {
        return "add_if_min element : добавит новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }

    @Override
    public String toString() {
        return "add_if_min";
    }
}

