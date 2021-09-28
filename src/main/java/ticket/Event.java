package ticket;

import java.util.Comparator;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class Event implements Comparable<Event>{

    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Date date; //Поле может быть null
    private Long minAge; //Поле не может быть null
    private Integer ticketsCount; //Значение поля должно быть больше 0
    private String description; //Длина строки не должна быть больше 416, Поле может быть null

    private static long cur_id = 0;

    public Event(String name,Long minAge, Integer ticketsCount, String description) {
        this.name = name;
        this.minAge = minAge;
        this.ticketsCount = ticketsCount;
        this.description = description;

        this.id = ++cur_id;
        this.date = new Date();
    }

    public Event() {
    }

    @Override
    public String toString() {
        return "[" + id + "; " + name + "; " + date + "; " + minAge + "; " + ticketsCount + "; " + description + "]";
    }

    @Override
    public int compareTo(Event o) {
        return Comparator.comparing(Event::getId)
                .compare(this, o);
    }
}

