
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;

public class X {

    char[] delete = {'\n','0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '?', '.', ';', ',', ':', '!', '-', '(', ')', '_', '/', '*', '[', ']'};


    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\vieir\\Documents\\GitHub\\ESII--TP2\\EXP\\file.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            //System.out.println(st);

           // System.out.println(new X().remove(st));

        }


        int count = 0;
        ArrayList<Palavra> palavra = new ArrayList();

        try (LineNumberReader r = new LineNumberReader(new FileReader(file))) {
            String line;

            while ((line = r.readLine()) != null) {
                String linha = new X().remove(line).toLowerCase();

                for (String element : linha.split(" ")) {
                   // System.out.println(element);
                if(!element.equals("")){
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
                        if(count==0) {
                            Palavra palavra1 = new Palavra(element, 1);
                            palavra.add(palavra1);
                        }else{
                            count=0;
                        }

                    }

                }}
            }
        }
        for (int i = 0; i < palavra.size(); i++) {
            System.out.println(palavra.get(i).getDescricao());
            System.out.println(palavra.get(i).getCount());
        }

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


}