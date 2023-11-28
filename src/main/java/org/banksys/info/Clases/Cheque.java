package org.banksys.info.Clases;

import java.time.LocalDate;

public class Cheque {
    private Long numeroCheque;
    private CuentaCorriente cuenta;
    private LocalDate fechaEmision;
    private Double monto;
    private static Long nroChequeautoIncremental = 0L;

    public Cheque() {
        this.numeroCheque = getNroChequeautoIncremental();
    }
    public Cheque(CuentaCorriente cuenta, LocalDate fechaEmision, Double monto) {
        this.numeroCheque = getNroChequeautoIncremental();
        this.cuenta = cuenta;
        this.fechaEmision = fechaEmision;
        this.monto = monto;
    }
    private static Long getNroChequeautoIncremental(){
        nroChequeautoIncremental ++;
        return nroChequeautoIncremental;
    }

    public Long getNroCheque() {
        return numeroCheque;
    }

    public void setNroCheque(Long nroCheque) {
        this.numeroCheque = nroCheque;
    }

    public CuentaCorriente getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaCorriente cuenta) {
        this.cuenta = cuenta;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public static void setNroChequeautoIncremental(Long nroChequeautoIncremental) {
        Cheque.nroChequeautoIncremental = nroChequeautoIncremental;
    }
    @Override
    public String toString(){
        return this.numeroCheque + " - " + getFechaEmision() + " - Monto: " + this.getMonto();
    }
}
