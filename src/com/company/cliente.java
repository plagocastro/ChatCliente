package com.company;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class cliente {
    static String HOST = "localhost";
    static int PUERTO = 6666;
    Socket sc;
    DataOutputStream salida;
    DataInputStream entrada;
    String mensajeRecibido;
    String Nick = "";

    public void pedirNome() {
        System.out.println("Dime o teu nick: ");
        Scanner sNome = new Scanner(System.in);
        Nick = sNome.nextLine();
        System.out.println("Chat iniciado... ");
    }

    public void initCliente() {
        Scanner teclado = new Scanner(System.in);
        try {
            sc = new Socket(HOST, PUERTO);
            salida = new DataOutputStream(sc.getOutputStream());
            entrada = new DataInputStream(sc.getInputStream());
            String msn = "";
            salida.writeUTF(Nick);
            System.out.println("->");

            while (!msn.equals("Salida()")) {

                msn = teclado.nextLine();
                salida.writeUTF("[" + Nick + "]:" + msn);//enviamos mensaje

                mensajeRecibido = entrada.readUTF();//Leemos respuesta
                System.out.println(mensajeRecibido);

            }
            sc.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static void main(String[] args) {
        cliente o = new cliente();
        o.pedirNome();
        if (o.Nick != "") {
            o.initCliente();
        }
    }
}


/*
System.out.println("Porfavor, introduce tu PUERTO: ");
        Scanner sPuerto = new Scanner(System.in);
        PUERTO = Integer.parseInt(sPuerto.nextLine());
 */