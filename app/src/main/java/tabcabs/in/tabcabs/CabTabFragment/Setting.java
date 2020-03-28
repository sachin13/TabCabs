package tabcabs.in.tabcabs.CabTabFragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import tabcabs.in.tabcabs.Model.AnimationItem;
import tabcabs.in.tabcabs.R;


public class Setting extends SettingBaseFragment  {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }

    @Override
    protected AnimationItem[] getAnimationItems() {
        return new AnimationItem[] {

                new AnimationItem("Scale random", R.anim.grid_layout_animation_scale)
        };
    }
}
