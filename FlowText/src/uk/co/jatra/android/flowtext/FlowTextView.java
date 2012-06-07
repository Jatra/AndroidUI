package uk.co.jatra.android.flowtext;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Class extending TexView to support flowing of text into a second continuation textview.
 * 
 * The continuation textview can be specified in the layout file using
 * the continuationView attribute, or by calling setContinuationView.
 * 
 * The continuation aspect works with left drawables, but is problematic for topDrawables.
 */
public class FlowTextView extends TextView {

    private static final String TAG = FlowTextView.class.getSimpleName();

    private static final String CONTINUATION_VIEW = "continuationView";

    /** The text display width */
    private int mTextWidth = -1;
    /** The text display height */
    private int mTextHeight = -1;
    
    /** The last character position being displayed. */
    private int mLastCharacter = -1;
    
    /** The View to hold overflow text */
    private TextView mContinuationView;

    /** continuation view id from attributeset */
    private int mContinuationViewId;
    
    public FlowTextView(Context context, AttributeSet set) {
        super(context, set);
        establishContinuationTextView(context, set);
    }
    /**
     * Establishes the continuation textview
     * 
     * @param context
     * @param set
     */
    private void establishContinuationTextView(Context context, AttributeSet set) {
        mContinuationViewId = set.getAttributeResourceValue(null, CONTINUATION_VIEW, 0);
    }
    
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            //retrieve continuation view from parent - only after this view has a parent.
            if (mContinuationView == null && mContinuationViewId > 0 && getParent() instanceof ViewGroup) {
                setContinuationView(((ViewGroup)getParent()).findViewById(mContinuationViewId));
            }
            if (getLayout() != null) {
                //FIXME: this isn't correct for all cases. 
                //TODO: This does not correctly handle drawableTop
                mTextWidth = getWidth();
                mTextHeight = getHeight()-getTotalPaddingTop()-getTotalPaddingBottom();
            }
            chainText();
        }
    }
    
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        chainText();
    }
    
    /**
     * Flow the text into any continuation view.
     */
    private void chainText() {
        if (mContinuationView != null && mTextWidth > -1) {
            mLastCharacter =  getOffsetForPosition(mTextWidth, mTextHeight);
            CharSequence text = getText();
            if (mLastCharacter > -1 && mLastCharacter < text.length()) {
                mContinuationView.setText(text.subSequence(mLastCharacter, text.length()));
            }
        }
    }
    
    /**
     * Find the last character to be displayed.
     * This is useful if the font is such that the string cannot be completely displayed.
     * @return the last character being displayed, or -1 to indicate the view has not been laid out.
     */
    public int getLastCharacterOffset() {
        return mLastCharacter;
    }
    
    /**
     * Specify the TextView to flow text into.
     * If the text will not fit into this view, the overflow text
     * will be set as the text of the continuation view.
     * Multiple FlowTextViews can be chained in this manner. 
     * @param tv the continuation view
     */
    public void setContinuationView(View view) {
        if (!(view instanceof TextView)) {
            throw new IllegalArgumentException("ContinuationViews must be TextViews (or subclass)");
        }
        mContinuationView = (TextView)view;
    }
    
}
