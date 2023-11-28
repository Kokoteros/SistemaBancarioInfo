package org.banksys.info.Servicios;

import org.banksys.info.Clases.*;
public interface CuentaBancariaServicio {
    CuentaAhorro abrirCajaAhorro(Cliente cliente, Moneda moneda);
    CuentaCorriente abrirCuentaCte(Cliente cliente, Moneda moneda, Double descubierto);
    void consultarSaldo(CuentaBancaria cuenta);
    void extraerDinero(Double cant, CuentaBancaria cuenta);
    void depositarDinero(Double cant, CuentaBancaria cuenta);
    void emitirCheque(Double cant, CuentaBancaria cuenta);
    void devengarIntereses(CuentaBancaria cuenta);
    void listarCheques(CuentaBancaria cta);
    void borrarCuentaBancaria(CuentaBancaria cuenta);
}

