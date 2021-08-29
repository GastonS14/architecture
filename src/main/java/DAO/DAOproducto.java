package DAO;

import DATA.DTOproducto;
import Entity.Producto;

import java.sql.*;

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

    public Producto getMasVendido ( ) throws SQLException{
        String query = "select p.idProducto AS idProducto, SUM(cantidad) * p.valor AS cantidad " +
                        "FROM Producto p JOIN Factura_Producto FP on p.idProducto = FP.idProducto " +
                        "GROUP BY p.idProducto " +
                        "ORDER BY cantidad DESC " +
                        "LIMIT 1 ";
        Connection con = DriverManager.getConnection( uri,user,password);
        con.setAutoCommit( false );
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        rs.next();
        DTOproducto resultado = new DTOproducto( rs.getInt("idProducto"), rs.getInt("cantidad"));
        Producto p = getProductoById( resultado.getIdProducto() );
        con.commit();
        con.close();
        return p;
    }

    public Producto getProductoById ( int id ) throws SQLException {
        String query = "SELECT * FROM Producto WHERE idProducto = ?";
        Connection con = DriverManager.getConnection( uri,user,password);
        con.setAutoCommit( false );
        PreparedStatement ps = con.prepareStatement( query );
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        con.commit();
        Producto p =  new Producto( rs.getInt("idProducto"),
                rs.getString("nombre"),
                rs.getInt("valor") );
        con.close();
        return p;
    }

}
