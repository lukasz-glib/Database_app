package pl.coderslab.controller;

import pl.coderslab.MainMenu;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Solution;
import pl.coderslab.service.SolutionService;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionController {

    public static final int DISPLAY_ALL = 1;
    public static final int ADD_SOLUTION = 2;
    public static final int VIEW_SOLUTION = 3;
    public static final int RETURN_TO_MENU = 4;

    private SolutionService solutionService; //po private - usunięto atrybut final

    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    public SolutionController() {
    }

    public void showMenu() {
        System.out.println("Wybierz opcje");
        System.out.println("1 : Display all");
        System.out.println("2 : Add solution");
        System.out.println("3 : View solution"); //wyświetla rozwiązanie po nr id użytkownika
        System.out.println("4 : powrót do menu");
    }

        //TODO: wczytaj input od solution, jeśli jest okej

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
                    displayAllSolution();
                    break;
                case ADD_SOLUTION:
                    addSolution();
                    break;
                case VIEW_SOLUTION:
                    viewSolution();
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


    public void displayAllSolution(){
        //TODO : teskt zachęty, wczytanie danych od
        // solution i wywołanie odpowiendniej metody z serwisu

        System.out.println("Zestawienie danych wszystkich użytkowników (uczniów w szkole IT)");
        SolutionDao solutionDao = new SolutionDao();
        System.out.println(Arrays.toString(solutionDao.findAll()));
    }

//    public void displayOneSolution(){
//
//    }

    public void viewSolution() {

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nr id użytkownika, którego rozwiązania chcesz wyświetlić: ");
        int userId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id użytkownika");
        }
        userId = scann.nextInt();
        scann.close();
        SolutionDao solutionDao = new SolutionDao();
        solutionDao.view(userId);
    }

    public void addSolution() {  //zadanie 4 - "add" (dział Programy administracyjne)

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nr id użytkownika, którego dotychczasowe rozwiązania chcesz wyświetlić " +
                "i dodać nowe rozwiązanie: ");
        int userId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id użytkownika");
        }
        userId = scann.nextInt();
        SolutionDao solutionDao = new SolutionDao();
        solutionDao.findAllByUserId(userId);

        System.out.println("Podaj nr id zadania, którego dotychczasowe rozwiązania chcesz wyświetlić " +
                "i dodać nowe rozwiązanie:: ");
        int exerciseId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id zadania");
        }
        exerciseId = scann.nextInt();
        scann.close();
        solutionDao.findAllByExerciseId(exerciseId);

        //pola updated i description mają byś PUSTE, pole created uzupełnia się automatycznie datą obecną NOW()
        Solution solution = new Solution(userId, exerciseId); //
        solutionDao.add(solution);
        System.out.println("Pomyślnie dodano zadanie ");
    }

//    public void updateSolution(){
//
//    }
//
//    public void deleteSolution(){
//
//    }
}
