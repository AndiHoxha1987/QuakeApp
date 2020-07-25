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
        TextView locationPrimary;
        TextView date;
        TextView time;

        public QuakeViewHolder(@NonNull View itemView) {
            super(itemView);
            magnitude = itemView.findViewById(R.id.magnitude);
            locationOffset = itemView.findViewById(R.id.location_offset);
            locationPrimary = itemView.findViewById(R.id.primary_location);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }

        public void bind(Properties properties){

            String magnitudeString = formatMagnitude(properties.getQuake().getMagnitude());
            magnitude.setText(magnitudeString);
            String location= properties.getQuake().getCityName();
            String offset, primary;
            if(!location.contains("of ")){
                offset = "Near the";
                primary = location;
            }else {
                offset = location.substring(0,(location.indexOf("of")));
                primary = location.substring((location.indexOf("of")+3));
            }

            locationOffset.setText(offset);

            locationPrimary.setText(primary);

            String dateString = formatDate(properties.getQuake().getTimeInMilliseconds());
            date.setText(dateString);

            String timeString = formatTime(properties.getQuake().getTimeInMilliseconds());
            time.setText(timeString);
        }

        private String formatMagnitude(double magnitude) {
            DecimalFormat magnitudeFormat = new DecimalFormat("0.00");
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
    }
}
