package DAO;

import Entity.Factura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static DataBase.Convert.uri;
import static DataBase.Convert.user;
import static DataBase.Convert.password;

public class DAOfactura {

    public DAOfactura (){}

    public void insert ( Factura f ) throws SQLException {
        String query = " INSERT INTO Factura VALUES (?,?)";
        Connection con = DriverManager.getConnection( uri,user,password);
        con.setAutoCommit( false );
        PreparedStatement ps = con.prepareStatement( query );
        ps.setInt( 1,f.getIdFactura() );
        ps.setInt( 2,f.getIdCliente() );
        ps.execute();
        con.commit();
        con.close();
    }
}
