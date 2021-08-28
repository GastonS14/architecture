package DAO;

import Entity.Factura;
import Entity.FacturaProducto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static DataBase.Convert.*;

public class DAOfactura_producto {

    public DAOfactura_producto(){}

    public void insert ( FacturaProducto fp ) throws SQLException {
        String query = " INSERT INTO Factura_Producto VALUES (?,?,?)";
        Connection con = DriverManager.getConnection( uri,user,password);
        con.setAutoCommit( false );
        PreparedStatement ps = con.prepareStatement( query );
        ps.setInt( 1,fp.getIdFactura() );
        ps.setInt( 2,fp.getIdProducto() );
        ps.setInt( 3,fp.getCantidad());
        ps.execute();
        con.commit();
        con.close();
    }


}
