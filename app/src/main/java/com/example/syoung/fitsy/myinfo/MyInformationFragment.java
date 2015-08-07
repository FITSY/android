package com.example.syoung.fitsy.myinfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.main.server.FITSYService;
import com.example.syoung.fitsy.main.server.UserCourse;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by syoung on 2015-06-28.
 */
public class MyInformationFragment extends Fragment {
    private View rootView;
    private ImageView img;
    int cnt1 = 0;
    TextView tv1, tv2, tv3, tv4, tv5, tv6;
    Button btn1, btn2, btn3, btn4;
    final int PICK_FROM_GALLERY = 2;
    private static MyInformationFragment instance;

    public MyInformationFragment() {
    }

    public static MyInformationFragment getInstance() {
        if (instance == null) {
            instance = new MyInformationFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_information, container, false);

        img = (ImageView) rootView.findViewById(R.id.imageView);
        tv1 = (TextView) rootView.findViewById(R.id.text1);
        tv2 = (TextView) rootView.findViewById(R.id.text2);
        tv3 = (TextView) rootView.findViewById(R.id.text3);
        tv4 = (TextView) rootView.findViewById(R.id.text4);
        tv5 = (TextView) rootView.findViewById(R.id.text5);
        tv6 = (TextView) rootView.findViewById(R.id.text6);
        btn1 = (Button) rootView.findViewById(R.id.button1);
        btn2 = (Button) rootView.findViewById(R.id.button2);
        btn3 = (Button) rootView.findViewById(R.id.button3);
        btn4 = (Button) rootView.findViewById(R.id.button4);

        Gson gson = new Gson();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://ebsud89.iptime.org:8022")
                .setConverter(new GsonConverter(gson))
                .build();

        MyInfoService service = restAdapter.create(MyInfoService.class);

        service.getInbodyInfo(new Callback<List<MyInfoData>>() {
            @Override
            public void success(List<MyInfoData> myInfoDatas, Response response) {
                Log.e("SUCCESS", myInfoDatas.get(0).getDate());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("FAIL", error.toString());
            }
        });

        tv1.append("이상훈");
        tv2.append("180cm");
        tv3.append("74kg");
        tv4.append("180cm");
        tv5.append("74kg");
        tv6.append("2100kcal");
        btn1.setBackgroundColor(Color.WHITE);
        btn1.setTextColor(Color.BLACK);
        btn2.setBackgroundColor(Color.WHITE);
        btn2.setTextColor(Color.BLACK);
            img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("aspcetX", 0);
                intent.putExtra("aspectY", 0);
                intent.putExtra("outputX", 150);
                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, PICK_FROM_GALLERY);
                    getActivity().setResult(Activity.RESULT_OK, intent);
                  } catch (ActivityNotFoundException e) {

                }

            }            ;
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setBackgroundColor(Color.BLUE);
                btn1.setTextColor(Color.WHITE);
                btn2.setBackgroundColor(Color.WHITE);
                btn2.setTextColor(Color.BLACK);/*
                if (cnt1 % 2 == 0) {
                    cnt1++;
                    getActivity().startService(new Intent("com.example.syoung.fitsy.mp3player"));
                } else {
                    cnt1++;
                    getActivity().stopService(new Intent("com.example.syoung.fitsy.mp3player"));
                }*/
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setBackgroundColor(Color.WHITE);
                btn1.setTextColor(Color.BLACK);
                btn2.setBackgroundColor(Color.BLUE);
                btn2.setTextColor(Color.WHITE);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage("비밀번호 수정");
                final EditText input = new EditText(getActivity());
                alert.setView(input);
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        Context context = rootView.getContext();
                        LinearLayout layout = new LinearLayout(context);
                        layout.setOrientation(LinearLayout.VERTICAL);
                        AlertDialog.Builder alert2 = new AlertDialog.Builder(getActivity());
                        alert2.setMessage("비밀번호 확인");
                        AlertDialog modify = alert2.create();
                        final EditText input2 = new EditText(getActivity());
                        input2.setHint(value);
                        layout.addView(input2);
                        final EditText input3 = new EditText(getActivity());
                        layout.addView(input3);
                        alert2.setView(layout);
                        alert2.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getActivity(), "비밀번호가 변경되었습니다", Toast.LENGTH_SHORT).show();
                            }
                        });
                        alert2.show();
                    }
                });
                alert.show();
            }
        });
        return rootView;
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        Toast.makeText(getActivity(),"ERROR",Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_FROM_GALLERY){
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(
                    selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
            img.setImageBitmap(yourSelectedImage);
        }
    }
}
