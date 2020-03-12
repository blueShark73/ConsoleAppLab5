package commands;

import app.Application;
import app.FieldsValidator;
import app.StudyGroup;
import exceptions.IdNotFoundException;
import exceptions.InputFormatException;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда обновит значения всех полей элемента с данным id
 */
public class UpdateCommand extends Command {
    private LinkedHashSet<StudyGroup> collection;
    private HashSet<Long> idList;

    /**
     * обновляет поля
     *
     * @param argument - заданный id для поиска
     * @param scanner  - сканер для ввода полей
     */
    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        collection = application.getCollection();
        idList = application.getIdList();
        try {
            if(!FieldsValidator.checkStringParseToLong(argument, "id - это целое число!!!")) throw new InputFormatException();
            long id = Long.parseLong(argument);
            if (!collection.removeIf(studyGroup -> studyGroup.getId() == id))
                throw new IdNotFoundException("Элемент нельзя обновить, т.к. элемента с таким id нет в коллекции!!!");
            StudyGroup studyGroup = new StudyGroup();
            studyGroup.setId(id);
            if (scanner != null) studyGroup.setScanner(scanner);
            studyGroup.setAllFields();
            collection.add(studyGroup);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода!!! id - число!!!");
        } catch (IdNotFoundException e) {
            System.out.println(e.getMessage());
        }
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
        return "update id : обновит значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String toString() {
        return "update";
    }

    @Override
    public boolean withArgument() {
        return true;
    }
}
