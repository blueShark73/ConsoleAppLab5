package app;

import commands.*;
import exceptions.InputFormatException;
import exceptions.SameIdException;
import exceptions.StackIsLimitedException;
import exceptions.WithoutArgumentException;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * класс приложения, в котором соединены функции отправителя команды и получателя результата
 */
public class Application {
    private LinkedHashSet<StudyGroup> collection;
    private HashSet<Long> idList;
    private CommandHistory commandHistory;
    private ZonedDateTime initializationDate;
    private Handler handler;
    private int scriptCounter;
    private static final int STACK_SIZE = 10;
    private static final int DEFAULT_HISTORY_SIZE = 9;

    /**
     * создаём множество идентификаторов, историю команд и устнавливаем время инициализации коллекции
     *
     * @param collection - коллекция элементов, которую мы получили из входного файла
     */
    public Application(LinkedHashSet<StudyGroup> collection) {
        this.collection = new LinkedHashSet<>();
        idList = new HashSet<>();
        for (StudyGroup studyGroup : collection) {
            try {
                //проверка валидности данных из файла
                if (!FieldsValidator.checkNumber((long) studyGroup.getName().length(), 2, 19, "У элемента некорректное имя, id: " + studyGroup.getId(), false)
                        || !FieldsValidator.checkNumber(studyGroup.getStudentsCount(), 0, 50, "У элемента некорректное кол-во студентов, id: " + studyGroup.getId(), true)
                        || !FieldsValidator.checkNumber((long) studyGroup.getGroupAdmin().getName().length(), 2, 19, "У элемента некорректное имя админа, id: " + studyGroup.getId(), false)
                        || !FieldsValidator.checkNumber(studyGroup.getGroupAdmin().getHeight(), 0, 300, "У элемента некорректный рост админа, id: " + studyGroup.getId(), true)
                        || !FieldsValidator.checkNumber((long) studyGroup.getGroupAdmin().getPassportID().length(), 7, 24, "У элемента некорректный пасспортный id админа, id: " + studyGroup.getId(), false)
                        || !FieldsValidator.checkNumber(studyGroup.getGroupAdmin().getWeight(), 0, 300, "У элемента некорректный вес админа, id: " + studyGroup.getId(), false)
                        || !FieldsValidator.checkNumber(studyGroup.getId(), 0, Long.MAX_VALUE, "У элемента некорректный id, имя элемента: " + studyGroup.getName(), false)
                        || studyGroup.getFormOfEducation() == null
                        || !FieldsValidator.checkNumber(studyGroup.getCoordinates().getX(), Long.MIN_VALUE, Long.MAX_VALUE, "У элемента некорректная координата, id: " + studyGroup.getId(), false))
                    throw new InputFormatException();
                if (!idList.add(studyGroup.getId()))
                    throw new SameIdException("В коллекции присутствуют элементы с одинаковыми id, будет загружен только один!!!");
                this.collection.add(studyGroup);
            } catch (InputFormatException e) {
                System.out.println("Ошибка во входном файле, элемент с некорректными полями не будет добавлен в коллекцию!!!");
            } catch (SameIdException e) {
                System.out.println(e.getMessage());
            }
        }
        setInitializationDate();
        scriptCounter = 0;
        commandHistory = new CommandHistory(DEFAULT_HISTORY_SIZE);
    }

    public void setCollection(LinkedHashSet<StudyGroup> collection) {
        this.collection = collection;
    }

    public void setInitializationDate() {
        initializationDate = ZonedDateTime.now();
    }

    public LinkedHashSet<StudyGroup> getCollection() {
        return this.collection;
    }

    public HashSet<Long> getIdList() {
        return idList;
    }

    /**
     * исполнение команды, переданной от обработчика команд
     *
     * @param command  - передаваемая команда
     * @param argument - аргумент команды или null, если команда без аргументов
     * @param scanner  - сканер, указывающий откуда считывать
     */
    public void executeCommand(Command command, String argument, Scanner scanner) {
        try {
            if (command.withArgument() && argument == null) throw new WithoutArgumentException();
            command.execute(this, argument, scanner);
            collection = command.getCollection();
            idList = command.getIdList();
            commandHistory.add(command);
        } catch (WithoutArgumentException | InputFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * используется для команды show
     */
    public ZonedDateTime getInitializationDate() {
        return initializationDate;
    }

    /**
     * используется для команды sum_of_students_count
     */
    public long getSumOfStudentsCount() {
        long sum = 0L;
        if (!collection.isEmpty()) {
            for (StudyGroup studyGroup : collection) {
                if (studyGroup.getStudentsCount() != null) sum += studyGroup.getStudentsCount();
            }
        }
        return sum;
    }

    /**
     * используется для команды add_if_max
     */
    public StudyGroup getMaxStudyGroup() {
        StudyGroup maxStudyGroup = null;
        if (!collection.isEmpty()) {
            for (StudyGroup studyGroup : collection) {
                if (studyGroup.compareTo(maxStudyGroup) > 0) maxStudyGroup = studyGroup;
            }
        }
        return maxStudyGroup;
    }

    /**
     * используется для команды add_if_min
     */
    public StudyGroup getMinStudyGroup() {
        StudyGroup minStudyGroup = null;
        if (!collection.isEmpty()) {
            for (StudyGroup studyGroup : collection) {
                if (studyGroup.compareTo(minStudyGroup) < 0) minStudyGroup = studyGroup;
            }
        }
        return minStudyGroup;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    /**
     * используется для команды history
     */
    public CommandHistory getCommandHistory() {
        return commandHistory;
    }

    /**
     * увеличить счетчик стека
     */
    public void increaseScriptCounter() {
        if (scriptCounter >= STACK_SIZE) throw new StackIsLimitedException();
        scriptCounter++;
    }

    public int getScriptCounter() {
        return scriptCounter;
    }

    /**
     * уменьшить счетчик стека
     */
    public void reduceScriptCounter() {
        scriptCounter--;
    }
}
