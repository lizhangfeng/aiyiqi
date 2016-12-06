/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uuzuche.lib_zxing.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.uuzuche.lib_zxing.R;
import com.uuzuche.lib_zxing.camera.CameraManager;

import java.util.Collection;
import java.util.HashSet;

/**
 * 自定义组件实现,扫描功能
 */
public final class ViewfinderView extends View {

    private static final long ANIMATION_DELAY = 100L;
    private static final int OPAQUE = 0xFF;

    private Paint paint, textPaint;
    private Bitmap resultBitmap;//扫码成功二维码图片
    private final int maskColor;
    private final int resultColor;
    private final int resultPointColor;
    private int textSize;
    //画有效点
    private Collection<ResultPoint> possibleResultPoints;
    private Collection<ResultPoint> lastPossibleResultPoints;

    // 扫描线移动的y
    private int scanLineTop;
    // 扫描线移动速度
    private int SCAN_VELOCITY;
    // 扫描线
    Bitmap scanLight;

    //扫码框的四个角绘制
    private Bitmap topLeftFramBounds;
    private Bitmap topRightFramBounds;
    private Bitmap bottomLeftFramBounds;
    private Bitmap bottomRightFramBounds;

    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textSize = DisplayUtil.dip2px(context, 16);
        textPaint.setTextSize(textSize);
        Resources resources = getResources();
        maskColor = resources.getColor(R.color.viewfinder_mask);
        resultColor = resources.getColor(R.color.result_view);
        resultPointColor = resources.getColor(R.color.possible_result_points);
        possibleResultPoints = new HashSet<ResultPoint>(5);

        scanLight = BitmapFactory.decodeResource(resources,
                R.drawable.qrcode_laser);

        topLeftFramBounds = BitmapFactory.decodeResource(resources,
                R.drawable.qrcode_corner_top_left);
        topRightFramBounds = BitmapFactory.decodeResource(resources,
                R.drawable.qrcode_corner_top_right);
        bottomLeftFramBounds = BitmapFactory.decodeResource(resources,
                R.drawable.qrcode_corner_bottom_left);
        bottomRightFramBounds = BitmapFactory.decodeResource(resources,
                R.drawable.qrcode_corner_bottom_right);

        initInnerRect(context, attrs);
    }

    /**
     * 初始化内部框的大小
     *
     * @param context
     * @param attrs
     */
    private void initInnerRect(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.innerrect);

        // 扫描框距离顶部
        float innerMarginTop = ta.getDimension(R.styleable.innerrect_inner_margintop, -1);
        if (innerMarginTop != -1) {
            CameraManager.FRAME_MARGINTOP = (int) innerMarginTop;
        }

        // 扫描框的宽度
        CameraManager.FRAME_WIDTH = (int) ta.getDimension(R.styleable.innerrect_inner_width, DisplayUtil.screenWidthPx * 3 / 4);

        // 扫描框的高度
        CameraManager.FRAME_HEIGHT = (int) ta.getDimension(R.styleable.innerrect_inner_height, DisplayUtil.screenWidthPx * 3 / 4);

        // 扫描框边角颜色
        innercornercolor = ta.getColor(R.styleable.innerrect_inner_corner_color, Color.parseColor("#45DDDD"));
        // 扫描框边角长度
        innercornerlength = (int) ta.getDimension(R.styleable.innerrect_inner_corner_length, 65);
        // 扫描框边角宽度
        innercornerwidth = (int) ta.getDimension(R.styleable.innerrect_inner_corner_width, 15);

        // 扫描bitmap
        Drawable drawable = ta.getDrawable(R.styleable.innerrect_inner_scan_bitmap);
        if (drawable != null) {

        }

        // 扫描控件
        scanLight = BitmapFactory.decodeResource(getResources(), ta.getResourceId(R.styleable.innerrect_inner_scan_bitmap, R.drawable.qrcode_laser));
        // 扫描速度
        SCAN_VELOCITY = ta.getInt(R.styleable.innerrect_inner_scan_speed, 5);

        ta.recycle();
    }

    @Override
    public void onDraw(Canvas canvas) {

        //拿到扫码框的矩形
        Rect frame = CameraManager.get().getFramingRect();

        if (frame == null) {
            return;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Draw the exterior (i.e. outside the framing rect) darkened
        paint.setColor(resultBitmap != null ? resultColor : maskColor);

        //画扫码框外的背景
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);


        if (resultBitmap != null) {
            // Draw the opaque result bitmap over the scanning rectangle
            paint.setAlpha(OPAQUE);
            canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
        } else {

            drawFrameBounds(canvas, frame);

            drawScanLight(canvas, frame);

            //开始画有效点
            Collection<ResultPoint> currentPossible = possibleResultPoints;
            Collection<ResultPoint> currentLast = lastPossibleResultPoints;
            if (currentPossible.isEmpty()) {
                lastPossibleResultPoints = null;
            } else {
                possibleResultPoints = new HashSet<ResultPoint>(5);
                lastPossibleResultPoints = currentPossible;
                paint.setAlpha(OPAQUE);
                paint.setColor(resultPointColor);
                for (ResultPoint point : currentPossible) {
                    canvas.drawCircle(frame.left + point.getX(), frame.top + point.getY(), 6.0f, paint);
                }
            }
            if (currentLast != null) {
                paint.setAlpha(OPAQUE / 2);
                paint.setColor(resultPointColor);
                for (ResultPoint point : currentLast) {
                    canvas.drawCircle(frame.left + point.getX(), frame.top + point.getY(), 3.0f, paint);
                }
            }

            postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top, frame.right, frame.bottom);

        }
    }


    /**
     * 绘制移动扫描线
     *
     * @param canvas
     * @param frame
     */
    private void drawScanLight(Canvas canvas, Rect frame) {

        if (scanLineTop == 0) {
            scanLineTop = frame.top;
        }

        if (scanLineTop >= frame.bottom - 30) {
            scanLineTop = frame.top;
        } else {
            scanLineTop += SCAN_VELOCITY;
        }
        Rect scanRect = new Rect(frame.left, scanLineTop, frame.right,
                scanLineTop + 30);
        canvas.drawBitmap(scanLight, null, scanRect, paint);
    }


    // 扫描框边角颜色
    private int innercornercolor;
    // 扫描框边角长度
    private int innercornerlength;
    // 扫描框边角宽度
    private int innercornerwidth;

    /**
     * 绘制取景框边框
     *
     * @param canvas
     * @param frame
     */
    private void drawFrameBounds(Canvas canvas, Rect frame) {

        paint.setColor(innercornercolor);
        paint.setStyle(Paint.Style.FILL);

//        int corWidth = innercornerwidth;
//        int corLength = innercornerlength;
        int corWidth = 45;//角的长度，单位:px
        // 左上角
        canvas.drawBitmap(topLeftFramBounds, frame.left, frame.top, null);
        // 右上角
        canvas.drawBitmap(topRightFramBounds, frame.right - corWidth, frame.top, null);
        // 左下角
        canvas.drawBitmap(bottomLeftFramBounds, frame.left, frame.bottom - corWidth, null);
        // 右下角
        canvas.drawBitmap(bottomRightFramBounds, frame.right - corWidth, frame.bottom - corWidth, null);

        String str = "将二维码放入框内即可自动扫描";

        canvas.drawText(str, (DisplayUtil.screenWidthPx - str.length() * textSize) / 2, frame.bottom + DisplayUtil.dip2px(getContext(), 40), textPaint);

    }


    public void drawViewfinder() {
        resultBitmap = null;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint point) {
        possibleResultPoints.add(point);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
