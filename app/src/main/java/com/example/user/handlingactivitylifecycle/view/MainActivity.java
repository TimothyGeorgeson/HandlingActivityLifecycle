package com.example.user.handlingactivitylifecycle.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.handlingactivitylifecycle.Constants;
import com.example.user.handlingactivitylifecycle.R;
import com.example.user.handlingactivitylifecycle.model.Person;
import com.example.user.handlingactivitylifecycle.model.PersonParcel;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private TextView tvMain;
    private EditText etMain;
    private EditText etPersonName;
    private EditText etPersonAge;
    private EditText etPersonGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");
        bindViews();

    }

    private void bindViews() {
        tvMain = findViewById(R.id.tvMain);
        etMain = findViewById(R.id.etMain);

        //bind views for person
        etPersonName = findViewById(R.id.etPersonName);
        etPersonAge = findViewById(R.id.etPersonAge);
        etPersonGender = findViewById(R.id.etPersonGender);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    //saving the state of the activity
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");

        String tvText = tvMain.getText().toString();
        outState.putString(Constants.KEYS.TV_MAIN, tvText);
    }

    //restore the state of the activity
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
        String tvText = savedInstanceState.getString(Constants.KEYS.TV_MAIN);
        tvMain.setText(tvText);
    }

    public void onUpdateTextView(View view) {
        //set the value of the edittext to the textview
        tvMain.setText(etMain.getText().toString());
    }

    public void OnStartSecond(View view) {

        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        //add data to the intent for the second activity
        intent.putExtra(Constants.KEYS.ET_MAIN, etMain.getText().toString());
        startActivity(intent);
    }

    public void onSendingPerson(View view) {
        //get person values from the edittext
        String personName = etPersonName.getText().toString();
        String personAge = etPersonAge.getText().toString();
        String personGender = etPersonGender.getText().toString();

        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

        switch (view.getId())
        {
            case R.id.btnPersonSerializable:
                //create person object
                Person person = new Person(personName, personAge, personGender);
                intent.putExtra(Constants.KEYS.PERSON_TYPE, "serializable");
                intent.putExtra(Constants.KEYS.Person, person);
                break;
            case R.id.btnPersonParcelable:
                PersonParcel personParcel = new PersonParcel(personName, personAge, personGender);
                intent.putExtra(Constants.KEYS.PERSON_TYPE, "parcelable");
                intent.putExtra(Constants.KEYS.Person, personParcel);
                break;
        }

        startActivity(intent);
    }
}
