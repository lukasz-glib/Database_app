import pl.coderslab.MainMenu;
import pl.coderslab.controller.UserController;
import pl.coderslab.controller.UserGroupController;
import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        //TODO : menu główne
        //UWAGA: menu główne zrobione w oddzielnej klasie MainMenu !!!

        MainMenu menu = new MainMenu();
        menu.mainMenu();







//        System.out.println("Czym chcesz zarządzac (wybierz numer) ? ");
//        System.out.println("1 - Użytkownikami ");
//        System.out.println("2 - Grupami");
//        //TODO : wczytać i użyć kontrollera odpowiedniego - w nowej klasie, patrz plik MainMenu




//        Scanner scanner = new Scanner(System.in);
//        int numChoice;
//        while (!scanner.hasNextInt()) {
//            scanner.next();
//            System.out.println("Wybierz prawidłowy numer: 1 lub 2");
//        }
//        numChoice = scanner.nextInt();
//        scanner.close();
//
//        UserDao userDao = new UserDao();
//        UserGroupDao userGroupDao = new UserGroupDao();
//        UserController userController = new UserController();
//        UserGroupController userGroupController = new UserGroupController();
//        if(numChoice == 1) {
//            userDao.findAll(); //dodać nową metodę do UserDao - wyświetli wszystkie imiona użytkowników - przerzucić to do MainMenu
//            userController.showMenu();
//        } else if (numChoice == 2) {
//            userGroupDao.findAll();
//            userGroupController.showMenu();
//        }



    }



}
