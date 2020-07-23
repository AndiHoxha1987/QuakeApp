package com.example.testing.ui.main.quakesFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testing.R;
import com.example.testing.model.Properties;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuakesRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Properties> quakes = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new QuakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        ((QuakeViewHolder)holder).bind(quakes.get(position));
    }

    @Override
    public int getItemCount() {
        return quakes.size();
    }

    public void setPosts(List<Properties> properties){
        this.quakes = properties;
        notifyDataSetChanged();
    }

    public static class QuakeViewHolder extends RecyclerView.ViewHolder{

        TextView magnitude;
        TextView locationOffset;
        TextView primaryLocation;
        TextView date;
        TextView time;

        public QuakeViewHolder(@NonNull View itemView) {
            super(itemView);
            magnitude = itemView.findViewById(R.id.magnitude);
            locationOffset = itemView.findViewById(R.id.location_offset);
            primaryLocation = itemView.findViewById(R.id.primary_location);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }

        public void bind(Properties properties){

            String magnitudeString = formatMagnitude(properties.getQuake().getMagnitude());
            magnitude.setText(magnitudeString);

            primaryLocation.setText(properties.getQuake().getCityName());

            String dateString = formatDate(properties.getQuake().getTimeInMilliseconds());
            date.setText(dateString);

            String timeString = formatTime(properties.getQuake().getTimeInMilliseconds());
            time.setText(timeString);
        }

        private String formatMagnitude(double magnitude) {
            DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
            return magnitudeFormat.format(magnitude);
        }

        private String formatDate(long milliseconds) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.getDefault());
            return dateFormat.format(milliseconds);
        }

        private String formatTime(long milliseconds) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a",Locale.getDefault());
            return timeFormat.format(milliseconds);
        }

        private int getMagnitudeColor(double magnitude) {
            int magnitudeColorResourceId;
            int magnitudeFloor = (int) Math.floor(magnitude);
            switch (magnitudeFloor) {
                case 0:
                case 1:
                    magnitudeColorResourceId = R.color.magnitude1;
                    break;
                case 2:
                    magnitudeColorResourceId = R.color.magnitude2;
                    break;
                case 3:
                    magnitudeColorResourceId = R.color.magnitude3;
                    break;
                case 4:
                    magnitudeColorResourceId = R.color.magnitude4;
                    break;
                case 5:
                    magnitudeColorResourceId = R.color.magnitude5;
                    break;
                case 6:
                    magnitudeColorResourceId = R.color.magnitude6;
                    break;
                case 7:
                    magnitudeColorResourceId = R.color.magnitude7;
                    break;
                case 8:
                    magnitudeColorResourceId = R.color.magnitude8;
                    break;
                case 9:
                    magnitudeColorResourceId = R.color.magnitude9;
                    break;
                default:
                    magnitudeColorResourceId = R.color.magnitude10plus;
                    break;
            }
            return magnitudeColorResourceId;
        }
    }
}
