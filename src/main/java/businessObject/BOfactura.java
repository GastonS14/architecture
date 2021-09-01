package businessObject;

import dao.DAOfactura;
import entity.Factura;
import factory.Factory;

import java.sql.SQLException;

public class BOfactura {
    private DAOfactura daoFactura;

    public BOfactura (Factory f ) {
        this.daoFactura = f.getDaoFactura();
    }

    public void save ( Factura f ) throws SQLException {
        this.daoFactura.insert( f );
    }

}
