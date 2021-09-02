package businessObject;

import dao.DAOproducto;
import entity.Producto;
import factory.Factory;

import java.sql.SQLException;

public class BOproducto {
    private DAOproducto daOproductoMySql;

    public BOproducto ( Factory f ) {
        this.daOproductoMySql = f.getDaoProducto();
    }

    public void save ( Producto p ) throws SQLException {
        this.daOproductoMySql.insert( p );
    }

    public Producto getMasVendido ( ) throws SQLException {
        return this.daOproductoMySql.getMasVendido();
    }

}
