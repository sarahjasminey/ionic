package com.example.teddy_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;

public class Document_Upload_page extends AppCompatActivity implements Personal_Document_Upload.OnStepOneListener, vehicle_Document_Fragment.OnStepTwoListener, education_Document_Fragment.OnStepThreeListener{

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private NonSwipeableViewPager mViewPager;
    private StepperIndicator stepperIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document__upload_page);


        setTitle("Become Partner");
//        setToolbarBackVisibility(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        stepperIndicator = findViewById(R.id.stepperIndicator);
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        stepperIndicator.setViewPager(mViewPager);





        stepperIndicator.showLabels(false);
        // stepperIndicator.setViewPager(mViewPager);


        // or keep last page as "end page"
        stepperIndicator.setViewPager(mViewPager, mViewPager.getAdapter().getCount() - 1); //

        /*// or manual change
        indicator.setStepCount(3);
        indicator.setCurrentStep(2);
*/
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return Personal_Document_Upload.newInstance("", "");
                case 1:
                    return vehicle_Document_Fragment.newInstance("", "");
                case 2:
                    return education_Document_Fragment.newInstance("", "");
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "First Level";
                case 1:
                    return "Second Level";
                case 2:
                    return "Finish";
            }
            return null;
        }
    }

    @Override
    public void onNextPressed(Fragment fragment) {
        if (fragment instanceof Personal_Document_Upload) {
            mViewPager.setCurrentItem(1, true);
        } else if (fragment instanceof vehicle_Document_Fragment) {
            mViewPager.setCurrentItem(2, true);
        } else if (fragment instanceof education_Document_Fragment){
            mViewPager.setCurrentItem(3, true);
            Intent intent = new Intent(Document_Upload_page.this,Terms_Condition.class);
            startActivity(intent);
           // Toast.makeText(this, "Thanks For Registering", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

//    @Override
//    public void onBackPressed(Fragment fragment) {
//        if (fragment instanceof vehicle_Document_Fragment) {
//            mViewPager.setCurrentItem(0, true);
//        } else if (fragment instanceof education_Document_Fragment) {
//            mViewPager.setCurrentItem(1, true);
//        }
//    }
}