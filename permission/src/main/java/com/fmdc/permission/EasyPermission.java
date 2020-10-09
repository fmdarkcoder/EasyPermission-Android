package com.fmdc.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;

import java.util.ArrayList;
import java.util.List;

public class EasyPermission {
    Activity activity;

    public EasyPermission(Activity activity){
        if(activity != null){
            this.activity = activity;
        }
    }
    public int checkPermission(String permission){
        return checkPermission(activity.getApplicationContext(),permission);
    }
    public void requestPermissions(List<String> permissions,int code){
        requestPermissions(activity,permissions,code);
    }
    private interface validateRPC{
        void requestPermissionCode(int code);
    }
    private interface validateRPR{
        void requestPermissionResult(int code,String[]permissions,int[]grantResults);
    }
    public static void requestPermissions(Activity activity, List<String> permissions,int code){
        if(activity == null){
            return;
        }
        if(permissions != null){
            List<String> requestList = new ArrayList<>();
            for(int id = 0; id < permissions.size(); id++){
                String permission = permissions.get(id);
                if (permission != null && checkPermission(activity.getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                    requestList.add(permission);
                }
            }
            if(requestList.size() > 0){
                baseRequestPermissions(activity,requestList.toArray(new String[0]),code);
            }
        }
    }
    public static int checkPermission(Context context,String permission){
        if(permission != null){
            return context.checkPermission(permission,Process.myPid(),Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }
    private static void baseRequestPermissions(final Activity activity, final String[] permissions, final int code){
        if(Build.VERSION.SDK_INT >= 23){
            if(activity instanceof validateRPC){
                ((validateRPC)activity).requestPermissionCode(code);
            }
            activity.requestPermissions(permissions,code);
        }else if(activity instanceof validateRPR){
            new Handler(Looper.getMainLooper()).post(() -> {
                int[] permissionsId = new int[permissions.length];
                PackageManager manager = activity.getPackageManager();
                String packageName = activity.getPackageName();
                int length = permissions.length;

                for(int id = 0; id < length; id++){
                    permissionsId[id] = manager.checkPermission(permissions[id],packageName);
                }
                ((validateRPR)activity).requestPermissionResult(code,permissions,permissionsId);
            });
        }
    }
}
