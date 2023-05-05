import java.util.HashMap;

public class Run {
    public static void main(String[] args) {

        String[] user = UserInput.getUserInput();
        HashMap<String, String> s = Checks.checkUserInput(user);
        String f = DataFile.fileSearch(user);
        DataFile.dataFile(s, f);
        
    }    
}
