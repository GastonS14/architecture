package main;

import Helper.Parser;
import businessObject.BOcliente;
import businessObject.BOfactura;
import businessObject.BOfactura_producto;
import businessObject.BOproducto;
import dataBase.EsquemaDB;
import entity.Cliente;
import entity.Factura;
import entity.FacturaProducto;
import entity.Producto;
import factory.Factory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Factory f = Factory.getInstance( "sql" );

        //EsquemaDB db = new EsquemaDB( f );

        BOcliente service_cliente = new BOcliente( f );
        BOfactura service_factura = new BOfactura( f );
        BOproducto service_prod = new BOproducto( f );
        BOfactura_producto serv_fp = new BOfactura_producto( f );


        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Factura> facturas = new ArrayList();
        ArrayList<Producto> prod = new ArrayList<>();
        ArrayList<FacturaProducto> fp = new ArrayList<>();

        //db.createTables();

        clientes.addAll( Parser.createClientes("C:\\Users\\Usuario\\Documents\\IntelliJProjects\\IntegradorPractico1\\src\\main\\java\\DATA\\clientes.csv") );
        for ( Cliente cli: clientes ) {
            service_cliente.save( cli );
        }

        facturas.addAll( Parser.createFacturas("C:\\Users\\Usuario\\Documents\\IntelliJProjects\\IntegradorPractico1\\src\\main\\java\\DATA\\facturas.csv"));
        for ( Factura factura : facturas ) {
            service_factura.save( factura );
        }

        prod.addAll( Parser.createProductos("C:\\Users\\Usuario\\Documents\\IntelliJProjects\\IntegradorPractico1\\src\\main\\java\\DATA\\productos.csv") );
        for ( Producto p : prod ) {
            service_prod.save( p );
        }

        fp.addAll( Parser.createFacturasProductos("C:\\Users\\Usuario\\Documents\\IntelliJProjects\\IntegradorPractico1\\src\\main\\java\\DATA\\facturas-productos.csv") );
        for ( FacturaProducto fp1: fp ) {
            serv_fp.save( fp1 );
        }

        System.out.println( service_prod.getMasVendido() );
        System.out.println( "\n");
        System.out.println( service_cliente.clientesPorFacturacion() );

    }


}
