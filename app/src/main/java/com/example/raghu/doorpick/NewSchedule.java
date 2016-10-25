package com.example.raghu.doorpick;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
//import com.google.android.gms.location.places.Place;
//import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
//import com.google.android.gms.location.places.ui.PlaceSelectionListener;
//
//import com.example.android.common.activities.SampleActivityBase;
//import com.example.android.common.logger.Log;

public class NewSchedule extends AppCompatActivity {

    private EditText pName;
    private EditText pDesc;
    public static EditText pDate;
    public static EditText pTime;
    private EditText pLoc;
    int PLACE_PICKER_REQUEST = 1;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule);

        pName = (EditText) findViewById(R.id.input_name);
        pDesc = (EditText) findViewById(R.id.input_details);
        pDate = (EditText) findViewById(R.id.input_date);
        pTime = (EditText) findViewById(R.id.input_time);
        button = (Button) findViewById(R.id.btn_schedule);
        pDate.setFocusable(false);
        pDate.setClickable(true);
        pTime.setFocusable(false);
        pTime.setClickable(true);

        pLoc = (EditText) findViewById(R.id.input_loc);
        pLoc.setFocusable(false);
        pLoc.setClickable(true);

        pLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locChange();
            }
        });

        pDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateChange();
            }
        });

        pTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeChange();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmSchedule();
            }
        });

    }

    private void locChange(){

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        }catch (Exception e){
            System.out.print(e.getCause());
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                pLoc.setText(place.getAddress().toString());
            }
        }
    }

    private void dateChange(){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void timeChange(){
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private void confirmSchedule(){

    }


}


