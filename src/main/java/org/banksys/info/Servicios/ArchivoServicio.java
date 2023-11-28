package org.banksys.info.Servicios;

import org.banksys.info.Clases.CuentaBancaria;
import java.util.List;

public interface ArchivoServicio {
    void ExportarCuentasACsv(List<CuentaBancaria> cuentas, String ruta);
}
