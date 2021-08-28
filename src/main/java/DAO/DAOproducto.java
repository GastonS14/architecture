package DAO;

import Entity.FacturaProducto;
import Entity.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static DataBase.Convert.*;

public class DAOproducto {

    public DAOproducto() {

    }

    public void insert ( Producto p ) throws SQLException {
        String query = " INSERT INTO Producto VALUES (?,?,?)";
        Connection con = DriverManager.getConnection( uri,user,password);
        con.setAutoCommit( false );
        PreparedStatement ps = con.prepareStatement( query );
        ps.setInt( 1,p.getIdProducto() );
        ps.setString(2, p.getNombre());
        ps.setInt( 3,p.getValor());
        ps.execute();
        con.commit();
        con.close();
    }

}
