/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.ads.pos.publicadores.mqtt.sensores;

import ifpb.ads.pos.publicadores.mqtt.elemento.ArCondicionado;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author Junior
 */
public class ThreadSensorTemperatura extends Thread {

    @Override
    public void run() {
        this.mqtt();
        super.run();
    }

    private void mqtt() {
        try {
            PublicadorService service = new PublicadorService(1, "temperatura");
            MqttClient broker;
            Random gerador = new Random();
            ArCondicionado ac = new ArCondicionado(true, 20);
            while (true) {
                broker = service.conectarBroker();
                service.publicar(String.valueOf(gerador.nextInt(8) - 4 + ac.getTemperatura()), broker);
                service.disconectarBroker(broker);
                Thread.sleep(5000);
//                ac.setTemperatura(gerador.nextInt(12) + 16);
            }
        } catch (MqttException | InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
