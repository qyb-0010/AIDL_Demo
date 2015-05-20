// IRemoteImpl.aidl
package qyb.demo;
import qyb.demo.IListener;
import qyb.demo.CustomObject;

// Declare any non-default types here with import statements

interface IRemoteImpl {

    void doCallBack(IListener l);

    CustomObject change(in CustomObject obj);

    void stop();
}
