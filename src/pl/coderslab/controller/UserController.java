package pl.coderslab.controller;

import com.sun.tools.javac.Main;
import pl.coderslab.MainMenu;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserGroupService;
import pl.coderslab.service.UserService;

import java.util.Arrays;
import java.util.Scanner;

public class UserController {

    public static final int DISPLAY_ALL = 1;
    public static final int DISPLAY_ONE = 2;
    public static final int DISPLAY_BY_GROUP = 3;
    public static final int CREATE_USER = 4;
    public static final int UPDATE_USER = 5;
    public static final int DELETE_USER = 6;
    public static final int RETURN_TO_MENU = 7;


    private UserService userService; //po private - usunięto atrybut final

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserController() {
    }

    public void showMenu() {
        System.out.println("Wybierz opcje");
        System.out.println("1 : Display all");
        System.out.println("2 : Display one");
        System.out.println("3 : Display all by group id");
        System.out.println("4 : Create user");
        System.out.println("5 : Update user");
        System.out.println("6 : Delete user");
        System.out.println("7 : powrót do menu");
    }


        //TODO: wczytaj input od użytkownika, jeśli jest okej

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
                    displayAllUsers();
                    break;
                case DISPLAY_ONE:
                    displayOneUser();
                    break;
                case DISPLAY_BY_GROUP:
                    displayAllUsersByGroupId();
                    break;
                case CREATE_USER:
                    createUser();
                    break;
                case UPDATE_USER:
                    updateUser();
                    break;
                case DELETE_USER:
                    deleteUser();
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



    public void displayAllUsers(){
        //TODO : teskt zachęty, wczytanie danych od
        // użytkownika i wywołanie odpowiendije metody z serwisu

        System.out.println("Zestawienie danych wszystkich użytkowników (uczniów w szkole IT)");
        UserDao userDao = new UserDao();
        System.out.println(Arrays.toString(userDao.findAll()));

    }

    public void displayAllUsersByGroupId() {

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nr id grupy: ");
        int userGroupId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id grupy");
        }
        userGroupId = scann.nextInt();
        scann.close();

        UserDao userDao = new UserDao();
        System.out.println("Do grupy o nr id: " + userGroupId + "należą użytkownicy: \n" +
                Arrays.toString(userDao.findAllByGroupId(userGroupId)));
    }

    public void displayOneUser() {

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nr id użytkownika, którego chcesz wyświetlić: ");
        int userId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id użytkownika");
        }
        userId = scann.nextInt();
        scann.close();

        UserDao userDao = new UserDao();
        userDao.read(userId);
    }

    public void createUser() {

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj imię użytkownika:");
        String userName;
        while (!scann.hasNextLine()) {
            scann.next();
            System.out.println("Podaj prawidłowe imię");
        }
        userName = scann.nextLine();

        System.out.println("Podaj email użytkownika:");
        String userEmail;
        while (!scann.hasNextLine()) {
            scann.next();
            System.out.println("Podaj prawidłowy email");
        }
        userEmail = scann.nextLine();

        System.out.println("Podaj hasło użytkownika:");
        String userPassword = scann.next();
        User user1 = new User(); //wywołuję pierwszy raz klasę User - by skorzystać z metody hashowania hasła
        user1.hashPassword(userPassword); //hasło wpisane przez użytkownika wysyłam do "hashowania" - metoda jest w klasie User

        System.out.println("Podaj id grupy, do której ma należeć nowy użytkownik:");
        int userGroupId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id grupy");
        }
        userGroupId = scann.nextInt();

        scann.close();

        User user2 = new User(); //drugi raz wywołuję klasę User by skorzystać z gettera dla hasła
        User user= new User(userName, userEmail, user2.getPassword(), userGroupId); //pobieram to hasło "zahashowane" powyższą metodą za pomocą gettera

        UserDao userDao = new UserDao();
        userDao.create(user);

    }

    public void updateUser() {

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nr id użytkownika, którego dane chcesz zaktualizować: ");
        int userId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id użytkownika");
        }
        userId = scann.nextInt();

        System.out.println("Podaj nowe imię użytkownika do aktualizacji:");
        String userName;
        while (!scann.hasNextLine()) {
            scann.next();
            System.out.println("Podaj prawidłowe imię");
        }
        userName = scann.nextLine();

        System.out.println("Podaj nowy adres email użytkownika do aktualizacji:");
        String userEmail;
        while (!scann.hasNextLine()) {
            scann.next();
            System.out.println("Podaj prawidłowy email");
        }
        userEmail = scann.nextLine();

        System.out.println("Podaj nowe hasło użytkownika do aktualizacji:");
        String userPassword = scann.next();
        User user1 = new User(); //wywołuję pierwszy raz klasę User - by skorzystać z metody hashowania hasła
        user1.hashPassword(userPassword); //hasło wpisane przez użytkownika wysyłam do "hashowania" - metoda jest w klasie User

        System.out.println("Podaj zaktualizowany nr id grupy, do której ma należeć użytkownik:");
        int userGroupId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id grupy");
        }
        userGroupId = scann.nextInt();

        scann.close();

        User user2 = new User();
        User user = new User(userId, userName, userEmail, user2.getPassword(), userGroupId);
        UserDao userDao = new UserDao();
        userDao.update(user);
        System.out.println("Pomyślnie zaktualizowano użytkownika");

    }

    public void deleteUser() {

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nr id użytkownika, którego chcesz usunąć z bazy: ");
        int userId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id użytkownika");
        }
        userId = scann.nextInt();
        scann.close();

        UserDao userDao = new UserDao();
        userDao.delete(userId);
        System.out.println("Usunięto użytkownika o nr id: " + userId);

    }
}
