import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Tratamento {

    char[] delete = {'\n', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '?', '.', ';', ',', ':', '!', '-', '(', ')', '_', '/', '*', '[', ']'};
    String[] palavras;
    File file = new File("C:\\Users\\vieir\\Documents\\GitHub\\ESII--TP2\\EXP");
    double[] docs = new double[listaFicheiros(file).length];
    LinkedHashSet<String> palavra = new LinkedHashSet<>();
    double[] ordenado = new double[listaFicheiros(file).length];




    public File[] listaFicheiros(File file) {

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");

            }
        };

        File[] files = file.listFiles(textFilter);

        return files;
    }

    public char isFound(char src) {

        for (int i = 0; i < delete.length; i++) {
            if (src == delete[i]) {
                return '\0';
            }
        }
        return src;
    }

    public String remove(String src) {

        char[] srcArr = src.toCharArray();
        StringBuilder sb = new StringBuilder();
        if (srcArr.length > 0) {
            for (int i = 0; i < srcArr.length; i++) {
                char foundChar = isFound(srcArr[i]);
                if (foundChar != '\0')
                    sb.append(foundChar);
            }
            return sb.toString();
        } else {
            return null;
        }

    }


    public double[][] tratamentoPalavras(int numFiles) throws IOException {

        if (numFiles > 0) {

            File[] f = listaFicheiros(file);

            int count = 0;

            for (int k = 0; k < numFiles; k++) {
                try (LineNumberReader r = new LineNumberReader(new FileReader(f[k]))) {
                    String line;
                    while ((line = r.readLine()) != null) {
                        String linha = new Tratamento().remove(line).toLowerCase();
                        for (String element : linha.split(" ")) {
                            if (!element.equals("")) {
                                palavra.add(element);

                            }
                        }
                    }
                }
            }
            double[][] matrizM = new double[numFiles][palavra.size()];
            palavras = palavra.toArray(new String[0]);

            for (int k = 0; k < numFiles; k++) {
                try (LineNumberReader r = new LineNumberReader(new FileReader(f[k]))) {
                    String line;
                    while ((line = r.readLine()) != null) {
                        String linha = new Tratamento().remove(line).toLowerCase();


                        for (int i = 0; i < palavra.size(); i++) {
                            count = 0;
                            for (String element : linha.split(" ")) {
                                if (palavras[i].compareTo(element) == 0) count++;
                            }
                            matrizM[k][i] = count;
                        }
                    }
                }
            }

            return matrizM;
        } else {
            return null;
        }
    }


    public void imprimirMatriz() throws IOException {


        int numFiles = listaFicheiros(file).length;
        double[][] matriz = tratamentoPalavras(numFiles);
        System.out.println();
        System.out.println("|-------------------------|");
        System.out.println("|    Matriz Principal:    |");
        System.out.println("|-------------------------|");
        for (int i = 0; i < palavra.size(); i++) {
            System.out.print(palavras[i] + "  | ");
        }
        for (int k = 0; k < numFiles; k++) {
            System.out.println();
            for (int i = 0; i < palavra.size(); i++) {
                System.out.print(matriz[k][i] + "\t|\t");
            }
        }
        System.out.println();
    }


    public int countforQ(int numFiles) {

        if (numFiles > 0) {

            int countforQ = 0;
            for (int k = 0; k < numFiles; k++) {
                for (int i = 0; i < palavra.size(); i++) {
                    countforQ++;
                }
            }
            return countforQ;
        } else {
            return -1;
        }
    }


    public double[] matrizQ(int numFiles, File[] f, double[][] matrizM) throws IOException {

        if (numFiles > 0 && f != null && matrizM != null) {

            Scanner input = new Scanner(System.in);

            System.out.println("\n \nIntroduza a palavra a pesquisar: \n");
            String p = input.next();

            //CALCULAR
            int countPp = 0;
            double countP = 0;
            for (int k = 0; k < numFiles; k++) {
                try (LineNumberReader r = new LineNumberReader(new FileReader(f[k]))) {
                    String line;
                    while ((line = r.readLine()) != null) {
                        String linha = new Tratamento().remove(line).toLowerCase();
                        for (int i = 0; i < palavra.size(); i++) {
                            countPp = 0;
                            for (String element : linha.split(" ")) {
                                if (element.compareTo(p) == 0) countPp++;
                            }

                        }
                    }
                }
                if (countPp > 0) {
                    countP++;

                }
            }

            System.out.println("\nA palavra " + p + " esta presente em " + countP + " ficheiros \n");

            //IMPRIMIR MATRIZ Q COM PRIMEIRA FORMULA APLICADA
            System.out.println("|-----------------|");
            System.out.println("|    Matriz Q:    |");
            System.out.println("|-----------------|");
            double[] matrizQ = new double[countforQ(numFiles)];
            int j = 0;
            double numeroFicheiro = numFiles;

            if (countP != 0) {
                for (int k = 0; k < numFiles; k++) {
                    for (int i = 0; i < palavra.size(); i++) {
                        matrizQ[j] = (matrizM[k][i] * (1 + Math.log10(numeroFicheiro / countP)));
                        j++;
                    }
                }

            } else {
                System.out.println("\nA palavra indicada não se encontra em nenhum documento!");
            }

            for (int l = 0; l < matrizQ.length; l++) {
                System.out.println(matrizQ[l]);
            }

            return matrizQ;
        } else {
            return null;
        }
    }

    public double[] grauSimilariedade(int numFiles, double[][] matrizM,  double[] matrizQ) throws IOException {

        if(numFiles>0 && matrizM!=null && matrizQ!=null) {

            double somadeCima = 0, somadeQ = 0, somadeM = 0;
            int j = 0;
            double[] grauSim = new double[numFiles];

            for (int k = 0; k < numFiles; k++) {
                for (int i = 0; i < palavra.size(); i++) {

                    somadeCima += (matrizM[k][i] * matrizQ[j]);
                    somadeM += Math.pow(matrizM[k][i], 2);
                    somadeQ += Math.pow(matrizQ[j], 2);
                    j++;
                }
                grauSim[k] = somadeCima / ((Math.sqrt(somadeM)) * (Math.sqrt(somadeQ)));
                somadeCima = 0;
                somadeM = 0;
                somadeQ = 0;
            }

            System.out.println();
            System.out.println("Desordenado: \n");

            for (int k = 0; k < numFiles; k++) {
                docs[k] = grauSim[k];
                System.out.println("Grau de similariedade com o documento " + (k + 1) + " = " + grauSim[k]);
            }
            return grauSim;
        }
        else{
            return null;
        }
    }

    public double[] ordenado(int numFiles) throws IOException {

        if(numFiles>0) {

            ordenado = grauSimilariedade(numFiles, tratamentoPalavras(numFiles), matrizQ(numFiles, listaFicheiros(file), tratamentoPalavras(numFiles)));
            System.out.println();
            System.out.println("Ordenado:\n");

            Arrays.sort(ordenado);

            for (int i = numFiles - 1; i >= 0; i--) {
                System.out.println("Grau similariedade do documento = " + ordenado[i]);
            }
            return ordenado;
        }
        else{
            return null;
        }
    }


    public int[] grauAcima(int numFiles, double[] ordenado) throws IOException {

        if(numFiles>0 && ordenado!=null) {
            int[] ficheiros = new int[numFiles];

            Scanner input = new Scanner(System.in);

            int i = numFiles - 1;
            double value;
            System.out.println("\n \nIntroduza o grau maximo de similariedade a  encontrar: \n");
            value = Double.parseDouble(input.nextLine());

            System.out.println("Ficheiros com grau superior a " + value + "\n");

            while (ordenado[i] > value) {
                System.out.println("Grau similariedade do documento = " + ordenado[i]);
                if (i == 0) {
                    break;
                }
                i--;
            }

            return ficheiros;
        }
        else{
            return null;
        }
    }

    public int[] maximoFicheiros(int numFiles, double[] ordenado) throws IOException {

        if(numFiles>0 && ordenado!=null) {
            
            int[] ficheiros = new int[numFiles];

            Scanner input = new Scanner(System.in);

            System.out.println("\n \nIntroduza o maximo de ficheiros a pesquisar similariedade: \n");
            int p = input.nextInt();

            for (int i = numFiles - 1; i >= (numFiles - p); i--) {
                System.out.println("Grau similariedade do documento = " + ordenado[i]);
            }

            return ficheiros;
        }
        else{
            return null;
        }
    }

}