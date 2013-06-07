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

	private Button joinQueue;
	private Button otherRestaurant;
	private Button updateInfo;
	private ListView list;
	List<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
	private SimpleAdapter adapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page2);
		joinQueue = (Button) findViewById(R.id.button1);
		list = (ListView) findViewById(R.id.MyListView);
		otherRestaurant = (Button) findViewById(R.id.button3);
		updateInfo = (Button) findViewById(R.id.button6);

		try {
			updateListView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		joinQueue.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				msgDialog();
			}
		});

		otherRestaurant.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		updateInfo.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				update();
			}
		});

	}

	private void updateListView() throws Exception {
		// ���һ����������ϸ��Ϣ

		String path = Message.rootPath + "m/show?id=" + Message.rid;

		try {
			List<RestaurantDetail> persons = Network.getJOSN2(path);
			String itemTitle[] = { "��������", "�Ŷ�����", "Ԥ��ʱ��", "������ַ", "�����绰",
					"������ɫ" };

			for (int i = 0; i < 6; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("itemTitle2", itemTitle[i]);
				map.put("itemText2", persons.get(0).getMessage(i));
				mylist.add(map);
			}

			adapter = new SimpleAdapter(this, mylist, R.layout.myitem2,
					new String[] { "itemTitle2", "itemText2" }, new int[] {
							R.id.itemTitle2, R.id.itemText2 });
			list.setAdapter(adapter);
		} catch (Exception e) {

		}

	}

	private void msgDialog() {

		final EditText time;
		final EditText numOfCustomer;

		View view = getLayoutInflater().inflate(R.layout.dialog1,
				(ViewGroup) findViewById(R.id.mdialog));
		time = (EditText) view.findViewById(R.id.editText1);
		numOfCustomer = (EditText) view.findViewById(R.id.editText2);

		if (Message.answer == 1) {
			time.append(Message.phone);
		}

		//�ò���Ϣ�Ի���
		final AlertDialog.Builder dialog = new AlertDialog.Builder(
				DetailShow.this);
		dialog.setView(view);
		dialog.setTitle(R.string.app_name)
				.setPositiveButton(R.string.send,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface arg0, int arg1) {

								String phone_num = "";
								String num_peo = "";
								phone_num = time.getEditableText().toString();
								num_peo = numOfCustomer.getEditableText().toString();
								// ������Ϣ������
								String path = Message.rootPath + "m/add?phone="
										+ phone_num + "&num=" + num_peo
										+ "&rid=" + Message.rid;

								replyShow(path);
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface arg0, int arg1) {
								dialog.setCancelable(true);
								update();
							}
						}).create().show();

	}

	private void update() {
		//���²�����Ϣ
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

	private void replyShow(String path) {
		//��ʾ�����ŶӺ�Ľ����Ϣ
		try {
			Network.postit(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final AlertDialog.Builder sucess = new AlertDialog.Builder(
				DetailShow.this);
		sucess.setTitle(R.string.app_name);
		//�ɹ�
		if (Message.answer2 == 1) {
			sucess.setMessage(R.string.sucess3);
		} else if (Message.answer2 == 0) {
			//�Ŷ�ʧ�ܣ��������ࣩ
			sucess.setMessage(R.string.failed3);
		} else if (Message.answer2 == -1) {
			//�Ŷ�ʧ�ܣ��Ѿ��������������Ŷӣ�
			sucess.setMessage(R.string.failed4);
		}
		sucess.setPositiveButton(R.string.OK,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				}).create().show();

		update();
	}

}
