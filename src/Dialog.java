import com.company.HiloCliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Dialog {

    private JPanel panel1;
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton ENVIARButton;
    String mensaxe;
    static String HOST = "localhost";
    static int PUERTO = 6666;
    Socket sc;
    DataOutputStream salida;
    DataInputStream entrada;
    String mensajeRecibido;
    String Nick = "";


    public Dialog() throws IOException {
        ENVIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensaxe = textArea1.getText();
                try {
                    sc = new Socket(HOST, PUERTO);
                    salida = new DataOutputStream(sc.getOutputStream());

                    salida.writeUTF("[" + Nick + "]:" + mensaxe);
                    textField1.setText(textField1.getText() + "\n" +"[" + Nick + "]:" + mensaxe);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        while (true) {
            sc = new Socket(HOST, PUERTO);
            entrada = new DataInputStream(sc.getInputStream());
            mensajeRecibido = entrada.readUTF();//Leemos respuesta
            textField1.setText(textField1.getText() + "\n" + mensajeRecibido);
        }
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Mensajes");
        frame.setContentPane(new Dialog().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); //Situamos el frame en el centro de la pantalla
    }

}


