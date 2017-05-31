package com.example.gabi.czujniki;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tv1, tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION), sm.SENSOR_DELAY_NORMAL, null);

        List<Sensor> lista =  manager.getSensorList(Sensor.TYPE_ALL);
        String napis = "znaleziono " + lista.size() + " czujnikow:\n";

        for (int i = 0; i<lista.size(); i++)
        {
            napis = napis +lista.get(i).getName() + "\n";
        }
        TextView textV = (TextView)findViewById(R.id.tekstWiu);
        textV.setText(napis);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        tv1.setText("event.values[0]=" + Math.round(event.values[0]) + "\n");

        String kierunek = "";
        if(event.values[0] > 355 || event.values[0] < 5)//0
        {
            kierunek = "Polnoc (N)";
        }else if(event.values[0] < 85 && event.values[0] >= 5 )//<90
        {
            kierunek = "Polnocny-wschod (NE)";
        }else if(event.values[0] >= 85 && event.values[0] < 95 )//==90
        {
            kierunek = "Wschod (E)";
        }else if(event.values[0] < 175 && event.values[0] >= 95 )//<180
        {
            kierunek = "Polodniowy-Wschod (SE)";
        }else if(event.values[0] >= 175 && event.values[0] < 185 )//==180
        {
            kierunek = "Polodnie (S)";
        }else if(event.values[0] >= 185 && event.values[0] < 265 )//<270
        {
            kierunek = "Polodniowy-zachod (SW)";
        }else if(event.values[0] >= 265  && event.values[0] < 275 )//==270
        {
            kierunek = "Zachod (W)";
        }else if(event.values[0] >= 275 && event.values[0] <= 355 )//<360
        {
            kierunek = "Polnocny-zachod (NW)";
        }
        tv2.setText("Idziesz na " + kierunek);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //nie to
    }
}
