package com.example.bruceprotocol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.bruceprotocol.Tools.Dlog;
import com.example.bruceprotocol.databinding.ActivityMainBinding;
import com.fxn.BubbleTabBar;
import com.fxn.OnBubbleClickListener;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    public static ActivityMainBinding binding;
    public static Vibrator vib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        changeMenu(new HomeFragment());

        binding.btbMain.addBubbleListener(new OnBubbleClickListener() {
            @Override
            public void onBubbleClick(int i) {
                Fragment fragment;
                switch (i){
                    case R.id.item_home :
                    default:
                        fragment = new HomeFragment();
                        break;
                    case R.id.item_record :
                        fragment = new RecordFragment();
                        break;
                }
                changeMenu(fragment);
            }
        });
    }

    void changeMenu(Fragment fragment){
        Fragment fragment1 = getSupportFragmentManager().findFragmentByTag("Fragment");
        if(fragment1 != null){
            try{
                if(getSupportFragmentManager() != null){
                    getSupportFragmentManager().popBackStackImmediate("Fragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        if(getSupportFragmentManager() != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rl_container, fragment,"Fragment");
            try{
                fragmentTransaction.commitNowAllowingStateLoss();
            }catch (Exception e){
                e.printStackTrace();
                fragmentTransaction.commitNow();

            }
        }
    }

    private static long time =0;
    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis()-time >=2000){
            time = System.currentTimeMillis();
            Toasty.custom(MainActivity.this, getResources().getString(R.string.exit), R.drawable.ic_launcher, getResources().getColor(R.color.gray), Toasty.LENGTH_SHORT, false, true).show();
        }else if(System.currentTimeMillis() - time < 2000){
            try{
                if (HomeFragment.isPlaying){
                    // TODO 기록 저장
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            finishAffinity();
                            System.runFinalization();
                            System.exit(0);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, 300);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
