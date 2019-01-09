import java.io.IOException;
import java.util.ArrayList;

public class X {

    public static void main(String[] args) throws IOException {

        Tratamento t = new Tratamento();
        ArrayList<Palavra> p = t.tratamentoPalavras();

        //System.out.println(p);

        t.matriz(p);



    }

}