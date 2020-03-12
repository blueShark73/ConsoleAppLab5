package app;

import commands.Command;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * класс для ведения истории команд
 */
public class CommandHistory {
    private ArrayDeque<Command> history;
    private int size;

    CommandHistory(int size) {
        this.size = size;
        history = new ArrayDeque<>(size);
    }

    /**
     * добавляет команду в историю, если она заполнена, то удалим следующую по очереди команду
     * @param command - команда, которую добавляем
     */
    public void add(Command command) {
        if (history.size() == size) history.pop();
        history.addLast(command);
    }

    /**
     * выводит историю
     */
    public void printHistory() {
        Iterator<Command> iterator = history.descendingIterator();
        System.out.println("Последние " + size + " команд: ");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
