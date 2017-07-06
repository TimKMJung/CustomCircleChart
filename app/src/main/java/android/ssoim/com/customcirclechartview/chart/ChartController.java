package android.ssoim.com.customcirclechartview.chart;


import android.os.Handler;

/**
 * Created by renewinspirit on 4/6/16.
 */
public class ChartController {

    protected boolean firstStage;

    private final Runnable unlockAction = new Runnable() {
        @Override
        public void run() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
//                    unlock();
                }
            }, 2000);
        }
    };


    private final Runnable showAction = new Runnable() {
        @Override
        public void run() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    show(unlockAction);
                }
            }, 2000);
        }
    };

    public void init() {
        show(unlockAction);

    }

    protected void show(Runnable action) {
//        lock();
        firstStage = !firstStage;
    }

    protected void update() {
        firstStage=!firstStage;

    }


    protected void dismiss(Runnable action) {
        lock();
    }

    private void lock() {

    }
}
