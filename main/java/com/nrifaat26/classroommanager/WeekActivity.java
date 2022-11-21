package com.nrifaat26.classroommanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.nrifaat26.classroommanager.Utils.LetterImageView;

public class WeekActivity extends AppCompatActivity {

    private ListView listView;
    public static SharedPreferences sharedPreferences;
    public static String SEL_DAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        setUIViews();
        setupListView();

    }

    private void setUIViews(){
        listView = (ListView)findViewById(R.id.lvWeek);
        sharedPreferences= getSharedPreferences("My_Day",MODE_PRIVATE);
    }

    private void setupListView(){
        String[] week= getResources().getStringArray(R.array.Week);

        WeekAdapter adapter= new WeekAdapter(this, R.layout.activity_week_single_item,week);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0: {
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "A Day").apply();

                        break;}
                    case 1:{
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY,"B Day").apply();
                        break;}
                    case 2:{
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY,"C Day").apply();
                        break;}
                    case 3:{
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY,"D Day").apply();
                        break;}
                    case 4:{
                        startActivity(new Intent(WeekActivity.this, DayDetail.class));
                        sharedPreferences.edit().putString(SEL_DAY,"E Day").apply();
                        break;}
                    default:break;
                }
            }
        });

    }

    public class WeekAdapter extends ArrayAdapter{
        private int resource;
        private LayoutInflater layoutInflater;
        private String[] week = new String[]{};
        private TextView tvWeek;


        public WeekAdapter(Context context, int resource, String[] objects) {
            super(context, resource,objects);
            this.resource= resource;
            this.week=objects;
            layoutInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            convertView= layoutInflater.inflate(resource,null);
            holder.ivLogo= (LetterImageView)convertView.findViewById(R.id.ivLetter);
            //holder.tvWeek=(TextView)convertView.findViewById(R.id.tvMain);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivLogo.setOval(true);
        holder.ivLogo.setLetter(week[position].charAt(0));
        tvWeek=(TextView)convertView.findViewById(R.id.tvWeek);
        tvWeek.setText(week[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageView ivLogo;

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
