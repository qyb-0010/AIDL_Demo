package qyb.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import qyb.demo.CustomObject;
import qyb.demo.IListener;
import qyb.demo.IRemoteImpl;
import qyb.demo.logger.LogHelper;

/**
 * Created by qiaoyubo on 2015/5/20.
 */
public class RemoteService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        LogHelper.d("onbind");
        return mBinder;
    }

    public IRemoteImpl.Stub mBinder = new IRemoteImpl.Stub() {
        @Override
        public void doCallBack(IListener l) throws RemoteException {
            l.callBack();
        }

        @Override
        public CustomObject change(CustomObject obj) throws RemoteException {
            obj.value = "value after change";
            obj.isOK = true;
            return obj;
        }

        @Override
        public void stop() throws RemoteException {
            stopSelf();
        }
    };

    @Override
    public void onDestroy() {
        LogHelper.d("service destory");
        super.onDestroy();
    }

}
