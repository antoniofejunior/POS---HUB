/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.ads.pos.publicadores.mqtt.sensores;

import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author Junior
 */
@Data        
public class PublicadorService {

    private int sala;
    private String sensor;
    private final String topic = String.format("hub/sala/%d/sensor/%s/", sala, sensor);
//    private final String content = "23";
    private final int qos = 2;
    private final String broker = "tcp://192.168.99.100:1883";
//    private final String broker = "tcp://0.0.0.0:1883";
    private final String clientId = "HUB";
    private final MemoryPersistence persistence = new MemoryPersistence();

    public PublicadorService(int sala, String sensor) {
        this.sala = sala;
        this.sensor = sensor;
    }

    public PublicadorService() {
    }

    public void publicar(String mensagem, MqttClient sampleClient) throws MqttException {
        System.out.println("Publishing message: " + mensagem);
        MqttMessage message = new MqttMessage(mensagem.getBytes());
        message.setQos(qos);
        sampleClient.publish(topic, message);
        System.out.println("Message published");
    }

    public void disconectarBroker(MqttClient sampleClient) throws MqttException {
        sampleClient.disconnect();
        System.out.println("Disconnected");
//        System.exit(0);
    }

    public MqttClient conectarBroker() {
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            return sampleClient;
        } catch (MqttException ex) {
            Logger.getLogger(PublicadorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
