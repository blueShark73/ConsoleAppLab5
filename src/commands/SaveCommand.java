package commands;

import app.Application;
import app.Collection;
import app.StudyGroup;

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
        this.application =application;
        try {
            File fileOutput = new File(System.getenv("OUTPUT_PATH"));
            if(!fileOutput.exists()) throw new FileNotFoundException();
            Collection collectionClass = new Collection();
            collectionClass.set(getCollection());
            JAXBContext jaxbContext = JAXBContext.newInstance(Collection.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            FileOutputStream fileOutputStream = new FileOutputStream(fileOutput);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(collectionClass, fileOutputStream);
        } catch (FileNotFoundException e){
            System.out.println("Входной файл не существует или переменная окружения указана неправильно!!!");
        } catch (JAXBException e){
            System.out.println("Проблемы с содержанием входного файла!!!");
        } catch (NullPointerException e){
            System.out.println("Переменная окружения не найдена!!!");
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
