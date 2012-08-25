/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javi
 */
public class Cliente extends Thread {

    String cliente;
    String ruta;

    Cliente() {
        start();
    }

    public static void main(String args[]) {
        //for(int j=0; j<50; j++)
        for (int i = 0; i < 6; i++) {
            try {
                new Cliente("cliente" + i, "C:/texto" + i + ".txt").join();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }

    }

    private Cliente(String cliente, String ruta) {
        this.cliente = cliente;
        this.ruta = ruta;
        start();
    }

    public void run() {
        Socket miCliente;
        try {
            miCliente = new Socket("localhost", 4444);
            //DataInputStream entrada;
            //entrada = new DataInputStream(miCliente.getInputStream());
            BufferedReader entrada;
            entrada = new BufferedReader(new InputStreamReader(miCliente.getInputStream()));
            PrintStream salida;
            salida = new PrintStream(miCliente.getOutputStream());
            //System.out.println("escriba msg");
            //salida.println(new Scanner(System.in).nextLine());
            System.out.println("arvhivo: " + ruta + " cliente: " + cliente);
            salida.println(ruta);
            int c;
            while ((c = entrada.read()) != -1) {
                System.out.print((char) c);
            }

            salida.close();
            entrada.close();
            miCliente.close();
            System.out.println("fin cliente " + miCliente.getInetAddress());
        } catch (IOException e) {
            System.out.println(e);
        }


    }
}
