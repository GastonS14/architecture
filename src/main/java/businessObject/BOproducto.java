package businessObject;

import dao.DAOproducto;
import entity.Producto;
import factory.Factory;

import java.sql.SQLException;

public class BOproducto {
    private DAOproducto daOproducto;

    public BOproducto ( Factory f ) {
        this.daOproducto = f.getDaoProducto();
    }

    public void save ( Producto p ) throws SQLException {
        this.daOproducto.insert( p );
    }

    public Producto getProductById ( int id ) throws SQLException {
        return this.daOproducto.getProductoById( id );
    }

    public Producto getMasVendido ( ) throws SQLException {
        return this.daOproducto.getMasVendido();
    }

}
