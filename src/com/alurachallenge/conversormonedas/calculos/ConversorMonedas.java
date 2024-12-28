package com.alurachallenge.conversormonedas.calculos;

public class ConversorMonedas {
    private String monedaActual;
    private String monedaConvertida;
    private double montoActual;
    private double montoConvertida;

    public ConversorMonedas(String monedaActual, String monedaConvertida, double montoActual) {
        this.monedaActual = monedaActual;
        this.monedaConvertida = monedaConvertida;
        this.montoActual = montoActual;
        //FUNCION PARA CONVERTIR MONEDAS
    }

    public String getMonedaActual() {
        return monedaActual;
    }

    public void setMonedaActual(String monedaActual) {
        this.monedaActual = monedaActual;
    }

    public String getMonedaConvertida() {
        return monedaConvertida;
    }

    public void setMonedaConvertida(String monedaConvertida) {
        this.monedaConvertida = monedaConvertida;
    }

    public double getMontoActual() {
        return montoActual;
    }

    public void setMontoActual(double montoActual) {
        this.montoActual = montoActual;
    }

    public double getMontoConvertida() {
        return montoConvertida;
    }

    public void setMontoConvertida(double montoConvertida) {
        this.montoConvertida = montoConvertida;
    }

    public ConversorMonedas(ConversorMonedasExchangeRate conversor1){
        this.monedaActual = conversor1.base_code();
        this.monedaConvertida = conversor1.target_code();
        this.montoActual = conversor1.conversion_rate();
        this.montoConvertida = conversor1.conversion_result();
    }

    public void mostrarDatos (){
        System.out.println("Moneda Actual: " + getMonedaActual());
        System.out.println("Moneda Convertida: " + getMonedaConvertida());
        System.out.println("Monto actual: " + getMontoActual());
        System.out.println("Monto a convertir: " + getMontoConvertida());
    }

    @Override
    public String toString() {
        return "Moneda existente: '" + monedaActual + "\n" +
                "Moneda Convertida: " + monedaConvertida + "\n" +
                "monto ingresado: " + montoActual + "\n" +
                "monto convertida: " + montoConvertida + "\n";
    }
}
