package DATA;

import DAO.DAOfactura;
import DAO.DAOfactura_producto;
import DAO.DAOproducto;
import DataBase.Convert;
import Entity.Factura;
import Entity.FacturaProducto;
import Entity.Producto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Convert c = new Convert();
        DAOfactura daoF = new DAOfactura();
        DAOproducto daoP = new DAOproducto();
        DAOfactura_producto daoFP = new DAOfactura_producto();

        //Convert.createTables();
        /*
        ArrayList<Factura> facturas = new ArrayList();
        ArrayList<Producto> prod = new ArrayList<>();
        ArrayList<FacturaProducto> fp = new ArrayList<>();
        /*
        facturas.addAll( c.createFacturas("C:\\Users\\Usuario\\Documents\\IntelliJProjects\\IntegradorPractico1\\src\\main\\java\\DATA\\facturas.csv"));
        for ( Factura f: facturas ) {
            daoF.insert(f);
        }

        prod.addAll(c.createProductos("C:\\Users\\Usuario\\Documents\\IntelliJProjects\\IntegradorPractico1\\src\\main\\java\\DATA\\productos.csv") );
        for ( Producto p : prod ) {
            daoP.insert( p );
        }

        fp.addAll(c.createFacturasProductos("C:\\Users\\Usuario\\Documents\\IntelliJProjects\\IntegradorPractico1\\src\\main\\java\\DATA\\facturas-productos.csv") );
        for ( FacturaProducto fp1: fp ) {
            daoFP.insert( fp1 );
        }

         */
    }

}
