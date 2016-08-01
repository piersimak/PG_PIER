package pier.com.pogo3;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pokegoapi.api.PokemonGo;
import com.pokegoapi.auth.CredentialProvider;
import com.pokegoapi.auth.PtcCredentialProvider;
import com.pokegoapi.exceptions.LoginFailedException;
import com.pokegoapi.exceptions.RemoteServerException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class SplashScreen extends AppCompatActivity {

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        context = this;
        new DBQueryTask().execute();
    }

    public void go()
    {
        Intent i = new Intent(context,MainActivity.class);
        startActivity(i);
    }

    public class DBQueryTask extends AsyncTask<Void, Void, PokemonGo> {

        @Override
        protected PokemonGo doInBackground(Void... params) {
            PokemonGo pokemonGo=null;
            //...populating array
            try {
                OkHttpClient httpClient = new OkHttpClient();
                CredentialProvider auth = new PtcCredentialProvider(httpClient, "pokemongopro332", "pokemongopro");
                pokemonGo = new PokemonGo(auth, httpClient);


            }catch (LoginFailedException L){}
            catch (RemoteServerException R){}
            return pokemonGo;//returning populated array
        }

        @Override
        protected void onPostExecute(PokemonGo pokemonGo) {
            //strings - array already populated
            //this method runs on the Main thread. Therefore you can create your spinner
            StaticFunction.pokemonGo = pokemonGo;
            if(pokemonGo!=null)
            new DBQueryTask2().execute();
            else new DBQueryTask().execute();
        }
    }


    public class DBQueryTask2 extends AsyncTask<Void, Void, List<PokemonModel> >{

        @Override
        protected List<PokemonModel> doInBackground(Void... params) {
            List<PokemonModel> pokemonModels = new ArrayList<>();
                for(int i=0; i<StaticFunction.pokemonGo.getInventories().getPokebank().getPokemons().size(); i++)
                {
                    String num = String.valueOf(StaticFunction.pokemonGo.getInventories().getPokebank().getPokemons().get(i).getPokemonId().getNumber());
                    Log.d("BACA",num);
                    pokemonModels.add(new PokemonModel("http://pokeapi.co/media/sprites/pokemon/"+num+".png"));
                }
            return pokemonModels;//returning populated array
        }

        @Override
        protected void onPostExecute(List<PokemonModel> pokemonGo) {
            //strings - array already populated
            //this method runs on the Main thread. Therefore you can create your spinner
            StaticFunction.pokemonModelList = pokemonGo;
            if(pokemonGo.size()>0)
                go();
            else new DBQueryTask2().execute();
        }
    }
}
