package uk.co.jatra.android.texture;

import android.content.Context;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

public class TiledDrawable extends ShapeDrawable {
    public TiledDrawable(Context context, int resid, Shape shape) {
        setShape(shape);
        final BitmapDrawable tile = (BitmapDrawable) context.getResources()
                .getDrawable(resid);
        setShaderFactory(new ShaderFactory() {
            @Override
            public Shader resize(int width, int height) {
                return new BitmapShader(tile.getBitmap(),
                        Shader.TileMode.REPEAT, Shader.TileMode.REPEAT) {
                };
            }
        });
    }
}