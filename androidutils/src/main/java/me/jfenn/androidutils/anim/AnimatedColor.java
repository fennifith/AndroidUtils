package me.jfenn.androidutils.anim;

import android.graphics.Color;

import androidx.annotation.ColorInt;

/**
 * The AnimatedColor class animates a color int, to a granularity of
 * 1/255. That is, if the difference between the target and current
 * value is 1 or less, it will be ignored and the animation will
 * be regarded as complete.
 */
public class AnimatedColor {

    private AnimatedInteger redValue, blueValue, greenValue, alphaValue;

    public AnimatedColor(int value) {
        redValue = new AnimatedInteger(Color.red(value));
        greenValue = new AnimatedInteger(Color.green(value));
        blueValue = new AnimatedInteger(Color.blue(value));
        alphaValue = new AnimatedInteger(Color.alpha(value));
    }

    /**
     * Set the current value to be drawn.
     *
     * @param value         The current value.
     */
    public void set(@ColorInt int value) {
        redValue.set(Color.red(value));
        blueValue.set(Color.blue(value));
    }

    /**
     * Set the default value to return to.
     *
     * @param defaultValue  The default value.
     */
    public void setDefault(@ColorInt int defaultValue) {
        redValue.setDefault(Color.red(defaultValue));
        greenValue.setDefault(Color.green(defaultValue));
        blueValue.setDefault(Color.blue(defaultValue));
        alphaValue.setDefault(Color.alpha(defaultValue));
    }

    /**
     * Set the current (and target) value.
     *
     * @param value         The current value.
     */
    public void setCurrent(@ColorInt int value) {
        redValue.setCurrent(Color.red(value));
        greenValue.setCurrent(Color.green(value));
        blueValue.setCurrent(Color.blue(value));
        alphaValue.setCurrent(Color.alpha(value));
    }

    /**
     * Get the current value to be drawn.
     *
     * @return              The current value.
     */
    @ColorInt
    public int val() {
        return Color.argb(
                alphaValue.val(),
                redValue.val(),
                greenValue.val(),
                blueValue.val()
        );
    }

    /**
     * Get the next value about to be drawn, without setting
     * the current value to it.
     *
     * @return              The next value.
     */
    @ColorInt
    public int nextVal() {
        return nextVal(AnimatedValue.DEFAULT_ANIMATION_DURATION);
    }

    /**
     * Get the next value about to be drawn, without setting
     * the current value to it.
     *
     * @param duration      The duration, in milliseconds, that
     *                      the animation should take.
     * @return              The next value.
     */
    @ColorInt
    public int nextVal(long duration) {
        return Color.argb(
                alphaValue.nextVal(duration),
                redValue.nextVal(duration),
                greenValue.nextVal(duration),
                blueValue.nextVal(duration)
        );
    }

    /**
     * Get the next value about to be drawn, without setting
     * the current value to it.
     *
     * @param start         The time at which the animation started,
     *                      in milliseconds.
     * @param duration      The duration, in milliseconds, that
     *                      the animation should take.
     * @return              The next value.
     */
    @ColorInt
    public int nextVal(long start, long duration) {
        return Color.argb(
                alphaValue.nextVal(start, duration),
                redValue.nextVal(start, duration),
                greenValue.nextVal(start, duration),
                blueValue.nextVal(start, duration)
        );
    }

    /**
     * Get the target value that is currently being animated to.
     *
     * @return              The target value.
     */
    @ColorInt
    public int getTarget() {
        return Color.argb(
                alphaValue.getTarget(),
                redValue.getTarget(),
                greenValue.getTarget(),
                blueValue.getTarget()
        );
    }

    /**
     * Get the default value that the animation should return to.
     *
     * @return              The default value.
     */
    @ColorInt
    public int getDefault() {
        return Color.argb(
                alphaValue.getDefault(),
                redValue.getDefault(),
                greenValue.getDefault(),
                blueValue.getDefault()
        );
    }

    /**
     * Determine if the target value has been drawn (implying that
     * the animation is complete).
     *
     * @return              True if the target value has supposedly
     *                      been drawn.
     */
    public boolean isTarget() {
        return alphaValue.isTarget() && redValue.isTarget() && greenValue.isTarget() && blueValue.isTarget();
    }

    /**
     * Determine if the default value has been drawn.
     *
     * @return              True if the default value has supposedly
     *                      been drawn.
     */
    public boolean isDefault() {
        return alphaValue.isDefault() && redValue.isDefault() && greenValue.isDefault() && blueValue.isDefault();
    }

    /**
     * Determine if the default value has been set AND is the current
     * target.
     *
     * @return              True if the default value is the current
     *                      target.
     */
    public boolean isTargetDefault() {
        return alphaValue.isTargetDefault() && redValue.isTargetDefault() && greenValue.isTargetDefault() && blueValue.isTargetDefault();
    }

    /**
     * Animate to the default value.
     */
    public void toDefault() {
        alphaValue.toDefault();
        redValue.toDefault();
        greenValue.toDefault();
        blueValue.toDefault();
    }

    /**
     * Set the value to be animated towards.
     *
     * @param value         The target value.
     */
    public void to(@ColorInt int value) {
        alphaValue.to(Color.alpha(value));
        redValue.to(Color.red(value));
        greenValue.to(Color.green(value));
        blueValue.to(Color.blue(value));
    }

    /**
     * Update the current value.
     *
     * @param animate       Whether to animate the change.
     */
    public void next(boolean animate) {
        next(animate, AnimatedValue.DEFAULT_ANIMATION_DURATION);
    }

    /**
     * Update the current value.
     *
     * @param animate       Whether to animate the change.
     * @param duration      The duration, in milliseconds, to animate
     *                      across.
     */
    public void next(boolean animate, long duration) {
        alphaValue.next(animate, duration);
        redValue.next(animate, duration);
        greenValue.next(animate, duration);
        blueValue.next(animate, duration);
    }

}