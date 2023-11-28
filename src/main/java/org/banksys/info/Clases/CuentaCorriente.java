package org.banksys.info.Clases;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CuentaCorriente extends CuentaBancaria {
    private Double limiteSobregiro;
    private Set<Cheque> cheques;
    public CuentaCorriente() {
        this.nroCuenta = getNroCuentaAutoIncremental();
        this.ultimaActualizacionIntereses = LocalDate.now();
        this.cheques = new HashSet<>();
    }
    public Double getLimiteSobregiro() {
        return limiteSobregiro;
    }
    public void setLimiteSobregiro(Double limiteSobregiro) {
        this.limiteSobregiro = limiteSobregiro;
    }
    public Set<Cheque> getCheques() {
        return cheques;
    }
    @Override
    public Boolean puedoExtraer(Double monto){
        return this.getSaldo() - monto + this.getLimiteSobregiro() >= 0;
    }
    public void setCheques(Set<Cheque> cheques) {
        this.cheques = cheques;
    }
    @Override
    public String toString(){
        return "Cuenta Corriente N°: " + this.nroCuenta + " - Saldo: "+ this.getSaldo() + " - " + this.titular;
    }
    @Override
    public String getTipoCuenta() {
        return "Cuenta Corriente";
    }
}