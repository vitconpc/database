package vn.com.example.demorealm.common;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Database {
    private static Realm realm;

    public static Realm getRealm(Context context){
        if (realm == null){
            Realm.init(context);
            RealmConfiguration configuration = new RealmConfiguration.Builder()
                    .name(Constants.NAME_DATABASE).build();
            realm = Realm.getInstance(configuration);
        }
        return realm;
    }
}
