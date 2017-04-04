package com.teamtreehouse.colorizer;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private SeekBar saturationSeekBar;
    private int[] imageResIds = {R.drawable.cuba1, R.drawable.cuba2, R.drawable.cuba3};
    private int imageIndex = 0;
    private boolean color = true;
    private boolean red = true;
    private boolean green = true;
    private boolean blue = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        saturationSeekBar = (SeekBar)findViewById(R.id.saturationSeekBar);

        saturationSeekBar.setOnSeekBarChangeListener(saturationSeekBarListener);

        loadImage();
    }

    private void loadImage() {
        Glide.with(this).load(imageResIds[imageIndex]).into(imageView);
    }

    private void updateSaturation(int saturation) {
        ColorMatrix colorMatrix = new ColorMatrix();
        if (color) {
            red = green = blue = true;
            colorMatrix.setSaturation(saturation);
        } else {
            colorMatrix.setSaturation(0);
        }
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(colorFilter);
    }

    private void updateColors() {
        ColorMatrix colorMatrix = new ColorMatrix();
        float[] matrix = {
                1, 0, 0, 0, 0,
                0, 1, 0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0, 1, 0,
        };
        if (!red) matrix[0] = 0;
        if (!green) matrix[6] = 0;
        if (!blue) matrix[12] = 0;
        colorMatrix.set(matrix);
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(colorFilter);
    }

    /*Add a SeekBar for saturation*/
    private final SeekBar.OnSeekBarChangeListener saturationSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateSaturation(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
