/*
 * Author Ahmed Abdelhalim - 2009
 * Email: englemo@hotmail.com
 * Please do not remove the above lines
 */
package remoteserver;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This is the entry class of the server
 */
public class ServerInitiator {
    //Main server frame
    private JFrame frame = new JFrame();
    //JDesktopPane represents the main container that will contain all
    //connected clients' screens
    private JDesktopPane desktop = new JDesktopPane();

    public static void main(String args[]){
        String port = JOptionPane.showInputDialog("Please enter listening port");
        try {
            new ServerInitiator().initialize(Integer.parseInt(port));
        } catch (Exception NumberFormatException e)
        {
            System.out.println("Invalid input entered for listening port. Aborting startup.");
            throw(e);
        }
    }

    public void initialize(int port){

        try {
            ServerSocket sc = new ServerSocket(port);
            //Show Server GUI
            drawGUI();
            //Listen to server port and accept clients connections
            while(true){
                Socket client = sc.accept();
                System.out.println("New client Connected to the server: " + client.toString());
                //Per each client create a ClientHandler
                new ClientHandler(client,desktop);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * Draws the main server GUI
     */
    public void drawGUI(){
            frame.add(desktop,BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Show the frame in a maximized state
            frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
    }
}
