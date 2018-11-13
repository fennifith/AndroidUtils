package me.jfenn.androidutils;

import android.graphics.Color;

import androidx.annotation.ColorInt;

public class ColorUtils {

    /**
     * Determine if a color is dark or not, using some magic numbers.
     *
     * @see [this confusing wikipedia article](https://en.wikipedia.org/wiki/Luma_%28video%29)
     *
     * @param color         A color int to determine the luminance of.
     * @return              True if the color should be considered "light".
     */
    public static boolean isColorDark(@ColorInt int color) {
        return getColorDarkness(color) >= 0.5;
    }

    /**
     * Determine the darkness of a color, using some magic numbers.
     *
     * @see [this confusing wikipedia article](https://en.wikipedia.org/wiki/Luma_%28video%29)
     *
     * @param color         A color int to determine the luminance of.
     * @return              The darkness of the color; a double between 0 and 1.
     */
    private static double getColorDarkness(@ColorInt int color) {
        if (color == Color.BLACK) return 1.0;
        else if (color == Color.WHITE || color == Color.TRANSPARENT) return 0.0;
        return (1 - (0.259 * Color.red(color) + 0.667 * Color.green(color) + 0.074 * Color.blue(color)) / 255);
    }

    /**
     * Calculates an opaque color that is equivalent to a translucent color drawn on top of
     * another.
     *
     * @param color         The (transparent) color to be drawn on top.
     * @param background    The (opaque) color to be drawn on top of.
     * @return              The opaque equivalent of the two colors.
     */
    @ColorInt
    public static int withBackground(@ColorInt int color, @ColorInt int background) {
        float alpha = Color.alpha(color) / 255f;
        return Color.rgb(
                (int) ((Color.red(color) * alpha) + (Color.red(background) * (1 - alpha))),
                (int) ((Color.green(color) * alpha) + (Color.green(background) * (1 - alpha))),
                (int) ((Color.blue(color) * alpha) + (Color.blue(background) * (1 - alpha)))
        );
    }

    public static int[] getHSVColorWheelArr(float saturation, float brightness) {
        int[] arr = new int[13];
        for (int i =  0; i <= 12; i++)
            arr[i] = Color.HSVToColor(new float[]{i * 30, saturation, brightness});

        return arr;
    }

}
