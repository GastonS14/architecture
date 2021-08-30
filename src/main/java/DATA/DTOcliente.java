package DATA;

public class DTOcliente {
    private int id;
    private String nombre;
    private String email;
    private int valorFacturacion;

    public DTOcliente(int id,String nombre, String email, int valor) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.valorFacturacion = valor;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getValorFacturacion() {
        return valorFacturacion;
    }

    @Override
    public String toString() {
        return "DTOcliente: " +
                "id=" + id ;
    }
}
