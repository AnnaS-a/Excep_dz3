import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {
    public static String[] getUserInput() {
        System.out.println("Введите данные, в указанных форматах, разделяя их пробелами:\n'Фамилия,Имя,Отчество' Дата рождения:'дд.мм.гггг' Телефон:'12 цифр' Пол:'f' или 'm'");
        //System.out.println("Используйте пробелы для разделения:");
        String[] userInputArray = null;
        boolean status = true;
        while (status) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, "Cp866"))) {
                String userInput = bufferedReader.readLine();
                if (userInput.isBlank()) {
                    throw new IllegalArgumentException(
                            "Неверный формат ввода! Вы ввели пустую строку.Попробуйте еще раз!");
                }
                userInputArray = userInput.split(" ");
            } catch (IOException ex) {
                System.out.println("Есть проблемы с вводом-выводом.");
                ex.printStackTrace();
            }
            if (userInputArray.length != 4) {
                throw new IllegalArgumentException(
                        "Неверный формат ввода! Вы ввели неверное количество параметров. Попробуйте еще раз!");
            } else {
                status = false;
            }

        }
        return userInputArray;
    }

    

    
}
