package pe.edu.upc.ecohabitproyecto.dtos;

public class CanjePuntosDTO {
    private int puntosACanjear;
    private String emailPaypal; // opcional, si quieres registrar a qué cuenta va

    public int getPuntosACanjear() {
        return puntosACanjear;
    }

    public void setPuntosACanjear(int puntosACanjear) {
        this.puntosACanjear = puntosACanjear;
    }

    public String getEmailPaypal() {
        return emailPaypal;
    }

    public void setEmailPaypal(String emailPaypal) {
        this.emailPaypal = emailPaypal;
    }
}