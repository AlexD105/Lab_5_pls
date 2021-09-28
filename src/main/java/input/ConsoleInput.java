package input;

import i18n.Messenger;
import ticket.Coordinates;
import ticket.Event;
import ticket.Ticket;
import ticket.TicketType;

import java.util.Scanner;

public class ConsoleInput extends Input {

    public ConsoleInput(Messenger messenger) {
        super(new Scanner(System.in), messenger);
    }

    @Override
    public Ticket enterElement() throws Exception {

        String name;
        Coordinates coordinates;
        double price;
        String comment;
        Boolean refundable;
        TicketType type;

        while (true) {
            System.out.println("Введите имя:");
            try {
                name = readName();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите координаты (Float, Integer через пробел):");
            try {
                coordinates = readCoordinates();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите цену");
            try {
                price = readPrice();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите комментарий");
            try {
                comment = readComment();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите возможность вернуть");
            try {
                refundable = readRefundable();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите тип организации (VIP, USUAL, BUDGETARY)");
            try {
                type = readTicketType();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        EventReader reader = new ConsoleEventReader();
        Event event = reader.enterElement();

        return new Ticket(name, coordinates, price, comment, refundable, type, event);
    }

    public EventReader getEventReader() {
        return new ConsoleEventReader();
    }


    public class ConsoleEventReader extends EventReader {

        @Override
        public Event enterElement() {

            Event event = new Event();

            while (true) {
                System.out.println("Введите название мероприятия");
                try {
                    event.setName(readName());
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                System.out.println("Введите минимальный возраст");
                try {
                    event.setMinAge(readMinAge());
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                System.out.println("Введите количество билетов");
                try {
                    event.setTicketsCount(readTicketsCount());
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                System.out.println("Введите описание");
                try {
                    event.setDescription(readDescription());
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            return event;
        }
    }
}