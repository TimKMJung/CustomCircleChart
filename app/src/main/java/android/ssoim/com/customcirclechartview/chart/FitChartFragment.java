/*
 * Copyright (C) 2015 Brent Marriott
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.ssoim.com.customcirclechartview.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.ssoim.com.customcirclechartview.R;
import android.ssoim.com.customcirclechartview.views.CircleChartViewMain;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;
import com.hookedonplay.decoviewlib.events.DecoEvent.EventType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class FitChartFragment extends FitChartBaseFragment {

    final private int STOCK_COLOR = Color.parseColor("#D04E5F");
    final private int BOND_COLOR = Color.parseColor("#2574B2");
    final private int ETC_COLOR = Color.parseColor("#68B0B2");
    final private int BG_COLOR = Color.parseColor("#FFFFFF");
    final private int RISK_TXT_COLOR = Color.parseColor("#4A4A4A");



    final float mSeriesMax = 100f;
    private int mSeries1Index;
    private int mSeries2Index;
    private int mSeries3Index;
    private int mBack1Index;

    private float[] valFloat = new float[3];
    private double[] tempValue = new double[3];

    private int activenessInt = 0;

//    private static final String valueCode = Constants.RATIO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        container = (ViewGroup) inflater.inflate(R.layout.inner_chart_lay, container, false);

        CircleChartViewMain ccvMain = (CircleChartViewMain) getActivity();

        tempValue =  ccvMain.getInputValue();
        
        for(int i=0; i<tempValue.length; i++) {
            valFloat[i] = (float) tempValue[i];
        }

        List<Float> maxList = Arrays.asList(valFloat[0] , valFloat[1], valFloat[2]);

        float max = Collections.max(maxList).floatValue();

        TextView activenessTxt = (TextView) container.findViewById(R.id.arc_riskness_txt);
        activenessTxt.setText(""+ max);

        final RelativeLayout riskLay = (RelativeLayout) container.findViewById(R.id.fit_risk_title_lay);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                riskLay.setVisibility(View.VISIBLE);


                Animation fadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
                riskLay.startAnimation(fadeInAnimation);


            }
        }, 1500);


        return container;
    }

    @Override
    protected void createTracks() {

        final DecoView decoView = getDecoView();
        final View view = getView();
        if (decoView == null || view == null) {
            return;
        }

        decoView.executeReset();
        decoView.deleteAll();

        SeriesItem seriesBack1Item = new SeriesItem.Builder(BG_COLOR)
                .setRange(0, mSeriesMax, mSeriesMax)
//                .setChartStyle(SeriesItem.ChartStyle.STYLE_PIE)
//                .setInset(new PointF(circleInset, circleInset))
                .build();

        mBack1Index = decoView.addSeries(seriesBack1Item);



        SeriesItem series1Item = new SeriesItem.Builder(STOCK_COLOR)
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .setLineWidth(getDimension(20))
                .setCapRounded(false)
//                .addEdgeDetail(new EdgeDetail(EdgeDetail.EdgeType.EDGE_INNER, COLOR_EDGE, 0.3f))
                .setShowPointWhenEmpty(false)
                .build();

        mSeries1Index = decoView.addSeries(series1Item);

        SeriesItem series2Item = new SeriesItem.Builder(BOND_COLOR)
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
                .setLineWidth(getDimension(20))
                .setCapRounded(false)
//                .addEdgeDetail(new EdgeDetail(EdgeDetail.EdgeType.EDGE_INNER, COLOR_EDGE, 0.3f))
                .setShowPointWhenEmpty(false)
                .build();

        mSeries2Index = decoView.addSeries(series2Item);

        SeriesItem series3Item = new SeriesItem.Builder(ETC_COLOR)
                .setRange(0, mSeriesMax, 0)
                .setInitialVisibility(false)
               .setLineWidth(getDimension(20))
                .setCapRounded(false)
//                .addEdgeDetail(new EdgeDetail(EdgeDetail.EdgeType.EDGE_INNER, COLOR_EDGE, 0.3f))
                .setShowPointWhenEmpty(false)
                .build();

        mSeries3Index = decoView.addSeries(series3Item);

    }

    @Override
    protected void setupEvents() {
        final DecoView arcView = getDecoView();
        final View view = getView();
        if (arcView == null || arcView.isEmpty() || view == null) {
            return;
        }

        arcView.executeReset();

        addAnimation(arcView, mSeries1Index, valFloat[0], 300, STOCK_COLOR);
        addAnimation(arcView, mSeries2Index, valFloat[1], 300, BOND_COLOR);

        arcView.addEvent(new DecoEvent.Builder(valFloat[0]+valFloat[1])
                .setIndex(mSeries1Index)
                .setDelay(600)
                .setDuration(600)
                .build());

        addAnimation(arcView, mSeries3Index, valFloat[2], 600, ETC_COLOR);

        arcView.addEvent(new DecoEvent.Builder(valFloat[1]+valFloat[2])
                .setIndex(mSeries2Index)
                .setDelay(1200)
                .setDuration(600)
                .build());

        arcView.addEvent(new DecoEvent.Builder(200)
                .setIndex(mSeries1Index)
                .setDelay(1800)
                .setDuration(600)
                .build());

        arcView.addEvent(new DecoEvent.Builder(EventType.EVENT_COLOR_CHANGE, BG_COLOR)
                .setIndex(mBack1Index)
                .setDelay(2400)
                .setDuration(600)
                .build());

    }

    private void addFinishAnimation(final DecoView arcView, final int series, final int duration, int delay, final View view) {
        arcView.addEvent(new DecoEvent.Builder(0)
                .setIndex(series)
                .setDelay(delay)
                .setDuration(duration)
                .setListener(new DecoEvent.ExecuteEventListener() {
                    @Override
                    public void onEventStart(DecoEvent event) {
                        arcView.getChartSeries(series).getSeriesItem().setSeriesLabel(null);
                    }

                    @Override
                    public void onEventEnd(DecoEvent event) {
                        if (view != null) {
//                            showAvatar(false, view);
//                            setDemoFinished(true);
                        }
                    }
                })
                .build());
    }

    private void addAnimation(final DecoView arcView,
                              int series, float moveTo, int delay,
                              final int color) {
        DecoEvent.ExecuteEventListener listener = new DecoEvent.ExecuteEventListener() {
            @Override
            public void onEventStart(DecoEvent event) {

            }


            @Override
            public void onEventEnd(DecoEvent event) {

            }

        };


        arcView.addEvent(new DecoEvent.Builder(moveTo)
                .setIndex(series)
                .setDelay(delay)
                .setDuration(1500)
                .setListener(listener)
                .build());
    }


}
