package dao;

import dto.DTOcliente;
import entity.Cliente;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOcliente {

    void insert ( Cliente c ) throws SQLException;
    ArrayList<DTOcliente> clientesPorFacturacion() throws SQLException;

}
