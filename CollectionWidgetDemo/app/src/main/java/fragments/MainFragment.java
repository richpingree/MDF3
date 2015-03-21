package fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.fullsail.android.collectionwidgetdemo.Person;
import com.fullsail.android.collectionwidgetdemo.R;

import java.util.ArrayList;

/**
 * Created by Richard Pingree MDF3 1503 Week3 on 3/21/15.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private PersonListener mListener;
    private ArrayList<Person> mPersonList;
    ArrayAdapter mAdapter;

    public interface PersonListener{
        public ArrayList<Person> getPersons();
        public void viewPerson(int position);
    }

    public MainFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof PersonListener){
            mListener = (PersonListener)activity;
        }else{
            throw new IllegalArgumentException("Containing activity must implement PersonListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView personListView = (ListView)getView().findViewById(R.id.people_list);
        mAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mListener.getPersons());
        personListView.setAdapter(mAdapter);

        personListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.viewPerson(position);
            }
        });

    }

    public void updateList(){
        ListView personListView = (ListView)getView().findViewById(R.id.people_list);
        BaseAdapter adapter = (BaseAdapter)personListView.getAdapter();
        adapter.notifyDataSetChanged();
    }
}
