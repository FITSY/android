package com.example.syoung.fitsy.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.syoung.fitsy.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

import com.example.syoung.fitsy.statistics.RobotoCalendarView.RobotoCalendarListener;

/**
 * Created by syoung on 2015-06-28.
 */
public class StatisticsFragment extends Fragment implements RobotoCalendarListener{

    private RelativeLayout mainLayout;
    private LineChart mChart;

    private RobotoCalendarView robotoCalendarView;
    private int currentMonthIndex;
    private Calendar currentCalendar;

    Date now = new Date();
    String dumi = "1995-01-01";

    ArrayList<String> sKey = new ArrayList<String>();
    ArrayList<String> sDate = new ArrayList<String>();

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    int count = 0;


    private View myView;


    private View rootView;

    private static StatisticsFragment instance;

    public StatisticsFragment() {

    }

    public static StatisticsFragment getInstance() {
        if (instance == null) {
            instance = new StatisticsFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_statistics, container, false);

        this.myView = rootView;



        mChart = new LineChart(this.getActivity());

        mChart.setDescription("");

        mChart.animateXY(2000, 0);

        mChart.setHighlightEnabled(true);

        mChart.setTouchEnabled(true);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        mChart.setDrawGridBackground(false);
        mChart.getAxisLeft().setDrawAxisLine(false);

        mChart.setPinchZoom(true);


        LineData data = new LineData(getXAxisValues(), getDataSet());

        mChart.setData(data);

        Legend i = mChart.getLegend();

        mChart.getLegend().setEnabled(false);

        i.setForm(Legend.LegendForm.LINE);
        i.setTextColor(Color.BLACK);

        XAxis xl = mChart.getXAxis();
        xl.setTextColor(Color.BLACK);
        xl.setTextSize(13f);
        xl.setDrawGridLines(false);
        xl.setDrawAxisLine(false);
        xl.setDrawLabels(true);
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);


        YAxis yl = mChart.getAxisLeft();
        yl.setTextColor(Color.BLACK);
        yl.setTextSize(0);
        yl.setAxisMaxValue(50);
        yl.setDrawGridLines(false);
        yl.setEnabled(false);

        yl.setLabelCount(5);

        YAxis yl2 = mChart.getAxisRight();
        yl2.setEnabled(false);

        mChart.setHighlightEnabled(false);

        mainLayout = (RelativeLayout) rootView.findViewById(R.id.mainLayout);
        mainLayout.addView(mChart);

        CustomMarkerView mv = new CustomMarkerView (getActivity(), R.layout.custom_markerview);

        mChart.setMarkerView(mv);

        // Gets the calendar from the view
        robotoCalendarView = (RobotoCalendarView) rootView.findViewById(R.id.robotoCalendarPicker);

        // Set listener, in this case, the same activity
        robotoCalendarView.setRobotoCalendarListener(this);

        // Initialize the RobotoCalendarPicker with the current index and date
        currentMonthIndex = 0;
        currentCalendar = Calendar.getInstance(Locale.getDefault());

        // Mark current day
        robotoCalendarView.markDayAsCurrentDay(now);


        String dd5 = "2015-08-07";
        sDate.add("0");
        sKey.add(dd5);

        try {
            robotoCalendarView.CheckDayZero(format.parse(dd5));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dd6 = "2015-08-08";
        sDate.add("50");
        sKey.add(dd6);

        try {
            robotoCalendarView.CheckDayHalf(format.parse(dd6));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dd = "2015-08-09";
        sDate.add("0");
        sKey.add(dd);

        try {
            robotoCalendarView.CheckDayZero(format.parse(dd));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dd2 = "2015-08-10";
        sDate.add("0");
        sKey.add(dd2);

        try {
            robotoCalendarView.CheckDayZero(format.parse(dd2));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        String dd3 = "2015-08-11";
        sDate.add("50");
        sKey.add(dd3);

        try {
            robotoCalendarView.CheckDayHalf(format.parse(dd3));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dd4 = "2015-08-12";
        sDate.add("100");
        sKey.add(dd4);

        try {
            robotoCalendarView.CheckDayPft(format.parse(dd4));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        saveArray();
        loadArray(getActivity());



        LinearLayout textview = (LinearLayout) rootView.findViewById(R.id.textview);

        textview.bringToFront() ;


        return rootView;
    }

    private ArrayList<LineDataSet> getDataSet() {
        ArrayList<LineDataSet> dataSets = null;

        ArrayList<Entry> valueSet1 = new ArrayList<>();
        Entry v1e1 = new Entry(10, 0); // Jan
        valueSet1.add(v1e1);
        Entry v1e2 = new Entry(15, 1); // Feb
        valueSet1.add(v1e2);
        Entry v1e3 = new Entry(20, 2); // Mar
        valueSet1.add(v1e3);
        Entry v1e4 = new Entry(24, 3); // Apr
        valueSet1.add(v1e4);

        ArrayList<Entry> valueSet2 = new ArrayList<>();
        Entry v2e1 = new Entry(5, 0); // Jan
        valueSet2.add(v2e1);
        Entry v2e2 = new Entry(10, 1); // Feb
        valueSet2.add(v2e2);
        Entry v2e3 = new Entry(8, 2); // Mar
        valueSet2.add(v2e3);
        Entry v2e4 = new Entry(20, 3); // Apr
        valueSet2.add(v2e4);

        ArrayList<Entry> valueSet3 = new ArrayList<>();
        Entry v3e1 = new Entry(18, 0); // Jan
        valueSet3.add(v3e1);
        Entry v3e2 = new Entry(26, 1); // Feb
        valueSet3.add(v3e2);
        Entry v3e3 = new Entry(15, 2); // Mar
        valueSet3.add(v3e3);
        Entry v3e4 = new Entry(26, 3); // Apr
        valueSet3.add(v3e4);



        LineDataSet LineDataSet1 = new LineDataSet(valueSet1, null); // "BMI");

        LineDataSet1.setDrawCubic(true);
        LineDataSet1.setCubicIntensity(0.2f);
        LineDataSet1.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineDataSet1.setColor(Color.rgb(255, 70, 70));
        LineDataSet1.setCircleColor(Color.rgb(255, 70, 70));
        LineDataSet1.setLineWidth(3f);
        LineDataSet1.setCircleSize(3f);
        LineDataSet1.setFillAlpha(65);
        LineDataSet1.setCircleColorHole(Color.rgb(255, 70, 70));
        LineDataSet1.setValueTextSize(0);

        LineDataSet LineDataSet2 = new LineDataSet(valueSet2, null); // "지방량");


        LineDataSet2.setDrawCubic(true);
        LineDataSet2.setCubicIntensity(0.2f);
        LineDataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineDataSet2.setColor(Color.rgb(25, 25 ,112));
        LineDataSet2.setCircleColor(Color.rgb(25, 25 ,112));
        LineDataSet2.setLineWidth(3f);
        LineDataSet2.setCircleSize(3f);
        LineDataSet2.setFillAlpha(65);
        LineDataSet2.setCircleColorHole(Color.rgb(25, 25 ,112));
        LineDataSet2.setValueTextSize(0);

        LineDataSet LineDataSet3 = new LineDataSet(valueSet3, null);//"근육량");

        LineDataSet3.setDrawCubic(true);
        LineDataSet3.setCubicIntensity(0.2f);
        LineDataSet3.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineDataSet3.setColor(Color.rgb(29, 202, 255));
        LineDataSet3.setCircleColor(Color.rgb(29, 202, 255));
        LineDataSet3.setLineWidth(3f);
        LineDataSet3.setCircleSize(3f);
        LineDataSet3.setFillAlpha(65);
        LineDataSet3.setCircleColorHole(Color.rgb(29, 202, 255));
        LineDataSet3.setValueTextSize(0);




        dataSets = new ArrayList<>();

        dataSets.add(LineDataSet1);
        dataSets.add(LineDataSet2);
        dataSets.add(LineDataSet3);

        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("5월");
        xAxis.add("6월");
        xAxis.add("7월");
        xAxis.add("8월");

        return xAxis;
    }

    @Override
    public void onDateSelected(final Date date) {


        if (format.format(date).equals( format.format(now) ) ) {

            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Are you sure?")
                    .setConfirmText("Yes, I am!")
                    .setCancelText("No,cancel plx!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            sDialog
                                    .setTitleText("Success!")
                                    .setContentText("Checked!")
                                    .setConfirmText("OK")
                                    .showCancelButton(false)
                                    .setConfirmClickListener(null)
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                            robotoCalendarView.markDayAsSelectedDay(date);

                        }
                    }).show();

        }


    }

    @Override
    public void onRightButtonClick() {
        currentMonthIndex++;
        updateCalendar();
    }

    @Override
    public void onLeftButtonClick() {
        currentMonthIndex--;
        updateCalendar();
    }

    private void updateCalendar() {
        currentCalendar = Calendar.getInstance(Locale.getDefault());
        currentCalendar.add(Calendar.MONTH, currentMonthIndex);
        robotoCalendarView.initializeCalendar(currentCalendar);
        loadArray(getActivity());

        if (currentMonthIndex != 0) {
            Date dumi2 = null;
            try {
                dumi2 = format.parse(dumi);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            robotoCalendarView.markDayAsCurrentDay(dumi2);
        }
        else {
            robotoCalendarView.markDayAsCurrentDay(now);
        }

    }

    public boolean saveArray()
    {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor mEdit1 = sp.edit();
        mEdit1.putInt("DataNumber", sKey.size()); /* sKey is an array */

        for(int i=0;i<sKey.size();i++)
        {
            mEdit1.remove("Data" + i);
            mEdit1.remove("DataPg" + i);
            mEdit1.putString("Data" + i, sKey.get(i));
            mEdit1.putString("DataPg" + i, sDate.get(i));
        }

        return mEdit1.commit();
    }


    public void loadArray(Context mContext)
    {


        LinearLayout graywoman = (LinearLayout) myView.findViewById(R.id.undercount);

        LinearLayout bluewoman = (LinearLayout) myView.findViewById(R.id.overcount);

        ImageView view = (ImageView) myView.findViewById(R.id.imagewoman);


        int month = currentCalendar.get(Calendar.MONTH);
        int year = currentCalendar.get(Calendar.YEAR);
        int day = currentCalendar.get(Calendar.DAY_OF_MONTH);

        SharedPreferences mSharedPreference1 = PreferenceManager.getDefaultSharedPreferences(mContext);
        sKey.clear();
        sDate.clear();

        int size = mSharedPreference1.getInt("DataNumber", 0);

        for(int i=0;i<size;i++)
        {
            sKey.add(mSharedPreference1.getString("Data" + i, null));
            sDate.add(mSharedPreference1.getString("DataPg" + i , null));
            try {
                Date formatdate = format.parse(sKey.get(i));
                String formdatadate = sDate.get(i);

                if (month == formatdate.getMonth() && 2015 == year) {

                    if (formdatadate.equals("0")) {
                        count++;
                        robotoCalendarView.CheckDayZero(formatdate);
                    } else if (formdatadate.equals("50")) {
                        if (count > 0) {
                            count--;
                        }
                        robotoCalendarView.CheckDayHalf(formatdate);
                    }
                    else if (formdatadate.equals("100")) {
                        if (count > 2) {
                            count -= 2;
                        }
                        else {
                            count = 0;
                        }
                        robotoCalendarView.CheckDayPft(formatdate);
                    }
                    if (count == 0) {
                        graywoman.setVisibility(View.INVISIBLE);
                        bluewoman.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        graywoman.setVisibility(View.VISIBLE);
                        if (count < 4) {
                            count = 3;
                        }
                        view.getLayoutParams().width = 280 + (count * 50);
                        bluewoman.setVisibility(View.INVISIBLE);
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onStop() {
        super.onStop();
        saveArray();
    }
}
