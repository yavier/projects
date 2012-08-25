/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

/**
 *
 * @author javi
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author javi
 */
public class Servidor2 {

    public static void main(String args[]){
        try {
            ServerSocket serverSocket;
            Socket clienteSocket;
            boolean terminar = false;
            serverSocket = new ServerSocket(4444);
            System.out.println("servidor Activo");
            //DataInputStream entrada;
            //BufferedReader entrada;
            //PrintStream salida;
            String input;
            while (!terminar) {
                clienteSocket = serverSocket.accept();
                //entrada = new DataInputStream(clienteSocket.getInputStream());
               // entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                //salida = new PrintStream(clienteSocket.getOutputStream());
                new ProcesarFile("cliente: " + clienteSocket.getInetAddress(), clienteSocket,serverSocket);
                /*input = entrada.readLine();
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
                }*/





              


            }
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }


    }
}
