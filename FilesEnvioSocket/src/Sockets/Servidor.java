/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author javi
 */
public class Servidor {

    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket;
        Socket clienteSocket;
        boolean terminar = false;
        serverSocket = new ServerSocket(4444);
        System.out.println("servidor Activo");
        //DataInputStream entrada;
        BufferedReader entrada;
        PrintStream salida;
        String input;
        while (!terminar) {
            clienteSocket = serverSocket.accept();
            //entrada = new DataInputStream(clienteSocket.getInputStream());
            entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            salida = new PrintStream(clienteSocket.getOutputStream());
            input = entrada.readLine();
            if (new File(input).exists()) {
                salida.println("enviando...");
                new ProcesarFile("cliente " + clienteSocket.getInetAddress(), input, clienteSocket, salida);
                
            } else {
                System.out.println("FileNotFound"+input+" \tCloseSocketServer");
                salida.println("archivo no encontrado " + input);
                terminar = true;
                entrada.close();
                salida.close();
                clienteSocket.close();
            }





          


        }
        serverSocket.close();


    }
}
