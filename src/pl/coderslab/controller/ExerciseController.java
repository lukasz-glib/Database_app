package pl.coderslab.controller;

import pl.coderslab.MainMenu;
import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Exercise;
import pl.coderslab.service.ExerciseService;

import java.util.Arrays;
import java.util.Scanner;

public class ExerciseController {

    public static final int DISPLAY_ALL = 1;
    public static final int DISPLAY_ONE = 2;
    public static final int CREATE_EXERCISE = 3;
    public static final int UPDATE_EXERCISE = 4;
    public static final int DELETE_EXERCISE = 5;
    public static final int RETURN_TO_MENU = 6;

    private ExerciseService exerciseService; //po private - usunięto atrybut final

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    public ExerciseController() {
    }

    public void showMenu() {
        System.out.println("Wybierz opcje");
        System.out.println("1 : Display all");
        System.out.println("2 : Display one");
        System.out.println("3 : Create exercise");
        System.out.println("4 : Update exercise");
        System.out.println("5 : Delete exercise");
        System.out.println("6 : powrót do menu");
    }


        //TODO: wczytaj input od exercise, jeśli jest okej

    public void chooseOption() {
        int options;
        showMenu();
        MainMenu returnTo = new MainMenu();
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Podaj poprawny nr wybranej opcji");
        }
        while ((options = scanner.nextInt()) != RETURN_TO_MENU) {
            switch (options) {
                case DISPLAY_ALL:
                    displayAllExercises();
                    break;
                case DISPLAY_ONE:
                    displayOneExercise();
                    break;
                case CREATE_EXERCISE:
                    createExercise();
                    break;
                case UPDATE_EXERCISE:
                    updateExercise();
                    break;
                case DELETE_EXERCISE:
                    deleteExercise();
                    break;
                default:
                    System.out.println("Błąd! Nie ma takiej opcji - spróbuj ponownie");
            }
            showMenu();
        }
        while ((options = scanner.nextInt()) == RETURN_TO_MENU) {
            returnTo.mainMenu();
        }
        scanner.close();
    }


    public void displayAllExercises(){
        //TODO : teskt zachęty, wczytanie danych od
        // exercise i wywołanie odpowiendije metody z serwisu

        System.out.println("Zestawienie danych do wszystkich zadań");
        ExerciseDao exerciseDao = new ExerciseDao();
        System.out.println(Arrays.toString(exerciseDao.findAll()));
    }

    public void displayOneExercise(){

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nr id zadania, którego chcesz wyświetlić: ");
        int exerciseId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id zadania");
        }
        exerciseId = scann.nextInt();
        scann.close();

        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.read(exerciseId);
    }

    public void createExercise(){

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj tytuł zadania:");
        String exerciseTitle;
        while (!scann.hasNextLine()) {
            scann.next();
            System.out.println("Podaj prawidłowy tytuł");
        }
        exerciseTitle = scann.nextLine();

        System.out.println("Podaj opis zadania:");
        String exerciseDescription = scann.nextLine();

        scann.close();

        Exercise exercise = new Exercise(exerciseTitle, exerciseDescription);
        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.create(exercise);
    }

    public void updateExercise() {

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nr id zadania, którego dane chcesz zaktualizować: ");
        int exerciseId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id zadania");
        }
        exerciseId = scann.nextInt();

        System.out.println("Podaj tytuł zadania do zaktualizowania:");
        String exerciseTitle;
        while (!scann.hasNextLine()) {
            scann.next();
            System.out.println("Podaj prawidłowy tytuł");
        }
        exerciseTitle = scann.nextLine();

        System.out.println("Podaj opis zadania do zaktualizowania:");
        String exerciseDescription = scann.nextLine();

        scann.close();

        Exercise exercise = new Exercise(exerciseId, exerciseTitle, exerciseDescription);
        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.update(exercise);
        System.out.println("Pomyślnie zaktualizowano zadanie");
    }

    public void deleteExercise(){

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nr id zadania, które chcesz usunąć z bazy: ");
        int exerciseId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id zadania");
        }
        exerciseId = scann.nextInt();
        scann.close();

        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.delete(exerciseId);
        System.out.println("Usunięto zadanie o nr id: " + exerciseId);
    }
}
