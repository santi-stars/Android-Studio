package com.svalero.listacompra.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class ImageUtils {

    /**
     * Se le manda un bitmap y devuelve un array de bytes
     *
     * @param imageView
     * @return
     */
    public static byte[] fromImageViewToByteArray(ImageView imageView) {
        // Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();
        return fromBitmapToByteArray(bitmap);
    }

    public static byte[] fromBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();
    }
}
