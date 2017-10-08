package ya.sample;

import java.text.*;
import java.util.*;
import android.os.*;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.CompoundButton.*;
import android.graphics.*;
import android.widget.AdapterView.*;
import android.content.*;

public class Sample1 extends AppCompatActivity implements Runnable {

    SampleView sv;
    Handler hn;
    float x, y, dx, dy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_sample1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        LinearLayout ll = new LinearLayout(this);
        setContentView(ll);

        hn = new Handler();
        hn.postDelayed(this, 10);

        sv = new SampleView(this);

        ll.addView(sv);

    }

    public void run()
    {
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        Display dp = wm.getDefaultDisplay();
        Point p = new Point();
        dp.getSize(p);

        int f = 40;
        if(x<0+f || x>p.x-f) dx = -dx;
        if(y<0+f || y>p.y-f) dy = -dy;

        x+=dx;
        y+=dy;

        sv.invalidate();

        hn.postDelayed(this, 10);
    }

    public void onDestroy()
    {
        super.onDestroy();
        hn.removeCallbacks(this);
    }

    class SampleView extends View
    {
        Paint p;
        int f = 40;
        public SampleView(Context cn)
        {
            super(cn);
            x = 0+f; y = 0+f;
            dx = 10; dy = 10;
            p = new Paint();
        }

        protected void onDraw(Canvas cv)
        {
            super.onDraw(cv);

            p.setColor(Color.GREEN);
            p.setStyle(Paint.Style.FILL);
            cv.drawCircle(x, y, 50, p);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_sample1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
