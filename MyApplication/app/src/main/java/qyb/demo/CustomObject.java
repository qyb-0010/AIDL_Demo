package qyb.demo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qiaoyubo on 2015/5/20.
 */
public class CustomObject implements Parcelable {

    public String value;
    public boolean isOK;

    public CustomObject(String value, boolean isOK) {
        this.value = value;
        this.isOK = isOK;
    }

    public CustomObject(Parcel parcel) {
        this.value = parcel.readString();
        this.isOK = parcel.readInt() == 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
        dest.writeInt(isOK ? 1 : 0);
    }

    public static final Parcelable.Creator<CustomObject> CREATOR = new Creator<CustomObject>() {
        @Override
        public CustomObject createFromParcel(Parcel source) {
            return new CustomObject(source);
        }

        @Override
        public CustomObject[] newArray(int size) {
            return new CustomObject[size];
        }
    };

    @Override
    public String toString() {
        return "CustomObject{" +
                "value='" + value + '\'' +
                ", isOK=" + isOK +
                '}';
    }
}
