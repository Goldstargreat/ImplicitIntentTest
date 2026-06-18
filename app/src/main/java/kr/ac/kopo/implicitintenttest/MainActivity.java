package kr.ac.kopo.implicitintenttest;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int[] btnIds =
                {
                        R.id.btn_call, R.id.btn_homepage, R.id.btn_map, R.id.btn_search,
                        R.id.btn_sms, R.id.btn_camera, R.id.btn_finish
                };
        Button[] btns = new Button[btnIds.length];

        int i = 0;
        for (int btnId : btnIds)
        {
            btns[i] = findViewById(btnId);
            btns[i].setOnClickListener(btnListener);
            i++;
        }
        Log.i("로그확인", "onCreate() 호출");
    }

    // 액티비티 호출되는 메소드
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i("로그확인", "onPause() 호출");
    }
    @Override
    protected void onStart()
    {
        super.onStop();
        Log.i("로그확인", "onStart() 호출");
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i("로그확인", "onStop() 호출");
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i("로그확인", "onDestroy() 호출");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("로그확인", "onRestart() 호출");
    }

    // 액티비티가 다른 화면에 가리어졌다가 다시 나타날 때

    View.OnClickListener btnListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent intent = null;
            Uri uri = null;
            int selectedId = v.getId();
            if (selectedId == R.id.btn_call)
            {
                uri = Uri.parse("tel:01012459865");
                intent = new Intent(Intent.ACTION_DIAL, uri);
            } else if (selectedId == R.id.btn_homepage)
            {
                uri = Uri.parse("https://www.naver.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
            } else if (selectedId == R.id.btn_map)
            {
                uri = Uri.parse("https://maps.google.com/maps?q=37.523854826456066, 126.97788991256569");
                intent = new Intent(Intent.ACTION_VIEW, uri);
            } else if (selectedId == R.id.btn_search)
            {
                intent = new Intent(Intent.ACTION_WEB_SEARCH, uri);
                intent.putExtra(SearchManager.QUERY, "국립중앙박물관");
            } else if (selectedId == R.id.btn_sms)
            {
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "안녕하세요");
                uri = Uri.parse("smsto:01011112222");
                intent.setData(uri);
            } else if(selectedId == R.id.btn_camera)
            {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            }
              if(selectedId == R.id.btn_finish)
              {
                  finish();
              } else
              {
                  startActivity(intent);
              }
        }
    };
}