/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.ads.pos.publicadores.mqtt.sensores;

import ifpb.ads.pos.publicadores.mqtt.elemento.Lampada;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author Junior
 */
public class ThreadSensorLuminosidade extends Thread {

    @Override
    public void run() {
        this.mqtt();
        super.run();
    }

    private void mqtt() {
        try {
            PublicadorService service = new PublicadorService(2, "luminosidade");
            MqttClient broker;
            Random gerador = new Random();
            Lampada lp = new Lampada();
            Thread.sleep(2000);
            while (true) {
                broker = service.conectarBroker();
                lp.setLigada(gerador.nextBoolean());
                service.publicar(lp.toString(), broker);
                service.disconectarBroker(broker);
                Thread.sleep(12000);
            }
        } catch (MqttException | InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
