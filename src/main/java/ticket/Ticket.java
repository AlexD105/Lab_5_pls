package ticket;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Ticket implements Comparable<Ticket>{
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double price; //Значение поля должно быть больше 0
    private String comment; //Длина строки не должна быть больше 860, Поле не может быть null
    private Boolean refundable; //Поле может быть null
    private TicketType type; //Поле не может быть null
    private Event event; //Поле не может быть null

    private static long cur_id = 0;

    public Ticket(String name,
                  Coordinates coordinates,
                  double price,
                  String comment,
                  Boolean refundable,
                  TicketType type,
                  Event event) {
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.comment = comment;
        this.refundable = refundable;
        this.type = type;
        this.event = event;

        this.id = ++cur_id;
        this.creationDate = ZonedDateTime.now();
    }

    public String[] getStringFields() {
        return new String[]{
                Long.toString(id),
                name,
                coordinates.toString(),
                creationDate.toString(),
                String.valueOf(price),
                comment,
                String.valueOf(refundable),
                String.valueOf(type),
                event.toString()};

    }

    public static Ticket setStringFields(List<String> fields) {

        // Здесь каждый элемент из fields - это одно из полей Vehicle
        // Все поля идут по порядку. Первое из них - id

        // id
        Long id = Long.parseLong(fields.get(0));

        // name
        String name = fields.get(1);

        // coordinates
        String[] arr = fields.get(2).split("; ");
        Float x = Float.parseFloat(arr[0].substring(1));
        Integer y = Integer.parseInt(arr[1].substring(0, arr[1].length() - 1));
        Coordinates coordinates = new Coordinates(x,y);

        // creation date
        ZonedDateTime creationDate = ZonedDateTime.parse(fields.get(3));

        // price
        Double price = Double.parseDouble(fields.get(4));

        // comment
        String comment = fields.get(5);

        // refundable
        Boolean refundable = Boolean.parseBoolean(fields.get(6));

        // Ticket type
        TicketType type = TicketType.valueOf(fields.get(7));

        // Event
        String[] eventFields = fields.get(8).split(";");


        Long eventId = Long.parseLong(eventFields[0].substring(1));
        String eventName = eventFields[1].substring(1);
        Date eventDate = new Date(Long.parseLong(eventFields[2].substring(1)));
        Long eventMinAge = Long.parseLong(eventFields[3].substring(1));
        Integer eventTicketsCount = Integer.parseInt(eventFields[4].substring(1));
        String eventDescription = eventFields[5].substring(1, eventFields[5].length() - 1);

        Event event = new Event(eventName, eventMinAge, eventTicketsCount, eventDescription);
        event.setId(eventId);
        event.setDate(eventDate);

        Ticket ticket = new Ticket(name, coordinates, price, comment, refundable, type, event);
        ticket.setId(id);
        ticket.setCreationDate(creationDate);

        return ticket;

    }

    @Override
    public int compareTo(Ticket o) {
        // Объекты сортируются по имени
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return new Formatter()
                .format("Name: %s\n" +
                                "ID: %d\n" +
                                "Coordinates: %s\n" +
                                "Creation date: %s\n" +
                                "Price: %s\n" +
                                "Comment: %s\n" +
                                "Refundable: %s\n" +
                                "Ticket type: %s\n",
                        name, id, coordinates, creationDate,
                        price, comment, refundable, type).toString();
    }

}
