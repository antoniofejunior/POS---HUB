/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.ads.pos.publicadores.mqtt.elemento;

import lombok.Data;

/**
 *
 * @author Junior
 */
@Data
public class Lampada {

    private boolean ligada;

    @Override
    public String toString() {
        return (isLigada())
                ? "Lampada ligada."
                : "Lampada desligada";
    }

}
