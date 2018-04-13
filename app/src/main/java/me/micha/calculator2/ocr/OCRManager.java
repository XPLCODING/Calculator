package me.micha.calculator2.ocr;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Environment;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import me.micha.calculator2.MainActivity;

/**
 * Created by micha on 02.04.2018.
 */

public class OCRManager {

    private TessBaseAPI api;
    private static String datapath = MainActivity.getInstance().getFilesDir().getAbsolutePath() + "/tesseract_ocr";

    public OCRManager() {}

    public void init() {
        api = new TessBaseAPI();
        File dir = new File(datapath + "/tessdata");
        dir.mkdirs();
        api.init(datapath, "deu");
    }

    public static void copyFile() {
        AssetManager assetManager = MainActivity.getInstance().getApplicationContext().getAssets();
        try {
            InputStream in = assetManager.open("deu.traineddata");
            OutputStream out = new FileOutputStream(datapath + "/tessdata/deu.traineddata");
            byte[] buffer = new byte[1024];
            int read = in.read(buffer);
            while (read != -1) {
                out.write(buffer, 0, read);
                read = in.read(buffer);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public String recognize(Bitmap bitmap) {
        if(api == null) {
            init();
        }
        api.setImage(bitmap);
        String text = api.getUTF8Text().trim();
        api.end();
        return text;
    }

}
