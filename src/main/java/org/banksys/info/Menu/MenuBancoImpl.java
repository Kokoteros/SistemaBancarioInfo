package org.banksys.info.Menu;

import org.banksys.info.Clases.*;
import org.banksys.info.Servicios.*;

import java.util.Scanner;
public class MenuBancoImpl implements MenuBanco {
    private Banco banco;
    private BancoServicio bancoServicio;
    private MenuClienteImpl menuCliente;
    private ClienteServicio clienteServicio;
    private MenuCuentaBancaria menuCuentaBancaria;
    private static String TITULO = "Bienvenido al Sistema Bancario";
    public static String DIVISION = "-".repeat(20);


    public MenuBancoImpl(Banco banco) {
        this.banco = banco;
        this.clienteServicio = new ClienteServicioImpl(banco);
    }

    public void iniciarPrograma() {
        String opc;
        Scanner scanner = scannerinput.getScanner();
        this.bancoServicio = new BancoServicioImpl(this.banco);
        this.menuCliente = new MenuClienteImpl(bancoServicio);
        this.menuCuentaBancaria = new MenuCuentaBancariaImpl(bancoServicio);
        System.out.println(TITULO);
        do {
            System.out.println(DIVISION);
            System.out.println("Ingrese una opcion:");
            System.out.println("1- Ingresar como cliente.");
            System.out.println("2- Registrar cliente.");
            System.out.println("3- Ver clientes.");
            System.out.println("4- Ver cuentas.");
            System.out.println("5- Operar una cuenta.");
            System.out.println("6- Exportar cuentas.");
            System.out.println("0- Salir");
            System.out.println(DIVISION);
            System.out.println("Seleccione una opción:");
            opc = scanner.nextLine();
            System.out.println(DIVISION);
            switch (opc) {
                case "1":
                    menuCliente.operarConCliente();
                    break;
                case "2":
                    clienteServicio.generarCliente();
                    break;
                case "3":
                    bancoServicio.getListaClientes();
                    break;
                case "4":
                    bancoServicio.verCuentas();
                    break;
                case "5":
                    bancoServicio.verCuentas();
                    menuCuentaBancaria.operarConCuenta();
                    break;
                case "6":
                    bancoServicio.exportarCuentasACsv();
            }
        } while (!opc.equals("0"));
        System.out.println("Gracias por utilizar nuestro servicio.");
    }
}

