package DATA;

public class DTOcliente {
    private int id;
    private int valor;

    public DTOcliente(int id, int valor) {
        this.id = id;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public int getValor() {
        return valor;
    }

}
