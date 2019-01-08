import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Matriz {


    private Documento doc;
    private Palavra palavra;
    private int countMatriz;


    public Matriz(Documento doc, Palavra palavra, int countMatriz) {

        this.doc = doc;
        this.palavra = palavra;
        this.countMatriz = countMatriz;
    }

    public Matriz(Documento doc, Palavra palavra) {
        this.doc = doc;
        this.palavra = palavra;
    }

    public Matriz() {

    }

    public Documento getDoc() {
        return doc;
    }

    public void setDoc(Documento doc) {
        this.doc = doc;
    }

    public Palavra getPalavra() {
        return palavra;
    }

    public void setPalavra(Palavra palavra) {
        this.palavra = palavra;
    }

    public int getCountMatriz() {
        return countMatriz;
    }

    public void setCountMatriz(int countMatriz) {
        this.countMatriz = countMatriz;
    }

    @Override
    public String toString() {
        return "Matriz{" +
                "doc=" + doc +
                ", palavra=" + palavra +
                ", countMatriz=" + countMatriz +
                '}';
    }
}
