package com.example.hashi.zooproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class AnimalsDetailsActivity extends AppCompatActivity {

    private TextView tvAnimalName, tvAnimalDetails;
    private Button btnPlayAudio;
    private String getId, getName, getCategory, getDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_details);

        //Below code will get the data send along with Intent from CategoryAdapter
        Intent intent = getIntent();
        getId = intent.getStringExtra("ANIMAL_ID");
        getName = intent.getStringExtra("ANIMAL_NAME");
        getCategory = intent.getStringExtra("ANIMAL_CATEGORY");
        getDetail = intent.getStringExtra("ANIMAL_DETAIL");

        linlViews();

        //setData will set the received data to the layout of this activity
        setData();
    }

    private void setData() {
        tvAnimalName.setText(getName);
        tvAnimalDetails.setText(getDetail);
    }

    private void linlViews() {
        btnPlayAudio = findViewById(R.id.btnPlayAudio);
        tvAnimalName = findViewById(R.id.tvAnimalName);
        tvAnimalDetails = findViewById(R.id.tvAnimalDetails);
    }
/*added media files to player*/
    public void playAudio(View view) {
        if (getName.equalsIgnoreCase("lion")){
            MediaPlayer audio = MediaPlayer.create(this, R.raw.lion_audio);
            audio.start();
        }if (getName.equalsIgnoreCase("cow")){
            MediaPlayer audio = MediaPlayer.create(this, R.raw.cow);
            audio.start();
        }
        if (getName.equalsIgnoreCase("frog")){
            MediaPlayer audio = MediaPlayer.create(this, R.raw.frog);
            audio.start();
        }
        if (getName.equalsIgnoreCase("pigeon")){
            MediaPlayer audio = MediaPlayer.create(this, R.raw.pigeon_audio);
            audio.start();
        }
        if (getName.equalsIgnoreCase("sparrow")){
            MediaPlayer audio = MediaPlayer.create(this, R.raw.sparrow_sms_new);
            audio.start();
        }
    }
}
