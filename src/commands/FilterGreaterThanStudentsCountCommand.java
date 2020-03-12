package commands;

import app.Application;
import app.FieldsValidator;
import app.StudyGroup;
import exceptions.InputFormatException;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда для вывода тех учебных групп, значение поля studentsCount которых больше заданного
 */
public class FilterGreaterThanStudentsCountCommand extends Command {
    private Application application;

    /**
     * сравнение и вывод нужных групп
     *
     * @param argument - заданное для сравнения кол-во студентов
     */
    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        this.application = application;
        if (!FieldsValidator.checkStringParseToLong(argument, "Кол-во студентов - это целое число!!!"))
            throw new InputFormatException();
        Long studentsCount = Long.parseLong(argument);
        LinkedHashSet<StudyGroup> collection = getCollection();
        for (StudyGroup studyGroup : collection) {
            if (studyGroup.getStudentsCount() != null && studyGroup.getStudentsCount() > studentsCount)
                System.out.println(studyGroup);
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
        return "filter_greater_than_students_count studentsCount : выведет элементы, значение поля studentsCount которых больше заданного";
    }

    @Override
    public String toString() {
        return "filter_greater_than_students_count";
    }

    @Override
    public boolean withArgument() {
        return true;
    }
}
