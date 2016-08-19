package cn.itcast.ueing;//把数据库中的数据显示到listview中

import java.util.ArrayList;
import java.util.List;

import cn.itcast.ueing.MyOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	private MyOpenHelper myOpenHelper;
	private List<Person> lists;
	private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
 myOpenHelper = new MyOpenHelper(getApplicationContext());
    lists = new ArrayList<Person>();
    
        
    }
    public void click1(View v){
    	SQLiteDatabase db = myOpenHelper.getWritableDatabase();
    	
    	ContentValues values=new ContentValues();
    	values.put("name", "祝云");
    	values.put("money", "263654");
		//db.execSQL("insert into info(_id,name,phone) values(?,?,?)", new Object[]{2,"张三","1343454544"});
    	long insert = db.insert("info", null, values);
    	db.close();
    	if (insert>0) {
    		Toast.makeText(getApplicationContext(), "插入成功，返回值是："+insert, 1).show();
    		
			
		}
    	else {
    		Toast.makeText(getApplicationContext(), "插入失败", 1).show();
		}
    	
    }
 public void click2(View v){
	 SQLiteDatabase db = myOpenHelper.getWritableDatabase();
 	//db.execSQL("delete from info where name =? ",new Object[]{"张三"});
	 int delete = db.delete("info", "name=?", new String[]{"王武"});
 	db.close();
 	Toast.makeText(getApplicationContext(), "共删除了"+delete+"行", 1).show();
    	
    }
 public void click3(View v){
 	
	 SQLiteDatabase db = myOpenHelper.getWritableDatabase();
	 ContentValues values=new ContentValues();
 	//db.execSQL("update info set phone=? where name=?",new Object[]{"124455555","张三"});
	 values.put("money", "26345");
	 int update = db.update("info", values, "name=?", new String[]{"王武"});
 	db.close();
 	Toast.makeText(getApplicationContext(), "共更新了"+update+"行", 1).show();
 }
 public void click4(View v){
	 SQLiteDatabase db = myOpenHelper.getWritableDatabase();
	 Cursor cursor = db.rawQuery("select *from info", null);
	 //用query时当要查询所有的时在columns部分写null1就行，如果要查指定的多行用数组
	 //Cursor cursor = db.query("info", new String[]{"name","phone"}, "name=?", new String[]{"王武"}, null, null, null);
	 if (cursor!=null&&cursor.getCount()>0) {//cursor里面有记录再去取
		 while (cursor.moveToNext()) {//cursor.movetonext的返回值是bool,所以这句话的意思是如果当前cursor有下一个值
			String name = cursor.getString(1);
			String money = cursor.getString(2);
			//System.out.println("name是"+name+"===="+phone);
			Person person=new Person();
			person.setName(name);
			person.setMoney(money);
			lists.add(person);
			
		}
		 lv.setAdapter(new myadaper());
	}
	Toast.makeText(getApplicationContext(), "正在查询。。。", 1).show();
 }
 private class myadaper extends BaseAdapter{

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		if(convertView==null){
			v=View.inflate(getApplicationContext(), R.layout.item, null);
		}
		else{
			v=convertView;
		}
		TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
		TextView tv_money = (TextView) v.findViewById(R.id.tv_money);
		Person person = lists.get(position);
		tv_name.setText(person.getName());
		tv_money.setText(person.getMoney());
		System.out.println("adapter正在运行"+person.getName()+"的余额是："+person.getMoney());
		return v;
	}
	 
 }

}
