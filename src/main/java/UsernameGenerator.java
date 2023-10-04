import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class UsernameGenerator {
    public static String GenerateUsername(){
        List<String> Alphabet = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
        StringBuilder username = new StringBuilder();
        Random rand = new Random();
        //generates a username 24 characters long
        for (int i = 0;i < 24;i++){
            username.append(Alphabet.get(rand.nextInt(Alphabet.size())));
        }
        return username.toString();
    }
}
