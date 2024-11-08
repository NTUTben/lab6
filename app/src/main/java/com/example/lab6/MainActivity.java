package com.example.lab6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static class Data {
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

        // Step1: 建立資料來源
        Data[] transData = new Data[transNameArray.length];
        for (int i = 0; i < transData.length; i++) {
            transData[i] = new Data();
            transData[i].name = transNameArray[i];
            transData[i].photo = transPhotoIdArray[i];
        }

        // Step2: 建立 MyAdapter 物件，並傳入 transData 和 trans_list.xml
        MyAdapter transAdapter = new MyAdapter(transData, R.layout.trans_list);
        Spinner spinner = findViewById(R.id.spinner);

        // Step3: 設定 Adapter 給 Spinner
        spinner.setAdapter(transAdapter);

        String[] cubeeNameArray = new String[] {"哭哭", "發抖", "暈倒", "生氣", "冒冷汗", "便便", "喝采", "你好", "嗨", "笑笑"};
        int[] cubeePhotoIdArray = new int[]{R.drawable.cubee1, R.drawable.cubee2, R.drawable.cubee3, R.drawable.cubee4,
                R.drawable.cubee5, R.drawable.cubee6, R.drawable.cubee7, R.drawable.cubee8,
                R.drawable.cubee9, R.drawable.cubee10};

        // Step1: 建立資料來源
        Data[] cubeeData = new Data[cubeeNameArray.length];
        for (int i = 0; i < cubeeData.length; i++) {
            cubeeData[i] = new Data();
            cubeeData[i].name = cubeeNameArray[i];
            cubeeData[i].photo = cubeePhotoIdArray[i];
        }

        // Step2: 建立 MyAdapter 物件，並放入 cubeeData 和 cubee_list.xml
        MyAdapter cubeeAdapter = new MyAdapter(cubeeData, R.layout.cubee_list);
        GridView gridView = findViewById(R.id.gridView);

        // Step3: 連接 GridView 元件，並連結 MyAdapter
        gridView.setAdapter(cubeeAdapter);
        gridView.setNumColumns(3);  // 設定 GridView 列數

        // 設置 ListView 的簡單訊息列表
        String[] messageArray = new String[] {"訊息1", "訊息2", "訊息3", "訊息4", "訊息5", "訊息6"};
        ArrayAdapter<String> messageAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, messageArray
        );

        ListView listView = findViewById(R.id.ListView);
        listView.setAdapter(messageAdapter);
    }

    // MyAdapter 類別
    public class MyAdapter extends BaseAdapter {
        private Data[] data; // 保存 MyAdapter 的資料來源
        private int view; // 保存佈局的畫面資源

        public MyAdapter(Data[] data, int view) { // 建構子
            this.data = data;
            this.view = view;
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(view, parent, false);
            }
            TextView name = convertView.findViewById(R.id.name);
            name.setText(data[position].name);

            ImageView imageView = convertView.findViewById(R.id.imageView);
            imageView.setImageResource(data[position].photo);

            return convertView;
        }
    }
}
