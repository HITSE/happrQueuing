package com.example.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListShow extends Activity {

	public String rid = "";

	List<Restaurant> restaurant;
	private ListView list;
	private TextView mTextView1;
	private Button mButton;
	private Button mButton7;
	List<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
	private SimpleAdapter adapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = (ListView) findViewById(R.id.MyListView);
		mTextView1 = (TextView) findViewById(R.id.textView1);
		mButton = (Button) findViewById(R.id.button2);
		mButton7 = (Button) findViewById(R.id.button7);

		try {
			updateListView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				rid = restaurant.get(position).getRid();
				Message.rid = rid;
				// 选择餐厅啦！！！！！！！！！！！！！！！！！！！
				String path = Message.rootPath + "m/show?id="
						+ Message.rid;
				try {
					Network.postit(path);
				} catch (Exception e) {
					// TODOAuto-generated catch block
					e.printStackTrace();
				}

				Intent intent = new Intent();
				intent.setClass(ListShow.this, DetailShow.class);
				startActivity(intent);
			}
		});

		mButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				try {
					int size = mylist.size();
					while (size > 0) {
						mylist.remove(size - 1);
						size--;
					}
					adapter.notifyDataSetChanged();
					updateListView();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		mButton7.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ListShow.this, SignUp.class);
				startActivity(intent);
			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Exit();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void updateListView() throws Exception {
		// 获得所有餐厅！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
		String path = Message.rootPath + "m/list";

		try {

			restaurant = Network.getJOSN1(path);

			for (int i = 0; i < restaurant.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("itemTitle", restaurant.get(i).getName());
				map.put("itemText", "排了"+restaurant.get(i).getNum().toString()
						+"人了,大约要等"+restaurant.get(i).getTime());
				mylist.add(map);
			}
			/*
			 * String it[] = { "永福小吃", "吴记酱骨", "王吉小吃", "李记酱骨" }; String a[] = {
			 * "4", "5", "13", "8" }; for(int i = 0;i<4;i++){ HashMap<String,
			 * String> map = new HashMap<String, String>();
			 * map.put("itemTitle",it[i]); map.put("itemText", a[i]);
			 * mylist.add(map); }
			 */

			adapter = new SimpleAdapter(this, mylist, R.layout.myitem,
					new String[] { "itemTitle", "itemText" }, new int[] {
							R.id.itemTitle, R.id.itemText });
			list.setAdapter(adapter);
		} catch (Exception e) {

		}
	}

	private void Exit() {
		final AlertDialog.Builder exit = new AlertDialog.Builder(ListShow.this);
		exit.setTitle(R.string.app_name);
		exit.setMessage(R.string.reallyexit);
		exit.setPositiveButton(R.string.yes,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						System.exit(0);
					}
				});
		exit.setNegativeButton(R.string.no,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						exit.setCancelable(true);
					}
				}).create().show();
	}

}
