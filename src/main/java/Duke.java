import Task.Event;
import Task.Task;

import java.util.ArrayList;
import java.util.Scanner;
import Task.*;

public class Duke {
    private final static String GREETING_MESSAGE = "Buenos Dias! Soy Duke, como estas mi amigo?";
    private final static String ACTION_LIST = "list";
    private final static String TERMINATION = "bye";
    private final static String TERMINATION_MESSAGE = "Adios, amigos!";
    private final static String TASK_COMPLETED = "done";
    private final static String TASK_TODO = "todo";
    private final static String TASK_DEADLINE = "deadline";
    private final static String TASK_EVENT = "event";
    private final static String DEADLINE_DATE = "/by";
    private final static String EVENT_DATE = "/at";
    private static ArrayList<Task> userInputsList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(GREETING_MESSAGE);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String userInput = sc.next();
            switch (userInput) {
                case ACTION_LIST:
                    try {
                        if (userInputsList.isEmpty()) {
                            throw new DukeExceptions("☹ OOPS !!! La lista está vacía. ¡Agregue una nueva tarea!");
                        }
                        System.out.println("Estas son las tareas de su lista:");
                        for (int i = 0; i < userInputsList.size(); i++) {
                            System.out.println((i + 1) + ". " + userInputsList.get(i).toString());
                        }
                    } catch (DukeExceptions e) {
                        System.out.println(e);
                    }
                    break;
                case TERMINATION:
                    System.out.println(TERMINATION_MESSAGE);
                    userInputsList.clear();
                    break;
                case TASK_COMPLETED:
                    try {
                        int positionDone = sc.nextInt() - 1;
                        if (positionDone < userInputsList.size()) {
                            System.out.println("Agradable! He marcado esta tarea como hecha:");
                            userInputsList.get(positionDone).completeTask();
                            System.out.println(userInputsList.get(positionDone));
                        } else {
                            throw new DukeExceptions("☹ OOPS !!! ¡Esta tarea aún no existe!");
                        }
                    } catch (DukeExceptions e) {
                        System.out.println(e);
                    }
                    break;
                case TASK_TODO:
                    try {
                        userInput = sc.nextLine();
                        if (userInput.isEmpty()) {
                            throw new DukeExceptions("☹ OOPS !!! La descripción de una tarea no puede estar vacía.");
                        }
                        int position = userInputsList.size() + 1;
                        Todo newTodo = new Todo(position, userInput);
                        userInputsList.add(newTodo);
                        System.out.println("Entendido. He agregado esta tarea:\n" +
                                newTodo +
                                "\nAhora tienes " + userInputsList.size() +" tareas en la lista. ");
                    } catch(DukeExceptions e) {
                        System.out.println(e);
                    }
                    break;
                case TASK_DEADLINE:
                    try {
                        userInput = sc.nextLine();
                        if (userInput.isEmpty()) {
                            throw new DukeExceptions("☹ OOPS !!! La descripción de una tarea no puede estar vacía.");
                        }
                        if (!userInput.contains(DEADLINE_DATE)) {
                            throw new DukeExceptions("☹ OOPS !!! Debe establecer una fecha límite para esta tarea.");
                        }
                        int byPosition = userInput.indexOf(DEADLINE_DATE);
                        int position = userInputsList.size() + 1;
                        String taskDescription = userInput.substring(0, byPosition);
                        String dateDescription = userInput.substring(byPosition + 3);
                        if (dateDescription.isEmpty()) {
                            throw new DukeExceptions("☹ OOPS !!! Debe establecer una fecha límite para esta tarea.");
                        }
                        Deadline newDeadline = new Deadline(position, taskDescription);
                        newDeadline.setTime(dateDescription);
                        userInputsList.add(newDeadline);
                        System.out.println("Entendido. He agregado esta tarea:\n" +
                                newDeadline +
                                "\nAhora tienes " + userInputsList.size() + " tareas en la lista. ");
                    } catch(DukeExceptions e) {
                        System.out.println(e);
                    }
                    break;
                case TASK_EVENT:
                    try {
                        userInput = sc.nextLine();
                        if (userInput.isEmpty()) {
                            throw new DukeExceptions("☹ OOPS !!! La descripción de una tarea no puede estar vacía.");
                        }
                        if (!userInput.contains(EVENT_DATE)) {
                            throw new DukeExceptions("☹ OOPS !!! Debe establecer la hora del evento para esta tarea.");
                        }
                        int atPosition = userInput.indexOf(EVENT_DATE);
                        int position = userInputsList.size() + 1;
                        String taskDescription = userInput.substring(0, atPosition);
                        String dateDescription = userInput.substring(atPosition + 3);
                        if (dateDescription.isEmpty()) {
                            throw new DukeExceptions("☹ OOPS !!! Debe establecer la hora del evento para esta tarea.");
                        }
                        Event newEvent = new Event(position, taskDescription);
                        newEvent.setTime(dateDescription);
                        userInputsList.add(newEvent);
                        System.out.println("Entendido. He agregado esta tarea:\n" +
                                newEvent +
                                "\nAhora tienes " + userInputsList.size() + " tareas en la lista. ");
                    } catch(DukeExceptions e) {
                        System.out.println(e);
                    }
                    break;
                default:
                    try {
                        throw new DukeExceptions("OOPS !!! Lo siento, pero no sé qué significa eso :-(");
                    } catch(DukeExceptions e) {
                        System.out.println(e);
                    }
            }
            if (userInput.equals(TERMINATION)) {
                sc.close();
                break;
            }
        }
    }
}
