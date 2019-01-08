public class Matriz {

    private int id;
    private String palavra;
    private int count;

    public Matriz(int id, String palavra, int count) {
        this.id = id;
        this.palavra = palavra;
        this.count = count;
    }

    public Matriz(int id, String palavra) {
        this.id = id;
        this.palavra = palavra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
