package app;

import javax.xml.bind.annotation.XmlEnum;

/**
 * форма обучения группы
 */
@XmlEnum
public enum FormOfEducation {
    DISTANCE_EDUCATION("Заочная"),
    FULL_TIME_EDUCATION("Очная"),
    EVENING_CLASSES("Вечерняя школа");

    private String russian;

    FormOfEducation(String russian) {
        this.russian = russian;
    }

    /**
     * аналог valueOf только еще и сообщением об ошибке
     *
     * @param value         - строка, которую ищем
     * @param messageIfNull - сообщение, если не нашли
     */
    public static FormOfEducation getValue(String value, String messageIfNull) {
        for (FormOfEducation formOfEducation : FormOfEducation.values()) {
            if (formOfEducation.russian.equals(value)) return formOfEducation;
        }
        System.out.println(messageIfNull);
        return null;
    }
}
