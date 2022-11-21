package com.nrifaat26.classroommanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import com.nrifaat26.classroommanager.Utils.LetterImageView;

public class DayDetail extends AppCompatActivity {

    private ListView listView;
    public static String[] A_Day;
    public static String[] B_Day;
    public static String[] C_Day;
    public static String[] D_Day;
    public static String[] E_Day;

    public static String[] Time1;
    public static String[] Time2;
    public static String[] Time3;
    public static String[] Time4;
    public static String[] Time5;

    public static String[] RoomsA;
    public static String[] RoomsB;
    public static String[] RoomsC;
    public static String[] RoomsD;
    public static String[] RoomsE;

    private String[] PreferredDay;
    private String[] PreferredTime;
    private String[] PreferredRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);

        setupUIViews();

        setupListView();
    }

    private void setupUIViews(){
        listView = (ListView)findViewById(R.id.lvDayDetail);

    }



    private void setupListView(){

        A_Day = getResources().getStringArray(R.array.A_Day);
        B_Day = getResources().getStringArray(R.array.B_Day);
        C_Day = getResources().getStringArray(R.array.C_Day);
        D_Day = getResources().getStringArray(R.array.D_Day);
        E_Day = getResources().getStringArray(R.array.E_Day);


        Time1 = getResources().getStringArray(R.array.time1);
        Time2 = getResources().getStringArray(R.array.time2);
        Time3 = getResources().getStringArray(R.array.time3);
        Time4 = getResources().getStringArray(R.array.time4);
        Time5 = getResources().getStringArray(R.array.time5);

        RoomsA = getResources().getStringArray(R.array.RoomsA);
        RoomsB = getResources().getStringArray(R.array.RoomsB);
        RoomsC = getResources().getStringArray(R.array.RoomsC);
        RoomsD = getResources().getStringArray(R.array.RoomsD);
        RoomsE = getResources().getStringArray(R.array.RoomsE);


        String selected_day = WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY, null);

        if(selected_day.equalsIgnoreCase("A Day")){
            PreferredDay = A_Day;
            PreferredTime = Time1;
            PreferredRoom=RoomsA;
        }else if(selected_day.equalsIgnoreCase("B Day")){
            PreferredDay = B_Day;
            PreferredTime = Time2;
            PreferredRoom=RoomsB;
        }else if(selected_day.equalsIgnoreCase("C Day")){
            PreferredDay = C_Day;
            PreferredTime = Time3;
            PreferredRoom=RoomsC;
        }else if(selected_day.equalsIgnoreCase("D Day")){
            PreferredDay = D_Day;
            PreferredTime = Time4;
            PreferredRoom=RoomsD;

        }else if(selected_day.equalsIgnoreCase("E Day")){
            PreferredDay = E_Day;
            PreferredTime = Time5;
            PreferredRoom=RoomsE;

        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(DayDetail.this, PreferredDay, PreferredTime,PreferredRoom);
        listView.setAdapter(simpleAdapter);

    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView subject, time,room;
        private String[] subjectArray;
        private String[] timeArray;
        private String[] roomArray;
        private LetterImageView letterImageView;

        public SimpleAdapter(Context context, String[] subjectArray, String[] timeArray,String[] roomArray){
            mContext = context;
            this.subjectArray = subjectArray;
            this.timeArray = timeArray;
            this.roomArray=roomArray;
            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return subjectArray.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.day_detail_single_item, null);
            }

            subject = (TextView)convertView.findViewById(R.id.tvSubjectDayDetails);
            time = (TextView)convertView.findViewById(R.id.tvTimeDayDetail);
            room=(TextView)convertView.findViewById(R.id.tvRoomDetail);

            letterImageView = (LetterImageView)convertView.findViewById(R.id.ivDayDetails);

            subject.setText(subjectArray[position]);
            time.setText(timeArray[position]);
            room.setText(roomArray[position]);

            letterImageView.setOval(true);
            letterImageView.setLetter(subjectArray[position].charAt(0));

            return convertView;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}