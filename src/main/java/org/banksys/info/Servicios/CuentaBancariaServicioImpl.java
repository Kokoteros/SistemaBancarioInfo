package org.banksys.info.Servicios;

import org.banksys.info.Clases.*;
import java.time.LocalDate;
import java.util.Set;
public class CuentaBancariaServicioImpl implements CuentaBancariaServicio {
    private Banco sucursal;
    private String MONTO_POSITIVO_TEMPLATE = "Debe ingresar un monto mayor a 0";
    private String SALDO_INSUFICIENTE_TEMPLATE = "Saldo insuficiente";
    public CuentaBancariaServicioImpl(Banco banco){
        this.sucursal = banco;
    }
    public CuentaBancariaServicioImpl(){}

    @Override
    public CuentaAhorro abrirCajaAhorro(Cliente cliente, Moneda moneda) {
        CuentaAhorro cuenta = new CuentaAhorro();
        cuenta.setSaldo(0D);
        cuenta.setMoneda(moneda);
        cuenta.setTitular(cliente);
        cuenta.setSucursal(this.sucursal);
        this.sucursal.getCuentas().add(cuenta);
        return cuenta;
    }

    @Override
    public CuentaCorriente abrirCuentaCte(Cliente cliente, Moneda moneda, Double sobregiro) {
        CuentaCorriente ctaCte = new CuentaCorriente();
        ctaCte.setSaldo(0D);
        ctaCte.setMoneda(moneda);
        ctaCte.setTitular(cliente);
        ctaCte.setLimiteSobregiro(sobregiro);
        ctaCte.setSucursal(this.sucursal);
        this.sucursal.getCuentas().add(ctaCte);
        return ctaCte;
    }

    @Override
    public void consultarSaldo(CuentaBancaria cuenta){
        System.out.println("Su saldo es de: $ " + cuenta.getSaldo() + " " + cuenta.getMoneda());
    }

    @Override
    public void extraerDinero(Double cant, CuentaBancaria cta) {
        if (cant > 0){
            if(cta.puedoExtraer(cant)){
                cta.setSaldo(cta.getSaldo() - cant);
            } else {
                System.out.println(SALDO_INSUFICIENTE_TEMPLATE);
            }
        } else {
            System.out.println(MONTO_POSITIVO_TEMPLATE);
        }
    }

    @Override
    public void depositarDinero(Double cant, CuentaBancaria cta) {
        if (cant > 0){
            cta.setSaldo(cta.getSaldo() + cant);
        } else {
            System.out.println(MONTO_POSITIVO_TEMPLATE);
        }
    }

    @Override
    public void emitirCheque(Double monto, CuentaBancaria cuenta) {
        if(!(cuenta instanceof CuentaCorriente cta)){
            System.out.println("Operacion no valida para cajas de ahorro");
        } else if(cuenta.puedoExtraer(monto) && monto > 0){
            Cheque cheque = new Cheque();
            cta.getCheques().add(cheque);
            cheque.setCuenta(cta);
            cheque.setMonto(monto);
            cheque.setFechaEmision(LocalDate.now());
            cta.setSaldo(cta.getSaldo() - monto);
        } else if(monto <= 0) {
            System.out.println(MONTO_POSITIVO_TEMPLATE);
        } else if(!cuenta.puedoExtraer(monto)){
            System.out.println(SALDO_INSUFICIENTE_TEMPLATE);
        }
    }
    @Override
    public void devengarIntereses(CuentaBancaria cta) {
        cta.calcularInteresDevengados();
    }

    @Override
    public void listarCheques(CuentaBancaria cta) {
        if(!(cta instanceof CuentaCorriente)){
            System.out.println("Operacion no valida para cajas de ahorro");
        } else {
            Set<Cheque> cheques = ((CuentaCorriente) cta).getCheques();
            if (cheques.isEmpty()){
                System.out.println("No hay cheques emitidos desde esta cuenta");
            } else {
                for (Cheque cheque : cheques){
                    System.out.println(cheque);
                }
            }

        }
    }

    @Override
    public void borrarCuentaBancaria(CuentaBancaria cuenta) {
        if (cuenta.getSaldo().equals(0D)) {
            cuenta.getTitular().getCuentas().remove(cuenta);
            cuenta.getSucursal().getCuentas().remove(cuenta);
            cuenta = null;
            System.out.println("Cuenta eliminada correctamente");
        } else {
            System.out.println("Para poder realizar esta operacion la cuenta debe estar en 0");
        }
    }
}
