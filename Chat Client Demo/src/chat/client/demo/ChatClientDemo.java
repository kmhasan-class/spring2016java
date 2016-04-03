/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmhasan
 */
public class ChatClientDemo {

    public ChatClientDemo() {
        String hostname = "172.17.0.134";
        int portNumber = 9876;
        try {
            Socket socket = new Socket(hostname, portNumber);
            OutputStream out = socket.getOutputStream();
            String message = "ওহে বিশ্ব";
            out.write(message.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(ChatClientDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ChatClientDemo();
    }
    
}
