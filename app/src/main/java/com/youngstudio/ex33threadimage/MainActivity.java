package com.youngstudio.ex33threadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= findViewById(R.id.iv);

    }

    public void clickBtn(View view) {
        //Newwork상에 있는 이미지를 일겅와서 이미지뷰에 보여주기
        //Network작업을 오래걸리는 작업으로 인지하므로..
        //MainThread에서 작업할 수 없음.
        //별도의 Thread를 생성해서 네트워크 작업을 수행하도록..
        new Thread(){
            @Override
            public void run() {
                //이미지의 경로
                String imgUrl="https://image.fmkorea.com/files/attach/new/20180805/486616/657611940/1194711853/bafb4810067e0278a55f75e94b1e7eca.jpg";

                //Stream을 열수 있는 해임달(URL) 객체 생성
                try {

                    URL url= new URL(imgUrl);
                    //무지개로드(Stream)을 얻어오기
                    InputStream is= url.openStream();

                    //스트림을 통해 이미지를 읽어와서
                    //이미지뷰에 설정!

                    final Bitmap bm= BitmapFactory.decodeStream(is);

                    //UI변경(이미지변경)은 MainThread만 할 수 있음.
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(bm);
                        }
                    });


                } catch (MalformedURLException e) {e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}

            }//run
        }.start();

    }
}

















