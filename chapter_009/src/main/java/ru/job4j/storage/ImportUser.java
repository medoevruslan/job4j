package ru.job4j.storage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.storage.model.User;

import java.util.Scanner;


public class ImportUser {

    public void addConsole(UserStorage storage) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("Please entert user name: ");
        user.setName(scanner.nextLine());
        System.out.println("Please enter user surname: ");
        user.setSurname(scanner.nextLine());
        storage.add(user);
        System.out.println("Add one more user? (y/n)");
        if (scanner.nextLine().equals("y")) {
            addConsole(storage);
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("storage-spring-context.xml");
        UserStorage storage = (UserStorage) context.getBean("userJdbcStorage");
        new ImportUser().addConsole(storage);
    }
}
