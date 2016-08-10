package com.siziksu.explorer.common.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class State implements Parcelable {

    private File directory;
    private List<File> files;

    public State() {}

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.directory);
        dest.writeList(this.files);
    }

    protected State(Parcel in) {
        this.directory = (File) in.readSerializable();
        this.files = new ArrayList<File>();
        in.readList(this.files, File.class.getClassLoader());
    }

    public static final Creator<State> CREATOR = new Creator<State>() {
        @Override
        public State createFromParcel(Parcel source) {return new State(source);}

        @Override
        public State[] newArray(int size) {return new State[size];}
    };
}
