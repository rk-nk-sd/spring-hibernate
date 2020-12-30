package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru", new Car("VAZ 21011",1));
      userService.add(user1);
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("VAZ 2107",7)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("VAZ 2103",3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("VAZ 2105",5)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Model auto = "+user.getCar().getModel());
         System.out.println("Series auto = "+user.getCar().getSeries());
         System.out.println();
      }

      System.out.println("Определяем пользователя по марке и серии автомобиля...");
      System.out.println("Нас будет интересовать 5 модель жигулей");
      for (User user : users) {
         if(user.getCar().getModel().equals("VAZ 2105") && user.getCar().getSeries() == 5) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = "+user.getEmail());
            System.out.println("Has model = "+user.getCar().getModel()+", series = "+user.getCar().getSeries());
            System.out.println();
         }

      }

      context.close();
   }
}
