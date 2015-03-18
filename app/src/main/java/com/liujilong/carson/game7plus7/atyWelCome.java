package com.liujilong.carson.game7plus7;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class atyWelCome extends Activity {
    private boolean hasSaved = true;
    private Button btnResume;
    private Button btnNewGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_aty_wel_come);
        btnResume= (Button)findViewById(R.id.button_resume_game);
        btnNewGame = (Button)findViewById(R.id.button_new_game);
        btnNewGame.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (hasSaved){
                    DialogFragment df = new fargConfirmNewGame();
                    df.show(getFragmentManager(),"new game");
                } else {
                    startGame(Config.START_GAME);
                }
            }
        });
    }
    //inputMessage :
    //Config.START_GAME Config.RESUME_GAME
    public  void startGame(int inputMessage){
        Intent i = new Intent(atyWelCome.this,atyGame.class);
        i.putExtra(Config.START_OR_RESUME_GAME,inputMessage);
        startActivity(i);
    }

}
