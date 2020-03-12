package commands;

import app.Application;
import app.StudyGroup;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * абстрактный класс команды, описывает общее для всех команд поведение(внезапно)
 * также каждая новая команда должна переопределять toString - возвращать представление команды без аргументов, нужно для истории и обработчика
 */
public abstract class Command {
    /**
     * метод исполнения команды
     * @param application - текущее работающее приложение
     * @param argument - строковый аргумент или null, если для этой команды он не нужен
     * @param scanner - поток, с которого некоторым командам нужно считывать данные дальше
     */
    public abstract void execute(Application application, String argument, Scanner scanner);

    public abstract LinkedHashSet<StudyGroup> getCollection();

    public abstract HashSet<Long> getIdList();

    /**
     * @return информация о команде, которая потом выводится с командой help
     */
    abstract String getCommandInfo();

    /**
     * метод, нужный для того, чтобы из ссылки на абстрактную команды, мы знали требуется ли этой команде аргумент
     */
    public abstract boolean withArgument();
}
