package DataBase;

import Entity.Cliente;
import Entity.Factura;
import Entity.FacturaProducto;
import Entity.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Convert {
    public static final String uri = "jdbc:mysql://localhost:3306/example_DB";
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "password";

    public Convert() {

    }

    public ArrayList<Producto> createProductos ( String path ) {
        try {
            ArrayList<Producto> retorno = new ArrayList();
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
            for (CSVRecord row : parser) {
                int idProducto = Integer.parseInt(row.get("idProducto"));
                int valor = Integer.parseInt(row.get("valor"));
                String aux = row.get("nombre");
                Producto p = new Producto(idProducto, aux, valor);
                retorno.add(p);
            }
            return retorno;
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Cliente> createClientes ( String path ) throws IOException {
        ArrayList<Cliente> retorno = new ArrayList();
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            int idProducto = Integer.parseInt(row.get("idProducto"));
            Cliente c = new Cliente(idProducto, row.get("nombre"), row.get("email"));
            retorno.add( c );
        }
        return retorno;
    }

    public ArrayList<Factura> createFacturas ( String path ) throws IOException {
        ArrayList<Factura> retorno = new ArrayList();
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader( path ) );
        for( CSVRecord row:parser ) {
            int idFactura = Integer.parseInt( row.get("idFactura") );
            int idCliente = Integer.parseInt( row.get("idCliente") );
            Factura p = new Factura( idFactura, idCliente );
            retorno.add( p );
        }
        return retorno;
    }

    public ArrayList<FacturaProducto> createFacturasProductos ( String path ) throws IOException {
        ArrayList<FacturaProducto> retorno = new ArrayList();
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader( path ) );
        for( CSVRecord row:parser ) {
            int idFactura = Integer.parseInt( row.get("idFactura") );
            int idProducto = Integer.parseInt( row.get("idProducto") );
            int cantidad = Integer.parseInt( row.get("cantidad") );
            FacturaProducto fp = new FacturaProducto( idFactura, idProducto, cantidad );
            retorno.add( fp );
        }
        return retorno;
    }

    public static void createTables () throws SQLException {
        String query = " CREATE TABLE Factura (" +
                "idFactura INT PRIMARY KEY," +
                "idCliente INT )";
        String query2 = "CREATE TABLE Producto ("+
                "idProducto INT PRIMARY KEY,"+
                "nombre varchar(45),"+
                "valor INT )";
        String query1 = " CREATE TABLE Factura_Producto (" +
                "idFactura INT," +
                "idProducto INT," +
                "cantidad INT," +
                "PRIMARY KEY (idProducto, idFactura),"+
                "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto),"+
                "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura) )";
        Connection con = DriverManager.getConnection(uri,user,password);
        con.setAutoCommit( false );
        PreparedStatement ps = con.prepareStatement( query );
        ps.execute();
        con.commit();
        PreparedStatement ps1 = con.prepareStatement( query2 );
        ps1.execute();
        con.commit();
        PreparedStatement ps2 = con.prepareStatement( query1 );
        ps2.execute();
        con.commit();
        con.close();
    }


}
