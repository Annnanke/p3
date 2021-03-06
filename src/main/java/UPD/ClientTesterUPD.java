package UPD;


import Packets.Message;
import Packets.Packet;
import TCP.ClientUPD;

import java.io.IOException;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class ClientTesterUPD {
    public static void main(String[] args) throws IOException {
        for(byte i = 0; i < 8; i++){
            client(i);
        }
    }


    private static void client(byte id){
        new Thread(()->{
            try{
                Thread.sleep(new Random().nextInt(200));
                ClientUPD client = new ClientUPD(id);
                client.send(new Packet(id, 1, new Message(1, 1,
                        "Hello world".getBytes(StandardCharsets.UTF_8))));
                System.out.println(client.receive());
            } catch (InterruptedException | SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        })
                .start();
    }
}