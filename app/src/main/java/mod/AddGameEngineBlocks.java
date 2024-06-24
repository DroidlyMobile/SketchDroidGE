package mod;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import mod.agus.jcoderz.lib.FileUtil;

public class AddGameEngineBlocks {
    private Context context;
    private String blockpath = "";
    private String palettepath = "";

    public AddGameEngineBlocks(Context context){
        this.context = context;
        blockpath = FileUtil.getExternalStorageDir()
                .concat("/.sketchwarege/resources/block/My Block/");
        palettepath = FileUtil.getExternalStorageDir()
                .concat("/.sketchwarege/resources/block/My Block/");
        //if (!FileUtil.isExistFile(componentpath + "component.json")) {
            FileUtil.makeDir(blockpath);
        FileUtil.makeDir(palettepath);

            copyBlockPath();
            copyPalettePath();
        //}
    }

    public void copyBlockPath() {
        //Copys items from assets to whatever location you'd like
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {

        }
        for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            File outFile = null;
            if (filename.equals("block.json")) {
                try {
                    in = assetManager.open("block.json");
                    //sortFileString(in);
                    outFile = new File(blockpath, "block.json");

                    out = new FileOutputStream(outFile);
                    _copyFile(in, out);
                    in.close();
                    in = null;
                    out.flush();
                    out.close();
                    out = null;
                } catch (IOException e) {

                }
            }
        }
    }
    public void copyPalettePath() {
        //Copys items from assets to whatever location you'd like
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {

        }
        for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            File outFile = null;
            if (filename.equals("palette.json")) {
                try {
                    in = assetManager.open("palette.json");
                    //sortFileString(in);
                    outFile = new File(palettepath, "palette.json");

                    out = new FileOutputStream(outFile);
                    _copyFile(in, out);
                    in.close();
                    in = null;
                    out.flush();
                    out.close();
                    out = null;
                } catch (IOException e) {

                }
            }
        }
    }
    private void _copyFile(InputStream in, OutputStream out) {
        byte[] buffer = new byte[1024];
        int read;
        while (true){
            try {
                if (!((read = in.read(buffer)) !=-1)) break;
                out.write(buffer,0,read);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
