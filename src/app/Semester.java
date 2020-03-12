package app;

import javax.xml.bind.annotation.XmlEnum;

/**
 * текущий семестр для учебной группы
 */
@XmlEnum
public enum Semester {
    THIRD("3"),
    FOURTH("4"),
    FIFTH("5"),
    SIXTH("6"),
    EIGHTH("8");

    private String russian;

    Semester(String russian) {
        this.russian = russian;
    }

    /**
     * аналог valueOf только еще и сообщением об ошибке
     *
     * @param value         - строка, которую ищем
     * @param messageIfNull - сообщение, если не нашли
     */
    public static Semester getValue(String value, String messageIfNull) {
        for (Semester semester : Semester.values()) {
            if (semester.russian.equals(value)) return semester;
        }
        System.out.println(messageIfNull);
        return null;
    }
}
