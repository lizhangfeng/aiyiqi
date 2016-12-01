package com.bwf.aiyiqi.common.tools;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.bwf.aiyiqi.ui.App;
import com.bwf.aiyiqi.common.util.DisplayUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by Lizhangfeng on 2016/8/16 0016.
 * Description: facebook 显示图片封装
 */
public class ImageLoader {

    private static ImageLoader imageLoader;

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {

        if (imageLoader == null)
            imageLoader = new ImageLoader();
        return imageLoader;

    }

    /**
     * 显示Image
     *
     * @param mSimpleDraweeView
     * @param url
     */
    public void disPlayImage(SimpleDraweeView mSimpleDraweeView, String url, final OnImageLoadListener onImageLoadListener) {

        if (TextUtils.isEmpty(url))
            return;

        int width = 0;
        int heigth = 0;

        //如果layout里面没有设置宽高就给个默认高度
        width = mSimpleDraweeView.getWidth();
        heigth = mSimpleDraweeView.getHeight();

        if (width <= 0) {
            width = DisplayUtil.dip2px(App.getAppContext(), 40);
        }
        if (heigth <= 0) {
            heigth = DisplayUtil.dip2px(App.getAppContext(), 40);
        }

        //配置ImageRequest
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setProgressiveRenderingEnabled(true)//渐进式显示
                .setLocalThumbnailPreviewsEnabled(true)
                .setResizeOptions(new ResizeOptions(width, heigth))//支持缩放
                .build();

        //设置图片下载监听
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {

            //图片下载成功
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (onImageLoadListener != null)
                    onImageLoadListener.onImageLoadSuccess(imageInfo);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {

            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                if (onImageLoadListener != null)
                    onImageLoadListener.onImageLoadFailed(throwable == null ? "" : throwable.getMessage());
            }
        };

        //配置控制器
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(mSimpleDraweeView.getController())
                .setControllerListener(controllerListener)
                .build();
        mSimpleDraweeView.setController(controller);
    }


    public interface OnImageLoadListener {
        void onImageLoadSuccess(ImageInfo imageInfo);

        void onImageLoadFailed(String errMsg);
    }

}
