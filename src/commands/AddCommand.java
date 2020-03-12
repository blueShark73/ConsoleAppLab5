package commands;

import app.Application;
import app.FieldsValidator;
import app.StudyGroup;
import exceptions.InputFormatException;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда для добавления элемента в коллекцию по имени
 */
public class AddCommand extends Command {
    private HashSet<Long> idList;
    private LinkedHashSet<StudyGroup> collection;

    /**
     * исполнение
     *
     * @param application - текущее приложение
     * @param argument    - имя учебной группы, которую пользователь хочет добавить
     * @param scanner     - сканнер, с которого мы считываем значения задаваемых полей
     */
    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        collection = application.getCollection();
        idList = application.getIdList();
        StudyGroup studyGroup = executeInitialization(application, argument, scanner);
        idList.add(studyGroup.getId());
        collection.add(studyGroup);
    }

    /**
     * инициализация, которая одинакова для трех команд
     */
    public StudyGroup executeInitialization(Application application, String argument, Scanner scanner) {
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setId(StudyGroup.generateId(application.getIdList()));
        if (scanner != null) studyGroup.setScanner(scanner);
        if (!FieldsValidator.checkNumber((long)argument.length(), 2, 19, "Некорректное имя элемента, оно должно быть из 2-19 знаков!!!", false))
            throw new InputFormatException();
        studyGroup.setName(argument);
        studyGroup.setFields();
        return studyGroup;
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
        return "add element : добавит новый элемент в коллекцию";
    }

    @Override
    public String toString() {
        return "add";
    }

    @Override
    public boolean withArgument() {
        return true;
    }
}
