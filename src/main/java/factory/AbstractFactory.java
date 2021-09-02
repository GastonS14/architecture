package factory;

import dao.*;

/**
 * Ddefine los metodos para la creacion de los DAOs.
 */

interface AbstractFactory {

    DAOcliente getDaoCliente();
    DAOproducto getDaoProducto();
    DAOfactura getDaoFactura();
    DAOfactura_producto getDaoFacProd();

}
