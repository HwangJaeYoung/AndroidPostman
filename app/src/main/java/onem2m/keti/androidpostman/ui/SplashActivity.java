package onem2m.keti.androidpostman.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import onem2m.keti.androidpostman.R;
import onem2m.keti.androidpostman.ui.main_data.MainDataActivity;
import onem2m.keti.androidpostman.ui.main_list.MainActivity;

/**
 * Created by Blossom on 2016-11-03.
 */

public class SplashActivity extends Activity {
    private static final int SPLASH_TIME_OUT = 1500; // Splash time

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                // Splash에 애니메이션을 적용하여 동적으로 보이게 한다.
                SplashActivity.this.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }

        }, SPLASH_TIME_OUT);
    }
}