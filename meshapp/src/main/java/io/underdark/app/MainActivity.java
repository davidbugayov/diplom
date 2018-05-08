package io.myMesh.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.concurrent.TimeUnit;

import io.myMesh.app.model.Node;

public class MainActivity extends AppCompatActivity
{
    private TextView peersTextView;
    private TextView framesTextView;
    private TextView framesTextView1;

    Node node;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peersTextView = (TextView) findViewById(R.id.peersTextView);
        framesTextView = (TextView) findViewById(R.id.framesTextView);
        framesTextView1 = (TextView) findViewById(R.id.framesTextView1);
        node = new Node(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        node.start();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if(node != null)
            node.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static boolean started = false;

    public void sendFrames(View view)
    {
		/*if(!started)
		{
			started = true;
			node = new Node(this);
			node.start();
			return;
		}*/
        int[] data = { 100, 200, 300, 400 };
        ByteBuffer byteBuffer = ByteBuffer.allocate(data.length * 4);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(data);

        byte[] array = byteBuffer.array();
        node.broadcastFrame(array);
        for(int i = 1; i < 10; i++) {
            System.out.println(i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        framesTextView1.setText("500000");
        Toast.makeText(this, "Общая сумма посчитана", Toast.LENGTH_LONG).show();

    }

    boolean isStarted = true;
    public void refreshPeers()
    {
        peersTextView.setText(node.getLinks().size() + " connected");
    }

    public void refreshFrames()
    {

        if(isStarted) {
            Toast.makeText(this, "Данные получены", Toast.LENGTH_LONG).show();

            Toast.makeText(this, "Локальная сумма посчитана посчитана", Toast.LENGTH_LONG).show();
            isStarted = false;
        }

    }
}
