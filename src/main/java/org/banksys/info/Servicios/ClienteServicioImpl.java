package org.banksys.info.Servicios;

import org.banksys.info.Clases.*;
import org.banksys.info.Menu.*;

import java.util.List;
import java.util.Scanner;

public class ClienteServicioImpl implements ClienteServicio {

    private Scanner scanner;
    public static String INGRESE_MONEDA_TEMPLATE = "Ingrese moneda a operar (PESO - DOLAR):";
    public static String MONEDA_NO_VALIDA_TEMPLATE = "Moneda no valida, intente nuevamente";
    private Banco sucursal;
    private BancoServicio bancoServicio;
    public ClienteServicioImpl(Banco banco){
        this.scanner = scannerinput.getScanner();
        this.sucursal = banco;
        this.bancoServicio = new BancoServicioImpl(banco);
    }
    @Override
    public void generarCliente() {
        try {
            System.out.println("Registro de Cliente");
            System.out.println(MenuBancoImpl.DIVISION);
            System.out.println("Ingrese Nombre:");
            String nombre = this.scanner.nextLine().toUpperCase();
            System.out.println("Ingrese Apellido");
            String apellido = this.scanner.nextLine().toUpperCase();
            System.out.println("Ingrese domicilio:");
            String domicilio = this.scanner.nextLine().toUpperCase();
            System.out.println("Ingrese DNI:");
            int dni = Integer.parseInt(this.scanner.nextLine());
            System.out.println(MenuBancoImpl.DIVISION);
            bancoServicio.registrarCliente(new Cliente(nombre, apellido, domicilio, dni));
        } catch (Exception e){
            System.out.println("Error al generar usuario.");
            System.out.println(MenuBancoImpl.DIVISION);
        }
    }

    @Override
    public void abrirCuenta(Cliente cliente) {
        CuentaBancariaServicio cuentaServicio = new CuentaBancariaServicioImpl(this.sucursal);
        boolean ok = true;
        do {
            System.out.println(INGRESE_MONEDA_TEMPLATE);
            String opc = scanner.nextLine().toUpperCase();
            try{
                Moneda moneda = Moneda.valueOf(opc);
                CuentaBancaria cuenta = cuentaServicio.abrirCajaAhorro(cliente, moneda);
                cliente.getCuentas().add(cuenta);
                ok = false;
            } catch (IllegalArgumentException e){
                System.out.println(MONEDA_NO_VALIDA_TEMPLATE);
            }
        } while (ok);
    }

    @Override
    public void abrirCuentaCorriente(Cliente cliente) {
        CuentaBancariaServicio cuentaServicio = new CuentaBancariaServicioImpl(this.sucursal);
        boolean ok = true;
        do {
            System.out.println(INGRESE_MONEDA_TEMPLATE);
            String opc = scanner.nextLine().toUpperCase();
            try {
                Moneda moneda = Moneda.valueOf(opc);
                System.out.println("Ingrese sobregiro:");
                Double sobregiro = Double.parseDouble(scanner.nextLine());
                CuentaCorriente cuenta = cuentaServicio.abrirCuentaCte(cliente, moneda, sobregiro);
                cliente.getCuentas().add(cuenta);
                ok = false;
            } catch (NullPointerException g){
                System.out.println("Debe ingresar un monto");
            } catch (NumberFormatException f){
                System.out.println("Sobregiro invalido");
            } catch (IllegalArgumentException e){
                System.out.println(MONEDA_NO_VALIDA_TEMPLATE);
            }
        } while (ok);
    }


    @Override
    public List<CuentaBancaria> getCuentasCliente(Cliente cliente) {
        List<CuentaBancaria> cuentas = cliente.getCuentas();
        if (cuentas.isEmpty()) {
            System.out.println("Cliente sin cuentas.");
            System.out.println(MenuBancoImpl.DIVISION);
        } else {
            for (CuentaBancaria cuenta : cuentas) {
                System.out.println(cuenta);
            }
        }
        return cuentas;
    }

}
