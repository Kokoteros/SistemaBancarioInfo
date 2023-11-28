package org.banksys.info;

import  org.banksys.info.Clases.Banco;
import org.banksys.info.Menu.*;
public class App {
    private static int SUCURSAL = 54;
    private static String DIRECCION = "Lavalle 120";
    private static Float TNA = 140F;
    public static void main(String[] args) {
    MenuBanco MenuBanco = new MenuBancoImpl(
            new Banco(SUCURSAL, DIRECCION, TNA)
    );
        MenuBanco.iniciarPrograma();
    }
}

