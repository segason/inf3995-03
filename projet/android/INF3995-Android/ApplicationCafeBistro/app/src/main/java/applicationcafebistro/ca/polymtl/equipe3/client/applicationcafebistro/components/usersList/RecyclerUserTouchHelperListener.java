package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.usersList;

import android.support.v7.widget.RecyclerView;

public interface RecyclerUserTouchHelperListener {
    /**
     * This method is call when we swipe an item in the recycler view
     * @param viewHolder
     * @param position
     */
    void onSwiped(RecyclerView.ViewHolder viewHolder, int position);

    /**
     * this method is call when we move an item in the recycler view
     * @param viewHolder
     * @param positionStart
     * @param positionEnd
     */
    void onMoved(RecyclerView.ViewHolder viewHolder, int positionStart, int positionEnd);
}
