package businessObject;

import dao.DAOfactura_producto;
import entity.FacturaProducto;
import factory.Factory;

import java.sql.SQLException;

public class BOfactura_producto {
    private DAOfactura_producto daoFactProd;

    public BOfactura_producto ( Factory f ) {
        this.daoFactProd = f.getDaoFacProd();
    }

    public void save (FacturaProducto fp ) throws SQLException {
        this.daoFactProd.insert( fp );
    }

}
