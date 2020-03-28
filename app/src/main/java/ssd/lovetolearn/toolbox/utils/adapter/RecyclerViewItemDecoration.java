package ssd.lovetolearn.toolbox.utils.adapter;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Paint;
import android.graphics.Color;
import android.view.View;

public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration
{
      private  Paint mPaint;
    
    
    public RecyclerViewItemDecoration(Context context){
        mPaint=new Paint();
        mPaint.setColor(Color.GREEN);
        
     
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) != 0) {
            //直接设置为1px
            outRect.top = 1;
        }
    }

    
    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        super.onDraw(c, parent);
        
    
        }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent) {
        super.onDrawOver(c, parent);
    }

   
    
    
    
    
    
}
