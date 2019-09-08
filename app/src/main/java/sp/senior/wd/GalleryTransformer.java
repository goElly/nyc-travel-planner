package sp.senior.wd;

import android.support.v4.view.ViewPager;
import android.view.View;

public class GalleryTransformer implements ViewPager.PageTransformer {

    private static final float MAX_ALPHA=0.5f;
    private static final float MAX_SCALE=0.9f;

    @Override
    public void transformPage(View pager1, float position) {
        if(position<-1||position>1){
            //不可见区域
            pager1.setAlpha(MAX_ALPHA);
            pager1.setScaleX(MAX_SCALE);
            pager1.setScaleY(MAX_SCALE);
        }else {
            //可见区域，透明度效果
            if(position<=0){
                //pos区域[-1,0)
                pager1.setAlpha(MAX_ALPHA+MAX_ALPHA*(1+position));
            }else{
                //pos区域[0,1]
                pager1.setAlpha(MAX_ALPHA+MAX_ALPHA*(1-position));
            }
            //可见区域，缩放效果
            float scale=Math.max(MAX_SCALE,1-Math.abs(position));
            pager1.setScaleX(scale);
            pager1.setScaleY(scale);
        }
    }
}