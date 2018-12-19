
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class X {

    char[] deleteDigitos = {'0','1','2','3','4','5','6','7','8','9'};
    char[] deletePont = {'?','.',';',',',':', '!', '-','(',')','_', '/','*','[',']'};

    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\vieir\\IdeaProjects\\EXP\\src\\main\\java\\file.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            System.out.println(st);

            System.out.println(new X().removeDigitos(st));
            System.out.println(new X().removeCarateresPont(st));
        }
    }


    public String removeDigitos(String src){
        char[] srcArr = src.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < srcArr.length; i++) {
            char foundChar = isFoundDig(srcArr[i]);
            if(foundChar!='\0')
                sb.append(foundChar);
        }
        return sb.toString();

    }

    public String removeCarateresPont(String src){
        char[] srcArr = src.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < srcArr.length; i++) {
            char foundChar = isFoundPont(srcArr[i]);
            if(foundChar!='\0')
                sb.append(foundChar);
        }
        return sb.toString();

    }

    public char isFoundDig(char src){
        for (int i = 0; i < deleteDigitos.length; i++) {
            if(src==deleteDigitos[i]){
                return '\0';
            }
        }
        return src;
    }
    public char isFoundPont(char src){
        for (int i = 0; i < deletePont.length; i++) {
            if(src==deletePont[i]){
                return '\0';
            }
        }
        return src;
    }
}