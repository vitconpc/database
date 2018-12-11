package vn.com.example.demosqlite.control;

import android.os.AsyncTask;

import java.util.List;

import vn.com.example.demosqlite.model.callback.GetDataCallback;
import vn.com.example.demosqlite.model.data.Student;

public class GetDataAsyntask extends AsyncTask<Void,Void,List<Student>> {

    private DatabaseHelper databaseHelper;
    private GetDataCallback getDataCallback;

    public GetDataAsyntask(DatabaseHelper databaseHelper, GetDataCallback getDataCallback) {
        this.databaseHelper = databaseHelper;
        this.getDataCallback = getDataCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Student> doInBackground(Void... voids) {
        return databaseHelper.getAllStudent();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Student> students) {
        super.onPostExecute(students);
        getDataCallback.onGetDataSuccess(students);
    }
}
