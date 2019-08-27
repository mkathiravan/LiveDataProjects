package net.kathir.myapplication.LiveDataWithLocalDB;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class NamesViewModel extends AndroidViewModel {

    private SQLIteDbHelper  mHelper;
    private MutableLiveData<List<NameModel>> mNames;


    NamesViewModel(Application application)
    {
        super(application);
        mHelper = new SQLIteDbHelper(application);
    }
    public MutableLiveData<List<NameModel>> getNames()
    {
        if(mNames == null)
        {
            mNames = new MutableLiveData<>();
            loadNames();

    }

        return mNames;
    }

    private void loadNames() {
        List<NameModel> newModels = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DbSettings.DBEntry.TABLE, new String[]{DbSettings.DBEntry._ID,DbSettings.DBEntry.COL_FAV_URL,DbSettings.DBEntry.COL_FAV_DATE},null, null, null, null, null);

        while(cursor.moveToNext())
        {
            int idxId = cursor.getColumnIndex(DbSettings.DBEntry._ID);
            int idxUrl = cursor.getColumnIndex(DbSettings.DBEntry.COL_FAV_URL);
            int idxDate = cursor.getColumnIndex(DbSettings.DBEntry.COL_FAV_DATE);

            newModels.add(new NameModel(cursor.getLong(idxId),cursor.getString(idxUrl),cursor.getLong(idxDate)));
        }

        cursor.close();
        db.close();
        mNames.setValue(newModels);

    }

    public void addNames(String name, long date)
    {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbSettings.DBEntry.COL_FAV_URL,name);
        values.put(DbSettings.DBEntry.COL_FAV_DATE,date);

        long id = db.insertWithOnConflict(DbSettings.DBEntry.TABLE,null,values,SQLiteDatabase.CONFLICT_REPLACE);

        db.close();

        List<NameModel> nameItems = mNames.getValue();

        ArrayList<NameModel> clonedNames;
        if(nameItems ==  null)
        {
            clonedNames = new ArrayList<>();
        }else
        {
            clonedNames = new ArrayList<>(nameItems.size());

            for(int i=0; i < nameItems.size(); i++)
            {
                clonedNames.add(new NameModel(nameItems.get(i)));
            }
        }

        NameModel fav = new NameModel(id, name, date);
        clonedNames.add(fav);
        mNames.setValue(clonedNames);

    }

    public void removeName(long id)
    {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(DbSettings.DBEntry.TABLE,DbSettings.DBEntry._ID + " = ?",new String[]{Long.toString(id)});
        db.close();
        List<NameModel> names = mNames.getValue();
        ArrayList<NameModel> clonedNames = new ArrayList<>(names.size());
        for(int i = 0; i < names.size(); i++)
        {
            clonedNames.add(new NameModel(names.get(i)));
        }

        int index = -1;
        for(int i = 0; i < clonedNames.size(); i++)
        {
            NameModel nameModel = clonedNames.get(i);
            if(nameModel.mId == id)
            {
                index = i;
            }
        }

        if(index != -1)
        {
            clonedNames.remove(index);
        }

        mNames.setValue(clonedNames);

    }

}