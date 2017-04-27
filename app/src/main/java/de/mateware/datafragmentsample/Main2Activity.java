package de.mateware.datafragmentsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView textView;

    DataFragment dataFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataFragment = DataFragment.createOrRetainDataFragment(getSupportFragmentManager(), DataFragment.class);

        textView = (TextView) findViewById(R.id.textView);
        updateUi();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataFragment.addOne();
                updateUi();
            }
        });
    }

    private void updateUi() {
        textView.setText(String.valueOf(dataFragment.get()));
    }
}
