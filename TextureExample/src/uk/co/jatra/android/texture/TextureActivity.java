package uk.co.jatra.android.texture;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class TextureActivity extends Activity
{
    private static final float RR_RADIUS = 10f;
    private static final float[] RR_RADII = new float[]{RR_RADIUS, RR_RADIUS, RR_RADIUS, RR_RADIUS, RR_RADIUS, RR_RADIUS, RR_RADIUS, RR_RADIUS};

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Shape rr1 = new RoundRectShape(RR_RADII, null, null);
        Shape rr2 = new RoundRectShape(RR_RADII, null, null);
        Shape rr3 = new RoundRectShape(RR_RADII, null, null);
        ShapeDrawable sd1 = new TiledDrawable(this, R.drawable.paper, rr1);
        ShapeDrawable sd2 = new TiledDrawable(this, R.drawable.paper, rr2);
        ShapeDrawable sd3 = new TiledDrawable(this, R.drawable.paper, rr3);
        

        View topView1 = (View)findViewById(R.id.topView);
        topView1.setBackgroundDrawable(sd1);
        
        
        TextView tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setBackgroundDrawable(sd3);
        
        ListView lv = (ListView)findViewById(R.id.listview);
        lv.setAdapter(new TextureAdaptor(this, R.layout.item));
    }
    
    static class TextureAdaptor extends ArrayAdapter<String> {
        private static String[] sData = {
            "one one one one one one one one one one one ",
            "two two two two two two two two two two two ",
            "threethree threethreethreethreethreethreethree ",
            "four four four four four four four four ",
            "five five five five five five five ",
            "one one one one one one one one one one one ",
            "two two two two two two two two two two two ",
            "threethree threethreethreethreethreethreethree ",
            "four four four four four four four four ",
            "five five five five five five five ",
            "one one one one one one one one one one one ",
            "two two two two two two two two two two two ",
            "threethree threethreethreethreethreethreethree ",
            "four four four four four four four four ",
            "five five five five five five five ",
            "one one one one one one one one one one one ",
            "two two two two two two two two two two two ",
            "threethree threethreethreethreethreethreethree ",
            "four four four four four four four four ",
            "five five five five five five five ",
            "one one one one one one one one one one one ",
            "two two two two two two two two two two two ",
            "threethree threethreethreethreethreethreethree ",
            "four four four four four four four four ",
            "five five five five five five five ",
        };
        private TiledDrawable mSd;
        @SuppressWarnings("unchecked")
        public TextureAdaptor(Context context, int textViewResourceId) {
            super(context, textViewResourceId, sData);
            RoundRectShape rr = new RoundRectShape(RR_RADII, null, null);
            mSd = new TiledDrawable(context, R.drawable.paper, rr);

        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View ret = convertView;
            if (ret == null) {
                ret = super.getView(position, convertView, parent);
                ret.setBackgroundDrawable(mSd);
            }
            return ret;
        }
    }
}
