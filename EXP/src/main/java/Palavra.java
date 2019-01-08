public class Palavra {
    private String descricao;
    private int count=0;


    public Palavra(String descricao, int count) {
        this.descricao = descricao;
        this.count=count;
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
}
