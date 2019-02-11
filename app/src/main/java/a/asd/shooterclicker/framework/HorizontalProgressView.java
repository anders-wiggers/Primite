package a.asd.shooterclicker.framework;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

public class HorizontalProgressView extends RoundCornerProgressBar {
    private final int ANIMATION_DURATION = 50; //milliseconds

    public HorizontalProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void animateView(int fromProgress, int toProgress) {
        ValueAnimator mProgressAnimator = new ValueAnimator();

        mProgressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setProgress((Integer) animation.getAnimatedValue());
            }
        });

        mProgressAnimator.setDuration(ANIMATION_DURATION);
        mProgressAnimator.setIntValues(fromProgress, toProgress);
        mProgressAnimator.start();
    }
}
