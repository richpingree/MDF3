package com.fullsail.android.collectionwidgetdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import fragments.DetailFragment;

public class DetailsActivity extends Activity implements DetailFragment.DetailListener{
	
	public static final String EXTRA_ITEM = "com.fullsail.android.DetailsActivity.EXTRA_ITEM";

    private Person mPerson;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		Intent intent = getIntent();
		mPerson= (Person)intent.getSerializableExtra(EXTRA_ITEM);
		if(mPerson == null) {
			finish();
			return;
		}

        getFragmentManager().beginTransaction().add(R.id.container, new DetailFragment()).commit();
//		TextView tv = (TextView)findViewById(R.id.firstTxt);
//		tv.setText(mPerson.getFirst());
//
//		tv = (TextView)findViewById(R.id.lastTxt);
//		tv.setText(mPerson.getLast());
//
//		tv = (TextView)findViewById(R.id.emailTxt);
//		tv.setText(mPerson.getEmail());
	}


    @Override
    public Person getPerson() {
        return mPerson;
    }
}
