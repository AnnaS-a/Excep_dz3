import java.util.HashMap;
import java.util.regex.Pattern;

public class Checks {

    public static HashMap<String, String> checkUserInput(String[] arrayStr) {
        // формрует HashMap для записи в файл
        int genderLen = 1;
        int dateLen = 10;
        HashMap<String, String> resultData = new HashMap<String, String>();
        for (int i = 0; i < arrayStr.length; i++) {
            if (arrayStr[i].length() == genderLen
                    && (arrayStr[i].equalsIgnoreCase("f") || arrayStr[i].equalsIgnoreCase("m"))) {
                resultData.put("Пол", arrayStr[i]);
            } else if (arrayStr[i].length() == dateLen && arrayStr[i].indexOf(".") == 2) {
                if (checkDate(arrayStr[i])) {
                    resultData.put("Дата рождения", arrayStr[i]);
                }
            } else if (arrayStr[i].contains(",")) {
                if (checkFullName(arrayStr[i])) {
                    resultData.put("Полное имя", arrayStr[i]);
                }
            } else if (!arrayStr[i].contains(".") && !arrayStr[i].contains(",")) {
                if (checkPhone(arrayStr[i])) {
                    resultData.put("Номер телефона", arrayStr[i]);
                }
            } else {
                throw new IllegalArgumentException("Неправильный формат ввода. Проверьте ввод");
            }
        }
        return resultData;
    }

    public static boolean checkFullName(String nameStr) {
        boolean result = false;
        String pattern = "\\D+";
        String[] tempArray = nameStr.split(",");
        if (tempArray.length != 3) {
            throw new IllegalArgumentException(
                    "Неверный формат ФИО. Вводите «Фамилия,Имя,Отчество» через , без пробелов."
                            + nameStr);
        } else {
            for (int i = 0; i < tempArray.length; i++) {             // проверяем, что в ФИО нет цифр.
                if (!Pattern.matches(pattern, tempArray[i])) {        // если строка содержит число,то ошибка
                    throw new IllegalArgumentException(
                            "Неверный '" + tempArray[i] + "' формат. ФИО должен содержать буквы.");
                }
            }
            result = true;
        }
        return result;
    }


    public static boolean checkDate(String dateStr) {
        boolean result = false;
        String[] tempArray = dateStr.split("\\.");
        if (tempArray.length != 3) {
            throw new IllegalArgumentException(
                    "Неправильный формат ДАТЫ РОЖДЕНИЯ. Вы должны использовать формат «дд.мм.гггг». " + dateStr);
        } else {
            if (Integer.parseInt(tempArray[2]) > 1900 && Integer.parseInt(tempArray[2]) < 2024) { // диапазон годов                                                                                 
                if (Integer.parseInt(tempArray[1]) > 0 && Integer.parseInt(tempArray[1]) < 13) {// диапазон месяцев                                                                              
                    if (Integer.parseInt(tempArray[1]) == 4 || Integer.parseInt(tempArray[1]) == 6
                            || Integer.parseInt(tempArray[1]) == 9 || Integer.parseInt(tempArray[1]) == 11) {
                        if (Integer.parseInt(tempArray[0]) < 1 || Integer.parseInt(tempArray[0]) > 30) {
                            throw new IllegalArgumentException(
                                    "Неверный день. Проверьте " + tempArray[0] + " ,должно быть в пределах [1;30].");
                        }
                    } else if (Integer.parseInt(tempArray[1]) == 2) {
                        if (Integer.parseInt(tempArray[0]) < 1 || Integer.parseInt(tempArray[0]) > 28) {
                            throw new IllegalArgumentException(
                                    "Неверный день. " + tempArray[0] + " ,должен быть в пределах [1;28].");
                        }
                    } else {
                        if (Integer.parseInt(tempArray[0]) < 1 || Integer.parseInt(tempArray[0]) > 31) {
                            throw new IllegalArgumentException(
                                    "Неверный день. " + tempArray[0] + " ,должен быть в пределах [1;31].");
                        }
                    }
                    result = true;
                } else {
                    throw new IllegalArgumentException(
                            "Неверный месяц. " + tempArray[1] + " ,должен быть в пределах [1;12].");
                }
            } else {
                throw new IllegalArgumentException(
                        "Неверный год. " + tempArray[2] + " ,должен быть в пределах [1900;текущ.год].");
            }
        }
        return result;
    }

    public static boolean checkPhone(String phoneStr) {
        boolean result = false;
        try {
            Double.parseDouble(phoneStr);
            result = true;
        } catch (NumberFormatException exception) {
            System.out.println(
                    "Неверный формат НОМЕРА ТЕЛЕФОНА. Необходим только номер. Проверьте, пожалуйста " + phoneStr);
        }
        return result;
    }

}
