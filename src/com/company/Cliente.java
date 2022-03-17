package com.company;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends javax.swing.JFrame {

    String msn;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String Nick;

    public Cliente() throws IOException {

        Nick = JOptionPane.showInputDialog("Dinos tu nick: ");
        String ip = JOptionPane.showInputDialog("Dinos a que host quieres conectarte: ");
        String Puerto = JOptionPane.showInputDialog("Dinos el puerto al que quieres conectarte: ");
        socket = new Socket(ip, Integer.parseInt(Puerto));

        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.Nick = Nick;
            bufferedWriter.write((Nick));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            cerrarConexiones(socket, bufferedReader, bufferedWriter);
        }
        initComponents();
        escuchaMensaje();
    }

    public void enviarmensaje(String mensaje) {
        try {
                if (!msn.equals("/sair")) {
                    bufferedWriter.write("[" + Nick + "]: " + msn);
                    conversacion.setText(conversacion.getText() + "\n" + "[" + Nick + "]: " + msn);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } else {
                    socket.close();
                    System.exit(0);
                }
        } catch (IOException e) {
            cerrarConexiones(socket, bufferedReader, bufferedWriter);
        }
    }

    public void escuchaMensaje() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msnDelResto;

                while (socket.isConnected()) {
                    try {
                        msnDelResto = bufferedReader.readLine();
                        //System.out.println(msnDelResto);
                        conversacion.setText(conversacion.getText() + "\n" + msnDelResto);
                    } catch (IOException e) {
                        cerrarConexiones(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    private void cerrarConexiones(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Dinos tu nick: ");
        String Nick = teclado.nextLine();
        System.out.println("Dinos a que host quieres conectarte: ");
        String ip = teclado.nextLine();
        System.out.println("Dinos el puerto al que quieres conectarte: ");
        String Puerto = teclado.nextLine();
        Socket socket = new Socket(ip, Integer.parseInt(Puerto));
        Cliente cliente = new Cliente(socket, Nick);
        cliente.escuchaMensaje();
        cliente.enviarmensaje();
    }
*/


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Boton = new javax.swing.JButton();
        mensaxe = new javax.swing.JTextField();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        conexion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        conversacion = new java.awt.TextArea();

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Boton.setText("Enviar");
        Boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonActionPerformed(evt);
            }
        });

        conexion.setColumns(20);
        conexion.setRows(5);
        jScrollPane3.setViewportView(conexion);

        scrollPane1.add(jScrollPane3);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        scrollPane1.add(jScrollPane2);
        scrollPane1.add(conversacion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 9, Short.MAX_VALUE)
                                                .addComponent(mensaxe, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(Boton)))
                                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Boton)
                                        .addComponent(mensaxe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22))
        );

        pack();
    }



    private void BotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonActionPerformed
        this.msn = mensaxe.getText();
        enviarmensaje(msn);
        mensaxe.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Cliente().setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton;
    private javax.swing.JTextArea conexion;
    private static java.awt.TextArea conversacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField mensaxe;
    private java.awt.ScrollPane scrollPane1;

}
