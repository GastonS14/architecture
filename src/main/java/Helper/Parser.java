package Helper;

import entity.Cliente;
import entity.Factura;
import entity.FacturaProducto;
import entity.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class Parser {

    private Parser (){}

    public static final ArrayList<Producto> createProductos (String path ) throws IOException {
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
    }

    public static final ArrayList<Cliente> createClientes (String path ) throws IOException {
        ArrayList<Cliente> retorno = new ArrayList();
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
        for (CSVRecord row : parser) {
            int idCliente = Integer.parseInt(row.get("idCliente"));
            Cliente c = new Cliente(idCliente, row.get("nombre"), row.get("email"));
            retorno.add( c );
        }
        return retorno;
    }

    public static final ArrayList<Factura> createFacturas (String path ) throws IOException {
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

    public static final ArrayList<FacturaProducto> createFacturasProductos (String path ) throws IOException {
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

}
