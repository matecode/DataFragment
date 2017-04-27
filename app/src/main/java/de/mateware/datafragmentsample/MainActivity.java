package de.mateware.datafragmentsample;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import de.mateware.datafragment.DataFragmentActivity;

public class MainActivity extends DataFragmentActivity<DataFragment> {

    TextView textView;

    public MainActivity() {
        super(DataFragment.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createOrRetainDataFragment();

        textView = (TextView) findViewById(R.id.textView);
        updateUi();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFragment().addOne();
                updateUi();
            }
        });
    }

    private void updateUi() {
        textView.setText(String.valueOf(getDataFragment().get()));
    }
}
