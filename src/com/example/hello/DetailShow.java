package com.example.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class DetailShow extends Activity {

	private Button button1;
	private Button button3;
	private Button button6;
	private ListView list;
	List<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
	private SimpleAdapter adapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page2);
		button1 = (Button) findViewById(R.id.button1);
		list = (ListView) findViewById(R.id.MyListView);
		button3 = (Button) findViewById(R.id.button3);
		button6 = (Button) findViewById(R.id.button6);

		try {
			updateListView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		button1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				showDialog1();
			}
		});

		button3.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		button6.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				update();
			}
		});

	}

	private void updateListView() throws Exception {
		// 获得一个餐厅的详细信息！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！

		String path = Message.rootPath + "m/show?id=" + Message.rid;

		try {
			List<RestaurantDetail> persons = Network.getJOSN2(path);
			String itemTitle[] = { "餐厅名称", "排队人数", "预计时间", "餐厅地址", "餐厅电话",
					"餐厅特色" };

			for (int i = 0; i < 6; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("itemTitle2", itemTitle[i]);
				map.put("itemText2", persons.get(0).getMessage(i));
				mylist.add(map);
			}

			/*String it[] = { "吴记酱骨", "4", "10min", "南岗区西大直街55号", "13612345678",
					"非常好吃" };

			for (int i = 0; i < 6; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("itemTitle2", itemTitle[i]);
				map.put("itemText2", it[i]);
				mylist.add(map);
			}*/

			adapter = new SimpleAdapter(this, mylist, R.layout.myitem2,
					new String[] { "itemTitle2", "itemText2" }, new int[] {
							R.id.itemTitle2, R.id.itemText2 });
			list.setAdapter(adapter);
		} catch (Exception e) {

		}

	}

	private void showDialog1() {

		final EditText input1;
		final EditText input2;	
		
		View view = getLayoutInflater().inflate(R.layout.dialog1,
				(ViewGroup) findViewById(R.id.mdialog));
		input1 = (EditText) view.findViewById(R.id.editText1);
		input2 = (EditText) view.findViewById(R.id.editText2);
		
		if(Message.answer == 1){
			input1.append(Message.phone);
		}
		
		final AlertDialog.Builder dialog1 = new AlertDialog.Builder(
				DetailShow.this);
		dialog1.setView(view);
		dialog1.setTitle(R.string.app_name)
				.setPositiveButton(R.string.send,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface arg0, int arg1) {

								String phone_num = "";
								String num_peo = "";
								phone_num = input1.getEditableText().toString();
								num_peo = input2.getEditableText().toString();
								// 发送信息在这里！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
								String path = Message.rootPath
										+ "m/add?phone=" + phone_num
										+ "&num=" + num_peo + "&rid="
										+ Message.rid;
								try {
									Network.postit(path);
									update();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface arg0, int arg1) {
								dialog1.setCancelable(true);
								update();
							}
						}).create().show();

	}

	private void update() {
		int size = mylist.size();
		while (size > 0) {
			mylist.remove(size - 1);
			size--;
		}
		adapter.notifyDataSetChanged();
		try {
			updateListView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
