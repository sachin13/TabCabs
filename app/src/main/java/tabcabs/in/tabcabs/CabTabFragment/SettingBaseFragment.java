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
import android.widget.LinearLayout;

import tabcabs.in.tabcabs.Adapter.ItemOffsetDecoration;
import tabcabs.in.tabcabs.Adapter.SettingAdapter;
import tabcabs.in.tabcabs.Model.AnimationItem;
import tabcabs.in.tabcabs.R;


public abstract class SettingBaseFragment extends Fragment implements View.OnClickListener {

    private final Handler mHandler = new Handler();

    private AnimationItem[] mAnimationItems;
    private AnimationItem mSelectedItem;
    private RecyclerView mRecyclerView;
    private AppCompatSpinner mSpinner;
    LinearLayout back;


    android.support.v4.app.FragmentManager fragmentManager;

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

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        back= (LinearLayout) view.findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                DashBoardFragment hello = new DashBoardFragment();


                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).commit();


            }
        });


        setupRecyclerView();

        runLayoutAnimation(mRecyclerView, mSelectedItem);
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
        mRecyclerView.setAdapter(new SettingAdapter());
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(spacing));




















    }

}
