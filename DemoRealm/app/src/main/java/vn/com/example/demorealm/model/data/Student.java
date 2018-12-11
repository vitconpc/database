package vn.com.example.demorealm.model.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Student extends RealmObject implements Parcelable {
    @PrimaryKey
    private String studentId;
    private String name;
    private int age;
    private String classRoom;
    private String course;
    private String email;
    private String numberphone;

    public Student() {
    }

    public Student(String name, int age, String classRoom, String course, String email, String numberphone) {
        this.name = name;
        this.age = age;
        this.classRoom = classRoom;
        this.course = course;
        this.email = email;
        this.numberphone = numberphone;
    }

    public Student(String name, int age, String studentId, String classRoom, String course, String email, String numberphone) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
        this.classRoom = classRoom;
        this.course = course;
        this.email = email;
        this.numberphone = numberphone;
    }

    protected Student(Parcel in) {
        name = in.readString();
        age = in.readInt();
        studentId = in.readString();
        classRoom = in.readString();
        course = in.readString();
        email = in.readString();
        numberphone = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(studentId);
        dest.writeString(classRoom);
        dest.writeString(course);
        dest.writeString(email);
        dest.writeString(numberphone);
    }
}
