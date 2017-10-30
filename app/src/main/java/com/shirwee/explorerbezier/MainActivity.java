package com.shirwee.explorerbezier;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

public class MainActivity
        extends AppCompatActivity
{

    private BezierView2 bezierView2;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bezierView2 = (BezierView2) findViewById(R.id.bezier_view2);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_control1:
                        bezierView2.selectControl(0);
                        break;
                    case R.id.rb_control2:
                        bezierView2.selectControl(1);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
