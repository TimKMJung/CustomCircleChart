package android.ssoim.com.customcirclechartview.adapter;

import android.ssoim.com.customcirclechartview.chart.FitChartFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


public class FitChartAdapter extends FragmentStatePagerAdapter {

    private final int mCount = 1;
    private float valFloat[] = null;

//    public FitChartAdapter(FragmentManager fm, float floatVal[]) {
//        super(fm);
//
//        this.valFloat = floatVal;
//    }

    public FitChartAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0 :

                return  new FitChartFragment();

            default :

                return new FitChartFragment();
        }
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }
}
