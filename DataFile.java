import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DataFile {
    
    public static String fileName = null;

    public static String fileSearch(String[] arrayStr){
        for (int i = 0; i < arrayStr.length; i++) {
        if (arrayStr[i].contains(",")) {
            String[] tempArray = arrayStr[i].split(",");
            fileName = tempArray[0] + ".txt";// формируем название файла на основе фамилии
        } }
        return fileName;
    }


    public static void dataFile(HashMap<String, String> personalData, String fileName)  {
        String currPathName = System.getProperty("user.dir");
        File currUserFile = new File(currPathName, fileName);
        try (FileWriter userData = new FileWriter(currUserFile, true);) {
            for (HashMap.Entry<String, String> item : personalData.entrySet()) {
                userData.write(item.getKey() + ": ");
                userData.write(item.getValue() + "; " ); 
            }
            userData.write("\n" ); 
            System.out.println(
                    "Файл '" + fileName + "' успешно создан " + " в папке '" + currPathName + "'.");
        } catch (IOException e) {
            System.out.println("Нет возможности создать файл " + fileName + ".");
            e.printStackTrace();
        }

    }
}
