package gs1.gestorsm.prototipegs1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Jorge on 28/11/2017.
 */

public class ImageManager {

    //Image to Base64 to store in DB.
    public String imgToBase64(Bitmap bitmap) {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            // compress image
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);

            out.flush();
            out.close();

            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Base64 to Image to insert in Image View.
    public byte[] base64ToImg(String str) {
        byte[] aux = Base64.decode(str, 1);
        return aux;
    }

    //Call to insert a Base64 image to ImageViewer.
    public void setStringImgtoImageView(ImageView view, String data) {
        byte[] img = base64ToImg(data);
        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        view.setImageBitmap(bitmap);
    }

    //Call to set a image to ImageViewer.
    public void setImagetoImageView(ImageView view, byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        view.setImageBitmap(bitmap);
    }
}
