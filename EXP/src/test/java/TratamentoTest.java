import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TratamentoTest {


    @Test
    public void isFound() {

        Tratamento t = new Tratamento();
        char src = 'a';
        char src1 = '?';
        char src2 = '\0';


        char result = t.isFound(src);
        char result1 = t.isFound(src1);
        char result2 = t.isFound(src2);

        assertEquals('a', result);
        assertEquals('\0', result1);
        assertEquals('\0', result2);
    }

    @Test
    public void remove() {
        Tratamento t = new Tratamento();

        String src = "a?";
        String src1 = "c";
        String src2 = "";

        String result = t.remove(src);
        String result1 = t.remove(src1);
        String result2 = t.remove(src2);

        assertEquals("a", result);
        assertEquals("c", result1);
        assertEquals(null, result2);

    }

    @Test
    public void tratamentoPalavras() throws IOException {

        Tratamento t = new Tratamento();
        int numFiles = 1;
        int numFiles1 = 0;


        double[][] result = t.tratamentoPalavras(numFiles);
        double[][] result1 = t.tratamentoPalavras(numFiles1);

        assertNotNull(result);
        assertNull(result1);

    }

    @Test
    public void countforQ() {

        Tratamento t = new Tratamento();
        int numFiles = 1;
        int numFiles1 = 0;

        int result = t.countforQ(numFiles);
        int result1 = t.countforQ(numFiles1);

        assertNotNull(result);
        assertEquals(-1, result1);


    }

    @Test(expected = NoSuchElementException.class)
    public void matrizQ() throws IOException {

        Tratamento t = new Tratamento();
        int numFiles = 1;
        int numFiles1 = 0;

        File[] f = t.listaFicheiros(t.file);
        File[] f1 = null;

        double[][] matrizM = new double[1][1];
        double[][] matrizM1 = null;

        String p= "cenas";
        String p1= "";

        double[] result = t.matrizQ(numFiles,f,matrizM, new Scanner(p));
        double[] result1 = t.matrizQ(numFiles1,f,matrizM,new Scanner(p));
        double[] result2 = t.matrizQ(numFiles,f1,matrizM, new Scanner(p));
        double[] result3 = t.matrizQ(numFiles,f,matrizM1, new Scanner(p));
        double[] result4 = t.matrizQ(numFiles,f,matrizM, new Scanner(p1));

        assertNotNull(result);
        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
        assertNull(result4);

    }

    @Test
    public void grauSimilariedade() throws IOException{

       Tratamento t = new Tratamento();

        int numFiles = 1;
        int numFiles1 = 0;

        double[][] matrizM = new double[numFiles][numFiles];
        double[][] matrizM1 = null;

        double[] matrizQ = new double[numFiles];
        double[] matrizQ1 = null;

        double[] result = t.grauSimilariedade(numFiles, matrizM, matrizQ);
        double[] result1 = t.grauSimilariedade(numFiles1, matrizM, matrizQ);
        double[] result2 = t.grauSimilariedade(numFiles, matrizM1, matrizQ);
        double[] result3 = t.grauSimilariedade(numFiles, matrizM, matrizQ1);

        assertNotNull(result);
        assertNull(result1);
        assertNull(result2);
        assertNull(result3);

    }

    @Test(expected = NoSuchElementException.class)
    public void ordenado() throws IOException{

        Tratamento t = new Tratamento();

        int numFiles = 1;
        int numFiles1 = 0;

        Map<String, Double> result = t.ordenado(numFiles);
        Map<String, Double> result1 = t.ordenado(numFiles1);

        assertNotNull(result);
        assertNull(result1);

    }

    @Test(expected = NoSuchElementException.class)
    public void grauAcima(){
        Tratamento t = new Tratamento();

        int numFiles = 1;
        int numFiles1 = 0;

        String[] result = t.grauAcima(numFiles);

        String[] result1 = t.grauAcima(numFiles1);

        assertNotNull(result);
        assertNull(result1);


    }

    @Test(expected = NoSuchElementException.class)
    public void maximoFicheiros() {

        Tratamento t = new Tratamento();


        int numFiles = 1;
        int numFiles1 = 0;

        double[] ordenado = new double[numFiles];
        double[] ordenado1 = new double[numFiles1];


        String[] result = t.maximoFicheiros(numFiles, ordenado);
        String[] result1 = t.maximoFicheiros(numFiles, ordenado1);


        assertNotNull(result);
        assertNull(result1);

    }


}