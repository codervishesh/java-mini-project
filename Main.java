package org.example;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Weather and Email System");

        while (true) {
            System.out.println("\n1. Add User\n2. Get Weather and Send Email\n3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                if (choice == 1) {
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter City: ");
                    String city = scanner.nextLine();

                    DatabaseService.addUser(name, email, city);

                } else if (choice == 2) {
                    System.out.print("Enter City: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    String json = WeatherService.getWeather(city);
                    String weatherReport = WeatherParser.parseWeatherJson(json, name);
                    System.out.println("Weather Info: " + weatherReport);

                    System.out.print("Enter recipient email: ");
                    String email = scanner.nextLine();

                    EmailService.sendEmail(email, "Weather Update for " + city, weatherReport);

                } else if (choice == 3) {
                    System.out.println("Exiting...");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}
