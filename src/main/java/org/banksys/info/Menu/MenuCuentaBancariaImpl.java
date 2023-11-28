package org.banksys.info.Menu;

import org.banksys.info.Clases.*;
import org.banksys.info.Servicios.*;

import java.util.List;
public class MenuCuentaBancariaImpl implements MenuCuentaBancaria{
    private BancoServicio bancoServicio;
    private String TITULO = "Menu Cuenta Bancaria";
    public static final String SIN_CUENTAS_TEMPLATE ="No hay cuentas cargadas";
    public MenuCuentaBancariaImpl(BancoServicio bancoServicio){
        this.bancoServicio = bancoServicio;
    }
    @Override
    public void operarConCuentaCliente(Cliente cliente) {
        ClienteServicio clienteServicio = new ClienteServicioImpl(bancoServicio.getBanco());
        List<CuentaBancaria> cuentas = clienteServicio.getCuentasCliente(cliente);
        if (cuentas.isEmpty()){
            System.out.println(SIN_CUENTAS_TEMPLATE);
        } else {
            System.out.println(MenuBancoImpl.DIVISION);
            System.out.println(MenuClienteImpl.SELECCION_TEMPLATE);
            Long nroCuenta = Long.parseLong(scannerinput.getScanner().nextLine());
            cuentas = cuentas.stream().filter(
                    cta -> cta.getNroCuenta().equals(nroCuenta)
            ).toList();
            if (cuentas.isEmpty()) {
                System.out.println(MenuClienteImpl.SIN_COINCIDENCIAS_TEMPLATE);
            } else {
                menuCuenta(cuentas.get(0));
            }
        }
    }
    @Override
    public void operarConCuenta() {
        List<CuentaBancaria> cuentas = bancoServicio.getListaCuentas();
        if (cuentas.isEmpty()){
            System.out.println(SIN_CUENTAS_TEMPLATE);
        } else {
            System.out.println(MenuBancoImpl.DIVISION);
            System.out.println(MenuClienteImpl.SELECCION_TEMPLATE);
            Long nroCuenta = Long.parseLong(scannerinput.getScanner().nextLine());
            cuentas = cuentas.stream().filter(
                    cta -> cta.getNroCuenta().equals(nroCuenta)
            ).toList();
            if (cuentas.isEmpty()) {
                System.out.println(MenuClienteImpl.SIN_COINCIDENCIAS_TEMPLATE);
            } else {
                menuCuenta(cuentas.get(0));
            }
        }
    }
    @Override
    public void menuCuenta(CuentaBancaria cuentaBancaria) {
        String opc;
        double monto;
        CuentaBancariaServicio cuentaBancariaServicio = new CuentaBancariaServicioImpl();
        do {
            System.out.println(TITULO);
            System.out.println(MenuBancoImpl.DIVISION);
            System.out.println("Ingrese una opcion:");
            System.out.println("1- Extraer.");
            System.out.println("2- Depositar.");
            System.out.println("3- Consultar saldo.");
            System.out.println("4- Calcular intereses.");
            if (cuentaBancaria instanceof CuentaCorriente) {
                System.out.println("5- Emitir un cheque.");
                System.out.println("6- Ver cheques emitidos.");
            }
            System.out.println("9- Eliminar cuenta.");
            System.out.println("0- Volver al menu anterior.");
            System.out.println(MenuBancoImpl.DIVISION);
            System.out.println("Seleccione una opcion:");
            opc = scannerinput.getScanner().nextLine();
            System.out.println(MenuBancoImpl.DIVISION);
            switch (opc) {
                case "1":
                    System.out.println("Ingrese el monto a extraer:");
                    monto = Double.parseDouble(scannerinput.getScanner().nextLine());
                    cuentaBancariaServicio.extraerDinero(monto, cuentaBancaria);
                    break;
                case "2":
                    System.out.println("Ingrese el monto a depositar:");
                    monto = Double.parseDouble(scannerinput.getScanner().nextLine());
                    cuentaBancariaServicio.depositarDinero(monto, cuentaBancaria);
                    break;
                case "3":
                    cuentaBancariaServicio.consultarSaldo(cuentaBancaria);
                    break;
                case "4":
                    cuentaBancariaServicio.devengarIntereses(cuentaBancaria);
                    break;
                case "5":
                    if(cuentaBancaria instanceof CuentaCorriente){
                        System.out.println("Ingrese monto de cheque a emitir:");
                        monto = Double.parseDouble(scannerinput.getScanner().nextLine());
                        cuentaBancariaServicio.emitirCheque(monto, cuentaBancaria);
                    }
                    break;
                case "6":
                    if(cuentaBancaria instanceof CuentaCorriente) {
                        cuentaBancariaServicio.listarCheques(cuentaBancaria);
                    }
                    break;
                case "9":
                    cuentaBancariaServicio.borrarCuentaBancaria(cuentaBancaria);
                    break;
            }
        } while (!opc.equals("0"));
    }
}

