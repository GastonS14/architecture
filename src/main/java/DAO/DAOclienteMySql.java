package dao;

import dto.DTOcliente;
import entity.Cliente;
import factory.Conexion;
import java.sql.*;
import java.util.ArrayList;


public class DAOclienteMySql implements DAOcliente {
    private Conexion conexion;

    public DAOclienteMySql(Conexion conexion ) {
        this.conexion = conexion;
    }

    public void insert ( Cliente c ) throws SQLException {
        String query = " INSERT INTO Cliente VALUES (?,?,?)";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ps.setInt(1, c.getIdCliente() );
        ps.setString( 2, c.getNombre() );
        ps.setString( 3, c.getEmail() );
        ps.execute();
        this.conexion.commit();
        this.conexion.closeConnection();
    }
/*
    public ArrayList<Cliente> getClienteInOrder ( ) throws SQLException {
        String query = "select idCliente, SUM(valoresPorFacturas) as valoresTotales\n" +
                "from ( select idFactura, SUM(valorPorFactura) as valoresPorFacturas\n" +
                "        from (\n" +
                "            select idFactura,fp.idProducto, cantidad * p.valor as valorPorFactura\n" +
                "            from Factura_Producto fp join Producto p on fp.idProducto = p.idProducto\n" +
                "            group by fp.idProducto, idFactura ) AS tableOne\n" +
                "    group by idFactura ) AS tableTwo JOIN Factura f ON f.idFactura = tableTwo.idFactura\n" +
                "group by idCliente\n" +
                "order by valoresTotales DESC ";
        Connection con = DriverManager.getConnection(uri,user,password);
        con.setAutoCommit( false );
        PreparedStatement ps = con.prepareStatement( query );
        ResultSet rs = ps.executeQuery();
        con.commit();
        ArrayList<DTOcliente> dtosClientes = new ArrayList<>();
        while ( rs.next() ) {
            dtosClientes.add( new DTOcliente(rs.getInt("idCliente"),
                    rs.getInt("valoresTotales") ) );
        }
        ArrayList<Integer> ids = new ArrayList<>();
        for ( DTOcliente dtoC : dtosClientes ) {
            ids.add( dtoC.getId());
        }
        ArrayList<Cliente> clientes = new ArrayList<>();

        for ( Integer i : ids ) {
           clientes.add( getClienteById( i ) );
        }
        con.close();
        return clientes;
    }

    private Cliente getClienteById ( int id ) throws SQLException {
        String query = "SELECT * FROM Cliente WHERE idCliente = (?)";
        Connection con = DriverManager.getConnection(uri,user,password);
        con.setAutoCommit( false );
        PreparedStatement ps = con.prepareStatement( query );
        ps.setInt( 1,id );
        ResultSet rs = ps.executeQuery();
        rs.next();
        Cliente c = new Cliente ( rs.getInt("idCliente"),
                                    rs.getString("nombre"),
                                    rs.getString("email"));
        con.commit();
        con.close();
        return c;
    }
*/

    public ArrayList<DTOcliente> clientesPorFacturacion( ) throws SQLException {
        ArrayList<DTOcliente> resultado = new ArrayList<>();
        String query =  "select c.idCliente, c.nombre, c.email, result.valoresTotales\n" +
                        "from Cliente c JOIN\n" +
    "                    ( select idCliente, SUM(valoresPorFacturas) as valoresTotales\n" +
    "                       from ( select idFactura, SUM(valorPorFactura) as valoresPorFacturas\n" +
    "                               from (\n" +
        "                                select idFactura,fp.idProducto, cantidad * p.valor as valorPorFactura\n" +
            "                                from Factura_Producto fp join Producto p on fp.idProducto = p.idProducto\n" +
            "                                group by fp.idProducto, idFactura ) AS tableOne\n" +
    "                               group by idFactura ) AS tableTwo JOIN Factura f ON f.idFactura = tableTwo.idFactura\n" +
    "                    group by idCliente\n" +
    "                    order by valoresTotales DESC ) AS result ON c.idCliente = result.idCliente";
        PreparedStatement ps = this.conexion.getConnection().prepareStatement( query );
        ResultSet rs = ps.executeQuery();
        while ( rs.next() ) {
        resultado.add( new  DTOcliente ( rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getInt( "valoresTotales")) );
        }
        this.conexion.commit();
        this.conexion.closeConnection();
        return resultado;
    }

}
