package pier.com.pogo3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gigamole.navigationtabbar.ntb.NavigationTabBar;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pokegoapi.api.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by GIGAMOLE on 28.03.2016.
 */
public class MainActivity extends Activity {
    public static String pokemonName = "[{\"Number\":\"001\",\"Name\":\"Bulbasaur\",\"Classification\":\"Seed Pokemon\",\"Type I\":[\"Grass\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Flying\",\"Psychic\"],\"Fast Attack(s)\":[\"Tackle\",\"Vine Whip\"],\"Weight\":\"6.9 kg\",\"Height\":\"0.7 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Bulbasaur candies\"},\"Next evolution(s)\":[{\"Number\":\"002\",\"Name\":\"Ivysaur\"},{\"Number\":\"003\",\"Name\":\"Venusaur\"}]},{\"Number\":\"002\",\"Name\":\"Ivysaur\",\"Classification\":\"Seed Pokemon\",\"Type I\":[\"Grass\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Flying\",\"Psychic\"],\"Fast Attack(s)\":[\"Razor Leaf\",\"Vine Whip\"],\"Weight\":\"13.0 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"001\",\"Name\":\"Bulbasaur\"}],\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"Bulbasaur candies\"},\"Next evolution(s)\":[{\"Number\":\"003\",\"Name\":\"Venusaur\"}]},{\"Number\":\"003\",\"Name\":\"Venusaur\",\"Classification\":\"Seed Pokemon\",\"Type I\":[\"Grass\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Flying\",\"Psychic\"],\"Fast Attack(s)\":[\"Razor Leaf\",\"Vine Whip\"],\"Weight\":\"100.0 kg\",\"Height\":\"2.0 m\",\"Previous evolution(s)\":[{\"Number\":\"001\",\"Name\":\"Bulbasaur\"},{\"Number\":\"002\",\"Name\":\"Ivysaur\"}]},{\"Number\":\"004\",\"Name\":\"Charmander\",\"Classification\":\"Lizard Pokemon\",\"Type I\":[\"Fire\"],\"Weaknesses\":[\"Water\",\"Ground\",\"Rock\"],\"Fast Attack(s)\":[\"Ember\",\"Scratch\"],\"Weight\":\"8.5 kg\",\"Height\":\"0.6 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Charmander candies\"},\"Next evolution(s)\":[{\"Number\":\"005\",\"Name\":\"Charmeleon\"},{\"Number\":\"006\",\"Name\":\"Charizard\"}]},{\"Number\":\"005\",\"Name\":\"Charmeleon\",\"Classification\":\"Flame Pokemon\",\"Type I\":[\"Fire\"],\"Weaknesses\":[\"Water\",\"Ground\",\"Rock\"],\"Fast Attack(s)\":[\"Ember\",\"\"],\"Weight\":\"19.0 kg\",\"Height\":\"1.1 m\",\"Previous evolution(s)\":[{\"Number\":\"004\",\"Name\":\"Charmander\"}],\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"Charmander candies\"},\"Next evolution(s)\":[{\"Number\":\"006\",\"Name\":\"Charizard\"}]},{\"Number\":\"006\",\"Name\":\"Charizard\",\"Classification\":\"Flame Pokemon\",\"Type I\":[\"Fire\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Water\",\"Electric\",\"Rock\"],\"Fast Attack(s)\":[\"Ember\",\"Wing Attack\"],\"Weight\":\"90.5 kg\",\"Height\":\"1.7 m\",\"Previous evolution(s)\":[{\"Number\":\"004\",\"Name\":\"Charmander\"},{\"Number\":\"005\",\"Name\":\"Charmeleon\"}]},{\"Number\":\"007\",\"Name\":\"Squirtle\",\"Classification\":\"Tiny Turtle Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Tackle\",\"Bubble\"],\"Weight\":\"9.0 kg\",\"Height\":\"0.5 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Squirtle candies\"},\"Next evolution(s)\":[{\"Number\":\"008\",\"Name\":\"Wartortle\"},{\"Number\":\"009\",\"Name\":\"Blastoise\"}]},{\"Number\":\"008\",\"Name\":\"Wartortle\",\"Classification\":\"Turtle Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Bite\",\"Water Gun\"],\"Weight\":\"22.5 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"007\",\"Name\":\"Squirtle\"}],\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"Squirtle candies\"},\"Next evolution(s)\":[{\"Number\":\"009\",\"Name\":\"Blastoise\"}]},{\"Number\":\"009\",\"Name\":\"Blastoise\",\"Classification\":\"Shellfish Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Bite\",\"Water Gun\"],\"Weight\":\"85.5 kg\",\"Height\":\"1.6 m\",\"Previous evolution(s)\":[{\"Number\":\"007\",\"Name\":\"Squirtle\"},{\"Number\":\"008\",\"Name\":\"Wartortle\"}]},{\"Number\":\"010\",\"Name\":\"Caterpie\",\"Classification\":\"Worm Pokemon\",\"Type I\":[\"Bug\"],\"Weaknesses\":[\"Fire\",\"Flying\",\"Rock\"],\"Fast Attack(s)\":[\"Bug Bite\",\"Tackle\"],\"Weight\":\"2.9 kg\",\"Height\":\"0.3 m\",\"Next Evolution Requirements\":{\"Amount\":12,\"Name\":\"Caterpie candies\"},\"Next evolution(s)\":[{\"Number\":\"011\",\"Name\":\"Metapod\"},{\"Number\":\"012\",\"Name\":\"Butterfree\"}]},{\"Number\":\"011\",\"Name\":\"Metapod\",\"Classification\":\"Cocoon Pokemon\",\"Type I\":[\"Bug\"],\"Weaknesses\":[\"Fire\",\"Flying\",\"Rock\"],\"Fast Attack(s)\":[\"Bug Bite\",\"Tackle\"],\"Weight\":\"9.9 kg\",\"Height\":\"0.7 m\",\"Previous evolution(s)\":[{\"Number\":\"010\",\"Name\":\"Caterpie\"}],\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Caterpie candies\"},\"Next evolution(s)\":[{\"Number\":\"012\",\"Name\":\"Butterfree\"}]},{\"Number\":\"012\",\"Name\":\"Butterfree\",\"Classification\":\"Butterfly Pokemon\",\"Type I\":[\"Bug\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Fire\",\"Electric\",\"Ice\",\"Flying\",\"Rock\"],\"Fast Attack(s)\":[\"Bug Bite\",\"Confusion\"],\"Weight\":\"32.0 kg\",\"Height\":\"1.1 m\",\"Previous evolution(s)\":[{\"Number\":\"010\",\"Name\":\"Caterpie\"},{\"Number\":\"011\",\"Name\":\"Metapod\"}]},{\"Number\":\"013\",\"Name\":\"Weedle\",\"Classification\":\"Hairy Pokemon\",\"Type I\":[\"Bug\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Flying\",\"Psychic\",\"Rock\"],\"Fast Attack(s)\":[\"Bug Bite\",\"Poison Sting\"],\"Weight\":\"3.2 kg\",\"Height\":\"0.3 m\",\"Next Evolution Requirements\":{\"Amount\":12,\"Name\":\"Weedle candies\"},\"Next evolution(s)\":[{\"Number\":\"014\",\"Name\":\"Kakuna\"},{\"Number\":\"015\",\"Name\":\"Beedrill\"}]},{\"Number\":\"014\",\"Name\":\"Kakuna\",\"Classification\":\"Cocoon Pokemon\",\"Type I\":[\"Bug\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Flying\",\"Psychic\",\"Rock\"],\"Fast Attack(s)\":[\"Bug Bite\",\"Posion Sting\"],\"Weight\":\"10.0 kg\",\"Height\":\"0.6 m\",\"Previous evolution(s)\":[{\"Number\":\"013\",\"Name\":\"Weedle\"}],\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Weedle candies\"},\"Next evolution(s)\":[{\"Number\":\"015\",\"Name\":\"Beedrill\"}]},{\"Number\":\"015\",\"Name\":\"Beedrill\",\"Classification\":\"Poison Bee Pokemon\",\"Type I\":[\"Bug\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Flying\",\"Psychic\",\"Rock\"],\"Fast Attack(s)\":[\"Bug Bite\",\"Poison Jab\"],\"Weight\":\"29.5 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"013\",\"Name\":\"Weedle\"},{\"Number\":\"014\",\"Name\":\"Kakuna\"}]},{\"Number\":\"016\",\"Name\":\"Pidgey\",\"Classification\":\"Tiny Bird Pokemon\",\"Type I\":[\"Normal\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Electric\",\"Rock\"],\"Fast Attack(s)\":[\"Quick Attack\",\"Tackle\"],\"Special Attack(s)\":[\"Aerial Ace\",\"Air Cutter\",\"Twister\"],\"Weight\":\"1.8 kg\",\"Height\":\"0.3 m\",\"Next Evolution Requirements\":{\"Amount\":12,\"Name\":\"Pidgey candies\"},\"Next evolution(s)\":[{\"Number\":\"017\",\"Name\":\"Pidgeotto\"},{\"Number\":\"018\",\"Name\":\"Pidgeot\"}]},{\"Number\":\"017\",\"Name\":\"Pidgeotto\",\"Classification\":\"Bird Pokemon\",\"Type I\":[\"Normal\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Electric\",\"Rock\"],\"Fast Attack(s)\":[\"Steel Wing\",\"Wing Attack\"],\"Special Attack(s)\":[\"Aerial Ace\",\"Air Cutter\",\"Twister\"],\"Weight\":\"30.0 kg\",\"Height\":\"1.1 m\",\"Previous evolution(s)\":[{\"Number\":\"016\",\"Name\":\"Pidgey\"}],\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Pidgey candies\"},\"Next evolution(s)\":[{\"Number\":\"018\",\"Name\":\"Pidgeot\"}]},{\"Number\":\"018\",\"Name\":\"Pidgeot\",\"Classification\":\"Bird Pokemon\",\"Type I\":[\"Normal\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Electric\",\"Rock\"],\"Fast Attack(s)\":[\"Steel Wing\",\"Wing Attack\"],\"Special Attack(s)\":[\"Hurricane\"],\"Weight\":\"39.5 kg\",\"Height\":\"1.5 m\",\"Previous evolution(s)\":[{\"Number\":\"016\",\"Name\":\"Pidgey\"},{\"Number\":\"017\",\"Name\":\"Pidgeotto\"}]},{\"Number\":\"019\",\"Name\":\"Rattata\",\"Classification\":\"Mouse Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Quick Attack\",\"Tackle\"],\"Special Attack(s)\":[\"Body Slam\",\"Dig\",\"Hyper Fang\"],\"Weight\":\"3.5 kg\",\"Height\":\"0.3 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Rattata candies\"},\"Next evolution(s)\":[{\"Number\":\"020\",\"Name\":\"Raticate\"}]},{\"Number\":\"020\",\"Name\":\"Raticate\",\"Classification\":\"Mouse Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Bite\",\"Quick Attack\"],\"Special Attack(s)\":[\"Dig\",\"Hyper Beam\",\"Hyper Fang\"],\"Weight\":\"18.5 kg\",\"Height\":\"0.7 m\",\"Previous evolution(s)\":[{\"Number\":\"019\",\"Name\":\"Rattata\"}]},{\"Number\":\"021\",\"Name\":\"Spearow\",\"Classification\":\"Tiny Bird Pokemon\",\"Type I\":[\"Normal\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Electric\",\"Rock\"],\"Fast Attack(s)\":[\"Peck\",\"Quick Attack\"],\"Weight\":\"2.0 kg\",\"Height\":\"0.3 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Spearow candies\"},\"Next evolution(s)\":[{\"Number\":\"022\",\"Name\":\"Fearow\"}]},{\"Number\":\"022\",\"Name\":\"Fearow\",\"Classification\":\"Beak Pokemon\",\"Type I\":[\"Normal\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Electric\",\"Rock\"],\"Fast Attack(s)\":[\"Peck\",\"Steel Wing\"],\"Weight\":\"38.0 kg\",\"Height\":\"1.2 m\",\"Previous evolution(s)\":[{\"Number\":\"021\",\"Name\":\"Spearow\"}]},{\"Number\":\"023\",\"Name\":\"Ekans\",\"Classification\":\"Snake Pokemon\",\"Type I\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Acid\",\"Poison Sting\"],\"Weight\":\"6.9 kg\",\"Height\":\"2.0 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Ekans candies\"},\"Next evolution(s)\":[{\"Number\":\"024\",\"Name\":\"Arbok\"}]},{\"Number\":\"024\",\"Name\":\"Arbok\",\"Classification\":\"Cobra Pokemon\",\"Type I\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Acid\",\"Bite\"],\"Weight\":\"65.0 kg\",\"Height\":\"3.5 m\",\"Previous evolution(s)\":[{\"Number\":\"023\",\"Name\":\"Ekans\"}]},{\"Number\":\"025\",\"Name\":\"Pikachu\",\"Classification\":\"Mouse Pokemon\",\"Type I\":[\"Electric\"],\"Weaknesses\":[\"Ground\"],\"Fast Attack(s)\":[\"Quick Attack\",\"Thunder Shock\"],\"Weight\":\"6.0 kg\",\"Height\":\"0.4 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Pikachu candies\"},\"Next evolution(s)\":[{\"Number\":\"026\",\"Name\":\"Raichu\"}]},{\"Number\":\"026\",\"Name\":\"Raichu\",\"Classification\":\"Mouse Pokemon\",\"Type I\":[\"Electric\"],\"Weaknesses\":[\"Ground\"],\"Fast Attack(s)\":[\"Thunder Shock\",\"Spark\"],\"Weight\":\"30.0 kg\",\"Height\":\"0.8 m\",\"Previous evolution(s)\":[{\"Number\":\"025\",\"Name\":\"Pikachu\"}]},{\"Number\":\"027\",\"Name\":\"Sandshrew\",\"Classification\":\"Mouse Pokemon\",\"Type I\":[\"Ground\"],\"Weaknesses\":[\"Water\",\"Grass\",\"Ice\"],\"Fast Attack(s)\":[\"Mud Shot\",\"Scratch\"],\"Weight\":\"12.0 kg\",\"Height\":\"0.6 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Sandshrew candies\"},\"Next evolution(s)\":[{\"Number\":\"028\",\"Name\":\"Sandslash\"}]},{\"Number\":\"028\",\"Name\":\"Sandslash\",\"Classification\":\"Mouse Pokemon\",\"Type I\":[\"Ground\"],\"Weaknesses\":[\"Water\",\"Grass\",\"Ice\"],\"Fast Attack(s)\":[\"Metal Claw\",\"Mud Shot\"],\"Weight\":\"29.5 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"027\",\"Name\":\"Sandshrew\"}]},{\"Number\":\"029\",\"Name\":\"Nidoran F\",\"Classification\":\"Poison Pin Pokemon\",\"Type I\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Bite\",\"Poison Sting\"],\"Weight\":\"7.0 kg\",\"Height\":\"0.4 m\",\"Next evolution(s)\":[{\"Number\":\"030\",\"Name\":\"Nidorina\"},{\"Number\":\"031\",\"Name\":\"Nidoqueen\"}]},{\"Number\":\"030\",\"Name\":\"Nidorina\",\"Classification\":\"Poison Pin Pokemon\",\"Type I\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Bite\",\"Poison Sting\"],\"Weight\":\"20.0 kg\",\"Height\":\"0.8 m\",\"Previous evolution(s)\":[{\"Number\":\"029\",\"Name\":\"Nidoran  F\"}],\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"Nidoran F candies\"},\"Next evolution(s)\":[{\"Number\":\"031\",\"Name\":\"Nidoqueen\"}]},{\"Number\":\"031\",\"Name\":\"Nidoqueen\",\"Classification\":\"Drill Pokemon\",\"Type I\":[\"Poison\"],\"Type II\":[\"Ground\"],\"Weaknesses\":[\"Water\",\"Ice\",\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Bite\",\"Poison Jab\"],\"Weight\":\"60.0 kg\",\"Height\":\"1.3 m\",\"Previous evolution(s)\":[{\"Number\":\"029\",\"Name\":\"Nidoran F\"},{\"Number\":\"030\",\"Name\":\"Nidorina\"}]},{\"Number\":\"032\",\"Name\":\"Nidoran M\",\"Classification\":\"Poison Pin Pokemon\",\"Type I\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Peck\",\"Poison Sting\"],\"Weight\":\"9.0 kg\",\"Height\":\"0.5 m\",\"Next evolution(s)\":[{\"Number\":\"033\",\"Name\":\"Nidorino\"},{\"Number\":\"034\",\"Name\":\"Nidoking\"}]},{\"Number\":\"033\",\"Name\":\"Nidorino\",\"Classification\":\"Poison Pin Pokemon\",\"Type I\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Bite\",\"Poison Jab\"],\"Weight\":\"19.5 kg\",\"Height\":\"0.9 m\",\"Previous evolution(s)\":[{\"Number\":\"032\",\"Name\":\"Nidoran M\"}],\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"NidoranM candies\"},\"Next evolution(s)\":[{\"Number\":\"034\",\"Name\":\"Nidoking\"}]},{\"Number\":\"034\",\"Name\":\"Nidoking\",\"Classification\":\"Drill Pokemon\",\"Type I\":[\"Poison\"],\"Type II\":[\"Ground\"],\"Weaknesses\":[\"Water\",\"Ice\",\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Fury Cutter\",\"Poison Jab\"],\"Weight\":\"62.0 kg\",\"Height\":\"1.4 m\",\"Previous evolution(s)\":[{\"Number\":\"032\",\"Name\":\"Nidoran M\"},{\"Number\":\"033\",\"Name\":\"Nidorino\"}]},{\"Number\":\"035\",\"Name\":\"Clefairy\",\"Classification\":\"Fairy Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Pound\",\"Zen Headbutt\"],\"Weight\":\"7.5 kg\",\"Height\":\"0.6 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Clefairy candies\"},\"Next evolution(s)\":[{\"Number\":\"036\",\"Name\":\"Clefable\"}]},{\"Number\":\"036\",\"Name\":\"Clefable\",\"Classification\":\"Fairy Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Pound\",\"Zen Headbutt\"],\"Weight\":\"40.0 kg\",\"Height\":\"1.3 m\",\"Previous evolution(s)\":[{\"Number\":\"035\",\"Name\":\"Clefairy\"}]},{\"Number\":\"037\",\"Name\":\"Vulpix\",\"Classification\":\"Fox Pokemon\",\"Type I\":[\"Fire\"],\"Weaknesses\":[\"Water\",\"Ground\",\"Rock\"],\"Fast Attack(s)\":[\"Ember\",\"Quick Attack\"],\"Weight\":\"9.9 kg\",\"Height\":\"0.6 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Vulpi\"},\"Next evolution(s)\":[{\"Number\":\"038\",\"Name\":\"Ninetales\"}]},{\"Number\":\"038\",\"Name\":\"Ninetales\",\"Classification\":\"Fox Pokemon\",\"Type I\":[\"Fire\"],\"Weaknesses\":[\"Water\",\"Ground\",\"Rock\"],\"Fast Attack(s)\":[\"Ember\",\"Quick Attack\"],\"Weight\":\"19.9 kg\",\"Height\":\"1.1 m\",\"Previous evolution(s)\":[{\"Number\":\"037\",\"Name\":\"Vulpix\"}]},{\"Number\":\"039\",\"Name\":\"Jigglypuff\",\"Classification\":\"Balloon Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Feint Attack\",\"Pound\"],\"Weight\":\"5.5 kg\",\"Height\":\"0.5 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Jigglypuff candies\"},\"Next evolution(s)\":[{\"Number\":\"039\",\"Name\":\"Jigglypuff\"}]},{\"Number\":\"040\",\"Name\":\"Wigglytuff\",\"Classification\":\"Balloon Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Feint Attack\",\"Pound\"],\"Weight\":\"12.0 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"040\",\"Name\":\"Wigglytuff\"}]},{\"Number\":\"041\",\"Name\":\"Zubat\",\"Classification\":\"Bat Pokemon\",\"Type I\":[\"Poison\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Electric\",\"Ice\",\"Psychic\",\"Rock\"],\"Fast Attack(s)\":[\"Bite\",\"Quick Attack\"],\"Weight\":\"7.5 kg\",\"Height\":\"0.8 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Zubat candies\"},\"Next evolution(s)\":[{\"Number\":\"042\",\"Name\":\"Golbat\"}]},{\"Number\":\"042\",\"Name\":\"Golbat\",\"Classification\":\"Bat Pokemon\",\"Type I\":[\"Poison\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Electric\",\"Ice\",\"Psychic\",\"Rock\"],\"Fast Attack(s)\":[\"Bite\",\"Wing Attack\"],\"Weight\":\"55.0 kg\",\"Height\":\"1.6 m\",\"Previous evolution(s)\":[{\"Number\":\"041\",\"Name\":\"Zubat\"}]},{\"Number\":\"043\",\"Name\":\"Oddish\",\"Classification\":\"Weed Pokemon\",\"Type I\":[\"Grass\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Flying\",\"Psychic\"],\"Fast Attack(s)\":[\"Acid\",\"Razor Leaf\"],\"Weight\":\"5.4 kg\",\"Height\":\"0.5 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Oddish candies\"},\"Next evolution(s)\":[{\"Number\":\"044\",\"Name\":\"Gloom\"},{\"Number\":\"045\",\"Name\":\"Vileplume\"}]},{\"Number\":\"044\",\"Name\":\"Gloom\",\"Classification\":\"Weed Pokemon\",\"Type I\":[\"Grass\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Flying\",\"Psychic\"],\"Fast Attack(s)\":[\"Acid\",\"Razor Leaf\"],\"Weight\":\"8.6 kg\",\"Height\":\"0.8 m\",\"Previous evolution(s)\":[{\"Number\":\"043\",\"Name\":\"Oddish\"}],\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"Oddish candies\"},\"Next evolution(s)\":[{\"Number\":\"045\",\"Name\":\"Vileplume\"}]},{\"Number\":\"045\",\"Name\":\"Vileplume\",\"Classification\":\"Flower Pokemon\",\"Type I\":[\"Grass\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Flying\",\"Psychic\"],\"Fast Attack(s)\":[\"Acid\",\"\"],\"Weight\":\"18.6 kg\",\"Height\":\"1.2 m\",\"Previous evolution(s)\":[{\"Number\":\"043\",\"Name\":\"Oddish\"},{\"Number\":\"044\",\"Name\":\"Gloom\"}]},{\"Number\":\"046\",\"Name\":\"Paras\",\"Classification\":\"Mushroom Pokemon\",\"Type I\":[\"Bug\"],\"Type II\":[\"Grass\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Poison\",\"Flying\",\"Bug\",\"Rock\"],\"Fast Attack(s)\":[\"Bug Bite\",\"Scratch\"],\"Weight\":\"5.4 kg\",\"Height\":\"0.3 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Paras candies\"},\"Next evolution(s)\":[{\"Number\":\"047\",\"Name\":\"Parasect\"}]},{\"Number\":\"047\",\"Name\":\"Parasect\",\"Classification\":\"Mushroom Pokemon\",\"Type I\":[\"Bug\"],\"Type II\":[\"Grass\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Poison\",\"Flying\",\"Bug\",\"Rock\"],\"Fast Attack(s)\":[\"Bug Bite\",\"Fury Cutter\"],\"Weight\":\"29.5 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"046\",\"Name\":\"Paras\"}]},{\"Number\":\"048\",\"Name\":\"Venonat\",\"Classification\":\"Insect Pokemon\",\"Type I\":[\"Bug\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Flying\",\"Psychic\",\"Rock\"],\"Fast Attack(s)\":[\"Bug Bite\",\"Confusion\"],\"Weight\":\"30.0 kg\",\"Height\":\"1.0 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Venonat candies\"},\"Next evolution(s)\":[{\"Number\":\"049\",\"Name\":\"Venomoth\"}]},{\"Number\":\"049\",\"Name\":\"Venomoth\",\"Classification\":\"Poison Moth Pokemon\",\"Type I\":[\"Bug\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Flying\",\"Psychic\",\"Rock\"],\"Fast Attack(s)\":[\"Bug Bite\",\"Confusion\"],\"Weight\":\"12.5 kg\",\"Height\":\"1.5 m\",\"Previous evolution(s)\":[{\"Number\":\"048\",\"Name\":\"Venonat\"}]},{\"Number\":\"050\",\"Name\":\"Diglett\",\"Classification\":\"Mole Pokemon\",\"Type I\":[\"Ground\"],\"Weaknesses\":[\"Water\",\"Grass\",\"Ice\"],\"Fast Attack(s)\":[\"Mud Shot\",\"Scratch\"],\"Weight\":\"0.8 kg\",\"Height\":\"0.2 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Diglett candies\"},\"Next evolution(s)\":[{\"Number\":\"051\",\"Name\":\"Dugtrio\"}]},{\"Number\":\"051\",\"Name\":\"Dugtrio\",\"Classification\":\"Mole Pokemon\",\"Type I\":[\"Ground\"],\"Weaknesses\":[\"Water\",\"Grass\",\"Ice\"],\"Fast Attack(s)\":[\"Mud Shot\",\"Sucker Punch\"],\"Weight\":\"33.3 kg\",\"Height\":\"0.7 m\",\"Previous evolution(s)\":[{\"Number\":\"050\",\"Name\":\"Diglett\"}]},{\"Number\":\"052\",\"Name\":\"Meowth\",\"Classification\":\"Scratch Cat Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Bite\",\"Scratch\"],\"Weight\":\"4.2 kg\",\"Height\":\"0.4 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Meowth candies\"},\"Next evolution(s)\":[{\"Number\":\"053\",\"Name\":\"Persian\"}]},{\"Number\":\"053\",\"Name\":\"Persian\",\"Classification\":\"Classy Cat Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Feint Attack\",\"Scratch\"],\"Weight\":\"32.0 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"052\",\"Name\":\"Meowth\"}]},{\"Number\":\"054\",\"Name\":\"Psyduck\",\"Classification\":\"Duck Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Water Gun\",\"Zen Headbutt\"],\"Weight\":\"19.6 kg\",\"Height\":\"0.8 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Psyduck candies\"},\"Next evolution(s)\":[{\"Number\":\"055\",\"Name\":\"Golduck\"}]},{\"Number\":\"055\",\"Name\":\"Golduck\",\"Classification\":\"Duck Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Confusion\",\"Zen Headbutt\"],\"Weight\":\"76.6 kg\",\"Height\":\"1.7 m\",\"Previous evolution(s)\":[{\"Number\":\"054\",\"Name\":\"Psyduck\"}]},{\"Number\":\"056\",\"Name\":\"Mankey\",\"Classification\":\"Pig Monkey Pokemon\",\"Type I\":[\"Fighting\"],\"Weaknesses\":[\"Flying\",\"Psychic\",\"Fairy\"],\"Fast Attack(s)\":[\"Karate Chop\",\"Scratch\"],\"Weight\":\"28.0 kg\",\"Height\":\"0.5 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Mankey candies\"},\"Next evolution(s)\":[{\"Number\":\"057\",\"Name\":\"Primeape\"}]},{\"Number\":\"057\",\"Name\":\"Primeape\",\"Classification\":\"Pig Monkey Pokemon\",\"Type I\":[\"Fighting\"],\"Weaknesses\":[\"Flying\",\"Psychic\",\"Fairy\"],\"Fast Attack(s)\":[\"Karate Chop\",\"Low Kick\"],\"Weight\":\"32.0 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"056\",\"Name\":\"Mankey\"}]},{\"Number\":\"058\",\"Name\":\"Growlithe\",\"Classification\":\"Puppy Pokemon\",\"Type I\":[\"Fire\"],\"Weaknesses\":[\"Water\",\"Ground\",\"Rock\"],\"Fast Attack(s)\":[\"Bite\",\"Ember\"],\"Weight\":\"19.0 kg\",\"Height\":\"0.7 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Growlithe candies\"},\"Next evolution(s)\":[{\"Number\":\"059\",\"Name\":\"Arcanine\"}]},{\"Number\":\"059\",\"Name\":\"Arcanine\",\"Classification\":\"Legendary Pokemon\",\"Type I\":[\"Fire\"],\"Weaknesses\":[\"Water\",\"Ground\",\"Rock\"],\"Fast Attack(s)\":[\"Bite\",\"Fire Fang\"],\"Weight\":\"155.0 kg\",\"Height\":\"1.9 m\",\"Previous evolution(s)\":[{\"Number\":\"058\",\"Name\":\"Growlithe\"}]},{\"Number\":\"060\",\"Name\":\"Poliwag\",\"Classification\":\"Tadpole Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Bubble\",\"Mud Shot\"],\"Weight\":\"12.4 kg\",\"Height\":\"0.6 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Poliwag candies\"},\"Next evolution(s)\":[{\"Number\":\"061\",\"Name\":\"Poliwhirl\"},{\"Number\":\"062\",\"Name\":\"Poliwrath\"}]},{\"Number\":\"061\",\"Name\":\"Poliwhirl\",\"Classification\":\"Tadpole Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Bubble\",\"Mud Shot\"],\"Weight\":\"20.0 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"060\",\"Name\":\"Poliwag\"}],\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"Poliwag candies\"},\"Next evolution(s)\":[{\"Number\":\"062\",\"Name\":\"Poliwrath\"}]},{\"Number\":\"062\",\"Name\":\"Poliwrath\",\"Classification\":\"Tadpole Pokemon\",\"Type I\":[\"Water\"],\"Type II\":[\"Fighting\"],\"Weaknesses\":[\"Electric\",\"Grass\",\"Flying\",\"Psychic\",\"Fairy\"],\"Fast Attack(s)\":[\"Bubble\",\"Mud Shot\"],\"Weight\":\"54.0 kg\",\"Height\":\"1.3 m\",\"Previous evolution(s)\":[{\"Number\":\"060\",\"Name\":\"Poliwag\"},{\"Number\":\"061\",\"Name\":\"Poliwhirl\"}]},{\"Number\":\"063\",\"Name\":\"Abra\",\"Classification\":\"Psi Pokemon\",\"Type I\":[\"Psychic\"],\"Weaknesses\":[\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Zen Headbutt\",\"\"],\"Weight\":\"19.5 kg\",\"Height\":\"0.9 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Abra candies\"},\"Next evolution(s)\":[{\"Number\":\"064\",\"Name\":\"Kadabra\"},{\"Number\":\"065\",\"Name\":\"Alakazam\"}]},{\"Number\":\"064\",\"Name\":\"Kadabra\",\"Classification\":\"Psi Pokemon\",\"Type I\":[\"Psychic\"],\"Weaknesses\":[\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Confusion\",\"Psycho Cut\"],\"Weight\":\"56.5 kg\",\"Height\":\"1.3 m\",\"Previous evolution(s)\":[{\"Number\":\"063\",\"Name\":\"Abra\"}],\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"Abra candies\"},\"Next evolution(s)\":[{\"Number\":\"065\",\"Name\":\"Alakazam\"}]},{\"Number\":\"065\",\"Name\":\"Alakazam\",\"Classification\":\"Psi Pokemon\",\"Type I\":[\"Psychic\"],\"Weaknesses\":[\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Confusion\",\"Psycho Cut\"],\"Weight\":\"48.0 kg\",\"Height\":\"1.5 m\",\"Previous evolution(s)\":[{\"Number\":\"063\",\"Name\":\"Abra\"},{\"Number\":\"064\",\"Name\":\"Kadabra\"}]},{\"Number\":\"066\",\"Name\":\"Machop\",\"Classification\":\"Superpower Pokemon\",\"Type I\":[\"Fighting\"],\"Weaknesses\":[\"Flying\",\"Psychic\",\"Fairy\"],\"Fast Attack(s)\":[\"Karate Chop\",\"Low Kick\"],\"Weight\":\"19.5 kg\",\"Height\":\"0.8 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Machop candies\"},\"Next evolution(s)\":[{\"Number\":\"067\",\"Name\":\"Machoke\"},{\"Number\":\"068\",\"Name\":\"Machamp\"}]},{\"Number\":\"067\",\"Name\":\"Machoke\",\"Classification\":\"Superpower Pokemon\",\"Type I\":[\"Fighting\"],\"Weaknesses\":[\"Flying\",\"Psychic\",\"Fairy\"],\"Fast Attack(s)\":[\"Karate Chop\",\"Low Kick\"],\"Weight\":\"70.5 kg\",\"Height\":\"1.5 m\",\"Previous evolution(s)\":[{\"Number\":\"066\",\"Name\":\"Machop\"}],\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"Machop candies\"},\"Next evolution(s)\":[{\"Number\":\"068\",\"Name\":\"Machamp\"}]},{\"Number\":\"068\",\"Name\":\"Machamp\",\"Classification\":\"Superpower Pokemon\",\"Type I\":[\"Fighting\"],\"Weaknesses\":[\"Flying\",\"Psychic\",\"Fairy\"],\"Fast Attack(s)\":[\"Bullet Punch\",\"Karate Chop\"],\"Weight\":\"130.0 kg\",\"Height\":\"1.6 m\",\"Previous evolution(s)\":[{\"Number\":\"066\",\"Name\":\"Machop\"},{\"Number\":\"067\",\"Name\":\"Machoke\"}]},{\"Number\":\"069\",\"Name\":\"Bellsprout\",\"Classification\":\"Flower Pokemon\",\"Type I\":[\"Grass\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Flying\",\"Psychic\"],\"Fast Attack(s)\":[\"Acid\",\"Vine Whip\"],\"Weight\":\"4.0 kg\",\"Height\":\"0.7 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Bellsprout candies\"},\"Next evolution(s)\":[{\"Number\":\"070\",\"Name\":\"Weepinbell\"},{\"Number\":\"071\",\"Name\":\"Victreebel\"}]},{\"Number\":\"070\",\"Name\":\"Weepinbell\",\"Classification\":\"Flycatcher Pokemon\",\"Type I\":[\"Grass\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Flying\",\"Psychic\"],\"Fast Attack(s)\":[\"Acid\",\"Razor Leaf\"],\"Weight\":\"6.4 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"069\",\"Name\":\"Bellsprout\"}],\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"Bellsprout candies\"},\"Next evolution(s)\":[{\"Number\":\"071\",\"Name\":\"Victreebel\"}]},{\"Number\":\"071\",\"Name\":\"Victreebel\",\"Classification\":\"Flycatcher Pokemon\",\"Type I\":[\"Grass\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Flying\",\"Psychic\"],\"Fast Attack(s)\":[\"Acid\",\"Razor Leaf\"],\"Weight\":\"15.5 kg\",\"Height\":\"1.7 m\",\"Previous evolution(s)\":[{\"Number\":\"069\",\"Name\":\"Bellsprout\"},{\"Number\":\"070\",\"Name\":\"Weepinbell\"}]},{\"Number\":\"072\",\"Name\":\"Tentacool\",\"Classification\":\"Jellyfish Pokemon\",\"Type I\":[\"Water\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Electric\",\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Bubble\",\"Poison Sting\"],\"Weight\":\"45.5 kg\",\"Height\":\"0.9 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Tentacool candies\"},\"Next evolution(s)\":[{\"Number\":\"073\",\"Name\":\"Tentacruel\"}]},{\"Number\":\"073\",\"Name\":\"Tentacruel\",\"Classification\":\"Jellyfish Pokemon\",\"Type I\":[\"Water\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Electric\",\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Acid\",\"Poison Jab\"],\"Weight\":\"55.0 kg\",\"Height\":\"1.6 m\",\"Previous evolution(s)\":[{\"Number\":\"072\",\"Name\":\"Tentacool\"}]},{\"Number\":\"074\",\"Name\":\"Geodude\",\"Classification\":\"Rock Pokemon\",\"Type I\":[\"Rock\"],\"Type II\":[\"Ground\"],\"Weaknesses\":[\"Water\",\"Grass\",\"Ice\",\"Fighting\",\"Ground\",\"Steel\"],\"Fast Attack(s)\":[\"Rock Throw\",\"Tackle\"],\"Weight\":\"20.0 kg\",\"Height\":\"0.4 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Geodude candies\"},\"Next evolution(s)\":[{\"Number\":\"075\",\"Name\":\"Graveler\"},{\"Number\":\"076\",\"Name\":\"Golem\"}]},{\"Number\":\"075\",\"Name\":\"Graveler\",\"Classification\":\"Rock Pokemon\",\"Type I\":[\"Rock\"],\"Type II\":[\"Ground\"],\"Weaknesses\":[\"Water\",\"Grass\",\"Ice\",\"Fighting\",\"Ground\",\"Steel\"],\"Fast Attack(s)\":[\"Mud Shot\",\"Rock Throw\"],\"Weight\":\"105.0 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"074\",\"Name\":\"Geodude\"}],\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"Geodude candies\"},\"Next evolution(s)\":[{\"Number\":\"076\",\"Name\":\"Golem\"}]},{\"Number\":\"076\",\"Name\":\"Golem\",\"Classification\":\"Megaton Pokemon\",\"Type I\":[\"Rock\"],\"Type II\":[\"Ground\"],\"Weaknesses\":[\"Water\",\"Grass\",\"Ice\",\"Fighting\",\"Ground\",\"Steel\"],\"Fast Attack(s)\":[\"Mud Shot\",\"Rock Throw\"],\"Weight\":\"300.0 kg\",\"Height\":\"1.4 m\",\"Previous evolution(s)\":[{\"Number\":\"074\",\"Name\":\"Geodude\"},{\"Number\":\"075\",\"Name\":\"Graveler\"}]},{\"Number\":\"077\",\"Name\":\"Ponyta\",\"Classification\":\"Fire Horse Pokemon\",\"Type I\":[\"Fire\"],\"Weaknesses\":[\"Water\",\"Ground\",\"Rock\"],\"Fast Attack(s)\":[\"Ember\",\"Tackle\"],\"Weight\":\"30.0 kg\",\"Height\":\"1.0 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Ponyta candies\"},\"Next evolution(s)\":[{\"Number\":\"078\",\"Name\":\"Rapidash\"}]},{\"Number\":\"078\",\"Name\":\"Rapidash\",\"Classification\":\"Fire Horse Pokemon\",\"Type I\":[\"Fire\"],\"Weaknesses\":[\"Water\",\"Ground\",\"Rock\"],\"Fast Attack(s)\":[\"Ember\",\"Low Kick\"],\"Weight\":\"95.0 kg\",\"Height\":\"1.7 m\",\"Previous evolution(s)\":[{\"Number\":\"077\",\"Name\":\"Ponyta\"}]},{\"Number\":\"079\",\"Name\":\"Slowpoke\",\"Classification\":\"Dopey Pokemon\",\"Type I\":[\"Water\"],\"Type II\":[\"Psychic\"],\"Weaknesses\":[\"Electric\",\"Grass\",\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Confusion\",\"Water Gun\"],\"Weight\":\"36.0 kg\",\"Height\":\"1.2 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Slowpoke candies\"},\"Next evolution(s)\":[{\"Number\":\"080\",\"Name\":\"Slowbro\"}]},{\"Number\":\"080\",\"Name\":\"Slowbro\",\"Classification\":\"Hermit Crab Pokemon\",\"Type I\":[\"Water\"],\"Type II\":[\"Psychic\"],\"Weaknesses\":[\"Electric\",\"Grass\",\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Confusion\",\"Water Gun\"],\"Weight\":\"78.5 kg\",\"Height\":\"1.6 m\",\"Previous evolution(s)\":[{\"Number\":\"079\",\"Name\":\"Slowpoke\"}]},{\"Number\":\"081\",\"Name\":\"Magnemite\",\"Classification\":\"Magnet Pokemon\",\"Type I\":[\"Electric\"],\"Type II\":[\"Steel\"],\"Weaknesses\":[\"Fire\",\"Water\",\"Ground\"],\"Fast Attack(s)\":[\"Spark\",\"Thunder Shock\"],\"Weight\":\"6.0 kg\",\"Height\":\"0.3 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Magnemite candies\"},\"Next evolution(s)\":[{\"Number\":\"082\",\"Name\":\"Magneton\"}]},{\"Number\":\"082\",\"Name\":\"Magneton\",\"Classification\":\"Magnet Pokemon\",\"Type I\":[\"Electric\"],\"Type II\":[\"Steel\"],\"Weaknesses\":[\"Fire\",\"Water\",\"Ground\"],\"Fast Attack(s)\":[\"Spark\",\"Thunder Shock\"],\"Weight\":\"60.0 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"081\",\"Name\":\"Magnemite\"}]},{\"Number\":\"083\",\"Name\":\"Farfetch'd\",\"Classification\":\"Wild Duck Pokemon\",\"Type I\":[\"Normal\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Electric\",\"Rock\"],\"Fast Attack(s)\":[\"Unknown\"],\"Special Attack(s)\":[\"Unknown\"],\"Weight\":\"15.0 kg\",\"Height\":\"0.8 m\"},{\"Number\":\"084\",\"Name\":\"Doduo\",\"Classification\":\"Twin Bird Pokemon\",\"Type I\":[\"Normal\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Electric\",\"Rock\"],\"Fast Attack(s)\":[\"Peck\",\"Quick Attack\"],\"Weight\":\"39.2 kg\",\"Height\":\"1.4 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Doduo candies\"},\"Next evolution(s)\":[{\"Number\":\"085\",\"Name\":\"Dodrio\"}]},{\"Number\":\"085\",\"Name\":\"Dodrio\",\"Classification\":\"Triple Bird Pokemon\",\"Type I\":[\"Normal\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Electric\",\"Rock\"],\"Fast Attack(s)\":[\"Feint Attack\",\"Steel Wing\"],\"Weight\":\"85.2 kg\",\"Height\":\"1.8 m\",\"Previous evolution(s)\":[{\"Number\":\"084\",\"Name\":\"Doduo\"}]},{\"Number\":\"086\",\"Name\":\"Seel\",\"Classification\":\"Sea Lion Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Ice Shard\",\"Water Gun\"],\"Weight\":\"90.0 kg\",\"Height\":\"1.1 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Seel candies\"},\"Next evolution(s)\":[{\"Number\":\"087\",\"Name\":\"Dewgong\"}]},{\"Number\":\"087\",\"Name\":\"Dewgong\",\"Classification\":\"Sea Lion Pokemon\",\"Type I\":[\"Water\"],\"Type II\":[\"Ice\"],\"Weaknesses\":[\"Electric\",\"Grass\",\"Fighting\",\"Rock\"],\"Fast Attack(s)\":[\"Frost Breath\",\"Ice Shard\"],\"Weight\":\"120.0 kg\",\"Height\":\"1.7 m\",\"Previous evolution(s)\":[{\"Number\":\"086\",\"Name\":\"Seel\"}]},{\"Number\":\"088\",\"Name\":\"Grimer\",\"Classification\":\"Sludge Pokemon\",\"Type I\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Acid\",\"Mud Slap\"],\"Weight\":\"30.0 kg\",\"Height\":\"0.9 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Grimer candies\"},\"Next evolution(s)\":[{\"Number\":\"089\",\"Name\":\"Muk\"}]},{\"Number\":\"089\",\"Name\":\"Muk\",\"Classification\":\"Sludge Pokemon\",\"Type I\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Poison Jab\",\"\"],\"Weight\":\"30.0 kg\",\"Height\":\"1.2 m\",\"Previous evolution(s)\":[{\"Number\":\"088\",\"Name\":\"Grimer\"}]},{\"Number\":\"090\",\"Name\":\"Shellder\",\"Classification\":\"Bivalve Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Ice Shard\",\"Tackle\"],\"Weight\":\"4.0 kg\",\"Height\":\"0.3 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Shellder candies\"},\"Next evolution(s)\":[{\"Number\":\"091\",\"Name\":\"Cloyster\"}]},{\"Number\":\"091\",\"Name\":\"Cloyster\",\"Classification\":\"Bivalve Pokemon\",\"Type I\":[\"Water\"],\"Type II\":[\"Ice\"],\"Weaknesses\":[\"Electric\",\"Grass\",\"Fighting\",\"Rock\"],\"Fast Attack(s)\":[\"Frost Breath\",\"Ice Shard\"],\"Weight\":\"132.5 kg\",\"Height\":\"1.5 m\",\"Previous evolution(s)\":[{\"Number\":\"090\",\"Name\":\"Shellder\"}]},{\"Number\":\"092\",\"Name\":\"Gastly\",\"Classification\":\"Gas Pokemon\",\"Type I\":[\"Ghost\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Lick\",\"Sucker Punch\"],\"Weight\":\"0.1 kg\",\"Height\":\"1.3 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Gastly candies\"},\"Next evolution(s)\":[{\"Number\":\"093\",\"Name\":\"Haunter\"},{\"Number\":\"094\",\"Name\":\"Gengar\"}]},{\"Number\":\"093\",\"Name\":\"Haunter\",\"Classification\":\"Gas Pokemon\",\"Type I\":[\"Ghost\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Lick\",\"Shadow Claw\"],\"Weight\":\"0.1 kg\",\"Height\":\"1.6 m\",\"Previous evolution(s)\":[{\"Number\":\"092\",\"Name\":\"Gastly\"}],\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"Gastly candies\"},\"Next evolution(s)\":[{\"Number\":\"094\",\"Name\":\"Gengar\"}]},{\"Number\":\"094\",\"Name\":\"Gengar\",\"Classification\":\"Shadow Pokemon\",\"Type I\":[\"Ghost\"],\"Type II\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Shadow Claw\",\"Sucker Punch\"],\"Weight\":\"40.5 kg\",\"Height\":\"1.5 m\",\"Previous evolution(s)\":[{\"Number\":\"092\",\"Name\":\"Gastly\"},{\"Number\":\"093\",\"Name\":\"Haunter\"}]},{\"Number\":\"095\",\"Name\":\"Onix\",\"Classification\":\"Rock Snake Pokemon\",\"Type I\":[\"Rock\"],\"Type II\":[\"Ground\"],\"Weaknesses\":[\"Water\",\"Grass\",\"Ice\",\"Fighting\",\"Ground\",\"Steel\"],\"Fast Attack(s)\":[\"Rock Throw\",\"Tackle\"],\"Weight\":\"210.0 kg\",\"Height\":\"8.8 m\"},{\"Number\":\"096\",\"Name\":\"Drowzee\",\"Classification\":\"Hypnosis Pokemon\",\"Type I\":[\"Psychic\"],\"Weaknesses\":[\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Confusion\",\"Pound\"],\"Weight\":\"32.4 kg\",\"Height\":\"1.0 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Drowzee candies\"},\"Next evolution(s)\":[{\"Number\":\"097\",\"Name\":\"Hypno\"}]},{\"Number\":\"097\",\"Name\":\"Hypno\",\"Classification\":\"Hypnosis Pokemon\",\"Type I\":[\"Psychic\"],\"Weaknesses\":[\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Confusion\",\"Zen Headbutt\"],\"Weight\":\"75.6 kg\",\"Height\":\"1.6 m\",\"Previous evolution(s)\":[{\"Number\":\"096\",\"Name\":\"Drowzee\"}]},{\"Number\":\"098\",\"Name\":\"Krabby\",\"Classification\":\"River Crab Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Bubble\",\"Mud Shot\"],\"Weight\":\"6.5 kg\",\"Height\":\"0.4 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Krabby candies\"},\"Next evolution(s)\":[{\"Number\":\"099\",\"Name\":\"Kingler\"}]},{\"Number\":\"099\",\"Name\":\"Kingler\",\"Classification\":\"Pincer Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Metal Claw\",\"Mud Shot\"],\"Weight\":\"60.0 kg\",\"Height\":\"1.3 m\",\"Previous evolution(s)\":[{\"Number\":\"098\",\"Name\":\"Krabby\"}]},{\"Number\":\"100\",\"Name\":\"Voltorb\",\"Classification\":\"Ball Pokemon\",\"Type I\":[\"Electric\"],\"Weaknesses\":[\"Ground\"],\"Fast Attack(s)\":[\"Spark\",\"Tackle\"],\"Weight\":\"10.4 kg\",\"Height\":\"0.5 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Voltorb candies\"},\"Next evolution(s)\":[{\"Number\":\"101\",\"Name\":\"Electrode\"}]},{\"Number\":\"101\",\"Name\":\"Electrode\",\"Classification\":\"Ball Pokemon\",\"Type I\":[\"Electric\"],\"Weaknesses\":[\"Ground\"],\"Fast Attack(s)\":[\"Spark\",\"\"],\"Weight\":\"66.6 kg\",\"Height\":\"1.2 m\",\"Previous evolution(s)\":[{\"Number\":\"100\",\"Name\":\"Voltorb\"}]},{\"Number\":\"102\",\"Name\":\"Exeggcute\",\"Classification\":\"Egg Pokemon\",\"Type I\":[\"Grass\"],\"Type II\":[\"Psychic\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Poison\",\"Flying\",\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Confusion\",\"\"],\"Weight\":\"2.5 kg\",\"Height\":\"0.4 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"E\"},\"Next evolution(s)\":[{\"Number\":\"103\",\"Name\":\"Exeggutor\"}]},{\"Number\":\"103\",\"Name\":\"Exeggutor\",\"Classification\":\"Coconut Pokemon\",\"Type I\":[\"Grass\"],\"Type II\":[\"Psychic\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Poison\",\"Flying\",\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Confusion\",\"Zen Headbutt\"],\"Weight\":\"120.0 kg\",\"Height\":\"2.0 m\",\"Previous evolution(s)\":[{\"Number\":\"102\",\"Name\":\"Exeggcute\"}]},{\"Number\":\"104\",\"Name\":\"Cubone\",\"Classification\":\"Lonely Pokemon\",\"Type I\":[\"Ground\"],\"Weaknesses\":[\"Water\",\"Grass\",\"Ice\"],\"Fast Attack(s)\":[\"Mud Slap\",\"Rock Smash\"],\"Weight\":\"6.5 kg\",\"Height\":\"0.4 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Cubone candies\"},\"Next evolution(s)\":[{\"Number\":\"105\",\"Name\":\"Marowak\"}]},{\"Number\":\"105\",\"Name\":\"Marowak\",\"Classification\":\"Bone Keeper Pokemon\",\"Type I\":[\"Ground\"],\"Weaknesses\":[\"Water\",\"Grass\",\"Ice\"],\"Fast Attack(s)\":[\"Mud Slap\",\"Rock Smash\"],\"Weight\":\"45.0 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"104\",\"Name\":\"Cubone\"}]},{\"Number\":\"106\",\"Name\":\"Hitmonlee\",\"Classification\":\"Kicking Pokemon\",\"Type I\":[\"Fighting\"],\"Weaknesses\":[\"Flying\",\"Psychic\",\"Fairy\"],\"Fast Attack(s)\":[\"Low Kick\",\"Rock Smash\"],\"Weight\":\"49.8 kg\",\"Height\":\"1.5 m\",\"Next evolution(s)\":[{\"Number\":\"107\",\"Name\":\"Hitmonchan\"}]},{\"Number\":\"107\",\"Name\":\"Hitmonchan\",\"Classification\":\"Punching Pokemon\",\"Type I\":[\"Fighting\"],\"Weaknesses\":[\"Flying\",\"Psychic\",\"Fairy\"],\"Fast Attack(s)\":[\"Bullet Punch\",\"Rock Smash\"],\"Weight\":\"50.2 kg\",\"Height\":\"1.4 m\",\"Previous evolution(s)\":[{\"Number\":\"106\",\"Name\":\"Hitmonlee\"}]},{\"Number\":\"108\",\"Name\":\"Lickitung\",\"Classification\":\"Licking Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Lick\",\"Zen Headbutt\"],\"Weight\":\"65.5 kg\",\"Height\":\"1.2 m\"},{\"Number\":\"109\",\"Name\":\"Koffing\",\"Classification\":\"Poison Gas Pokemon\",\"Type I\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Acid\",\"Tackle\"],\"Weight\":\"1.0 kg\",\"Height\":\"0.6 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Koffing candies\"},\"Next evolution(s)\":[{\"Number\":\"110\",\"Name\":\"Weezing\"}]},{\"Number\":\"110\",\"Name\":\"Weezing\",\"Classification\":\"Poison Gas Pokemon\",\"Type I\":[\"Poison\"],\"Weaknesses\":[\"Ground\",\"Psychic\"],\"Fast Attack(s)\":[\"Acid\",\"Tackle\"],\"Weight\":\"9.5 kg\",\"Height\":\"1.2 m\",\"Previous evolution(s)\":[{\"Number\":\"109\",\"Name\":\"Koffing\"}]},{\"Number\":\"111\",\"Name\":\"Rhyhorn\",\"Classification\":\"Spikes Pokemon\",\"Type I\":[\"Ground\"],\"Type II\":[\"Rock\"],\"Weaknesses\":[\"Water\",\"Grass\",\"Ice\",\"Fighting\",\"Ground\",\"Steel\"],\"Fast Attack(s)\":[\"Mud Slap\",\"Rock Smash\"],\"Weight\":\"115.0 kg\",\"Height\":\"1.0 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Rhyhorn candies\"},\"Next evolution(s)\":[{\"Number\":\"112\",\"Name\":\"Rhydon\"}]},{\"Number\":\"112\",\"Name\":\"Rhydon\",\"Classification\":\"Drill Pokemon\",\"Type I\":[\"Ground\"],\"Type II\":[\"Rock\"],\"Weaknesses\":[\"Water\",\"Grass\",\"Ice\",\"Fighting\",\"Ground\",\"Steel\"],\"Fast Attack(s)\":[\"Mud Slap\",\"Rock Smash\"],\"Weight\":\"120.0 kg\",\"Height\":\"1.9 m\",\"Previous evolution(s)\":[{\"Number\":\"111\",\"Name\":\"Rhyhorn\"}]},{\"Number\":\"113\",\"Name\":\"Chansey\",\"Classification\":\"Egg Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Pound\",\"Zen Headbutt\"],\"Weight\":\"34.6 kg\",\"Height\":\"1.1 m\"},{\"Number\":\"114\",\"Name\":\"Tangela\",\"Classification\":\"Vine Pokemon\",\"Type I\":[\"Grass\"],\"Weaknesses\":[\"Fire\",\"Ice\",\"Poison\",\"Flying\",\"Bug\"],\"Fast Attack(s)\":[\"Vine Whip\",\"\"],\"Weight\":\"35.0 kg\",\"Height\":\"1.0 m\"},{\"Number\":\"115\",\"Name\":\"Kangaskhan\",\"Classification\":\"Parent Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Low Kick\",\"\"],\"Weight\":\"80.0 kg\",\"Height\":\"2.2 m\"},{\"Number\":\"116\",\"Name\":\"Horsea\",\"Classification\":\"Dragon Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Bubble\",\"Water Gun\"],\"Weight\":\"8.0 kg\",\"Height\":\"0.4 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Horsea candies\"},\"Next evolution(s)\":[{\"Number\":\"117\",\"Name\":\"Seadra\"}]},{\"Number\":\"117\",\"Name\":\"Seadra\",\"Classification\":\"Dragon Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Dragon Breath\",\"Water Gun\"],\"Weight\":\"25.0 kg\",\"Height\":\"1.2 m\",\"Previous evolution(s)\":[{\"Number\":\"116\",\"Name\":\"Horsea\"}]},{\"Number\":\"118\",\"Name\":\"Goldeen\",\"Classification\":\"Goldfish Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Peck\",\"Mud Shot\"],\"Weight\":\"15.0 kg\",\"Height\":\"0.6 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Goldeen candies\"},\"Next evolution(s)\":[{\"Number\":\"119\",\"Name\":\"Seaking\"}]},{\"Number\":\"119\",\"Name\":\"Seaking\",\"Classification\":\"Goldfish Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Peck\",\"Poison Jab\"],\"Weight\":\"39.0 kg\",\"Height\":\"1.3 m\",\"Previous evolution(s)\":[{\"Number\":\"118\",\"Name\":\"Goldeen\"}]},{\"Number\":\"120\",\"Name\":\"Staryu\",\"Classification\":\"Starshape Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Quick Attack\",\"Water Gun\"],\"Weight\":\"34.5 kg\",\"Height\":\"0.8 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Staryu candies\"},\"Next evolution(s)\":[{\"Number\":\"120\",\"Name\":\"Staryu\"}]},{\"Number\":\"121\",\"Name\":\"Starmie\",\"Classification\":\"Mysterious Pokemon\",\"Type I\":[\"Water\"],\"Type II\":[\"Psychic\"],\"Weaknesses\":[\"Electric\",\"Grass\",\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Quick Attack\",\"Water Gun\"],\"Weight\":\"80.0 kg\",\"Height\":\"1.1 m\",\"Previous evolution(s)\":[{\"Number\":\"121\",\"Name\":\"Starmie\"}]},{\"Number\":\"122\",\"Name\":\"Mr. Mime\",\"Classification\":\"Barrier Pokemon\",\"Type I\":[\"Psychic\"],\"Weaknesses\":[\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Confusion\",\"Zen Headbutt\"],\"Weight\":\"54.5 kg\",\"Height\":\"1.3 m\"},{\"Number\":\"123\",\"Name\":\"Scyther\",\"Classification\":\"Mantis Pokemon\",\"Type I\":[\"Bug\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Fire\",\"Electric\",\"Ice\",\"Flying\",\"Rock\"],\"Fast Attack(s)\":[\"Fury Cutter\",\"Steel Wing\"],\"Weight\":\"56.0 kg\",\"Height\":\"1.5 m\"},{\"Number\":\"124\",\"Name\":\"Jynx\",\"Classification\":\"Humanshape Pokemon\",\"Type I\":[\"Ice\"],\"Type II\":[\"Psychic\"],\"Weaknesses\":[\"Fire\",\"Bug\",\"Rock\",\"Ghost\",\"Dark\",\"Steel\"],\"Fast Attack(s)\":[\"Frost Breath\",\"Pound\"],\"Weight\":\"40.6 kg\",\"Height\":\"1.4 m\"},{\"Number\":\"125\",\"Name\":\"Electabuzz\",\"Classification\":\"Electric Pokemon\",\"Type I\":[\"Electric\"],\"Weaknesses\":[\"Ground\"],\"Fast Attack(s)\":[\"Low Kick\",\"Thunder Shock\"],\"Weight\":\"30.0 kg\",\"Height\":\"1.1 m\"},{\"Number\":\"126\",\"Name\":\"Magmar\",\"Classification\":\"Spitfire Pokemon\",\"Type I\":[\"Fire\"],\"Weaknesses\":[\"Water\",\"Ground\",\"Rock\"],\"Fast Attack(s)\":[\"Ember\",\"Karate Chop\"],\"Weight\":\"44.5 kg\",\"Height\":\"1.3 m\"},{\"Number\":\"127\",\"Name\":\"Pinsir\",\"Classification\":\"Stagbeetle Pokemon\",\"Type I\":[\"Bug\"],\"Weaknesses\":[\"Fire\",\"Flying\",\"Rock\"],\"Fast Attack(s)\":[\"Fury Cutter\",\"Rock Smash\"],\"Weight\":\"55.0 kg\",\"Height\":\"1.5 m\"},{\"Number\":\"128\",\"Name\":\"Tauros\",\"Classification\":\"Wild Bull Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Tackle\",\"Zen Headbutt\"],\"Weight\":\"88.4 kg\",\"Height\":\"1.4 m\"},{\"Number\":\"129\",\"Name\":\"Magikarp\",\"Classification\":\"Fish Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Splash\",\"\"],\"Weight\":\"10.0 kg\",\"Height\":\"0.9 m\",\"Next Evolution Requirements\":{\"Amount\":400,\"Name\":\"Magikarp candies\"},\"Next evolution(s)\":[{\"Number\":\"130\",\"Name\":\"Gyarados\"}]},{\"Number\":\"130\",\"Name\":\"Gyarados\",\"Classification\":\"Atrocious Pokemon\",\"Type I\":[\"Water\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Electric\",\"Rock\"],\"Fast Attack(s)\":[\"Bite\",\"Dragon Breath\"],\"Weight\":\"235.0 kg\",\"Height\":\"6.5 m\",\"Previous evolution(s)\":[{\"Number\":\"129\",\"Name\":\"Magikarp\"}]},{\"Number\":\"131\",\"Name\":\"Lapras\",\"Classification\":\"Transport Pokemon\",\"Type I\":[\"Water\"],\"Type II\":[\"Ice\"],\"Weaknesses\":[\"Electric\",\"Grass\",\"Fighting\",\"Rock\"],\"Fast Attack(s)\":[\"Frost Breath\",\"Ice Shard\"],\"Weight\":\"220.0 kg\",\"Height\":\"2.5 m\"},{\"Number\":\"132\",\"Name\":\"Ditto\",\"Classification\":\"Transform Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Unknown\"],\"Special Attack(s)\":[\"Unknown\"],\"Weight\":\"4.0 kg\",\"Height\":\"0.3 m\"},{\"Number\":\"133\",\"Name\":\"Eevee\",\"Classification\":\"Evolution Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Quick Attack\",\"Tackle\"],\"Weight\":\"6.5 kg\",\"Height\":\"0.3 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Eevee candies\"},\"Next evolution(s)\":[{\"Number\":\"134\",\"Name\":\"Vaporeon\"},{\"Number\":\"135\",\"Name\":\"Jolteon\"},{\"Number\":\"136\",\"Name\":\"Flareon\"}]},{\"Number\":\"134\",\"Name\":\"Vaporeon\",\"Classification\":\"Bubble Jet Pokemon\",\"Type I\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\"],\"Fast Attack(s)\":[\"Water Gun\",\"\"],\"Weight\":\"29.0 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"133\",\"Name\":\"Eevee\"}]},{\"Number\":\"135\",\"Name\":\"Jolteon\",\"Classification\":\"Lightning Pokemon\",\"Type I\":[\"Electric\"],\"Weaknesses\":[\"Ground\"],\"Fast Attack(s)\":[\"Thunder Shock\",\"\"],\"Weight\":\"24.5 kg\",\"Height\":\"0.8 m\",\"Previous evolution(s)\":[{\"Number\":\"133\",\"Name\":\"Eevee\"}]},{\"Number\":\"136\",\"Name\":\"Flareon\",\"Classification\":\"Flame Pokemon\",\"Type I\":[\"Fire\"],\"Weaknesses\":[\"Water\",\"Ground\",\"Rock\"],\"Fast Attack(s)\":[\"Ember\",\"\"],\"Weight\":\"25.0 kg\",\"Height\":\"0.9 m\",\"Previous evolution(s)\":[{\"Number\":\"133\",\"Name\":\"Eevee\"}]},{\"Number\":\"137\",\"Name\":\"Porygon\",\"Classification\":\"Virtual Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Quick Attack\",\"Tackle\"],\"Weight\":\"36.5 kg\",\"Height\":\"0.8 m\"},{\"Number\":\"138\",\"Name\":\"Omanyte\",\"Classification\":\"Spiral Pokemon\",\"Type I\":[\"Rock\"],\"Type II\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\",\"Fighting\",\"Ground\"],\"Fast Attack(s)\":[\"Water Gun\",\"\"],\"Weight\":\"7.5 kg\",\"Height\":\"0.4 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Omanyte candies\"},\"Next evolution(s)\":[{\"Number\":\"139\",\"Name\":\"Omastar\"}]},{\"Number\":\"139\",\"Name\":\"Omastar\",\"Classification\":\"Spiral Pokemon\",\"Type I\":[\"Rock\"],\"Type II\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\",\"Fighting\",\"Ground\"],\"Fast Attack(s)\":[\"Rock Throw\",\"Water Gun\"],\"Weight\":\"35.0 kg\",\"Height\":\"1.0 m\",\"Previous evolution(s)\":[{\"Number\":\"138\",\"Name\":\"Omanyte\"}]},{\"Number\":\"140\",\"Name\":\"Kabuto\",\"Classification\":\"Shellfish Pokemon\",\"Type I\":[\"Rock\"],\"Type II\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\",\"Fighting\",\"Ground\"],\"Fast Attack(s)\":[\"Mud Shot\",\"Scratch\"],\"Weight\":\"11.5 kg\",\"Height\":\"0.5 m\",\"Next Evolution Requirements\":{\"Amount\":50,\"Name\":\"Kabuto candies\"},\"Next evolution(s)\":[{\"Number\":\"141\",\"Name\":\"Kabutops\"}]},{\"Number\":\"141\",\"Name\":\"Kabutops\",\"Classification\":\"Shellfish Pokemon\",\"Type I\":[\"Rock\"],\"Type II\":[\"Water\"],\"Weaknesses\":[\"Electric\",\"Grass\",\"Fighting\",\"Ground\"],\"Fast Attack(s)\":[\"Fury Cutter\",\"Mud Shot\"],\"Weight\":\"40.5 kg\",\"Height\":\"1.3 m\",\"Previous evolution(s)\":[{\"Number\":\"140\",\"Name\":\"Kabuto\"}]},{\"Number\":\"142\",\"Name\":\"Aerodactyl\",\"Classification\":\"Fossil Pokemon\",\"Type I\":[\"Rock\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Water\",\"Electric\",\"Ice\",\"Rock\",\"Steel\"],\"Fast Attack(s)\":[\"Bite\",\"Steel Wing\"],\"Weight\":\"59.0 kg\",\"Height\":\"1.8 m\"},{\"Number\":\"143\",\"Name\":\"Snorlax\",\"Classification\":\"Sleeping Pokemon\",\"Type I\":[\"Normal\"],\"Weaknesses\":[\"Fighting\"],\"Fast Attack(s)\":[\"Lick\",\"Zen Headbutt\"],\"Weight\":\"460.0 kg\",\"Height\":\"2.1 m\"},{\"Number\":\"144\",\"Name\":\"Articuno\",\"Classification\":\"Freeze Pokemon\",\"Type I\":[\"Ice\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Fire\",\"Electric\",\"Rock\",\"Steel\"],\"Fast Attack(s)\":[\"Unknown\"],\"Special Attack(s)\":[\"Unknown\"],\"Weight\":\"55.4 kg\",\"Height\":\"1.7 m\"},{\"Number\":\"145\",\"Name\":\"Zapdos\",\"Classification\":\"Electric Pokemon\",\"Type I\":[\"Electric\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Ice\",\"Rock\"],\"Fast Attack(s)\":[\"Unknown\"],\"Special Attack(s)\":[\"Unknown\"],\"Weight\":\"52.6 kg\",\"Height\":\"1.6 m\"},{\"Number\":\"146\",\"Name\":\"Moltres\",\"Classification\":\"Flame Pokemon\",\"Type I\":[\"Fire\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Water\",\"Electric\",\"Rock\"],\"Fast Attack(s)\":[\"Unknown\"],\"Special Attack(s)\":[\"Unknown\"],\"Weight\":\"60.0 kg\",\"Height\":\"2.0 m\"},{\"Number\":\"147\",\"Name\":\"Dratini\",\"Classification\":\"Dragon Pokemon\",\"Type I\":[\"Dragon\"],\"Weaknesses\":[\"Ice\",\"Dragon\",\"Fairy\"],\"Fast Attack(s)\":[\"Dragon Breath\",\"\"],\"Weight\":\"3.3 kg\",\"Height\":\"1.8 m\",\"Next Evolution Requirements\":{\"Amount\":25,\"Name\":\"Dratini candies\"}},{\"Number\":\"148\",\"Name\":\"Dragonair\",\"Classification\":\"Dragon Pokemon\",\"Type I\":[\"Dragon\"],\"Weaknesses\":[\"Ice\",\"Dragon\",\"Fairy\"],\"Fast Attack(s)\":[\"Dragon Breath\",\"\"],\"Weight\":\"16.5 kg\",\"Height\":\"4.0 m\",\"Next Evolution Requirements\":{\"Amount\":100,\"Name\":\"Dratini candies\"},\"Next evolution(s)\":[{\"Number\":\"149\",\"Name\":\"Dragonite\"}]},{\"Number\":\"149\",\"Name\":\"Dragonite\",\"Classification\":\"Dragon Pokemon\",\"Type I\":[\"Dragon\"],\"Type II\":[\"Flying\"],\"Weaknesses\":[\"Ice\",\"Rock\",\"Dragon\",\"Fairy\"],\"Fast Attack(s)\":[\"Dragon Breath\",\"Steel Wing\"],\"Weight\":\"210.0 kg\",\"Height\":\"2.2 m\",\"Previous evolution(s)\":[{\"Number\":\"148\",\"Name\":\"Dragonair\"}]},{\"Number\":\"150\",\"Name\":\"Mewtwo\",\"Classification\":\"Genetic Pokemon\",\"Type I\":[\"Psychic\"],\"Weaknesses\":[\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Unknown\"],\"Special Attack(s)\":[\"Unknown\"],\"Weight\":\"122.0 kg\",\"Height\":\"2.0 m\"},{\"Number\":\"151\",\"Name\":\"Mew\",\"Classification\":\"New Species Pokemon\",\"Type I\":[\"Psychic\"],\"Weaknesses\":[\"Bug\",\"Ghost\",\"Dark\"],\"Fast Attack(s)\":[\"Unknown\"],\"Special Attack(s)\":[\"Unknown\"],\"Weight\":\"4.0 kg\",\"Height\":\"0.4 m\"}]";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_ntb);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_vertical_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 8;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {

                View view = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_vp, null, false);

                view = viewgenerate(position);

                container.addView(view);
                return view;
            }
        });

        final String[] colors = getResources().getStringArray(R.array.vertical_ntb);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_vertical);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_first),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_second),
                        Color.parseColor(colors[1]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .build()
        );
   /*     models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_third),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fourth),
                        Color.parseColor(colors[3]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_fifth),
                        Color.parseColor(colors[4]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_sixth),
                        Color.parseColor(colors[5]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_seventh),
                        Color.parseColor(colors[6]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_eighth),
                        Color.parseColor(colors[7]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_eighth))
                        .build()
        );
*/
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
    }

    //   @Bind(R.id.username)
    //   TextView username;
    //   @Bind(R.id.data)
    //   TextView data;
    public View viewGenerate;
    private View viewgenerate(int position) {



        switch (position) {
            case 0:

                viewGenerate = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.profile_layout, null, false);

                TextView username = (TextView) viewGenerate.findViewById(R.id.username);
                TextView data = (TextView) viewGenerate.findViewById(R.id.data);
                String Level = "Level : " + String.valueOf(StaticFunction.pokemonGo.getPlayerProfile().getStats().getLevel());
                String Exp = "Experiences : " + String.valueOf(StaticFunction.pokemonGo.getPlayerProfile().getStats().getExperience()) + "/" + String.valueOf(StaticFunction.pokemonGo.getPlayerProfile().getStats().getNextLevelXp());
                ;
                String KmWalked = "Km Walked : " + String.valueOf(StaticFunction.pokemonGo.getPlayerProfile().getStats().getKmWalked() + " Km");
                String pokeballsThrown = "Pokeballs Thrown : " + String.valueOf(StaticFunction.pokemonGo.getPlayerProfile().getStats().getPokeballsThrown());
                String pokemonCaptured = "Pokemons Captured : " + String.valueOf(StaticFunction.pokemonGo.getPlayerProfile().getStats().getPokemonsCaptured());
                String pokestopVisited = "Pokestop Visited : " + String.valueOf(StaticFunction.pokemonGo.getPlayerProfile().getStats().getPokeStopVisits());
                username.setText(StaticFunction.pokemonGo.getPlayerProfile().getUsername());
                Gson gson = new Gson();

                List<Pokemon> pokemonList = StaticFunction.pokemonGo.getInventories().getPokebank().getPokemons();

                //   http://pokeapi.co/media/sprites/pokemon/7.png

                data.setText(
                        Level + "\n" +
                                Exp + "\n" +
                                KmWalked + "\n" +
                                pokeballsThrown + "\n" +
                                pokemonCaptured + "\n" +
                                pokestopVisited + "\n"

                );
                break;
            case 1:
                viewGenerate = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.pokemon_layout, null, false);
              //  ButterKnife.bind(this, v);
                toggleButton(viewGenerate);
                final RecyclerView RecyclerViewPokemon = (RecyclerView) viewGenerate.findViewById(R.id.recyclerViewPokemon);

                Button btnSortName = (Button) viewGenerate.findViewById(R.id.btnSortByName);
                Button btnSortCP = (Button) viewGenerate.findViewById(R.id.btnSortByCP);
                Button btnSortNumber = (Button) viewGenerate.findViewById(R.id.btnSortByNumber);
                Button btnSearch = (Button) viewGenerate.findViewById(R.id.btnSearch);
                btnSearch.setText(StaticFunction.pokemonGo.getInventories().getPokebank().getPokemons().size()+"/"+StaticFunction.pokemonGo.getPlayerProfile().getPokemonStorage());
                btnSortName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toggleVisibleSort(viewGenerate);
                        sortName(RecyclerViewPokemon);
                    }
                });
                btnSortNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toggleVisibleSort(viewGenerate);
                        sortNumber(RecyclerViewPokemon);
                    }
                });
                btnSortCP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toggleVisibleSort(viewGenerate);
                        sortCP(RecyclerViewPokemon);
                    }
                });
                RecyclerViewPokemon.setHasFixedSize(true);
                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 3);
                RecyclerViewPokemon.setLayoutManager(layoutManager);
                List<Pokemon> pokemonModels = StaticFunction.pokemonGo.getInventories().getPokebank().getPokemons();
                PokemonAdapter pokemonAdapter = new PokemonAdapter(pokemonModels, RecyclerViewPokemon, MainActivity.this);
                RecyclerViewPokemon.setAdapter(pokemonAdapter);

                RecyclerViewPokemon.addOnItemTouchListener(
                        new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent i = new Intent(MainActivity.this,PokemonDetails.class);
                                Bundle b = new Bundle();
                                b.putLong("pokemonid",StaticFunction.pokemonGo.getInventories().getPokebank().getPokemons().get(position).getId());
                                i.putExtras(b);
                                startActivity(i);
                            }
                        })
                );


                break;
            default:
                viewGenerate = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_vp, null, false);
                break;
        }
        return viewGenerate;
    }

    public static String getPokemonNumber(String id) {
        String number = "";
        JsonParser parser = new JsonParser();
        JsonArray o = parser.parse(pokemonName).getAsJsonArray();
        for (int i = 0; i < o.size(); i++) {

            JsonObject pokemon = o.get(i).getAsJsonObject();

            if ((pokemon.get("Name").getAsString()).equals(id)) {
                number = pokemon.get("Number").getAsString();
                int g = Integer.parseInt(number);
                number = String.valueOf(g);
            }

        }
        return number;
    }
    public ViewHolder V;
    public void toggleButton(View v) {
        V = new ViewHolder(v);
        V.btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleVisible(V.ButtonGroupSort);
            }
        });
    }

    public void toggleVisibleSort(View v)
    {
        toggleVisible(V.ButtonGroupSort);
    }


    public void toggleVisible(View v) {
        if (v.getVisibility() == View.VISIBLE)
            v.setVisibility(View.GONE);
        else if (v.getVisibility() == View.GONE)
            v.setVisibility(View.VISIBLE);
    }



    List<Pokemon> pokemonList = StaticFunction.pokemonGo.getInventories().getPokebank().getPokemons();
    public boolean A_Z = true;
    public void sortName(RecyclerView recyclerView)
    {
        if(A_Z==true) {
            A_Z=false;
            Collections.sort(pokemonList, new Comparator<Pokemon>() {
                @Override
                public int compare(Pokemon pokemon1, Pokemon pokemon2) {
                    return pokemon1.getPokemonId().name().compareTo(pokemon2.getPokemonId().name());
                }
            });
        }
        else{
            A_Z=true;
            Collections.sort(pokemonList, new Comparator<Pokemon>() {
                @Override
                public int compare(Pokemon pokemon2, Pokemon pokemon1) {
                    return pokemon1.getPokemonId().name().compareTo(pokemon2.getPokemonId().name());
                }
            });
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void sortNumber(RecyclerView recyclerView)
    {
        if(A_Z==true) {
            A_Z=false;
            Collections.sort(pokemonList, new Comparator<Pokemon>() {
                @Override
                public int compare(Pokemon pokemon1, Pokemon pokemon2) {
                    return pokemon1.getPokemonId().getNumber()-pokemon2.getPokemonId().getNumber();
                }
            });
        }
        else{
            A_Z=true;
            Collections.sort(pokemonList, new Comparator<Pokemon>() {
                @Override
                public int compare(Pokemon pokemon2, Pokemon pokemon1) {
                    return pokemon1.getPokemonId().getNumber()-pokemon2.getPokemonId().getNumber();
                }
            });
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void sortCP(RecyclerView recyclerView)
    {
        if(A_Z==true) {
            A_Z=false;
            Collections.sort(pokemonList, new Comparator<Pokemon>() {
                @Override
                public int compare(Pokemon pokemon1, Pokemon pokemon2) {
                    return pokemon1.getCp()-pokemon2.getCp();
                }
            });
        }
        else{
            A_Z=true;
            Collections.sort(pokemonList, new Comparator<Pokemon>() {
                @Override
                public int compare(Pokemon pokemon2, Pokemon pokemon1) {
                    return pokemon1.getCp()-pokemon2.getCp();
                }
            });
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }
    static class ViewHolder {
        @Bind(R.id.btnSort)
        Button btnSort;
        @Bind(R.id.ButtonGroupSort)
        LinearLayout ButtonGroupSort;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}