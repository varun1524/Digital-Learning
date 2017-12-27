package com.example.varun.client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.varun.digitallearning.ListUser;
import com.example.varun.digitallearning.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by varun on 16/04/15.
 */
public class DialogMessanger extends Activity {
    AlertDialog.Builder alertadd;
    TextView textserver;
    EditText editTextmsg;
    Button btnSend;
    final View view;
    static final int SocketServerPORT = 8080;


    ChatClientThread chatClientThread = null;
    Socket socket = null;
    String newMsg;

    public DialogMessanger(Context context) {

        alertadd = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
        LayoutInflater factory = LayoutInflater.from(context);
        view = factory.inflate(R.layout.dialog_messanger, null);
        editTextmsg = (EditText) view.findViewById(R.id.etmsg);
        btnSend = (Button) view.findViewById(R.id.btnsend);
        btnSend.setText("Send Message");
        btnSend.setBackgroundColor(Color.parseColor("#FFFFA337"));
    }

    public void showDialog(){
        alertadd.setView(view);
        final AlertDialog dlg=alertadd.create();
        dlg.show();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* System.out.println(editTextUserName.getText().toString());

        String textUserName = editTextUserName.getText().toString();

        if (textUserName.equals("")) {
            Toast.makeText(SendMessage.this, "Enter User Name",
                    Toast.LENGTH_LONG).show();
            return;
        }
    */
                System.out.println(editTextmsg.getText().toString());

                String textmsg= editTextmsg.getText().toString();
                if (textmsg.equals(null)) {
                    Toast.makeText(DialogMessanger.this, "Enter Message",
                            Toast.LENGTH_LONG).show();
                    return;
                }

            /*String textAddress = editTextAddress.getText().toString();
            if (textAddress.equals("")) {
                Toast.makeText(MyActivity.this, "Enter Address",
                        Toast.LENGTH_LONG).show();
                return;
            }*/

                String textAddress="192.168.1.1";


                chatClientThread = new ChatClientThread(textmsg,textAddress,SocketServerPORT);

                chatClientThread.start();
                dlg.dismiss();
            }
        });


    }





    private class ChatClientThread extends Thread {

        String name= ListUser.logged_user;
        String msg;
        String dstAddress;
        int dstPort;

        //String msgToSend = "";
        //boolean goOut = false;

        ChatClientThread(String msg, String address, int port) {

            dstAddress = address;
            this.msg=msg;
            dstPort = port;
        }

        @Override
        public void run() {

            DataOutputStream dataOutputStream = null;
            DataInputStream dataInputStream = null;

            try {
                socket = new Socket(dstAddress, dstPort);
                dataOutputStream = new DataOutputStream(
                        socket.getOutputStream());

                dataInputStream = new DataInputStream(socket.getInputStream());
                Log.i("selected user:", name);
                if(!msg.equals(null) && !msg.equals("")) {
                    dataOutputStream.writeUTF(name);

                    dataOutputStream.writeUTF(msg);
                }
                dataOutputStream.flush();

                newMsg=null;
                newMsg= dataInputStream.readUTF();






            /*    while (!goOut) {
                    if (dataInputStream.available() > 0) {
                        msgLog += dataInputStream.readUTF();

                        MyActivity.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                chatMsg.setText(msgLog);
                            }
                        });
                    }

                    if(!msgToSend.equals("")){
                        dataOutputStream.writeUTF(msgToSend);
                        dataOutputStream.flush();
                        msgToSend = "";
                    }
                }
*/
            } catch (UnknownHostException e) {
                e.printStackTrace();
                final String eString = e.toString();
                DialogMessanger.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(DialogMessanger.this, eString, Toast.LENGTH_LONG).show();
                    }

                });
            } catch (IOException e) {
                e.printStackTrace();

                //final String eString = e.toString();
                /*DialogMessanger.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(DialogMessanger.this, eString, Toast.LENGTH_LONG).show();
                    }

                });*/

            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                if (dataOutputStream != null) {
                    try {
                        dataOutputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
/*
                MyActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //loginPanel.setVisibility(View.VISIBLE);
                        //chatPanel.setVisibility(View.GONE);
                    }

                });*/
            }

        }

        /*private void sendMsg(String msg){
            msgToSend = msg;
        }

        private void disconnect(){
            goOut = true;
        }*/
    }
}
