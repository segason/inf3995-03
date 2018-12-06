package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.view.ListMusic.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.R;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.communication.CommunicationRest;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.snackbar.SnackBarSuccess;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.usersList.ListUserAdapter;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.usersList.RecyclerUserTouchHelper;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.components.usersList.UserListener;
import applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.service.ServiceGetList;


public class FragmentUsersList extends Fragment {

    public static View view;
    public static UserListener listenerUsers;

    public FragmentUsersList() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initSnackBar() {
        Intent intent = new Intent(getActivity(), ServiceGetList.class);
        SnackBarSuccess.make(view, getContext(), intent.getStringExtra(getString(R.string.welcome_message_key)), 3000);
        SnackBarSuccess.show();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_users_list);
        ListUserAdapter mAdapter = new ListUserAdapter();
        listenerUsers = new UserListener(mAdapter, getContext().getString(R.string.users_list),getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerUserTouchHelper(0, ItemTouchHelper.LEFT, listenerUsers);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.users_list_fragment, container, false);
        initRecyclerView();
        getUsersList();
//            initSnackBar();
        return view;
    }
    private void getUsersList(){
        final String GET = "GET";
        CommunicationRest communication = new CommunicationRest(
                getResources().getString(R.string.list_users),
                GET,
                view,
                FragmentUsersList.listenerUsers, FragmentBlackList.listenerUsers
        );
        communication.send(null);
    }
}