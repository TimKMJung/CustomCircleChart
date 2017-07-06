package android.ssoim.com.customcirclechartview.views;

import android.app.Activity;
import android.os.Bundle;
import android.ssoim.com.customcirclechartview.R;
import android.ssoim.com.customcirclechartview.adapter.FitChartAdapter;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class CircleChartViewMain extends AppCompatActivity {

    public static Activity CircleChartViewMain;

    private FloatingActionButton fabBtn;
    private ViewPager mViewPager;
    private FitChartAdapter mFitChartAdapter;

    private EditText oEdit, tEdit, thEdit;
    private String oEdString = "";
    private String  tEdString = "";
    private String thEdString = "";


    public double[] inputValue = new double[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_chart_view_main);

        init();

    }


    private void init() {
        CircleChartViewMain = CircleChartViewMain.this;

        oEdit = (EditText) findViewById(R.id.ed_ip_o);
        tEdit = (EditText) findViewById(R.id.ed_ip_t);
        thEdit = (EditText) findViewById(R.id.ed_ip_th);
//        fEdit = (EditText) findViewById(R.id.ed_ip_f);


        fabBtn = (FloatingActionButton) findViewById(R.id.fab_btn);


        oEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                oEdString = s.toString();

            }
        });

        tEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tEdString = s.toString();
            }
        });

        thEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                thEdString = s.toString();
            }
        });

//        fEdit.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                fEdString = s.toString();
//            }
//        });

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        checkNull();
            }
        });


    }

    private void checkNull() {
        if(oEdString.equals("") || tEdString.equals("") || thEdString.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.null_error_msg), Toast.LENGTH_LONG).show();
        } else {
            inputValue();
        }
    }

    private void inputValue() {

        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(thEdit.getWindowToken(), 0);


        inputValue[0] = new Double(oEdString);
        inputValue[1] =  new Double(tEdString);
        inputValue[2] =  new Double(thEdString);
//

                mViewPager = (ViewPager) findViewById (R.id.custom_pager);
                mFitChartAdapter = new FitChartAdapter(getSupportFragmentManager());
                mViewPager.setAdapter(mFitChartAdapter);

    }

    public double[] getInputValue() {
        return inputValue;
    }

    public void setInputValue(double[] inputValue) {
        this.inputValue = inputValue;
    }
}
