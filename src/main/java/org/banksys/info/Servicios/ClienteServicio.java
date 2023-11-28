package org.banksys.info.Servicios;

import org.banksys.info.Clases.*;
import java.util.List;

public interface ClienteServicio {

    void generarCliente();

    void abrirCuenta(Cliente cliente);

    void abrirCuentaCorriente(Cliente cliente);

    List<CuentaBancaria> getCuentasCliente(Cliente cliente);


}
