package dao;

import dto.DTOproducto;
import entity.Producto;
import factory.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAOproducto {
    private Conexion conexion;

    public DAOproducto( Conexion con ) {
        this.conexion = con;
    }

    public void insert ( Producto p ) throws SQLException {
        String query = " INSERT INTO Producto VALUES (?,?,?)";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt( 1,p.getIdProducto() );
        ps.setString(2, p.getNombre());
        ps.setInt( 3,p.getValor());
        ps.execute();
        this.conexion.commit();
        this.conexion.closeConnection();
    }

    public Producto getMasVendido ( ) throws SQLException{
        String query = "select p.idProducto AS idProducto, SUM(cantidad) * p.valor AS cantidad " +
                        "FROM Producto p JOIN Factura_Producto FP on p.idProducto = FP.idProducto " +
                        "GROUP BY p.idProducto " +
                        "ORDER BY cantidad DESC " +
                        "LIMIT 1 ";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ResultSet rs = ps.executeQuery();
        rs.next();
        this.conexion.commit();
        DTOproducto resultado = new DTOproducto( rs.getInt("idProducto"), rs.getInt("cantidad"));
        Producto p = getProductoById( resultado.getIdProducto() );
        this.conexion.closeConnection();  //despues del commit connection closed xD
        return p;
    }

    public Producto getProductoById ( int id ) throws SQLException {
        String query = "SELECT * FROM Producto WHERE idProducto = ?";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Producto p =  new Producto( rs.getInt("idProducto"),
                rs.getString("nombre"),
                rs.getInt("valor") );
        this.conexion.commit();
        this.conexion.closeConnection();
        return p;
    }

}
