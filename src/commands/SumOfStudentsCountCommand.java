package commands;

import app.Application;
import app.StudyGroup;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда выводит на консоль сумму значений поля studentsCount для всех элементов коллекции
 */
public class SumOfStudentsCountCommand extends Command {
    private Application application;

    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        this.application = application;
        System.out.println("Всего студентов во всех грппах в коллекции: " + application.getSumOfStudentsCount());
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
        return "sum_of_students_count : выведет сумму значений поля studentsCount для всех элементов коллекции";
    }

    @Override
    public String toString() {
        return "sum_of_students_count";
    }

    @Override
    public boolean withArgument() {
        return false;
    }
}
