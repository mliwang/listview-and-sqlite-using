package cn.itcast.ueing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

	public MyOpenHelper(Context context) {
super(context, "Acount.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table info(_id  integer primary key autoincrement,name varchar(20),money varchar(20))");
		db.execSQL("insert into info('name','money') values('张三','5000')");
		db.execSQL("insert into info('name','money') values('李斯','3000')");
		db.execSQL("insert into info('name','money') values('王武','2000')");
		db.execSQL("insert into info('name','money') values('从回','2100')");

	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
