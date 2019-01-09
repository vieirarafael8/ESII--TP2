public class Palavra {

    private int id;
    private String descricao;
    private int count=0;


    public Palavra(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Palavra(String descricao, int count) {
        this.descricao = descricao;
        this.count = count;
    }

    public Palavra(int id, String descricao, int count) {
        this.id = id;
        this.descricao = descricao;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Palavra{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", count=" + count +
                '}';
    }
}
