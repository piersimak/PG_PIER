package pier.com.pogo3;

/**
 * Created by PIER on 8/1/2016.
 */
public class PokemonModel {
    private String gambar;

    public PokemonModel(String gambar) {
        this.gambar = gambar;
    }

    public String getGambar() {

        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
