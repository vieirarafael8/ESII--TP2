

import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.util.*;


public class Tratamento {


    char[] delete = {'\n', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '?', '.', ';', ',', ':', '!', '-', '(', ')', '_', '/', '*', '[', ']'};
    String[] palavras;
    File file = new File("C:\\Users\\EU\\Documents\\GitHub\\ESII--TP2\\EXP");
    double[] docs = new double[listaFicheiros(file).length];
    LinkedHashSet<String> palavra = new LinkedHashSet<>();
    double[] ordenado = new double[listaFicheiros(file).length];
    public static boolean ASC = true;
    public static boolean DESC = false;

    /*
    Metodo Para Listar Ficheiros
     */

    public File[] listaFicheiros(File file) {

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");

            }
        };

        File[] files = file.listFiles(textFilter);

        return files;
    }

    /*
    Metodo Para Encontrar Caracteres Especiais
     */

    public char isFound(char src) {


            for (int i = 0; i < delete.length; i++) {
                if (src == delete[i]) {
                    return '\0';
                }
            }
            return src;
    }

    /*
    Metodo Para Remover Caracteres
     */
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


    /*
    Metodo Para Criar Matriz Principal
     */
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

    /*
    Metodo Para Imprimir a Matriz
     */

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

    /*
        Metodo Para Contar Palavras Do Array de Palavras Para a MatrizQ
         */
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

    /*
        Metodo Para Procurar Palavra Na MatrizM E Construir MatrizQ
         */
    public double[] matrizQ(int numFiles, File[] f, double[][] matrizM)  throws IOException {

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

    /*
    Metodo Para Verificar o Grau de Similariedade Dos Docuumentos
     */
    public double[] grauSimilariedade(int numFiles, double[][] matrizM, double[] matrizQ) throws IOException {

        if (numFiles > 0 && matrizM != null && matrizQ != null) {

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

                System.out.println("Grau de similariedade com o documento " + listaFicheiros(file)[k].getName() + " = " + grauSim[k]);

            }
            return grauSim;
        } else {
            return null;
        }
    }
    /*
        Metodo Para Ordenar Palavras ListaMap
         */
    private static Map<String, Double> sortByComparator(Map<String, Double> unsortMap, final boolean order) {

        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    /*
         Metodo Para Ordenar A MatrizQ
          */
    public Map<String, Double> ordenado(int numFiles) throws IOException {

        if (numFiles > 0) {

            ordenado = grauSimilariedade(numFiles, tratamentoPalavras(numFiles), matrizQ(numFiles, listaFicheiros(file), tratamentoPalavras(numFiles)));
            System.out.println();
            System.out.println("Ordenado:\n");

            Map<String, Double> hash = new HashMap<>();

            for (int i = numFiles - 1; i >= 0; i--) {
                hash.put(listaFicheiros(file)[i].getName(), ordenado[i]);
            }
            Map<String, Double> sortedMapDesc = sortByComparator(hash, DESC);
            for (Map.Entry<String, Double> entry : sortedMapDesc.entrySet()) {
                System.out.println("O Documento " + entry.getKey() + " tem um grau de Similariedade de " + entry.getValue());
            }

            return sortedMapDesc;
        } else {
            return null;
        }
    }

    /*
    Metodo Para Listar Documentos Com Grau de Similariedade Superior Ao Dado Por Input
     */

    public String[] grauAcima(int numFiles) {

        if (numFiles > 0) {

            String[] ficheiros = new String[numFiles];

            Scanner input = new Scanner(System.in);

            double value;
            System.out.println("\n \nIntroduza o grau minimo de similariedade a  encontrar: \n");
            value = Double.parseDouble(input.nextLine());

            Map<String, Double> hash = new HashMap<>();

            for (int i = numFiles - 1; i >= 0; i--) {
                hash.put(listaFicheiros(file)[i].getName(), ordenado[i]);
            }

            Map<String, Double> sortedMapDesc = sortByComparator(hash, DESC);
            System.out.println("Ficheiros com grau superior a " + value + "\n");



            int i = 0;
            for (Map.Entry<String, Double> entry : sortedMapDesc.entrySet()) {
                if (entry.getValue() > value) {
                    ficheiros[i] = entry.getKey();
                    System.out.println("O Documento " + entry.getKey() + " tem um grau de Similariedade de " + entry.getValue());
                    i++;
                }
            }

            return ficheiros;
        } else {
            return null;
        }
    }

    /*
    Metodo Para Introdução Do Máximo de Ficheiros A Apresentar
     */

    public String[] maximoFicheiros(int numFiles, double[] ordenado) {

        if (numFiles > 0 &&ordenado!=null) {

            String[] ficheiros = new String[numFiles];

            Scanner input = new Scanner(System.in);

            System.out.println("\n \nIntroduza o maximo de ficheiros a pesquisar similariedade: \n");
            int p = input.nextInt();

            Map<String, Double> hash = new HashMap<>();

            for (int i = numFiles - 1; i >= 0; i--) {
                hash.put(listaFicheiros(file)[i].getName(), ordenado[i]);
            }
            Map<String, Double> c = sortByComparator(hash, DESC);

            int i = 0;
            for (Map.Entry<String, Double> entry : c.entrySet()) {
                if (i < p) {
                    ficheiros[i] = entry.getKey();
                    System.out.println("O Documento " + entry.getKey() + " tem um grau de Similariedade de " + entry.getValue());
                    i++;
                }
            }


            return ficheiros;
        } else {
            return null;
        }
    }

}