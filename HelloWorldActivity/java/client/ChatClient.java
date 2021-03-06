// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package com.example.almog.client;
import android.util.Log;
import android.view.View;

import com.thalmic.android.sample.helloworld.HelloWorldActivity;

import java.io.*;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI;
  String myusername = "ido";
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    try {

      Log.d("Superman", "2)after Super ON ChatClient");
      HelloWorldActivity.unlockText.setVisibility(View.INVISIBLE);
      this.clientUI = clientUI;
      openConnection();
      Log.d("Superman", "after OpenConnection ON ChatClient");
    }catch(Exception e)
    {
      Log.d("Superman","IN ChatClient " + e.toString());
    }
  }

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */

  public void handleMessageFromServer(Object msg)
  {
	  String theMSG = (String)msg;
    Log.d("Superman","Message came from server "+ theMSG + "   user.getusername = "+ HelloWorldActivity.user.getUsername());
	if((theMSG.split(",")[0]).equals(HelloWorldActivity.user.getUsername()))//if its to us
    {
      HelloWorldActivity.resultFromServer = theMSG.substring(2);

      HelloWorldActivity.resultFromServerGUI.setText(theMSG.substring(2));

      HelloWorldActivity.resultFromServerGUI.setVisibility(View.INVISIBLE);
      HelloWorldActivity.unlockText.setVisibility(View.VISIBLE);
      HelloWorldActivity.l.invalidate();

      //tts("RECIVED MESSAGE");
      //HelloWorldActivity.recived.append("\n"+(theMSG.split(","))[1]);
      try {
        openConnection();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    //MainActivity.gridLayout.removeAllViews();
   //     MainActivity.gridLayout.addView(MainActivity.recived);
       //MainActivity.gridLayout.postInvalidate();


  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
    try
    {
    	sendToServer(message);
    }
    catch(IOException e)
    {
     if(!isConnected())
       try {
         openConnection();
       } catch (IOException e1) {
         e1.printStackTrace();
       }
    }
  }
  
  /**-
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
