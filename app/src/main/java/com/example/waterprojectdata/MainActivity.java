package com.example.waterprojectdata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    GraphView graphView;
    LineGraphSeries series;
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphView graph = (GraphView) findViewById(R.id.graphView);

        DataPoint[] dataPoints = new DataPoint[30];
        Calendar calendar = Calendar.getInstance();
        for(int i = 0;i<30;i++){
            double y = getRandomNumber(7,11);
            Date tempo = calendar.getTime();
            //new Date(2023,3,12,17,i+1);
            calendar.add(Calendar.MINUTE, 1);
            calendar.add(Calendar.SECOND, i+1);

            DataPoint dataPoint = new DataPoint(tempo, y);
            dataPoints[i] = dataPoint;
        }
        //ordena pelo tempo
        /*
        Arrays.sort(dataPoints, new Comparator<DataPoint>() {
            @Override
            public int compare(DataPoint dp1, DataPoint dp2) {
                return Long.compare( (long)dp1.getX(), (long)dp2.getX() );
            }
        });*/
        for(int i = 0;i<30;i++){
            Date date = new Date((long)dataPoints[i].getX());
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
            String dateFormatted = format1.format(date.getTime());

            double y = dataPoints[i].getY();
            System.out.println("DataPoint " + i + ": x = " + dateFormatted + ", y = " + y);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
        series.setTitle("Teste pH");
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(25);
        series.setColor(Color.MAGENTA);
        series.setThickness(1);
        graph.addSeries(series);

        graph.setBackgroundColor(Color.argb(50, 255,250,250));

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(15);
        graph.getViewport().setScrollable(true);

        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(5); // only 3 because of the space

        // set manual x bounds to have nice steps
        graph.getViewport().setMinX(new Date(2023, 3, 12,18,0).getTime());
        graph.getViewport().setMaxX(new Date(2023, 3, 12,20,59).getTime());
        graph.getViewport().setXAxisBoundsManual(false);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not necessary
        graph.getGridLabelRenderer().setHumanRounding(true);

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                //Toast.makeText(MainActivity.this, "Series1: On Data Point clicked: " + dataPoint, Toast.LENGTH_SHORT).show();
                double pointY = dataPoint.getY();
                Date date = new Date((long)dataPoint.getX());
                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
                String dateFormatted = format1.format(date.getTime());
                Toast.makeText(MainActivity.this, dateFormatted+" "+pointY, Toast.LENGTH_SHORT).show();
            }
        });


        /*
        series = new LineGraphSeries();
        graphView.addSeries(series);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("END_NODE1");
         */

        setListeners();
    }

    public double getRandomNumber(int min, int max) {
        return (double) ((Math.random() * (max - min)) + min);
    }

    private void setListeners(){

    }

    @Override
    protected void onStart(){
        super.onStart();
    }
}