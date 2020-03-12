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
 * команда удаляет элемент из коллекции по его id
 */
public class RemoveCommand extends Command {
    private LinkedHashSet<StudyGroup> collection;
    private HashSet<Long> idList;

    /**
     * удаление элемента
     *
     * @param argument - id по которому ищем элемент
     */
    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        collection = application.getCollection();
        idList = application.getIdList();
        try {
            if (!FieldsValidator.checkStringParseToLong(argument, "id - это целое число!!!"))
                throw new InputFormatException();
            long id = Long.parseLong(argument);
            if (!idList.remove(id))
                throw new IdNotFoundException("Элемент не удален, т.к. элемента с таким id нет в коллекции!!!");
            collection.removeIf(studyGroup -> studyGroup.getId() == id);
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
        return "remove_by_id id : удалит элемент из коллекции по его id";
    }

    @Override
    public String toString() {
        return "remove_by_id";
    }

    @Override
    public boolean withArgument() {
        return true;
    }
}
