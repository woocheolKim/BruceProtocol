package com.example.bruceprotocol;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bruceprotocol.Tools.Dlog;
import com.example.bruceprotocol.Tools.ViewClickEffect;
import com.example.bruceprotocol.databinding.FragmentHomeBinding;
import com.gusakov.library.PulseCountDown;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    static boolean isPlaying;
    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setVariable(BR.homeFragment, this);

        binding.btnStart.setOnTouchListener(new ViewClickEffect());
        binding.btnPause.setOnTouchListener(new ViewClickEffect());
        binding.btnStop.setOnTouchListener(new ViewClickEffect());

        return binding.getRoot();
    }

    // 시작버튼
    public void start(View view){
        // 3초 카운트
        count();
    }

    void count(){
        binding.rlCounter.setVisibility(View.VISIBLE);
        binding.pcdCntDwn.start(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                isPlaying = true;
                binding.rlCounter.setVisibility(View.GONE);
                binding.llNonPlay.setVisibility(View.GONE);
                binding.llPlay.setVisibility(View.VISIBLE);
                binding.rlParent.setBackgroundResource(R.color.defaultYellow);
                MainActivity.binding.btbMain.setBackgroundResource(R.color.defaultBlack);
                return null;
            }
        });
    }

    public boolean stop(View view){
        MainActivity.vib.vibrate(50);

        isPlaying = false;

        binding.llNonPlay.setVisibility(View.VISIBLE);
        binding.llPlay.setVisibility(View.GONE);
        binding.rlParent.setBackgroundResource(R.color.white);
        MainActivity.binding.btbMain.setBackgroundResource(R.color.white);

        return false;
    }

    public void pause(View view){
        isPlaying = !isPlaying;

        if (isPlaying){
            binding.btnPause.setText(getText(R.string.pause));
            binding.rlParent.setBackgroundResource(R.color.defaultYellow);
            MainActivity.binding.btbMain.setBackgroundResource(R.color.defaultBlack);
        }else{
            binding.btnPause.setText(getText(R.string.start));
            binding.rlParent.setBackgroundResource(R.color.white);
            MainActivity.binding.btbMain.setBackgroundResource(R.color.white);
        }
    }
}
