package org.banksys.info.Servicios;

import org.banksys.info.Clases.*;
import java.util.List;

public interface BancoServicio {

    void getListaClientes();
    List<CuentaBancaria> getListaCuentas();
    List<CuentaBancaria> getListaCuentasOrdenada();
    List<Cliente> getClienteByDni(int dni);
    void registrarCliente(Cliente cliente);
    void verCuentas();
    void exportarCuentasACsv();
    Banco getBanco();

}