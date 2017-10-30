package com.shirwee.explorerbezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * explorer bezier
 * @author shirwee
 * @version 2017-10-27
 */
public class BezierView2
        extends View {

    private int width;
    private int height;
    private Paint paint;
    /** 起始点*/
    private PointF start;
    /** 结束点*/
    private PointF end;
    /** 控制点1*/
    private PointF c1;
    /** 控制点2*/
    private PointF c2;
    /** 中心*/
    private PointF center;
    /** 用于保存二阶贝塞尔曲线 */
    private Path path;
    private int index = 0;

    public BezierView2(Context context) {
        this(context,null);
    }

    public BezierView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        start = new PointF();
        end = new PointF();
        c1 = new PointF();
        c2 = new PointF();
        center = new PointF();

        path = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        center.x = width/2;
        center.y = height/2;

        start.set(center.x/2,center.y*3/2);
        end.set(center.x*3/2,center.y*3/2);
        c1.set(center.x+center.x/4,center.y/2);
        c2.set(center.x+center.x/2,center.y*3/4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.index == 0) {
            c1.x = event.getX();
            c1.y = event.getY();
        }else {
            c2.x = event.getX();
            c2.y = event.getY();
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw start , end and control point
        paint.setStrokeWidth(20);
        paint.setColor(Color.GRAY);
        canvas.drawPoint(start.x,start.y,paint);
        canvas.drawPoint(end.x,end.y,paint);
        canvas.drawPoint(c1.x,c1.y,paint);
        canvas.drawPoint(c2.x,c2.y,paint);

        // draw line
        paint.setStrokeWidth(4);
        paint.setColor(Color.GRAY);
        canvas.drawLine(start.x,start.y,c1.x,c1.y,paint);
        canvas.drawLine(end.x,end.y,c2.x,c2.y,paint);
        canvas.drawLine(c1.x,c1.y,c2.x,c2.y,paint);

        // draw bezier
        paint.setStrokeWidth(8);
        paint.setColor(Color.RED);

        path = new Path();
        path.moveTo(start.x,start.y);
        path.cubicTo(c1.x,c1.y,c2.x,c2.y,end.x,end.y);
        canvas.drawPath(path,paint);
    }

    /**
     * switch control point
     * @param index 0 mean c1 , 1 mean c2
     */
    public void selectControl(@IntRange(from = 0,to = 1) int index) {
        this.index = index;
    }

}
