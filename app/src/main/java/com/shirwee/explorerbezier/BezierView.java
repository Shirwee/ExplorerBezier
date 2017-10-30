package com.shirwee.explorerbezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * explorer bezier
 * @author shirwee
 * @version 2017-10-27
 */
public class BezierView extends View {

    private int width;
    private int height;
    private Paint paint;
    /** 起始点*/
    private PointF start;
    /** 结束点*/
    private PointF end;
    /** 控制点*/
    private PointF control;
    /** 中心*/
    private PointF center;
    /** 用于保存二阶贝塞尔曲线 */
    private Path path;

    public BezierView(Context context) {
        this(context,null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
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
        control = new PointF();
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
        control.set(center.x,center.y/2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        control.x = event.getX();
        control.y = event.getY();
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
        canvas.drawPoint(control.x,control.y,paint);

        // draw line
        paint.setStrokeWidth(4);
        paint.setColor(Color.GRAY);
        canvas.drawLine(start.x,start.y,control.x,control.y,paint);
        canvas.drawLine(end.x,end.y,control.x,control.y,paint);

        // draw bezier
        paint.setStrokeWidth(8);
        paint.setColor(Color.RED);

        path = new Path();
        path.moveTo(start.x,start.y);
        path.quadTo(control.x,control.y,end.x,end.y);
        canvas.drawPath(path,paint);
    }


}
