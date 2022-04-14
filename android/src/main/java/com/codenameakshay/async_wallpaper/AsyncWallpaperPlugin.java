package com.codenameakshay.async_wallpaper;

import android.app.Activity;
import android.app.Application;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.net.Uri;
import android.content.ContentValues;
import android.content.*;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import android.content.Context;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;

/**
 * AsyncWallpaperPlugin
 */
public class AsyncWallpaperPlugin extends Application implements FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;
    public static Context context;
    private Activity activity;
    public static MethodChannel.Result res;
    private  int mheight;
    private  int mwidth;


    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap resource, Picasso.LoadedFrom from) {
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + "Image Downloaded");
            //            DisplayMetrics displayMetrics = new DisplayMetrics();
//            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            int height = displayMetrics.heightPixels;
//            int width = displayMetrics.widthPixels;
            SetWallPaperTask setWallPaperTask = new SetWallPaperTask(context, mheight, mwidth);
            setWallPaperTask.execute(new Pair(resource, "1"));
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };
    private Target target1 = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap resource, Picasso.LoadedFrom from) {
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + "Image Downloaded");
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            int height = displayMetrics.heightPixels;
//            int width = displayMetrics.widthPixels;
            SetWallPaperTask setWallPaperTask = new SetWallPaperTask(context, mheight, mwidth);
            setWallPaperTask.execute(new Pair(resource, "2"));
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };
    private Target target2 = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap resource, Picasso.LoadedFrom from) {
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + "Image Downloaded");
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            int height = displayMetrics.heightPixels;
//            int width = displayMetrics.widthPixels;
            SetWallPaperTask setWallPaperTask = new SetWallPaperTask(context, mheight, mwidth);
            setWallPaperTask.execute(new Pair(resource, "3"));
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };
    private Target target3 = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap resource, Picasso.LoadedFrom from) {
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + "Image Downloaded");
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            int height = displayMetrics.heightPixels;
//            int width = displayMetrics.widthPixels;
            SetWallPaperTask setWallPaperTask = new SetWallPaperTask(context, mheight, mwidth);
            setWallPaperTask.execute(new Pair(resource, "4"));
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "async_wallpaper");
        channel.setMethodCallHandler(this);
        context = flutterPluginBinding.getApplicationContext();
    }

    @Override
    public void onDetachedFromActivity() {
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding flutterPluginBinding) {
    }


    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding flutterPluginBinding) {
        activity = flutterPluginBinding.getActivity();
    }


    @Override
    public void onDetachedFromActivityForConfigChanges() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        res = result;
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("set_wallpaper")) {
            String url = call.argument("url"); // .argument returns the correct type
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + url);
            Picasso.get().load(url).into(target);
            // result.success(1);
        } else if (call.method.equals("set_wallpaper_file")) {
            String url = call.argument("url"); // .argument returns the correct type
            mheight=call.argument("height");
            mwidth=call.argument("width");
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + url);
            Picasso.get().load("file://" + url).into(target);
            // result.success(1);

        } else if (call.method.equals("set_lock_wallpaper")) {
            String url = call.argument("url");// .argument returns the correct type
            mheight=call.argument("height");
            mwidth=call.argument("width");
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + url);
            Picasso.get().load(url).into(target1);
            // result.success(1);

        } else if (call.method.equals("set_home_wallpaper")) {
            String url = call.argument("url"); // .argument returns the correct type
            mheight=call.argument("height");
            mwidth=call.argument("width");
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + url);
            Picasso.get().load(url).into(target2);
            // result.success(1);

        } else if (call.method.equals("set_both_wallpaper")) {
            String url = call.argument("url"); // .argument returns the correct type
            mheight=call.argument("height");
            mwidth=call.argument("width");
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + url);
            Picasso.get().load(url).into(target3);
            // result.success(1);

        } else if (call.method.equals("set_lock_wallpaper_file")) {
            String url = call.argument("url"); // .argument returns the correct type
            mheight=call.argument("height");
            mwidth=call.argument("width");
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + url);
            Picasso.get().load("file://" + url).into(target1);
            // result.success(1);

        } else if (call.method.equals("set_home_wallpaper_file")) {
            String url = call.argument("url"); // .argument returns the correct type
            mheight=call.argument("height");
            mwidth=call.argument("width");
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + url);
            Picasso.get().load("file://" + url).into(target2);
            // result.success(1);

        } else if (call.method.equals("set_both_wallpaper_file")) {
            String url = call.argument("url"); // .argument returns the correct type
            mheight=call.argument("height");
            mwidth=call.argument("width");
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + url);
            Picasso.get().load("file://" + url).into(target3);
            // result.success(1);

        } else if (call.method.equals("set_video_wallpaper")) {
            String url = call.argument("url"); // .argument returns the correct type
            android.util.Log.i("Arguments ", "configureFlutterEngine: " + url);
            // Picasso.get().load("file://" + url).into(target3);
            copyFile(new File(url), new File(activity.getFilesDir().toPath() + "/file.mp4"));
            VideoLiveWallpaper mVideoLiveWallpaper = new VideoLiveWallpaper();
            mVideoLiveWallpaper.setToWallPaper(context);
            result.success(true);

        } else {
            result.notImplemented();
        }
    }

    public void copyFile(File fromFile, File toFile) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannelInput = null;
        FileChannel fileChannelOutput = null;
        try {
            fileInputStream = new FileInputStream(fromFile);
            fileOutputStream = new FileOutputStream(toFile);
            fileChannelInput = fileInputStream.getChannel();
            fileChannelOutput = fileOutputStream.getChannel();
            fileChannelInput.transferTo(0, fileChannelInput.size(), fileChannelOutput);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (fileChannelInput != null)
                    fileChannelInput.close();
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (fileChannelOutput != null)
                    fileChannelOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }
}


class SetWallPaperTask extends AsyncTask<Pair<Bitmap, String>, Boolean, Boolean> {

    private final Context mContext;
    private final int mheight;
    private final int mwidth;




    public SetWallPaperTask(final Context context, final int height, final int width) {
        mContext = context;
        mheight = height;
        mwidth = width;

    }

    @Override
    protected final Boolean doInBackground(Pair<Bitmap, String>... pairs) {

        switch (pairs[0].second) {
            case "1": {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
                try {
                    Uri tempUri = getImageUri(mContext, pairs[0].first);
                    Log.i("Arguments ", "configureFlutterEngine: " + "Saved image to storage");
                    File finalFile = new File(getRealPathFromURI(tempUri));
                    Uri contentURI = getImageContentUri(mContext, finalFile.getAbsolutePath());
                    Log.i("Arguments ", "configureFlutterEngine: " + "Opening crop intent");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        mContext.startActivity(wallpaperManager.getCropAndSetWallpaperIntent(contentURI));
                    }
                    // wallpaperManager.setBitmap(pairs[0].first);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
            case "2": {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                        wallpaperManager.setWallpaperOffsetSteps(1,1);
                        wallpaperManager.suggestDesiredDimensions(mwidth, mheight);
                        wallpaperManager.setBitmap(pairs[0].first, null, true, WallpaperManager.FLAG_LOCK);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return false;
                }
                break;
            }
            case "3": {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setWallpaperOffsetSteps(1,1);
                        wallpaperManager.suggestDesiredDimensions(mwidth, mheight);
                        wallpaperManager.setBitmap(pairs[0].first, null, true, WallpaperManager.FLAG_SYSTEM);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return false;
                }
                break;
            }
            case "4": {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        wallpaperManager.setWallpaperOffsetSteps(1,1);
                        wallpaperManager.suggestDesiredDimensions(mwidth, mheight);
                        wallpaperManager.setBitmap(pairs[0].first, null, true, WallpaperManager.FLAG_LOCK );
                        wallpaperManager.setBitmap(pairs[0].first, null, true, WallpaperManager.FLAG_SYSTEM);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return false;
                }
                break;
            }

        }
        return true;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        myMethod(aBoolean);
    }

    private void myMethod(Boolean result) {
        AsyncWallpaperPlugin.res.success(result);
    }

    public static Uri getImageContentUri(Context context, String absPath) {

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{absPath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Integer.toString(id));

        } else if (!absPath.isEmpty()) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DATA, absPath);
            return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        } else {
            return null;
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        fixMediaDir();
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    void fixMediaDir() {
        File sdcard = Environment.getExternalStorageDirectory();
        if (sdcard != null) {
            File mediaDir = new File(sdcard, "DCIM/Camera");
            if (!mediaDir.exists()) {
                mediaDir.mkdirs();
            }
        }

        if (sdcard != null) {
            File mediaDir = new File(sdcard, "Pictures");
            if (!mediaDir.exists()) {
                mediaDir.mkdirs();
            }
        }
    }
}
