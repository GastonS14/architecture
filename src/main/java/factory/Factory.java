package factory;

import dao.*;

import java.sql.SQLException;

/**
 * Clase encargada de suministrar la creacion de DAOs y manejar la conexion a la DB.
 * Es singleton, es decir, habra una unica instancia de esta clase en el programa.
 */

public class Factory implements AbstractFactory {
    private String tecnologia;
    private String driver;
    private String uri;
    private Conexion con;
    private static Factory f;

    private Factory ( String tecnologia ) {
        this.tecnologia = tecnologia;
    }

    /**
     * Dependiendo de la DB a utilizar, se setea el driver y la uri de la conexion.
     * @throws SQLException
     */
    private void setConexion ( ) throws SQLException {
        if ( this.tecnologia.equalsIgnoreCase( "sql" ) ) {
            this.driver = "com.mysql.cj.jdbc.Driver";
            this.uri = "jdbc:mysql://localhost:3306/example_DB";
        } else {
            this.driver = "org.apache.derby.jdbc.EmbeddedDriver";
            this.uri = "jdbc:derby:MyDerbyDB;create=true";
        }
        con = Conexion.getInstance( this.driver, this.uri );
    }

    /**
     * Solo a travez de este metodo se obtiene una instancia de factory
     * @param tecnologia
     * @return
     * @throws SQLException
     */
    public static Factory getInstance ( String tecnologia ) throws SQLException {
        if ( f == null ) {
            f = new Factory(tecnologia);
            f.setConexion();
        }
        return f;
    }

    public Conexion getConexion () {
        return this.con;
    }

    /** Creacion de los DAO segun la tecnologia.
     *  En este caso solo se usara MySql, pero de usarse otra DB, se debera crear
     *  una clase abstracta para cada DAO y sus clases concretas por cada DB a usar.
     * @return
     */

    public DAOcliente getDaoCliente ( ) {
        return new DAOclienteMySql( this.con );
    }

    public DAOfactura getDaoFactura () {
        return new DAOfacturaMySql( this.con );
    }

    public DAOproducto getDaoProducto () {
        return new DAOproductoMySql( this.con );
    }

    public DAOfactura_producto getDaoFacProd () {
        return new DAOfactura_productoMySql( this.con );
    }

}
