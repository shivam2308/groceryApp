package com.amazaar.CommonCode.ImageCacheLoader;

import java.io.File;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.amazaar.Module.AmazaarApplication;

public class FileCache{

    private File cacheDir;

    public FileCache(Context context){
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(context.getFilesDir(),"TTImages_cache");
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            try {
                if (cacheDir.mkdirs()) {
                    Log.e("Folder", "Folder Created");
                } else {
                    Log.e("Folder", "Folder not Created");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    public File getFile(String url){
        //I identify images by hashcode. Not a perfect solution, good for the demo.
        String filename=String.valueOf(url.hashCode());
        //Another possible solution (thanks to grantland)
        //String filename = URLEncoder.encode(url);
        File f = new File(cacheDir, filename);
        return f;

    }

    public void clear(){
        File[] files=cacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }

}
