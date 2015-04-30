package com.gmr.acacia.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class MainActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView status = (TextView) findViewById(R.id.status);

        App.getPlayerService().getPlayerState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PlayerState>()
                {
                    @Override
                    public void call(PlayerState playerState)
                    {
                        status.setText(getString(R.string.status, playerState.name()));
                    }
                });


        findViewById(R.id.play).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                App.getPlayerService().play("Cool song");
            }
        });

        findViewById(R.id.pause).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                App.getPlayerService().pause();
            }
        });

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                App.getPlayerService().stop();
            }
        });

        findViewById(R.id.trackInfo).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TrackInfo trackInfo = App.getPlayerService().getTrackInfo();

                if (trackInfo != null)
                {
                    Toast.makeText(MainActivity.this, trackInfo.toString(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, getString(R.string.not_playing), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
