/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kmhasan
 */
public class ChatServerDemo {

    public ChatServerDemo() {
        int portNumber = 9876;
        try {
            // Socket - a medium for communication
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.printf("Server running on port %d\n", portNumber);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.printf("Connected with %s\n", socket.getInetAddress());

                ThreadedServer threadedServer = new ThreadedServer(socket);
                threadedServer.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServerDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ChatServerDemo();
    }

}
