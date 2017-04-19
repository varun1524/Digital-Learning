package com.example.varun.fillcolor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.varun.digitallearning.R;

/**
 * Created by varun on 04/03/15.
 */
public class ImageAdapter extends BaseAdapter {

        /** The parent context */
        private Context myContext;
        // Put some images to project-folder: /res/drawable/
        // format: jpg, gif, png, bmp, ...
        private int[] myImageIds = {R.drawable.ic_color,R.drawable.fill_puppy,R.drawable.fill_flower,R.drawable.fill_butterfly,R.drawable.fill_house,R.drawable.fill_boy,R.drawable.fill_girl};

        /** Simple Constructor saving the 'parent' context. */
        public ImageAdapter(Context c) {
            this.myContext = c;
        }

        // inherited abstract methods - must be implemented
        // Returns count of images, and individual IDs
        public int getCount() {
            return this.myImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

    public View getView(int position, View convertView,
                        ViewGroup parent) {

        // Get a View to display image data
        ImageView iv = new ImageView(this.myContext);
        iv.setImageResource(this.myImageIds[position]);

        // Image should be scaled somehow
        //iv.setScaleType(ImageView.ScaleType.CENTER);
        //iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        //iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setScaleType(ImageView.ScaleType.FIT_END);

        // Set the Width & Height of the individual images
        iv.setLayoutParams(new Gallery.LayoutParams(95, 70));

        return iv;
    }
}
