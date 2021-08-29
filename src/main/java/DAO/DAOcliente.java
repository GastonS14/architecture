package DAO;

import DATA.DTOcliente;
import Entity.Cliente;
import Entity.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import static DataBase.Convert.*;

public class DAOcliente {

    public DAOcliente() { }

    public void insert ( Cliente c ) throws SQLException {
        String query = " INSERT INTO Cliente VALUES (?,?,?)";
        Connection con = DriverManager.getConnection( uri,user,password);
        con.setAutoCommit( false );
        PreparedStatement ps = con.prepareStatement( query );
        ps.setInt(1, c.getIdCliente());
        ps.setString( 2,c.getNombre() );
        ps.setString( 3,c.getEmail() );
        ps.execute();
        con.commit();
        con.close();
    }

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
/*
    private ArrayList<Cliente> getClientes ( ArrayList<Integer> ids ) throws SQLException {
        String aux = ids.toString();
        ArrayList<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente WHERE idCliente IN ( ? ) ";
        Connection con = DriverManager.getConnection(uri,user,password);
        con.setAutoCommit( false );
        PreparedStatement ps = con.prepareStatement( query );
        ps.setString( 1, aux );
        ResultSet rs = ps.executeQuery();
        while ( rs.next() ) {
        clientes.add( new  Cliente ( rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("email") ) );
        }
        con.commit();
        con.close();
        return clientes;
    }

*/

}
