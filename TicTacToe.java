package com.JavaChatApp;
//This java program imports necessary classes for GUI(Graphical User Interface), Event handling, and Networking.
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class JavaChatAppUser1 extends JFrame implements ActionListener,Runnable {
    JTextArea displayArea;     //Instance variables for the GUI components (displayArea, msgInputField, sendButton) and
    JTextField msgInputField;
    JButton sendButton;

    Socket socket;  // And for networking (socket, dataInputStream, dataOutputStream)
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    Thread readInputThread;  // And thread (readInputThread).

    JavaChatAppUser1(){ //This constructor used to initializing and setting the alignment for the GUI components,

        displayArea = new JTextArea();
        displayArea.setBounds(10,30,180,310);

        msgInputField = new JTextField();
        msgInputField.setBounds(10,340,180,30);

        sendButton = new JButton("Send");
        sendButton.setBounds(150,368,40,25);
        sendButton.addActionListener(this); //Activating the ActionListener for the send button and the logic needs to be performed is written within below actionPerformed();;

        add(displayArea);  //Adding the components to the frame otherwise the components will not be displayed in the frame.
        add(msgInputField);
        add(sendButton);


        try {
            socket = new Socket("localhost",12000); //set up the socket connection to localhost on port 12000,

            dataInputStream = new DataInputStream(socket.getInputStream());//And setting up input and output streams.
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e)
        { //Exceptions are handled that occurs when the Socket tries to build the connection with the server.
            System.out.println(e.getMessage());
        }

        ThreadGroup tg = new ThreadGroup("JavaChatApp");
        readInputThread = new Thread(tg,this);//Creating and linking the current class to the Thread class by passing the instance.
        readInputThread.setDaemon(true);//Main use of this thread is to run the logic that the user should always ready to take the input msg from the sender from the beginning to end of the program execution, Hence the thread is made as daemon thread.
        readInputThread.start();   //And start the readInputThread.

        setLayout(null); //This method is within the Frame class and should be null as we are setting our own values for the components by using "setBounds()";
        setTitle("User1");//Setting the title for the frame inside which the components are present.
        setSize(200,400);
        setVisible(true);//This should be always true otherwise the components will not be visible within the frame.

    }

    public void actionPerformed(ActionEvent e) {//This method is used to handle the action when the particular button (here I gave send button) is clicked.
        String msg = msgInputField.getText(); //Get the message from the input field (TextArea) and
        displayArea.append("User1: "+msg+"\n"); //display it in the top displayArea (TextField) .
        msgInputField.setText("");//So when the typed message appears in the msgInputField, it will be reset to empty after the send button is clicked.

        try {
            dataOutputStream.writeUTF(msg);  //Sending the message to User2 through the output stream (dataOutputStream).
            dataOutputStream.flush(); //If the msg is in the buffer memory then it is made to send faster for the recipient by using the flush() method of DOS.
        }
        catch (IOException ex) {

        }

    }

    public void run() {  //The run(); method is used to continuously read messages from User2 --
        while (true){
            try {
                String msg = dataInputStream.readUTF(); //through the input stream(dataInputStream)
                displayArea.append("User2: "+msg+"\n"+"\n"); // and display them in the displayArea.
            }
            catch (IOException e) {
            }
        }
    }


    public static void main(String[] args){
        new JavaChatAppUser1();  //Is used to create an instance of "JavaChatAppUser1" to run the application.
    }


}
