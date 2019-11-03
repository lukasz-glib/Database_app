package pl.coderslab.controller;

import pl.coderslab.MainMenu;
import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.entity.UserGroup;
import pl.coderslab.service.UserGroupService;

import java.util.Arrays;
import java.util.Scanner;

public class UserGroupController {

    public static final int DISPLAY_ALL = 1;
    public static final int DISPLAY_ONE = 2;
    public static final int CREATE_USER_GROUP = 3;
    public static final int UPDATE_USER_GROUP = 4;
    public static final int DELETE_USER_GROUP = 5;
    public static final int RETURN_TO_MENU = 6;

    private UserGroupService userGroupService; //usunięto atrybut final (po private)

    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    public UserGroupController() {
    }

    public void showMenu() {
        System.out.println("Wybierz opcje");
        System.out.println("1 : Display all");
        System.out.println("2 : Display one");
        System.out.println("3 : Create group");
        System.out.println("4 : Update group ");
        System.out.println("5 : Delete group ");
        System.out.println("6 : powrót do menu");
    }

        //TODO: wczytaj input od grupy, jeśli jest okej

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
                    displayAllGroups();
                    break;
                case DISPLAY_ONE:
                    displayOneGroup();
                    break;
                case CREATE_USER_GROUP:
                    createGroup();
                    break;
                case UPDATE_USER_GROUP:
                    updateGroup();
                    break;
                case DELETE_USER_GROUP:
                    deleteGroup();
                    break;
                default:
                    System.out.println("Błąd! Nie ma takiej opcji - spróbuj ponownie");
            }
            showMenu();
        }
        while ((options = scanner.nextInt()) == RETURN_TO_MENU) {
            returnTo.mainMenu();
            scanner.close();
        }
        scanner.close();
    }



    public void displayAllGroups(){
        //TODO : teskt zachęty, wczytanie danych od
        // grupy i wywołanie odpowiendije metody z serwisu

        System.out.println("Zestawienie danych do wszystkich grup");
        UserGroupDao userGroupDao = new UserGroupDao();
        System.out.println(Arrays.toString(userGroupDao.findAll()));
    }

    public void displayOneGroup(){

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nr id grupy, którą chcesz wyświetlić: ");
        int userGroupId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id grupy");
        }
        userGroupId = scann.nextInt();
        scann.close();

        UserGroupDao userGroupDao = new UserGroupDao();
        userGroupDao.read(userGroupId);
    }

    public void createGroup(){

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nazwę grupy:");
        String userGroupName;
        while (!scann.hasNextLine()) {
            scann.next();
            System.out.println("Podaj prawidłową nazwę grupy");
        }
        userGroupName = scann.nextLine();
        scann.close();

        UserGroup userGroup = new UserGroup(userGroupName);
        UserGroupDao userGroupDao = new UserGroupDao();
        userGroupDao.create(userGroup);
    }

    public void updateGroup(){

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nr id grupy, której dane chcesz zaktualizować: ");
        int userGroupId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id grupy");
        }
        userGroupId = scann.nextInt();

        System.out.println("Podaj nazwę grupy do zaktualizowania:");
        String userGroupName;
        while (!scann.hasNextLine()) {
            scann.next();
            System.out.println("Podaj prawidłową nazwę");
        }
        userGroupName = scann.nextLine();
        scann.close();

        UserGroup userGroup = new UserGroup(userGroupId, userGroupName);
        UserGroupDao userGroupDao = new UserGroupDao();
        userGroupDao.update(userGroup);
        System.out.println("Pomyślnie zaktualizowano grupę");
    }

    public void deleteGroup(){

        Scanner scann = new Scanner(System.in);
        System.out.println("Podaj nr id grupy, którą chcesz usunąć z bazy: ");
        int userGroupId;
        while (!scann.hasNextInt()) {
            scann.next();
            System.out.println("Podaj prawidłowy nr id grupy");
        }
        userGroupId = scann.nextInt();
        scann.close();

        UserGroupDao userGroupDao = new UserGroupDao();
        userGroupDao.delete(userGroupId);
        System.out.println("Usunięto grupę o nr id: " + userGroupId);
    }

}
