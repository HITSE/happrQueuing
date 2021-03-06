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

public class ListShow extends Activity {

	public String rid = "";

	List<Restaurant> restaurant;
	private ListView list;
	private Button refresh;
	private Button signIn;
	List<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
	private SimpleAdapter adapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page1);
		list = (ListView) findViewById(R.id.MyListView);
		refresh = (Button) findViewById(R.id.button2);
		signIn = (Button) findViewById(R.id.button7);

		try {
			updateListView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		list.setOnItemClickListener(new OnItemClickListener() {
			//单击某个餐厅条目后跳转到该餐厅详细信息界面
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				rid = restaurant.get(position).getRid();
				Message.rid = rid;
				// 选择餐厅啦
				String path = Message.rootPath + "m/show?id=" + Message.rid;
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

		refresh.setOnClickListener(new Button.OnClickListener() {
			//刷新界面
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

		signIn.setOnClickListener(new Button.OnClickListener() {
			//跳转到登陆界面
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ListShow.this, SignUp.class);
				startActivity(intent);
			}
		});

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//判断是否按下了回退键，若是则弹出对话框确认是否退出
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Exit();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void updateListView() throws Exception {
		// 获得所有餐厅信息
		String path = Message.rootPath + "m/list";

		try {

			restaurant = Network.getJOSN1(path);

			for (int i = 0; i < restaurant.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("itemTitle", restaurant.get(i).getName());
				map.put("itemText", "排了"
						+ restaurant.get(i).getNum().toString() + "人了,大约要等"
						+ restaurant.get(i).getTime());
				mylist.add(map);
			}

			adapter = new SimpleAdapter(this, mylist, R.layout.myitem,
					new String[] { "itemTitle", "itemText" }, new int[] {
							R.id.itemTitle, R.id.itemText });
			list.setAdapter(adapter);
		} catch (Exception e) {

		}
	}

	private void Exit() {
		//退出軟件，弹出对话框
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
