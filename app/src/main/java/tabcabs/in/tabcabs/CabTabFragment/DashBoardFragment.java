package tabcabs.in.tabcabs.CabTabFragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import tabcabs.in.tabcabs.Model.AnimationItem;
import tabcabs.in.tabcabs.R;


/**
 * Created by Patrick Ivarsson on 7/17/17.
 */
public class DashBoardFragment extends BaseFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_grid;
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
