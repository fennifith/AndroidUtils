package me.jfenn.androidutils.seekbar;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSeekBar;

public class SeekBarUtils {

    /**
     * Apply a color to an AppCompatSeekBar view.
     *
     * @param seekbar           The view to apply the color to.
     * @param color             The color to tint the view.
     */
    public static void setProgressBarColor(AppCompatSeekBar seekbar, @ColorInt int color) {
        seekbar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            seekbar.getThumb().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    /**
     * Apply a drawable to a seekbar's background track.
     *
     * @param seekbar           The seekbar to apply the drawable to.
     * @param drawable          The drawable to use as the track.
     * @param handleColor       The color of the seekbar's handle.
     */
    public static void setProgressBarDrawable(AppCompatSeekBar seekbar, @NonNull Drawable drawable, @ColorInt int handleColor) {
        Drawable background = new SeekBarBackgroundDrawable(drawable.mutate().getConstantState().newDrawable());
        background.setAlpha(127);

        LayerDrawable layers = new LayerDrawable(new Drawable[]{
                new SeekBarDrawable(drawable),
                background
        });

        layers.setId(0, android.R.id.progress);
        layers.setId(1, android.R.id.background);
        seekbar.setProgressDrawable(layers);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            seekbar.getThumb().setColorFilter(handleColor, PorterDuff.Mode.SRC_IN);
    }

}
