package com.nrifaat26.classroommanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SubjectDetailsActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);
        setUIViews();
        setupListView();
    }
    private void setUIViews(){
        listView = (ListView)findViewById(R.id.lvSubjectDetails);

    }

    private void setupListView(){
        String subject_selected = SubjectActivity.subjectPreferences.getString(SubjectActivity.SUB_PREF,null);
        String[] syllabus= new String[]{};
        String[] titles= getResources().getStringArray(R.array.titles);
        if(subject_selected.equalsIgnoreCase("Programming_Fundamentals")){
            syllabus= getResources().getStringArray(R.array.Programming_Fundamentals);
        }
        else if(subject_selected.equalsIgnoreCase("Data_Structure")){
            syllabus= getResources().getStringArray(R.array.Data_Structure);
        }
        else if(subject_selected.equalsIgnoreCase("Object_Oriented_Programming")){
            syllabus= getResources().getStringArray(R.array.Object_Oriented_Programming);
        }

        SubjectDetailAdapter subjectDetailAdapter = new SubjectDetailAdapter(this, titles,syllabus);
        listView.setAdapter(subjectDetailAdapter);
    }
    public class SubjectDetailAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, syllabus;
        private String[] titleArray;
        private String[] syllabusArray;
       // private ImageView imageView;

        public SubjectDetailAdapter(Context context, String[] title , String[] syllabus ){
            mContext=context;
            titleArray=title;
            syllabusArray=syllabus;
            layoutInflater=LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int i) {
            return titleArray[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=layoutInflater.inflate(R.layout.subject_detail_single_item,null);

            }

            title=(TextView)view.findViewById(R.id.tvSubjectTitle);
            syllabus=(TextView)view.findViewById(R.id.tvSyllabus);
            //imageView=(ImageView)view.findViewById(R.id.ivMain);
            title.setText(titleArray[i]);
            syllabus.setText(syllabusArray[i]);



            return view;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
            }

        }
        return super.onOptionsItemSelected(item);
    }


}
