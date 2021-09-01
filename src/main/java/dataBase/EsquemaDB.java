package dataBase;

import factory.Conexion;
import factory.Factory;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class EsquemaDB {

    private Conexion con;

    public EsquemaDB(Factory f ) {
        con = f.getConexion();
    }

    public void createTables () throws SQLException {
        String tCliente = "CREATE TABLE Cliente (" +
                            "idCliente INT PRIMARY KEY," +
                              "nombre varchar(500)," +
                                "email varchar(150) ) ";
        String tFactura = " CREATE TABLE Factura (" +
                "idFactura INT PRIMARY KEY," +
                "idCliente INT, " +
                "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) )";
        String tProducto = "CREATE TABLE Producto ("+
                "idProducto INT PRIMARY KEY,"+
                "nombre varchar(45),"+
                "valor INT )";
        String tFac_Prod = " CREATE TABLE Factura_Producto (" +
                "idFactura INT," +
                "idProducto INT," +
                "cantidad INT," +
                "PRIMARY KEY (idProducto, idFactura),"+
                "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto),"+
                "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura) )";

        PreparedStatement ps0 = con.getConnection().prepareStatement( tCliente );
        ps0.execute();
        //con.commit();
        PreparedStatement ps = con.getConnection().prepareStatement( tFactura );
        ps.execute();
        //con.commit();
        PreparedStatement ps1 = con.getConnection().prepareStatement( tProducto );
        ps1.execute();
        //con.commit();
        PreparedStatement ps2 = con.getConnection().prepareStatement( tFac_Prod );
        ps2.execute();
        con.commit();
        con.closeConnection();
    }

}
