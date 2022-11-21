package com.nrifaat26.classroommanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private ListView listView;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //toolbar = (Toolbar)findViewById(R.id.too)
        listView = (ListView)findViewById(R.id.lvMain);
        firebaseAuth= FirebaseAuth.getInstance();
        setupListView();

    }

    private void setupListView(){
        String[] title=getResources().getStringArray(R.array.Main);
        String[] description=getResources().getStringArray(R.array.Description);
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,title,description);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:{
                        Intent intent= new Intent(SecondActivity.this,WeekActivity.class);
                        startActivity(intent);
                        break;}

                    case 1:{
                        startActivity(new Intent(SecondActivity.this,SubjectActivity.class));
                        break;}
                    case 2:{
                        startActivity(new Intent(SecondActivity.this,DBActivity.class));
                        break;}

                    case 3:{
                        startActivity(new Intent(SecondActivity.this,EmailActivity.class));
                        break;}

                }
            }
        });
    }

    public class SimpleAdapter extends BaseAdapter{

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;

        public SimpleAdapter(Context context, String[] title , String[] description ){
            mContext=context;
            titleArray=title;
            descriptionArray=description;
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
                view=layoutInflater.inflate(R.layout.single_item_for_list,null);

            }

            title=(TextView)view.findViewById(R.id.tvMain);
            description=(TextView)view.findViewById(R.id.tvDescription);
    imageView=(ImageView)view.findViewById(R.id.ivMain);
    title.setText(titleArray[i]);
    description.setText(descriptionArray[i]);

    if (titleArray[i].equalsIgnoreCase("Schedule")){

        imageView.setImageResource(R.drawable.calender);
    }
    else if(titleArray[i].equalsIgnoreCase("Subjects")){
        imageView.setImageResource(R.drawable.book);
    }
    else if(titleArray[i].equalsIgnoreCase("Room Allocations")){
        imageView.setImageResource(R.drawable.timetable);
    }
    else if(titleArray[i].equalsIgnoreCase("Send Email")){
        imageView.setImageResource(R.drawable.gmail_icon);
    }

            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.DBMenu:{
                startActivity(new Intent(SecondActivity.this,EmailActivity.class));

            }break;

            case R.id.logoutMenu:{
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
            }break;
        }


        return super.onOptionsItemSelected(item);
    }
}

