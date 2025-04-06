package Commands;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

public class Help extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 3526235198917310737L;
    protected static HashMap<String, String> manual;
    private static String s = "Справка пользователя." + "\n" + "Команды:" + "\n";

    public Help(){
        super(null);
    }

    @Override
    public String execute() {

        manual = new HashMap<>();
        manual.put("help", "Вывести справку по доступным командам");
        manual.put("info", "Вывести в стандартный поток вывода информацию о коллекции.");
        manual.put("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении.");
        manual.put("insert", "Добавить новый элемент с заданным ключом.");
        manual.put("update_id {id}", "Обновить значение элемента коллекции, id которого равен заданному.");
        manual.put("remove_key {key}", "Удалить элемент из коллекции по его ключу.");
        manual.put("clear", "Очистить коллекцию.");
        manual.put("exit", "Завершить программу (без сохранения в файл)");
        manual.put("remove_lower {id}", "Удалить из коллекции все элементы, меньшие, чем заданный.");
        manual.put("remove_greater_key {than this key}", "Удалить из коллекции все элементы, ключ которых превышает заданный.");
        manual.put("remove_all_by_minutes_of_waiting {minutesOfWaiting}", "Удалить из коллекции все элементы, значение поля minutesOfWaiting которого эквивалентно заданному.");
        manual.put("sum_of_mow", "Вывести сумму значений поля minutesOfWaiting для всех элементов коллекции.");
        manual.put("print_car", "Вывести значения поля car всех элементов в порядке возрастания.");
        for (String i : manual.keySet()) {
            s += (i + " - " + manual.get(i) +"\n");
        }

        return(s);
    }
}
