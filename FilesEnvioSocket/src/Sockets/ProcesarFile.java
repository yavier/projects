/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javi
 */
public class ProcesarFile extends Thread {

    private String ruta;
    Socket clienteSocket;
    PrintStream salida;
    BufferedReader entrada;
    ServerSocket server;

    ProcesarFile(String msg, String ruta, Socket clienteS, PrintStream salida) {
        super(msg);
        this.ruta = ruta;
        this.clienteSocket = clienteS;
        this.salida = salida;
        start();


    }

    ProcesarFile(String msg, Socket clienteSocket, ServerSocket server) {
        super(msg);
        this.clienteSocket = clienteSocket;
        this.server = server;
        start();

    }

    String readFile() throws FileNotFoundException, IOException {

        FileReader in = new FileReader(new File(getRuta()));
        int c;
        StringBuffer sb = new StringBuffer();
        while ((c = in.read()) != -1) {
            sb.append((char) c);
        }
        return sb.toString();


    }

    @Override
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            salida = new PrintStream(clienteSocket.getOutputStream());
            setRuta(entrada.readLine());
            System.out.println("cliente" + getName());
            salida.println(readFile());
            finSocket();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            
finServer();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void finSocket() throws IOException {
        clienteSocket.close();

    }
    
    
    

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    private void finServer() {
        try {
            server.close();
        } catch (IOException ex) {
            //Logger.getLogger(ProcesarFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
