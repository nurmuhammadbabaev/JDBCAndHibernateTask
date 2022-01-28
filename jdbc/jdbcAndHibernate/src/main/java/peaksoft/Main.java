package peaksoft;

import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;


import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
        int number=0;
        String nameCreate=null;
        byte ageCreate=0;

        UserService userService=new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Kanat","Subanov", (byte) 21);
        userService.saveUser("Nurik","Subanov", (byte) 22);
        userService.saveUser("Nurmu","Subanov", (byte) 23);
        userService.saveUser("Kanat","Subanov", (byte) 24);
        userService.getAllUsers();
        userService.removeUserById(1);
       userService.cleanUsersTable();

        while (true){
    System.out.println("Панель управления!");
    System.out.println("1.Для того чтобы создать таблицу нажмите клавищу->'1'");
    System.out.println("2.Для того чтобы добавить информацию нажмите клавищу->'2'");
    System.out.println("3.Для того чтобы получить информацию о таблице нажмите клавищу->'3'");
    System.out.println("4.Для того чтобы удалить таблицу нажмите клавищу->'4'");
    System.out.println("5.Для того чтобы удалить 'id' пользователья нажмите клавищу->'5'");
    System.out.println("6.Для того чтобы очистить таблицу нажмите клавищу->'6'");
    System.out.println("##########################################################");
    number=scanner.nextInt();
    if (number==1){
        userService.createUsersTable();
    }
        if (number==2){
            System.out.println(" name\t\t| lastName\t\t| age |");
            System.out.println("-----------+---------------+-----+");
            userService.saveUser(nameCreate=scanner.next(),nameCreate=scanner.next(),ageCreate= scanner.nextByte());
        }else if (number==3){
            System.out.println(userService.getAllUsers());
        }else if (number==4){
            userService.dropUsersTable();
        }
        if(number==5){
            System.out.println("Сколько 'id' вы хотите удалить!");
            userService.removeUserById(1);

        }else if(number==6){
            userService.cleanUsersTable();
        }
        else {
            System.out.println("");
        }

   }
    }
}
