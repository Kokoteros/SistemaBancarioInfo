package org.banksys.info.Menu;

import org.banksys.info.Clases.*;
import org.banksys.info.Servicios.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MenuClienteImpl implements MenuCliente {
    private Scanner scanner;
    private final String TITULO = "Menu del Cliente";
    public static final String SELECCION_TEMPLATE = "Ingrese un DNI para operar:";
    public static final String SIN_COINCIDENCIAS_TEMPLATE ="No hay coincidencias para ese DNI.";
    public static final String SIN_CLIENTES_TEMPLATE ="No hay clientes cargados.";

    private BancoServicio bancoServicio;

    public MenuClienteImpl(BancoServicio bancoServicio){
        this.scanner = scannerinput.getScanner();
        this.bancoServicio = bancoServicio;
    }
    @Override
    public void operarConCliente() {
        bancoServicio.getListaClientes();
        HashMap<Long, Cliente> clientes = bancoServicio.getBanco().getClientes();
        if (clientes.isEmpty()){
            System.out.println(SIN_CLIENTES_TEMPLATE);
        } else {
            System.out.println(MenuBancoImpl.DIVISION);
            System.out.println(SELECCION_TEMPLATE);
            int dni = Integer.parseInt(scanner.nextLine());
            List<Cliente> cliente = bancoServicio.getClienteByDni(dni);
            if (!cliente.isEmpty()) {
                menuCliente(cliente.get(0));
            } else {
                System.out.println(SIN_COINCIDENCIAS_TEMPLATE);
            }
        }
    }

    public void menuCliente(Cliente cliente) {
        String opc;
        ClienteServicio clienteServicio = new ClienteServicioImpl(this.bancoServicio.getBanco());
        MenuCuentaBancaria menuCuentaBancaria = new MenuCuentaBancaria() {
            public void operarConCuentaCliente(Cliente cliente) {

            }
            public void operarConCuenta() {

            }
            public void menuCuenta(CuentaBancaria cuenta) {

            }
        };
        do {
            System.out.println(TITULO);
            System.out.println(MenuBancoImpl.DIVISION);
            System.out.println("Ingrese una opcion:");
            System.out.println("1- Ver cuentas.");
            System.out.println("2- Operar con cuenta.");
            System.out.println("3- Abrir cuenta de ahorro.");
            System.out.println("4- Abrir cuenta corriente.");
            System.out.println("0- Volver al menu anterior.");
            System.out.println(MenuBancoImpl.DIVISION);
            System.out.println("Seleccione una opcion:");
            opc = scanner.nextLine();
            System.out.println(MenuBancoImpl.DIVISION);
            switch (opc) {
                case "1":
                    clienteServicio.getCuentasCliente(cliente);
                    break;
                case "2":
                    menuCuentaBancaria.operarConCuentaCliente(cliente);
                    break;
                case "3":
                    clienteServicio.abrirCuenta(cliente);
                    break;
                case "4":
                    clienteServicio.abrirCuentaCorriente(cliente);
            }
        } while (!opc.equals("0"));

    }
}
