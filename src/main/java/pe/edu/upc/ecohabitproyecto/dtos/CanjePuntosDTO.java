package pe.edu.upc.ecohabitproyecto.dtos;

public class CanjePuntosDTO {
    private int puntosACanjear;
    private String cuentaBancaria; // opcional, si quieres registrar a qué cuenta va

    public int getPuntosACanjear() {
        return puntosACanjear;
    }

    public void setPuntosACanjear(int puntosACanjear) {
        this.puntosACanjear = puntosACanjear;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
}