package com.example.lab6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static class Data { // 設為 static，便於其他類別訪問
        int photo; // 圖片 id
        String name; // 名稱
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] transNameArray = new String[]{"腳踏車", "機車", "汽車", "巴士", "飛機", "輪船"};
        int[] transPhotoIdArray = new int[]{R.drawable.trans1, R.drawable.trans2,
                R.drawable.trans3, R.drawable.trans4,
                R.drawable.trans5, R.drawable.trans6};

        // Step1 建立資料來源
        Data[] transData = new Data[transNameArray.length];
        for (int i = 0; i < transData.length; i++) {
            transData[i] = new Data();
            transData[i].name = transNameArray[i];
            transData[i].photo = transPhotoIdArray[i];
        }

        // Step2 建立 MyAdapter 物件，並傳入 transData 和 trans_list.xml
        MyAdapter transAdapter = new MyAdapter(transData, R.layout.trans_list);
        Spinner spinner = findViewById(R.id.spinner);

        // Step3 設定 Adapter 給 Spinner
        spinner.setAdapter(transAdapter);

        // 設置 ListView 的簡單訊息列表
        String[] messageArray = new String[] { "訊息1", "訊息2", "訊息3", "訊息4", "訊息5", "訊息6" };
        ArrayAdapter<String> messageAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, messageArray
        );

        ListView listView = findViewById(R.id.ListView);
        listView.setAdapter(messageAdapter);
    }

    // MyAdapter 類別
    public class MyAdapter extends BaseAdapter { // 繼承 BaseAdapter
        private Data[] data; // 保存 MyAdapter 的資料來源
        private int view; // 保存佈局的畫面資源

        public MyAdapter(Data[] data, int view) { // 建構子
            this.data = data;
            this.view = view;
        }

        @Override
        public int getCount() { // 傳資料來源的筆數
            return data.length;
        }

        @Override
        public Object getItem(int position) { // 傳資料來源的項目內容
            return data[position];
        }

        @Override
        public long getItemId(int position) { // 傳資料項目 id
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) { // 實現顯示的項目畫面
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(view, parent, false); // 取得 Xml 畫面
            }
            TextView name = convertView.findViewById(R.id.name); // 連結 TextView 元件
            name.setText(data[position].name); // 設定字串

            ImageView imageView = convertView.findViewById(R.id.imageView); // 連結 ImageView 元件
            imageView.setImageResource(data[position].photo); // 設定圖片

            return convertView;
        }
    }
}
