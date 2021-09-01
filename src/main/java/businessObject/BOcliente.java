package businessObject;

import dao.DAOcliente;
import dto.DTOcliente;
import entity.Cliente;
import factory.Factory;

import java.sql.SQLException;
import java.util.ArrayList;

public class BOcliente {
    private DAOcliente daOcliente;

    public BOcliente ( Factory f ) {
        this.daOcliente = f.getDaoCliente();
    }

    public ArrayList<DTOcliente> clientesPorFacturacion () throws SQLException {
        return this.daOcliente.ejercicio4();
    }

    public void save (Cliente c ) throws SQLException {
        this.daOcliente.insert( c );
    }

}
