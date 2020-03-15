package commands;

import app.Application;
import app.StudyGroup;
import exceptions.StackIsLimitedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда для выполнения скриптов
 */
public class ExecuteScriptCommand extends Command {
    private LinkedHashSet<StudyGroup> collection;
    private HashSet<Long> idList;

    /**
     * во время исполнения обработчик приложения запускается со сканером файла, что позполяет читать данные из файла
     *
     * @param application - текущее приложения
     * @param argument    - имя файла
     */
    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        try {
            collection = application.getCollection();
            idList = application.getIdList();
            File file = new File(argument);
            Scanner fileScanner = new Scanner(file);
            application.increaseScriptCounter();
            application.getHandler().run(fileScanner);
            application.reduceScriptCounter();
            collection = application.getCollection();
            idList = application.getIdList();
        } catch (FileNotFoundException e) {
            System.out.println("Такого скрипта не существует!!! Все скрипты должны лежать на одном уровне с jar или src.");
        } catch (StackIsLimitedException e) {
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
        return "execute_script file_name : считает и исполнит скрипт из указанного файла";
    }

    @Override
    public String toString() {
        return "execute_script";
    }

    @Override
    public boolean withArgument() {
        return true;
    }
}
