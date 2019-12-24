package ro.atelieruldigital.news.model.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IBaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertObject(T object);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertObjects(T... objects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertObjects(List<T> objects);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateObject(T object);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateObjects(T... objects);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateObjects(List<T> objects);

    @Delete
    void deleteObject(T object);

    @Delete
    void deleteObjects(T... objects);

    @Delete
    void deleteObjects(List<T> objects);
}
