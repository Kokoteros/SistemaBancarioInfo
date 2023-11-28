package org.banksys.info.Clases;

import java.time.LocalDate;
import java.time.Period;

public class CuentaAhorro extends CuentaBancaria {

    public CuentaAhorro() {
        this.nroCuenta = getNroCuentaAutoIncremental();
        this.ultimaActualizacionIntereses = LocalDate.now();
    }

}
