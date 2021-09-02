package dao;

import entity.FacturaProducto;
import factory.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOfactura_productoMySql implements DAOfactura_producto{
    private Conexion conexion;

    public DAOfactura_productoMySql(Conexion con ){
        this.conexion = con;
    }

    public void insert ( FacturaProducto fp ) throws SQLException {
        String query = " INSERT INTO Factura_Producto VALUES (?,?,?)";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt( 1,fp.getIdFactura() );
        ps.setInt( 2,fp.getIdProducto() );
        ps.setInt( 3,fp.getCantidad());
        ps.execute();
        this.conexion.commit();
        this.conexion.closeConnection();
    }


}
