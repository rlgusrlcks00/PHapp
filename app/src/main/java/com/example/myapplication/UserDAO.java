package com.example.myapplication;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.lifecycle.LiveData;


@Dao
public interface UserDAO {

    @Insert
    void setInsertUser(User user);

    @Update
    void setUpdateUser(User user);

    @Delete
    void setDeleteUser(User user);

    @Query("Select * from User")
    List<User> getUserAll();

    // 이메일을 기준으로 사용자를 검색하는 메서드 추가
    @Query("Select * from User where email = :email")
    User getUserByEmail(String email);

    //이메일이 중복되는지 검사하는 메소드
    @Query("SELECT * FROM User WHERE email = :email")
    User findByEmail(String email);

    @Query("SELECT * FROM User WHERE User_id = :userId")
    User getUserById(int userId);

    @Query("SELECT * FROM User WHERE User_id = :userId")
    LiveData<User> getUserByIdL(int userId);

    @Query("UPDATE User SET muscle_mass = :muscleMass, body_fat_percentage = :bodyFat WHERE email = :email")
    int updateInbodyInfo(String email, String muscleMass, String bodyFat);

}
