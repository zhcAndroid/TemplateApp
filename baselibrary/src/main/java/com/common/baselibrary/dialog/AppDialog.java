package com.common.baselibrary.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.common.baselibrary.AppManager;
import com.common.baselibrary.R;

/**
 * 创建日期：2017/5/5 on 16:54
 * 作者: @zhenghc
 */

public class AppDialog {
    /**
     * 退出程序
     */
    public static void Exit(final Context cont) {
        AlertDialog.Builder builder = new AlertDialog.Builder(cont);
        builder.setMessage("确定退出吗？");
        builder.setTitle(R.string.app_exit);
        builder.setPositiveButton(R.string.sure,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // 退出
                        AppManager.getAppManager().AppExit(cont);

                    }
                });
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

}
