package cn.itcast.ueing;//�����ݿ��е�������ʾ��listview��

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
    	values.put("name", "ף��");
    	values.put("money", "263654");
		//db.execSQL("insert into info(_id,name,phone) values(?,?,?)", new Object[]{2,"����","1343454544"});
    	long insert = db.insert("info", null, values);
    	db.close();
    	if (insert>0) {
    		Toast.makeText(getApplicationContext(), "����ɹ�������ֵ�ǣ�"+insert, 1).show();
    		
			
		}
    	else {
    		Toast.makeText(getApplicationContext(), "����ʧ��", 1).show();
		}
    	
    }
 public void click2(View v){
	 SQLiteDatabase db = myOpenHelper.getWritableDatabase();
 	//db.execSQL("delete from info where name =? ",new Object[]{"����"});
	 int delete = db.delete("info", "name=?", new String[]{"����"});
 	db.close();
 	Toast.makeText(getApplicationContext(), "��ɾ����"+delete+"��", 1).show();
    	
    }
 public void click3(View v){
 	
	 SQLiteDatabase db = myOpenHelper.getWritableDatabase();
	 ContentValues values=new ContentValues();
 	//db.execSQL("update info set phone=? where name=?",new Object[]{"124455555","����"});
	 values.put("money", "26345");
	 int update = db.update("info", values, "name=?", new String[]{"����"});
 	db.close();
 	Toast.makeText(getApplicationContext(), "��������"+update+"��", 1).show();
 }
 public void click4(View v){
	 SQLiteDatabase db = myOpenHelper.getWritableDatabase();
	 Cursor cursor = db.rawQuery("select *from info", null);
	 //��queryʱ��Ҫ��ѯ���е�ʱ��columns����дnull1���У����Ҫ��ָ���Ķ���������
	 //Cursor cursor = db.query("info", new String[]{"name","phone"}, "name=?", new String[]{"����"}, null, null, null);
	 if (cursor!=null&&cursor.getCount()>0) {//cursor�����м�¼��ȥȡ
		 while (cursor.moveToNext()) {//cursor.movetonext�ķ���ֵ��bool,������仰����˼�������ǰcursor����һ��ֵ
			String name = cursor.getString(1);
			String money = cursor.getString(2);
			//System.out.println("name��"+name+"===="+phone);
			Person person=new Person();
			person.setName(name);
			person.setMoney(money);
			lists.add(person);
			
		}
		 lv.setAdapter(new myadaper());
	}
	Toast.makeText(getApplicationContext(), "���ڲ�ѯ������", 1).show();
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
		System.out.println("adapter��������"+person.getName()+"������ǣ�"+person.getMoney());
		return v;
	}
	 
 }

}
