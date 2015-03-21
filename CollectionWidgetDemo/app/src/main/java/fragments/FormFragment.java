package fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.fullsail.android.collectionwidgetdemo.Person;
import com.fullsail.android.collectionwidgetdemo.R;

/**
 * Created by Richard Pingree MDF3 1503 Week 3 on 3/21/15.
 */
public class FormFragment extends Fragment {

    private final String TAG = "FormFragment";

    public EditText first, last, email;

    public Person newPerson;
    
    private FormListener mListener;

    public interface FormListener{
        public void addEntry(Person newPerson);
    }

    public FormFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof FormListener){
            mListener = (FormListener)activity;
        }else{
            throw new IllegalArgumentException("Containing activity must implement FormListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        first = (EditText)getView().findViewById(R.id.firstText);
        last = (EditText)getView().findViewById(R.id.lastText);
        email = (EditText)getView().findViewById(R.id.emailText);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_save:
                newPerson = new Person();

                newPerson.mFirst = first.getText().toString();
                newPerson.mLast = last.getText().toString();
                newPerson.mEmail = email.getText().toString();

                mListener.addEntry(newPerson);
                break;
            case R.id.action_clear:
                first.setText("");
                last.setText("");
                email.setText("");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
