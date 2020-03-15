package commands;

import app.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * команда записывает коллекцию в файл в формате xml
 */
public class SaveCommand extends Command {
    private Application application;

    /**
     * запись в файл
     */
    @Override
    public void execute(Application application, String argument, Scanner scanner) {
        this.application = application;
        Collection collectionClass = new Collection();
        collectionClass.set(getCollection());
        FileWorker.saveCollection(collectionClass, "INPUT_PATH", "input.xml");
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
        return "save : сохранит коллекцию в файл";
    }

    @Override
    public String toString() {
        return "save";
    }

    @Override
    public boolean withArgument() {
        return false;
    }
}
