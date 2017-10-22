package android.support.v17.leanback.supportleanbackshowcase.app.settings;

import android.content.Context;
import android.support.v7.preference.PreferenceViewHolder;
import android.support.v7.preference.SeekBarPreference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * Created by yuejun on 2017/9/13.
 */

public class CustomSeekBarPreference extends SeekBarPreference {

    private boolean mTrackingTouch;
    private TextView mSeekBarValueTextView;
    private SeekBar mSeekBar;

    public CustomSeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomSeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
    }

    public CustomSeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs, android.support.v7.preference.R.attr.seekBarPreferenceStyle);
    }

    public CustomSeekBarPreference(Context context) {
        super(context, (AttributeSet)null);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder view) {
        mSeekBar = (SeekBar)view.findViewById(android.support.v7.preference.R.id.seekbar);
        super.onBindViewHolder(view);
//        final boolean mAdjustable = isAdjustable();
//        final int mMax = getMax();
//        final int mMin = getMin();
//
//        OnKeyListener mSeekBarKeyListener = new OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if(event.getAction() != 0) {
//                    return false;
//                } else if(!mAdjustable && (keyCode == 21 || keyCode == 22)) {
//                    return false;
//                } else if(keyCode != 23 && keyCode != 66) {
//                    if(mSeekBar == null) {
//                        Log.e("SeekBarPreference", "SeekBar view is null and hence cannot be adjusted.");
//                        return false;
//                    } else {
//                        return mSeekBar.onKeyDown(keyCode, event);
//                    }
//                } else {
//                    return false;
//                }
//            }
//        };
//
//        OnSeekBarChangeListener mSeekBarChangeListener = new OnSeekBarChangeListener() {
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if(fromUser && !CustomSeekBarPreference.this.mTrackingTouch) {
//                    syncValueInternal(seekBar);
//                }
//
//            }
//
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                CustomSeekBarPreference.this.mTrackingTouch = true;
//            }
//
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                CustomSeekBarPreference.this.mTrackingTouch = false;
//                if(seekBar.getProgress() + mMin != getValue()) {
//                    syncValueInternal(seekBar);
//                }
//
//            }
//        };
//        view.itemView.setOnKeyListener(mSeekBarKeyListener);
//        mSeekBarValueTextView = (TextView)view.findViewById(android.support.v7.preference.R.id.seekbar_value);
//        mSeekBarValueTextView.setVisibility(View.VISIBLE);
//
//        if(mSeekBar == null) {
//            Log.e("SeekBarPreference", "SeekBar view is null in onBindViewHolder.");
//        } else {
//            mSeekBar.setOnSeekBarChangeListener(mSeekBarChangeListener);
//            mSeekBar.setMax(getMax() - getMin());
//            mSeekBar.setKeyProgressIncrement(getSeekBarIncrement());
//
//            mSeekBar.setProgress(getValue() - getMin());
//            if(this.mSeekBarValueTextView != null) {
//                this.mSeekBarValueTextView.setText(String.valueOf(getValue()));
//            }
//
//            mSeekBar.setEnabled(this.isEnabled());
//        }
    }

    private void syncValueInternal(SeekBar seekBar) {
        int seekBarValue = getMin() + seekBar.getProgress();
        if(seekBarValue != getValue()) {
            if(this.callChangeListener(Integer.valueOf(seekBarValue))) {
                setValueInternal(seekBarValue, false);
            } else {
                seekBar.setProgress(getValue() - getMin());
            }
        }

    }

    private void setValueInternal(int seekBarValue, boolean notifyChanged) {
        if(seekBarValue < getMin()) {
            seekBarValue = getMin();
        }

        if(seekBarValue > getMax()) {
            seekBarValue = getMax();
        }

        if(seekBarValue != getValue()) {
            setValue(seekBarValue);
            if(this.mSeekBarValueTextView != null) {
                this.mSeekBarValueTextView.setText(String.valueOf(getValue()));
            }

            this.persistInt(seekBarValue);
            if(notifyChanged) {
                this.notifyChanged();
            }
        }

    }

    public SeekBar getSeekBar() {
        return this.mSeekBar;
    }
}
