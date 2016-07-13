package in.junctiontech.searchb4kharch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FrontActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
/**
 * Creating all buttons instances
 * */
        // Dashboard News feed button
        Button btn_products = (Button) findViewById(R.id.btn_products);

        // Dashboard Friends button
        Button btn_flights = (Button) findViewById(R.id.btn_flights);

        // Dashboard Messages button
        Button btn_hotels = (Button) findViewById(R.id.btn_hotels);

        /**
         * Handling all button click events
         * */

        // Listening to News Feed button click
        btn_products.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });

        btn_hotels.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), HotelsActivity.class);
                startActivity(i);
            }
        });

        btn_flights.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), FlightsActivity.class);
                startActivity(i);
            }
        });

    }
}
