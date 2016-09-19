/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.ads.pos.publicadores.mqtt.elemento;

/**
 *
 * @author Junior
 */
public class ArCondicionado {

    private boolean ligado;
    private int temperatura;

    public ArCondicionado() {
        this(false, 0);
    }

    public ArCondicionado(boolean ligado, int temperatura) {
        this.ligado = ligado;
        this.temperatura = temperatura;
    }

    /**
     * @return the temperatura
     */
    public int getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(int temperatura) {
        if (this.isLigado() && temperatura >= 16 && temperatura <= 28) {
            this.temperatura = temperatura;
        }
    }

    /**
     * @return the ligado
     */
    public boolean isLigado() {
        return ligado;
    }

    /**
     * @param ligado the ligado to set
     */
    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.ligado ? 1 : 0);
        hash = 79 * hash + this.temperatura;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArCondicionado other = (ArCondicionado) obj;
        if (this.ligado != other.ligado) {
            return false;
        }
        return this.temperatura == other.temperatura;
    }

    @Override
    public String toString() {
        return (isLigado())
                ? String.format("Arcondicionado ligado com a temperatura de %d", temperatura)
        : "Arcondicionado desligado";
    }

}
