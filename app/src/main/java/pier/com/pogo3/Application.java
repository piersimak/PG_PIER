package pier.com.pogo3;

import android.os.AsyncTask;

import com.pokegoapi.api.PokemonGo;
import com.pokegoapi.api.inventory.Inventories;
import com.pokegoapi.api.inventory.PokeBank;
import com.pokegoapi.api.player.PlayerProfile;
import com.pokegoapi.api.pokemon.Pokemon;
import com.pokegoapi.auth.CredentialProvider;
import com.pokegoapi.auth.PtcCredentialProvider;
import com.pokegoapi.exceptions.LoginFailedException;
import com.pokegoapi.exceptions.RemoteServerException;
import com.pokegoapi.util.Log;

import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by PIER on 7/31/2016.
 */
public class Application extends android.app.Application{

    public Application(){
       // new DBQueryTask().execute();
    }




}
