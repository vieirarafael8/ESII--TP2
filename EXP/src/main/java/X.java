import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class X {

    public static void main(String[] args) throws IOException {

        Tratamento t = new Tratamento();

        t.tratamentoPalavras(t.listaFicheiros(t.file).length);
        t.imprimirMatriz();

        //ARRAY DESORNEDAO
       //t.grauSimilariedade();
       t.ordenado(t.listaFicheiros(t.file).length);

       t.grauAcima(t.listaFicheiros(t.file).length);
       t.maximoFicheiros(t.listaFicheiros(t.file).length, t.ordenado);



    }

}