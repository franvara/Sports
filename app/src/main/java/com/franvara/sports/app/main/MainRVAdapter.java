package com.franvara.sports.app.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.franvara.sports.R;
import com.franvara.sports.app.utils.PicassoCache;
import com.franvara.sports.domain.model.Player;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Player> playerList;
    private Context context;

    MainRVAdapter() {
        this.playerList = new ArrayList<>();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    void addPlayers(List<Player> players) {
        playerList.addAll(players);
        notifyDataSetChanged();
    }

    public void clearPlayers() {
        playerList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MainViewHolder) {
            final MainViewHolder mainViewHolder = (MainViewHolder) holder;
            final Player player = playerList.get(position);

            mainViewHolder.name.setText(player.getName() != null ? player.getName() : context.getString(R.string.string_null));
            mainViewHolder.surname.setText(player.getSurname() != null ? player.getSurname() : context.getString(R.string.string_null));
            mainViewHolder.date.setText(player.getDate() != null ? player.getDate() : "");
            mainViewHolder.sport.setText(player.getSport() != null ? player.getSport() : context.getString(R.string.string_null));

            Picasso pic = PicassoCache.getPicassoInstance(context);
            pic.load(String.valueOf(player.getImage())).error(R.drawable.ic_launcher_background).placeholder(R.drawable.ic_launcher_background).into(mainViewHolder.image);

        }
    }

    @Override
    public int getItemCount() {
        return playerList == null ? 0 : playerList.size();
    }

    //region ViewHolder

    class MainViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView name;
        @BindView(R.id.tvSurname)
        TextView surname;
        @BindView(R.id.tvDate)
        TextView date;
        @BindView(R.id.tvSport)
        TextView sport;
        @BindView(R.id.imageView)
        ImageView image;

        MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    //endregion

}
