package pl.coderslab;

import pl.coderslab.controller.ExerciseController;
import pl.coderslab.controller.SolutionController;
import pl.coderslab.controller.UserController;
import pl.coderslab.controller.UserGroupController;

import java.util.Scanner;

public class MainMenu {

    public static final int USERS = 1;
    public static final int GROUPS = 2;
    public static final int EXERCISES = 3;
    public static final int SOLUTIONS = 4;


    public void mainMenu() {

        System.out.println("Czym chcesz zarządzac (wybierz numer) ? ");
        System.out.println("1 - Użytkownikami ");
        System.out.println("2 - Grupami");
        System.out.println("3 - Zadaniami");
        System.out.println("4 - Rozwiązaniami");
        System.out.println("5 - Zakończ program");

        int options;
        UserController userController = new UserController();
        UserGroupController userGroupController = new UserGroupController();
        ExerciseController exerciseController = new ExerciseController();
        SolutionController solutionController = new SolutionController();

        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Podaj poprawny nr wybranej opcji");
        }

        while (((options = scanner.nextInt()) != 0) && ((options = scanner.nextInt()) < 5)) {
            switch (options) {
                case USERS:
                    userController.chooseOption();
                    break;
                case GROUPS:
                    userGroupController.chooseOption();
                    break;
                case EXERCISES:
                    exerciseController.chooseOption();
                    break;
                case SOLUTIONS:
                    solutionController.chooseOption();
                    break;
                default:
                    System.out.println("Błąd! Nie ma takiej opcji - spróbuj ponownie");
            }
        }
        while (((options = scanner.nextInt()) == 5)) {
            System.exit(0);
        }
        scanner.close();
    }
}
