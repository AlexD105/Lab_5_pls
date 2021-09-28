package input;

import i18n.Messenger;
import lombok.AllArgsConstructor;
import ticket.Coordinates;
import ticket.Event;
import ticket.Ticket;
import ticket.TicketType;

import java.util.Scanner;

@AllArgsConstructor
public abstract class Input {

    private final Scanner in;
    private final Messenger messenger;

    public abstract Ticket enterElement() throws Exception;

    public String readName() throws Exception {
        try {
            String name = in.nextLine();
            if(name.equals("")) throw new IllegalArgumentException();
            return name;
        } catch (Exception e) {
            throw new Exception("Некорректный ввод.");
        }
    }

    public Coordinates readCoordinates() throws Exception {
        try {
            String[] arr = (in.nextLine()).split(" ");
            Float x = Float.parseFloat(arr[0]);
            Integer y = Integer.parseInt(arr[1]);
            if(y < -824) throw new IllegalArgumentException("Значение поля должно быть больше -824");
            return new Coordinates(x,y);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception("Некорректный ввод.");
        }
    }

    public Double readPrice() throws Exception {
        try {
            double price = in.nextDouble();
            if(price < 0) throw new IllegalArgumentException("Значение поля должно быть больше 0");
            return price;
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception("Некорректный ввод.");
        }
    }

    public String readComment() throws Exception {
        try {
            String comment = in.nextLine();
            if(comment == null && comment.length() > 860) throw new IllegalArgumentException("Значение поля должно быть больше 860");
            return comment;
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception("Некорректный ввод.");
        }
    }

    public Boolean readRefundable() throws Exception {
        try {
            return in.nextBoolean();
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception("Некорректный ввод.");
        }
    }


    public TicketType readTicketType() throws Exception {
        try {
            String response = in.nextLine();
            TicketType organizationType;
            if(!response.equals("")) {
                organizationType = TicketType.valueOf(response);
            } else {
                throw new Exception("Поле не может быть null");
            }
            return organizationType;
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception("Некорректный ввод.");
        }
    }
    public abstract EventReader getEventReader();

    public abstract class EventReader {

        public abstract Event enterElement() throws Exception;

        public String readName() throws Exception {
            try {
                String name = in.nextLine();
                if(name.equals("") || name == null) throw new IllegalArgumentException("Строка не может быть пустой.");
                return name;
            } catch (IllegalArgumentException e) {
                throw new Exception(e.getMessage());
            } catch (Exception e) {
                throw new Exception("Некорректный ввод");
            }
        }

        public Long readMinAge() throws Exception {
            try {
                Long minAge = in.nextLong();
                if(minAge == null) throw new IllegalArgumentException("Строка не может быть пустой.");
                return minAge;
            } catch (IllegalArgumentException e) {
                throw new Exception(e.getMessage());
            } catch (Exception e) {
                throw new Exception("Некорректный ввод");
            }
        }
        public Integer readTicketsCount() throws Exception {
            try {
                Integer ticketsCount = in.nextInt();
                if(ticketsCount < 0) throw new IllegalArgumentException("Число должно быть больше 0");
                return ticketsCount;
            } catch (IllegalArgumentException e) {
                throw new Exception(e.getMessage());
            } catch (Exception e) {
                throw new Exception("Некорректный ввод");
            }
        }
        public String readDescription() throws Exception {
            try {
                String description = in.nextLine();
                if(description.length() > 416) throw new IllegalArgumentException("Строка не может длиннее 416ти символов");
                return description;
            } catch (IllegalArgumentException e) {
                throw new Exception(e.getMessage());
            } catch (Exception e) {
                throw new Exception("Некорректный ввод");
            }
        }
    }
}
