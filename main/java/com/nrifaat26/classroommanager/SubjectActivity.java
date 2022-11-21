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

public class SubjectActivity extends AppCompatActivity {


    private ListView listView;
    private String[] subjects;
    public static String SUB_PREF;
    public static SharedPreferences subjectPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        setUIViews();
        setupListView();
    }
    private void setUIViews(){
        listView = (ListView)findViewById(R.id.lvSubject);
        subjectPreferences = getSharedPreferences("Subjects", MODE_PRIVATE);

    }

    private void setupListView()
    {
        subjects= getResources().getStringArray(R.array.Subjects);
        SubjectAdapter subjectAdapter= new SubjectAdapter(this,R.layout.subject_single_item,subjects);
listView.setAdapter(subjectAdapter);

listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch(position){
            case 0:
                subjectPreferences.edit().putString(SUB_PREF,"Programming_Fundamentals").apply();
                startActivity(new Intent(SubjectActivity.this,SubjectDetailsActivity.class));
                break;
            case 1:subjectPreferences.edit().putString(SUB_PREF,"Data_Structure").apply();
                startActivity(new Intent(SubjectActivity.this,SubjectDetailsActivity.class));
                break;
            case 2:
                subjectPreferences.edit().putString(SUB_PREF,"Object_Oriented_Programming").apply();
                startActivity(new Intent(SubjectActivity.this,SubjectDetailsActivity.class));

                break;
        }
    }
});
    }

    public class SubjectAdapter extends ArrayAdapter{
        private int resource;
        private LayoutInflater layoutInflater;
        private String[] week = new String[]{};
        private TextView tvSubject;


        public SubjectAdapter(Context context, int resource, String[] objects) {
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
                holder.ivLogo= (LetterImageView)convertView.findViewById(R.id.ivLetterSubject);
                //holder.tvWeek=(TextView)convertView.findViewById(R.id.tvMain);
                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(subjects[position].charAt(0));
            tvSubject=(TextView)convertView.findViewById(R.id.tvSubject);
            tvSubject.setText(subjects[position]);

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
