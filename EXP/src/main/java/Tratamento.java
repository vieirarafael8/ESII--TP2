import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Tratamento {

    char[] delete = {'\n','0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '?', '.', ';', ',', ':', '!', '-', '(', ')', '_', '/', '*', '[', ']'};
    String[] palavras;
    public File[] listaFicheiros() {
        File file = new File("C:\\Users\\nunof\\Desktop\\Universidade\\PAW\\ESII--TP2.1\\EXP");

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };

        File[] files = file.listFiles(textFilter);
//        System.out.println(files.toString());
        return files;
    }


    public String remove(String src) {
        char[] srcArr = src.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < srcArr.length; i++) {
            char foundChar = isFound(srcArr[i]);
            if (foundChar != '\0')
                sb.append(foundChar);
        }
        return sb.toString();

    }


    public char isFound(char src) {

        for (int i = 0; i < delete.length; i++) {
            if (src == delete[i]) {
                return '\0';
            }
        }
        return src;
    }

    public int[][] tratamentoPalavras() throws IOException {

        int numFiles = listaFicheiros().length;
        File[] f = listaFicheiros();
        LinkedHashSet<String> palavra = new LinkedHashSet<>();
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
            int[][] matriz = new int[numFiles][palavra.size()];
        palavras = palavra.toArray(new String[0]);

        for (int k = 0; k < numFiles; k++) {
            try (LineNumberReader r = new LineNumberReader(new FileReader(f[k]))) {
                String line;
                while ((line = r.readLine()) != null) {
                    String linha = new Tratamento().remove(line).toLowerCase();


                        for (int i = 0; i<palavra.size();i++) {
                            count=0;
                            for (String element : linha.split(" ")) {
                                if(palavras[i].compareTo(element)==0)count++;
                            }
                            matriz[k][i] = count;
                        }
                    }
                }
            }
        int countforQ=0;
        for (int i=0; i<palavra.size();i++){
            System.out.print(palavras[i] + "  | ");
        }
        for(int k=0; k<numFiles;k++){
            System.out.println();
            for (int i=0; i<palavra.size();i++){
                System.out.print(matriz[k][i] + "\t|\t");
                countforQ++;
            }
        }

        double[] matrizQ =new double[countforQ];

        Scanner input = new Scanner(System.in);

        System.out.println("\nIntroduza a palavra a pesquisar: \n");
        String p = input.next();

        int countPp =0;
        double countP=0;
        for (int k = 0; k < numFiles; k++) {
            try (LineNumberReader r = new LineNumberReader(new FileReader(f[k]))) {
                String line;
                while ((line = r.readLine()) != null) {
                    String linha = new Tratamento().remove(line).toLowerCase();

                    for (int i = 0; i<palavra.size();i++) {
                        countPp=0;
                        for (String element : linha.split(" ")) {
                            if(element.compareTo(p)==0)countPp++;
                        }

                    }
                }
            }
            if(countPp>0){
                countP++;

            }
        }
        System.out.println(countP);
        double numeroFicheiro = numFiles;
        if(countP!=0) {
            for (int k = 0; k < numFiles; k++) {
                for (int i = 0; i < palavra.size(); i++) {

                    matrizQ[i] = (matriz[k][i] * (1 + Math.log10(numeroFicheiro / countP)));

                    System.out.println(matrizQ[i]);

                }
            }
        }else{
                System.out.println("A palavra indicada não se encontra em nenhum documento!");
            }


        return matriz;
        }




    }