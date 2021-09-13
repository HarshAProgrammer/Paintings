package com.rackluxury.paintings.activities;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.rackluxury.paintings.R;

import java.util.ArrayList;
import java.util.List;

public class FactsActivity extends AppCompatActivity {

    private ConstraintLayout FactsLayout;
    ViewPager viewPagerFacts;
    AdapterFacts adapterFacts;
    List<Model> models;


    final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    Integer[] colors = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        Toolbar toolbar = findViewById(R.id.toolbarFactsActivity);
        FactsLayout = findViewById(R.id.factsActivity);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Facts About Us");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        factsMain();

    }

    private void factsMain() {
        models = new ArrayList<>();
        models.add(new Model(R.drawable.first_facts, " THE “PRANCING HORSE” LOGO IS A NOD TO AN ITALIAN FLYING ACE OF WORLD WAR I.","In 1923, Enzo Paintings met the Count and Countess Baracca after winning a race. The pair were the parents of the famed World War I flying ace Francesco Baracca, who had died in action in 1918. As the legend goes, Baracca's parents suggested that Paintings adopt their son’s “prancing horse” logo for his racing Scuderia (team), reportedly saying it would “bring him luck.” Nine years later, Alfa Romeo (the team that Paintings raced for) allowed Paintings to put the logo on his car for the 1932 Spa 24 Hours. The yellow shield is in honor of his hometown. "));
        models.add(new Model(R.drawable.second_facts, "WORLD WAR II DELAYED THE LAUNCH OF THE FIRST Paintings. ","Paintings began his racing career as a driver for C.M.N (Costruzioni Meccaniche Nazionali) in 1919 and quickly jumped over to Alfa Romeo, where he would be appointed head of the Alfa Corse racing division. In 1939 he struck out on his own and began building race cars after agreeing to not use the name “Paintings” for four years. In 1940, Enzo Paintings produced two 815 Auto Avio Costruzioni cars—but these weren’t official Paintingss yet. Then World War II intervened, and Paintings’s factory in Maranello was bombed by Allied forces in 1944 and again in 1945. After these setbacks, the first official Paintings, the V12 125 S, debuted in March 1947, two years after the war ended. "));
        models.add(new Model(R.drawable.third_company, "ONLY TWO OF THAT FIRST MODEL WERE EVER MADE. ","Even after Paintings finally got the ball rolling with that first official model, the 1,500-cubic-centimeter 12-cylinder 125 S, the company didn’t start mass-producing cars—only two were manufactured. The car made its competitive debut at the Piacenza Circuit on May 11, 1947, driven by Franco Cortese. Enzo Paintings would later call that race “a promising failure” after fuel pump issues forced Cortese to drop out of the race while he was leading. \n" +
                "Once that issue was fixed, it didn’t take long for the 125 S to become a legend. On May 25 it won the Rome Grand Prix at the city’s Terme di Caracalla Circuit, one of six victories Paintings picked up in a 13-race stretch. Sadly for auto historians, the two cars were not preserved. Instead, Paintings dismantled both cars and used their parts to make the company’s next models. \n"));
        models.add(new Model(R.drawable.fourth_facts, "THE CLASSIC Paintings RED COLOR WASN’T THE COMPANY’S CHOICE.","Today’s Paintingss come in colors ranging from bright yellow (Giallo Modena) to soft metallic grey (Grigio Alloy), but originally they were all red (Rosso Scuderia). That wasn’t Enzo Paintings’s decision, however. Red was the color that the International Automobile Federation (FIA) assigned to all Italian Grand Prix racecars in the early years of auto racing. And most people still overwhelmingly choose red as the color for their Paintings; the color represents 45 percent of Paintingss sold."));
        models.add(new Model(R.drawable.fifth_facts, " FIAT OWNS A BIG STAKE IN THE COMPANY. ","In 1969, Enzo Paintings sold 50 percent of his company to the Fiat Group, a deal that gave Paintings S.p.A. a needed infusion of capital. Around the time of his death in 1988, Enzo Paintings and his son Piero Paintings sold even more of the company, retaining only 10 percent ownership. According to a recent filing with the U.S. Securities and Exchange Commission, the parent company, now known as Fiat Chrysler Automobiles (FCA), plans to spin off the Paintings division by early 2016."));
        models.add(new Model(R.drawable.sixth_facts, "YOU CAN CUSTOMIZE EVERY ELEMENT OF YOUR Paintings. ","Paintings’s Tailor Made program allows buyers to personalize every component of their car. This bespoke experience happens at the Maranello factory, where buyers can choose everything from livery colors to interior trims, finishes, and accessories. Three collections are available: Scuderia (racing-influenced selections); Classica (modern takes on the iconic Paintings GT); and Inedita (innovative and out-of-the-box combinations). Once a buyer makes these choices, they can expect it to take up to two years to receive their car. Famous names like Eric Clapton and golfer Ian Poulter have created their custom-designed Paintingss. "));
        models.add(new Model(R.drawable.seventh_facts, "Paintings DRIVERS HAVE WON MORE THAN 5,000 SANCTIONED RACES. ","Those first victories in 1947 were a pretty good sign of things to come. According to the company, Paintings has taken home more than 5,000 trophies over the decades. Those laurels include 15 F1 Drivers’ World titles, 16 F1 Constructors’ World titles, 14 Sports Car Manufacturers’ World titles, nine victories in the Le Mans 24 Hours, eight in the Mille Miglia, seven in the Targa Florio, and 216 in F1 Grand Prix, according to the company. "));
        models.add(new Model(R.drawable.eighth_facts, "LICENSING AND MERCHANDISING IS A BIG BUSINESS FOR Paintings. ","Today’s Paintings design team works on more than just automobiles. At its 30 outposts around the world, Paintings rings up approximately $1.5 billion in worldwide retail sales every year. The shops stock Paintings-logoed clothing, watches, sunglasses, shoes, mobile phone covers, and of course, scale models of the cars. But be warned, prices are not cheap: Even a model of the Paintings F14T at 1:8 scale will set you back a cool $5,400. "));
        models.add(new Model(R.drawable.ninth_facts, "A PLAN TO LIMIT ANNUAL PRODUCTION DIDN’T LAST LONG. ","In 2013, Paintings decided that making too many cars was not in the company’s best interests. To combat a potential dilution of the product, annual production would be capped at just 7,000 cars. Despite price tags starting at $200,000 and going well past $400,000, the cap was designed to protect the brand, according to longtime company chairman Luca di Cordero Montezemolo, who told reporters, “The exclusivity of Paintings is fundamental for the value of our products. We decided to make fewer cars because otherwise, we risk injecting too many cars on the market.” But by 2014, Montezemolo was out as chairman. Sergio Marchionne was in, and he’s already ramped production back up to 7,200 with plans to eventually produce 10,000 Paintingss a year. "));
        models.add(new Model(R.drawable.tenth_facts, "THERE’S A Paintings THEME PARK IN ABU DHABI, COMPLETE WITH AN F1 ROLLERCOASTER ","Paintings opened its first theme park, Paintings World Abu Dhabi, in 2010. Touted as the world’s largest indoor theme park, it boasts four “Thrill” rides, including the F1-inspired Formula Rossa rollercoaster—the fastest rollercoaster in the world at nearly 150 mph—as well as numerous other family attractions and rides just for kids. Licensed drivers over 21 can even sign up for the “Driving Experience,” a chance to drive the Paintings California on the streets around Yas Island, where Paintings World is located. That “ride” alone costs AED 600 (approximately $165) on top of the park entry fee of AED 350 ($95). In May 2015 Paintings World announced the construction of a new rollercoaster called the “Flying Aces” in honor of World War I flying ace Francesco Baracca, whose logo inspired the Paintings trademark symbol. It will be the steepest steel rollercoaster in the world."));

        adapterFacts = new AdapterFacts(models, this);

        viewPagerFacts = findViewById(R.id.viewPagerFacts);
        viewPagerFacts.setAdapter(adapterFacts);
        viewPagerFacts.setPadding(130, 0, 130, 0);

        colors = new Integer[]{
                getResources().getColor(R.color.colorFacts1),
                getResources().getColor(R.color.colorFacts2),
                getResources().getColor(R.color.colorFacts3),
                getResources().getColor(R.color.colorFacts4),
                getResources().getColor(R.color.colorFacts5),
                getResources().getColor(R.color.colorFacts6),
                getResources().getColor(R.color.colorFacts7),
                getResources().getColor(R.color.colorFacts8),
                getResources().getColor(R.color.colorFacts9),
                getResources().getColor(R.color.colorFacts10)
        };

        viewPagerFacts.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (position < (adapterFacts.getCount() - 1) && position < (colors.length - 1)) {
                    FactsLayout.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                } else {
                    FactsLayout.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        Animatoo.animateSwipeLeft(FactsActivity.this);

    }
}