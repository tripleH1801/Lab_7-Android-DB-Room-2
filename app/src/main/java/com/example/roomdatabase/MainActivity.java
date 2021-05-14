package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Adapter1 adt;
    private RecyclerView rec;
    private Database db;
    private UserDao userDao;
    private TextView tvEdit;
    private int idFlat;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rec = findViewById(R.id.rec1);
        tvEdit = findViewById(R.id.tvEdit);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser(new User());
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvEdit.setText("");
                idFlat = 0;
            }
        });

        db = Room.databaseBuilder(getApplicationContext(),
                Database.class, "database-name")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        userDao = db.userDao();

//        this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                userDao.insertAll(new User("Thanh Hóa"));
//                userDao.insertAll(new User("Đà Lạt"));
//                userDao.insertAll(new User("Buôn Mê Thuộc"));
//                userDao.insertAll(new User("Cần Thơ"));
//                userDao.insertAll(new User("Phú Quốc"));
//                userDao.insertAll(new User("Lý Sơn"));
//            }
//        });


//        List<User> list = new ArrayList<>();
//        list = db.userDao().getAll();
//        adt = new Adapter1(list, this);
//        rec.setAdapter(adt);
//        rec.setLayoutManager(new LinearLayoutManager(this));
        loadDataToList();
    }
    public void loadDataToList(){
        List<User> list = new ArrayList<>();
        list = db.userDao().getAll();
        adt = new Adapter1(list, this);
        rec.setAdapter(adt);
        rec.setLayoutManager(new LinearLayoutManager(this));
    }

    public void xoaUser(User u){
        userDao.delete(u);
        loadDataToList();
    }

    public void setEdit(User u){
        tvEdit.setText(u.getName());
        idFlat = u.getUid();
    }

    public void saveUser(User u){
        if(idFlat == 0){
            userDao.insertAll(new User(tvEdit.getText().toString()));
            loadDataToList();
            tvEdit.setText("");
            idFlat = 0;
            return;
        }
        userDao.update(tvEdit.getText().toString(), idFlat);
        loadDataToList();
        tvEdit.setText("");
        idFlat = 0;
    }
}