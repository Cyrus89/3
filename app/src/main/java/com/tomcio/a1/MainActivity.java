package com.tomcio.a1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View.OnTouchListener;
import android.app.Activity;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnTouchListener {

   public ImageView obrazek;
    Button b1;
    Button b2;
    Button stop;
    int i;
  //  public boolean a=false;
    Animation an;
    Animation.AnimationListener al;
    private double CurrAngle =0;
    private double PrevAngle = 0;
    EditText Imie;
    int stop_licznik=0;
    int lewo_licznik=0;
    int prawo_licznik=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obrazek = (ImageView) findViewById(R.id.imageView);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        stop = (Button) findViewById(R.id.button3);
        Imie = (EditText) findViewById(R.id.editText);
        obrazek.setOnTouchListener(this);

      //  an = AnimationUtils.loadAnimation(this,R.anim.rotate);

    }

    public void Stop(View view) {
// a = true;
       obrazek.clearAnimation();
//        obrazek.setAnimation(null);
        stop_licznik++;

    }

    public void Lewo(View view)  {

        Animation an = new RotateAnimation(obrazek.getRotation(),obrazek.getRotationX()-360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        an.setDuration(5000);
        an.setRepeatCount(Animation.INFINITE);
        an.setInterpolator(new LinearInterpolator());
        an.setFillAfter(true);
       // an.setFillBefore(true);
      //  an.setFillEnabled(true);
        obrazek.setAnimation(an);
        obrazek.startAnimation(an);

//        obrazek.setRotation(obrazek.getRotation() - 10);

        lewo_licznik++;


    }


    public void Prawo(View view) {


       /* for (i=0;i<5;i++) {
            try {
                Thread.sleep(5000);
//                obrazek.setRotation(obrazek.getRotation() + 10);

                Log.d("Prawo","1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }*/

       Animation an = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        an.setDuration(5000);
        an.setRepeatCount(Animation.INFINITE);
        an.setInterpolator(new LinearInterpolator());
        an.setFillAfter(true);
        obrazek.setAnimation(an);
        obrazek.startAnimation(an);

        prawo_licznik++;

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final float xc = obrazek.getWidth() /2;
        final float yc = obrazek.getHeight() /2;

        final float x = event.getX();
        final float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: {
//                obrazek.clearAnimation();
//                CurrAngle = Math.toDegrees(Math.atan2(x - xc,yc - y));
                break;
            }
            case MotionEvent.ACTION_MOVE :{
                PrevAngle = CurrAngle;
                CurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                animate(PrevAngle,CurrAngle,0);
                System.out.println(CurrAngle);
                break;
            }
            case MotionEvent.ACTION_UP:{
                PrevAngle = CurrAngle;
                break;
            }

        }
        return true;
    }

    private void animate(double fromDegrees, double toDegrees, long durationMillis) {

        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees,(float) toDegrees,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(durationMillis);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        obrazek.startAnimation(rotate);
        System.out.println(CurrAngle);
    }

    public void Klik(View view) {
//        String ImieSowy = Imie.getText().toString();

        Toast.makeText(MainActivity.this, "Hej, jestem " + Imie.getText().toString() + "! Miło mi Cię poznać :)", Toast.LENGTH_LONG).show();

    }

    public void wynik(View view) {
        Intent intent = new Intent(this,WynikActivity.class);
//        intent.putExtra("ImieSowy",Imie.getText().toString());
        Bundle data = new Bundle();
        data.putString("imie_key",Imie.getText().toString());
        data.putInt("stop_key",stop_licznik);
        data.putInt("lewo_key",lewo_licznik);
        data.putInt("prawo_key",prawo_licznik);
        intent.putExtras(data);
        startActivity(intent);

    }
@Override
    public void onPause(){

    super.onPause();
    stop_licznik = 0;
    lewo_licznik=0;
    prawo_licznik=0;
    obrazek.clearAnimation();

    }
}
