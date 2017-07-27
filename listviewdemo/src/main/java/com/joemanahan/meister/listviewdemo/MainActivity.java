package com.joemanahan.meister.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String[] ITEMS={
      "AKO DOE", "JANE DOE", "BLUE DOE", "BLACK DOE","GREEN DOE","PINK DOE","DOE DOE","SUN DOE","DO DOE",
            "BABA DOE","IBU DOE","MAMA DOE","ANAK DOE","NOM DOE","KOE DOE"
    };

    private static final int[] S3_DRAWABLES= {
      R.drawable.android_s2_bernard,
            R.drawable.android_s2_blackbeard,
            R.drawable.android_s2_bluebot,
            R.drawable.android_s2_chocolatecupcake
    };

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView) findViewById(R.id.list);

        //ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ITEMS);
        //ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.row_item,R.id.imageview,ITEMS);
        ListImageAdapter arrayAdapter=new ListImageAdapter();
        listView.setAdapter(arrayAdapter);

       // listView.setOnItemClickListener(new AdapterView.OnItemClickListener(arrayAdapter).);

    }


    private class ListImageAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return S3_DRAWABLES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item,null);
            ImageView imageView=(ImageView) view.findViewById(R.id.imageview);
            TextView textView=(TextView) view.findViewById(R.id.textview);

            imageView.setImageResource(S3_DRAWABLES[i]);
            textView.setText(ITEMS[i]);
            return view;
        }

        @Override
        public CharSequence[] getAutofillOptions() {
            return new CharSequence[0];
        }
    }
}
