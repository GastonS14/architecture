package businessObject;

import dao.DAOcliente;
import dto.DTOcliente;
import entity.Cliente;
import factory.Factory;

import java.sql.SQLException;
import java.util.ArrayList;

public class BOcliente {
    private DAOcliente daOclienteMySql;

    public BOcliente ( Factory f ) {
        this.daOclienteMySql = f.getDaoCliente();
    }

    public ArrayList<DTOcliente> clientesPorFacturacion () throws SQLException {
        return this.daOclienteMySql.clientesPorFacturacion();
    }

    public void save (Cliente c ) throws SQLException {
        this.daOclienteMySql.insert( c );
    }

}
