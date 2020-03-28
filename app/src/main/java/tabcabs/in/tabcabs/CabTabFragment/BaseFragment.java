package tabcabs.in.tabcabs.CabTabFragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import tabcabs.in.tabcabs.Adapter.CardAdapter;
import tabcabs.in.tabcabs.Adapter.ItemOffsetDecoration;
import tabcabs.in.tabcabs.Model.AnimationItem;
import tabcabs.in.tabcabs.R;

/**
 * Base class for layout animation demos
 *
 * Created by Patrick Ivarsson on 7/17/17.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private final Handler mHandler = new Handler();

    private AnimationItem[] mAnimationItems;
    private AnimationItem mSelectedItem;
    private RecyclerView mRecyclerView;
    private AppCompatSpinner mSpinner;

    android.support.v4.app.FragmentManager fragmentManager;
    SliderLayout sliderLayout;

    /**
     * Get the layout to use for the demo
     * @return the resource id
     */
    protected abstract int getLayoutResId();

    /**
     * Get the layout manager to use for the demo
     * @param context the context
     * @return the layout manager
     */
    protected abstract RecyclerView.LayoutManager getLayoutManager(Context context);

    /**
     * Get the array of animations to choose from
     * @return the array
     */
    protected abstract AnimationItem[] getAnimationItems();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAnimationItems = getAnimationItems();
        mSelectedItem = mAnimationItems[0];






        sliderLayout =(SliderLayout) view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        setSliderViews();




        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        setupRecyclerView();

        runLayoutAnimation(mRecyclerView, mSelectedItem);
    }








    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            SliderView sliderView = new SliderView(getActivity());

            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://slamonlineph.com/wp-content/uploads/Nike-Witness-History-LeBron-Ad-1.jpg");
                    break;
                case 1:
                    sliderView.setImageUrl("https://i.ytimg.com/vi/GP7lngAGpQk/maxresdefault.jpg");
                    break;
                case 2:
                    sliderView.setImageUrl("https://i.ytimg.com/vi/uKmc9iEi3R8/maxresdefault.jpg");
                    break;
                case 3:
                    sliderView.setImageUrl("https://freeblues.files.wordpress.com/2009/05/kobe-ad-2.jpg");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);


            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {




                    Post_Adv hello = new Post_Adv();

                    fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).commit();




                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }


    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onClick(View view) {

    }

    private void runLayoutAnimation(final RecyclerView recyclerView, final AnimationItem item) {
        final Context context = recyclerView.getContext();

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, item.getResourceId());

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void setupRecyclerView() {
        final Context context = mRecyclerView.getContext();
        final int spacing = getResources().getDimensionPixelOffset(R.dimen.default_spacing_small);
        mRecyclerView.setLayoutManager(getLayoutManager(context));
        mRecyclerView.setAdapter(new CardAdapter());
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spacing));




















    }

}
