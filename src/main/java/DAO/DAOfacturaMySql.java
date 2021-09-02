package dao;

import entity.Factura;
import factory.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOfacturaMySql implements DAOfactura {
    private Conexion conexion;

    public DAOfacturaMySql(Conexion con ){
        this.conexion = con;
    }

    public void insert ( Factura f ) throws SQLException {
        String query = " INSERT INTO Factura VALUES (?,?)";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt( 1,f.getIdFactura() );
        ps.setInt( 2,f.getIdCliente() );
        ps.execute();
        this.conexion.commit();
        this.conexion.closeConnection();
    }

}
