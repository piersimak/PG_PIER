package pier.com.pogo3;

/**
 * Created by PIER on 8/1/2016.
 */

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pokegoapi.api.pokemon.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by PIER on 7/18/2016.
 */


public class PokemonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    RecyclerView recyclerView;


    private boolean isLoading;
    List<Pokemon> pokemonModels;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public PokemonAdapter(List<Pokemon> pokemons, RecyclerView recyclerViewPokemon, Context con) {
        this.recyclerView = recyclerViewPokemon;
        this.pokemonModels = pokemons;

        this.context = con;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(view);

    }

    private SparseBooleanArray selectedItems;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        Pokemon pokemonModel = pokemonModels.get(position);
        PokemonViewHolder pokemonViewHolder = (PokemonViewHolder) holder;

        String num = String.valueOf(pokemonModel.getPokemonId().getNumber());
        String url = "http://pokeapi.co/media/sprites/pokemon/" + num + ".png";

        Picasso.with(context).load(url).into(pokemonViewHolder.gambarPokemon);
        pokemonViewHolder.txtnama.setText(pokemonModel.getPokemonId().name());
        pokemonViewHolder.txtcp.setText(pokemonModel.getCp()+" CP");
        pokemonViewHolder.txtnumber.setText("#"+String.valueOf(pokemonModel.getPokemonId().getNumber()));
    }


    @Override
    public int getItemCount() {
        return pokemonModels == null ? 0 : pokemonModels.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.gambarPokemon)
        ImageView gambarPokemon;
        @Bind(R.id.txtnama)
        TextView txtnama;
        @Bind(R.id.txtcp)
        TextView txtnumber;
        @Bind(R.id.txtnumber)
        TextView txtcp;
        public PokemonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}