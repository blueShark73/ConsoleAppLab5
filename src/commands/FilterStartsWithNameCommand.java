package commands;

import app.Application;
import app.StudyGroup;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда выведет элементы, значение поля name которых начинается с заданной подстроки
 */
public class FilterStartsWithNameCommand extends Command {
    private Application application;

    /**
     * поиск и вывод на консоль
     * @param argument - строка, которую ищем в начале названия учебной группы
     */
    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        this.application = application;
        LinkedHashSet<StudyGroup> collection = getCollection();
        for (StudyGroup studyGroup : collection) {
            if (new StringBuffer(studyGroup.getName()).indexOf(argument) == 0) System.out.println(studyGroup);
        }
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
        return "filter_starts_with_name name : выведет элементы, значение поля name которых начинается с заданной подстроки";
    }

    @Override
    public String toString() {
        return "filter_starts_with_name";
    }

    @Override
    public boolean withArgument() {
        return true;
    }
}
