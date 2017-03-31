/*
 * Copyright (C) 2014 Thalmic Labs Inc.
 * Distributed under the Myo SDK license agreement. See LICENSE.txt for details.
 */

package com.thalmic.android.sample.helloworld;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Arm;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.XDirection;
import com.thalmic.myo.scanner.ScanActivity;

import java.util.Locale;
import java.util.Timer;
import java.util.ArrayList;
import java.util.TimerTask;
import client;
import client.ClientConsole;
import client.User;

public class HelloWorldActivity extends Activity {

    private TextView mLockStateView;
    private TextView mTextView;
    public static Button unlockText;
    public static TextView resultFromServerGUI;
    public static ArrayList<Code> codeArrayList = new ArrayList<Code>();
    public static EditText mes;
    public static Parser mparser = new Parser(0);
    public static LinearLayout l;
    boolean freelock = false;
    boolean send = false;
    private static String host = "10.10.192.77";
    private static int DEFAULT_PORT = 999;
    public static ClientConsole chat;
    public static User user,toUser;
    public static String resultFromServer = "";
    public static Button hear;
    public TextToSpeech t1;
    // Classes that inherit from AbstractDeviceListener can be used to receive events from Myo devices.
    // If you do not override an event, the default behavior is to do nothing.
    private DeviceListener mListener = new AbstractDeviceListener() {

        // onConnect() is called whenever a Myo has been connected.
        @Override
        public void onConnect(Myo myo, long timestamp) {
            // Set the text color of the text view to cyan when a Myo connects.
            mTextView.setTextColor(Color.GREEN);
        }

        // onDisconnect() is called whenever a Myo has been disconnected.
        @Override
        public void onDisconnect(Myo myo, long timestamp) {
            // Set the text color of the text view to red when a Myo disconnects.
            mTextView.setTextColor(Color.RED);
        }

        // onArmSync() is called whenever Myo has recognized a Sync Gesture after someone has put it on their
        // arm. This lets Myo know which arm it's on and which way it's facing.
        @Override
        public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection) {
            mTextView.setText(myo.getArm() == Arm.LEFT ? R.string.arm_left : R.string.arm_right);
        }

        // onArmUnsync() is called whenever Myo has detected that it was moved from a stable position on a person's arm after
        // it recognized the arm. Typically this happens when someone takes Myo off of their arm, but it can also happen
        // when Myo is moved around on the arm.
        @Override
        public void onArmUnsync(Myo myo, long timestamp) {
            mTextView.setText(R.string.hello_world);
        }

        // onUnlock() is called whenever a synced Myo has been unlocked. Under the standard locking
        // policy, that means poses will now be delivered to the listener.
        @Override
        public void onUnlock(Myo myo, long timestamp) {
            mLockStateView.setText(R.string.unlocked);
        }

        // onLock() is called whenever a synced Myo has been locked. Under the standard locking
        // policy, that means poses will no longer be delivered to the listener.
        @Override
        public void onLock(Myo myo, long timestamp) {
            mLockStateView.setText(R.string.locked);
            myo.unlock(Myo.UnlockType.HOLD);
        }

        // onOrientationData() is called whenever a Myo provides its current orientation,
        // represented as a quaternion.
        @Override
        public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
            // Calculate Euler angles (roll, pitch, and yaw) from the quaternion.
        if(freelock==false)
        {
            freelock = true;
            myo.unlock(Myo.UnlockType.HOLD);
        }
        }

        // onPose() is called whenever a Myo provides a new pose.
        @Override
        public void onPose(Myo myo, long timestamp, Pose pose) {
            // Handle the cases of the Pose enumeration, and change the text of the text view
            // based on the pose we receive.
            switch (pose) {
                case UNKNOWN:send=false;
                    mTextView.setText(getString(R.string.hello_world));
                    break;
                case REST:
                case DOUBLE_TAP: send=false;
                     int restTextId = R.string.hello_world;
                    switch (myo.getArm()) {
                        case LEFT:
                            restTextId = R.string.arm_left;
                            break;
                        case RIGHT:
                            restTextId = R.string.arm_right;
                            break;
                    }
                    mTextView.setText("DOUBLE TAP");
                    break;
                case FIST:send=true;
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if(send) { sendMessage(toUser.getUsername(),mes.getText().toString());
                            //   tts("SENT");
                            }
                        }
                    },5*1000);
                    codeArrayList.add(Code.DOT);
                    mTextView.setText(getString(R.string.pose_fist));
                    break;
                case WAVE_IN:send=false;
                    if(mes.getText().length()>0) {
                        mes.setText(mes.getText().toString().substring(0, mes.getText().toString().length() - 1));
                       // tts("Delete");
                        //tts("Delete");
                    }
                    mTextView.setText(getString(R.string.pose_wavein));
                    break;
                case WAVE_OUT:send=false;
                    if(codeArrayList.size()!=0) {
                        codeArrayList.add(Code.END);
                        String s = "";
                        Code[] codes = new Code[codeArrayList.size()];
                        for (int i = 0; i < codeArrayList.size(); i++) {
                            if (codeArrayList.get(i) == Code.DOT) s = s + ".";
                            else s = s + "/";

                            codes[i] = codeArrayList.get(i);
                        }
                        mes.setText(mes.getText() + "" + mparser.morse_tree.getLetter(codes));
                    }
                    codeArrayList.clear();
                    mTextView.setText(getString(R.string.pose_waveout));
                    break;
                case FINGERS_SPREAD:send=false;
                    codeArrayList.add(Code.LINE);
                    mTextView.setText(getString(R.string.pose_fingersspread));
                    break;
            }

            if (pose != Pose.UNKNOWN && pose != Pose.REST) {
                // Tell the Myo to stay unlocked until told otherwise. We do that here so you can
                // hold the poses without the Myo becoming locked.
                myo.unlock(Myo.UnlockType.HOLD);

                // Notify the Myo that the pose has resulted in an action, in this case changing
                // the text on the screen. The Myo will vibrate.
                myo.notifyUserAction();
            } else {
                // Tell the Myo to stay unlocked only for a short period. This allows the Myo to
                // stay unlocked while poses are being performed, but lock after inactivity.
                myo.unlock(Myo.UnlockType.HOLD);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        unlockText = (Button) findViewById(R.id.button4);
        unlockText.setVisibility(View.INVISIBLE);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    t1.setLanguage(Locale.US);
                }
            }
        });
        toUser = new User("2");//toUser 1 is on computer
        user = new User("1"); // this is user 2 on the phone...

        chat = new ClientConsole(host, DEFAULT_PORT);
        mLockStateView = (TextView) findViewById(R.id.lock_state);
        mTextView = (TextView) findViewById(R.id.text);
        mes = (EditText) findViewById(R.id.editText2);
        hear = (Button) findViewById(R.id.button);
        Button checker = (Button) findViewById(R.id.button2);
        checker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(toUser.getUsername(),mes.getText().toString());
            }
        });
        unlockText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlockText.setVisibility(View.INVISIBLE);
                resultFromServerGUI.setVisibility(View.VISIBLE);
            }

        });
        hear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultFromServer!="")
                {
                    vib(resultFromServer);
                }
            }
        });
                resultFromServerGUI = (TextView) findViewById(R.id.res);
        l = (LinearLayout) findViewById(R.id.l);
        // First, we initialize the Hub singleton with an application identifier.
        Hub hub = Hub.getInstance();
        if (!hub.init(this, getPackageName())) {
            // We can't do anything with the Myo device if the Hub can't be initialized, so exit.
            Toast.makeText(this, "Couldn't initialize Hub", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Next, register for DeviceListener callbacks.
        hub.addListener(mListener);

    }

    @Override
    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // We don't want any callbacks when the Activity is gone, so unregister the listener.
        Hub.getInstance().removeListener(mListener);

        if (isFinishing()) {
            // The Activity is finishing, so shutdown the Hub. This will disconnect from the Myo.
            Hub.getInstance().shutdown();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (R.id.action_scan == id) {
            onScanActionSelected();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onScanActionSelected() {
        // Launch the ScanActivity to scan for Myos to connect to.
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    private void vib(String word)
    {
        for(int i=0; i<word.length(); i++)
        {
            vibeLetter(word.charAt(i));
            SystemClock.sleep(700);
        }
    }

    private void vibeLetter(char c) {
        Vibrator vi = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        TextToMorse ttm = new TextToMorse(c);

        ArrayList<Code> mors = ttm.getMorseCode();

        for(int i=0;i<mors.size();++i){

            if(mors.get(i) == Code.DOT){

                if (vi.hasVibrator()) {

                    Log.v("Can Vibrate", ". ");

                    vi.vibrate(200);

                } else {

                    Log.v("Can Vibrate", "NO");

                }

            }else{

                if(mors.get(i) == Code.LINE){

                    if (vi.hasVibrator()) {

                        Log.v("Can Vibrate", "_ ");

                        vi.vibrate(500);

                    } else {

                        Log.v("Can Vibrate", "NO");

                    }

                }



            }

            SystemClock.sleep(700);

        }


    }
    public void sendMessage(String toUser, String msg) {
        chat.sendToServer(toUser, msg);
    }
}
