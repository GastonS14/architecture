package factory;

import dao.DAOcliente;
import dao.DAOfactura;
import dao.DAOfactura_producto;
import dao.DAOproducto;

/**
 * Ddefine los metodos para la creacion de los DAOs.
 */

interface AbstractFactory {

    DAOcliente getDaoCliente();
    DAOproducto getDaoProducto();
    DAOfactura getDaoFactura();
    DAOfactura_producto getDaoFacProd();

}
