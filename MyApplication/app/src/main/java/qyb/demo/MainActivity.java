package qyb.demo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import qyb.demo.logger.LogHelper;
import qyb.demo.service.RemoteService;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button mBindBtn;
    private Button mCallBack;
    private Button mChange;
    private Button mStop;

    private IRemoteImpl mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mBindBtn = (Button) findViewById(R.id.btn_bind);
        mCallBack = (Button) findViewById(R.id.btn_callback);
        mChange = (Button) findViewById(R.id.btn_change);
        mStop = (Button) findViewById(R.id.btn_stop);

        mBindBtn.setOnClickListener(this);
        mCallBack.setOnClickListener(this);
        mChange.setOnClickListener(this);
        mStop.setOnClickListener(this);
    }

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = IRemoteImpl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IListener.Stub mListener = new IListener.Stub() {

        @Override
        public void callBack() throws RemoteException {
            LogHelper.d("call back invoked");
        }
    };

    @Override
    public void onClick(View v) {
        if (v == mBindBtn) {
            Intent intent = new Intent(MainActivity.this, RemoteService.class);
            bindService(intent, mConn, BIND_AUTO_CREATE);
        } else if (v == mCallBack){
            if (mBinder != null) {
                try {
                    mBinder.doCallBack(mListener);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else if (v == mChange) {
            if (mBinder != null) {
                try {
                    CustomObject obj = new CustomObject("value before change", false);
                    LogHelper.d("before: " + obj.toString());
                    CustomObject obj2 = mBinder.change(obj);
                    LogHelper.d("after: " + obj2.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else if (v == mStop) {
            if (mBinder != null) {
                try {
                    mBinder.stop();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
