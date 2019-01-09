import java.io.*;
import java.util.ArrayList;

public class Tratamento {

    char[] delete = {'\n','0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '?', '.', ';', ',', ':', '!', '-', '(', ')', '_', '/', '*', '[', ']'};

    public File[] listaFicheiros() {
        File file = new File("C:\\Users\\vieir\\Documents\\GitHub\\ESII--TP2\\EXP");

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        };

        File[] files = file.listFiles(textFilter);
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

    public ArrayList tratamentoPalavras() throws IOException {

        int numFiles = listaFicheiros().length;
        File[] f = listaFicheiros();
        ArrayList<Palavra> palavra = new ArrayList();
        int count = 0;

        for (int k = 0; k < numFiles; k++) {
            try (LineNumberReader r = new LineNumberReader(new FileReader(f[k]))) {
                String line;
                while ((line = r.readLine()) != null) {
                    String linha = new Tratamento().remove(line).toLowerCase();
                    for (String element : linha.split(" ")) {
                        if (!element.equals("")) {
                            if (palavra.size() == 0) {
                                Palavra palavra3 = new Palavra(element, 1);
                                palavra.add(palavra3);
                            } else {
                                for (int i = 0; i < palavra.size(); i++) {
                                    if (element.equals(palavra.get(i).getDescricao())) {
                                        palavra.get(i).setCount(palavra.get(i).getCount() + 1);
                                        count++;
                                    }

                            }
                            if (count == 0) {
                                Palavra palavra1 = new Palavra(element, 1);
                                palavra.add(palavra1);
                            } else {
                                count = 0;
                            }


                        }
                    }
                    }
                }
            }

        }
        return palavra;
    }

    public int[][] matriz(ArrayList palavra)throws IOException{
        int numFiles = listaFicheiros().length;
        int numPalavras = tratamentoPalavras().size();
        File[] f = listaFicheiros();
        String[] aux;
        ArrayList<Palavra> p = tratamentoPalavras();

        int[][] m=new int[numFiles][numPalavras];

        for(int i=0; i<numFiles; i++){
            System.out.println("\n");
          for(int j=0; j<numPalavras; j++){
                m[i][j]= p.get(j).getCount();
              System.out.println(p.get(j).getDescricao());
                System.out.println("contador:"+m[i][j]+"\t");
          }
        }

        return m;
    }

}