package com.BlueTooth;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import com.Bean.ECGData;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 */
public class ConnectedThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
   // private final Handler mHandler;
    String ReceiveData="";
    private final BufferedInputStream bufferedInputStream;
    private ECGData ecgData;
    private ArrayList<String> dataList=new ArrayList<String>();
    public ConnectedThread(BluetoothSocket socket,ECGData ecgData) {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        ecgData.setDataList(dataList);
        this.ecgData=ecgData;
       // mHandler = handler;
        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = socket.getInputStream();

            tmpOut = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mmInStream = tmpIn;
        bufferedInputStream = new BufferedInputStream(mmInStream);
        mmOutStream = tmpOut;
    }


    public void run() {
        byte[] buffer = new byte[1024];  // buffer store for the stream
        int bytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs
     while (true) {
            try {

                // Read from the InputStream
                bytes = bufferedInputStream.read(buffer);
                // Send the obtained bytes to the UI activity
                if( bytes >0) {

                    ReceiveData=ReceiveData+new String(buffer);
                    // newbuff=null;
                    for(int i=0;i<(ReceiveData.length())/3;i++) {
                        String subStringData = ReceiveData.substring(i, i + 3);
                         ecgData.getDataList().add(subStringData);
                    }

                }
                Log.d("GOTMSG", "message size" + bytes);
            } catch (IOException e) {
              //  mHandler.sendMessage(mHandler.obtainMessage(Constant.MSG_ERROR, e));
                break;
            }
        }
    }

//    /* Call this from the main activity to send data to he remote device */
//    public void write(byte[] bytes) {
//        try {
//            mmOutStream.write(bytes);
//        } catch (IOException e) { }
//    }

    /* Call this from the main activity to shutdown the connection */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
}