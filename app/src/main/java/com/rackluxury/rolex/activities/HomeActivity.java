package com.rackluxury.rolex.activities;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rackluxury.rolex.R;
import com.rackluxury.rolex.adapters.CategoriesData;
import com.rackluxury.rolex.adapters.MyCategoriesAdapter;
import com.rackluxury.rolex.adapters.UserProfile;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.dmoral.toasty.Toasty;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView categoriesRecyclerView;
    List<CategoriesData> myCategoriesList;
    CategoriesData mCategoriesData;

    private long backPressedTime;

    private Toolbar toolbar;

    private MyCategoriesAdapter myCategoriesAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;


    private String ShareOnFacebookString;
    private String ShareOnTwitterString;
    final List<CategoriesData> favouriteCategories = new ArrayList<>();
    final List<CategoriesData> deletedCategories = new ArrayList<>();
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private UserProfile userProfile;
    private ImageView navUserPhoto;
    private DrawerLayout drawer;
    private TextView navUsername, navUserMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpUIViewsHomeActivity();
        initToolbar();
        setupNavigationDrawer();
        loadMainData();
        itemTouchCategories();


    }


    private void setUpUIViewsHomeActivity() {

        toolbar = findViewById(R.id.toolbarHomeActivity);
        categoriesRecyclerView = findViewById(R.id.rvCategoriesRecycler);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navUsername = headerView.findViewById(R.id.nav_username);
        navUserMail = headerView.findViewById(R.id.nav_user_mail);
        navUserPhoto = headerView.findViewById(R.id.nav_user_photo);


    }


    private void initToolbar() {
        setSupportActionBar(toolbar);

    }

    private void setupNavigationDrawer() {


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open_home, R.string.navigation_drawer_close_home);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(HomeActivity.this);
        updateNavHeader();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        getMenuInflater().inflate(R.menu.toolbar_search_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search_main);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sort_home_price_low_to_high) {
            sortViewPriceLowToHigh();
            return true;
        } else if (item.getItemId() == R.id.sort_home_price_high_to_low) {
            sortViewPriceHighToLow();
            return true;
        } else if (item.getItemId() == R.id.sort_home_name_a_to_z) {
            sortViewNameAToZ();
            return true;
        } else if (item.getItemId() == R.id.sort_home_name_z_to_a) {
            sortViewNameZtoA();
            return true;
        } else if (item.getItemId() == R.id.home_videos) {
            homeVideos();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void homeVideos() {
        Intent openVideoFromMain = new Intent(HomeActivity.this, VideoActivity.class);
        startActivity(openVideoFromMain);
        Animatoo.animateSplit(HomeActivity.this);

    }

    public void sortViewNameAToZ() {
        Collections.sort(myCategoriesList, CategoriesData.ByNameAToZ);
        myCategoriesAdapter.notifyDataSetChanged();
    }

    public void sortViewNameZtoA() {
        Collections.sort(myCategoriesList, CategoriesData.ByNameZToA);
        myCategoriesAdapter.notifyDataSetChanged();
    }

    public void sortViewPriceLowToHigh() {
        Collections.sort(myCategoriesList, CategoriesData.ByPriceLowToHigh);
        myCategoriesAdapter.notifyDataSetChanged();
    }

    public void sortViewPriceHighToLow() {
        Collections.sort(myCategoriesList, CategoriesData.ByPriceHighToLow);
        myCategoriesAdapter.notifyDataSetChanged();
    }

    private void filter(String text) {

        ArrayList<CategoriesData> filterList = new ArrayList<>();

        for (CategoriesData item : myCategoriesList) {

            if (item.getCategoriesName().toLowerCase().contains(text.toLowerCase())) {

                filterList.add(item);

            }

        }

        myCategoriesAdapter.filteredList(filterList);

    }

    @Override
    public void onBackPressed() {
        drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toasty.normal(HomeActivity.this, "Click Back again to Exit", Toast.LENGTH_SHORT, ContextCompat.getDrawable(HomeActivity.this, R.drawable.ic_main_exit_toast)).show();
        }
        backPressedTime = System.currentTimeMillis();

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.imagesNavigation) {
            Images();

        } else if (id == R.id.profileNavigation) {
            ProfileDisplay();

        } else if (id == R.id.billingNavigation) {
            InAppPurchase();

        } else if (id == R.id.factsNavigation) {
            FactsAboutUs();

        } else if (id == R.id.signOutNavigation) {
            FirebaseAuth.getInstance().signOut();
            SignOut();

        } else if (id == R.id.shareNavigation) {
            GeneralShareMain();

        } else if (id == R.id.facebookLikeNavigation) {
            LikeFacebookPage();

        } else if (id == R.id.facebookShareNavigation) {
            ShareOnFacebook();

        } else if (id == R.id.twitterShareNavigation) {
            ShareOnTwitter();
        } else if (id == R.id.companyNavigation) {
            openCompanyInfo();
        } else if (id == R.id.aboutUsNavigation) {

            aboutUs();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void FactsAboutUs() {
        Intent openFactsFromMain = new Intent(HomeActivity.this, FactsActivity.class);
        startActivity(openFactsFromMain);
        Animatoo.animateSwipeRight(HomeActivity.this);
    }

    private void InAppPurchase() {
        Intent openBillingFromMain = new Intent(HomeActivity.this, BillingActivity.class);
        startActivity(openBillingFromMain);
        Animatoo.animateSwipeRight(HomeActivity.this);
    }

    private void aboutUs() {
        Intent openAboutUsFromMain = new Intent(HomeActivity.this, AboutUsActivity.class);
        startActivity(openAboutUsFromMain);
        Animatoo.animateSwipeRight(HomeActivity.this);

    }



    private void Images() {
        Intent openImagesFromMain = new Intent(HomeActivity.this, ImagesActivity.class);
        startActivity(openImagesFromMain);
        Animatoo.animateSwipeRight(HomeActivity.this);

    }


    private void ProfileDisplay() {
        Intent openProfileActivityFromMain = new Intent(HomeActivity.this, ProfileActivity.class);
        startActivity(openProfileActivityFromMain);
        Animatoo.animateSwipeRight(HomeActivity.this);
    }

    private void SignOut() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        finish();
        Intent openLoginActivityFromMain = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(openLoginActivityFromMain);
        Animatoo.animateSlideDown(HomeActivity.this);
    }

    private void GeneralShareMain() {

        setShareDialogue();
        Handler handler = new Handler();
        int TRANSITION_SCREEN_LOADING_TIME = 3400;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent GeneralShareMainIntent = new Intent(Intent.ACTION_SEND);
                final String appPackageName = getApplicationContext().getPackageName();
                String appLink = "https://play.google.com/store/apps/details?id=" + appPackageName;


                GeneralShareMainIntent.setType("Text/plain");
                String generalMainShareBody = "Rolex:A Crown For Every Achievement      " +
                        "" +
                        appLink;
                String generalMainShareSub = "Rolex App";
                GeneralShareMainIntent.putExtra(Intent.EXTRA_SUBJECT, generalMainShareSub);
                GeneralShareMainIntent.putExtra(Intent.EXTRA_TEXT, generalMainShareBody);
                startActivity(Intent.createChooser(GeneralShareMainIntent, "Share Via"));
                Animatoo.animateSpin(HomeActivity.this);
            }
        }, TRANSITION_SCREEN_LOADING_TIME);


    }

    private void setShareDialogue() {
        final ShareDialogue shareDialogue = new ShareDialogue(HomeActivity.this);
        shareDialogue.startShareDialogue();
        Handler handler = new Handler();
        int TRANSITION_SCREEN_TIME = 3000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shareDialogue.dismissDialogue();
            }
        }, TRANSITION_SCREEN_TIME);
    }


    private void LikeFacebookPage() {
        try {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + "101799174956163"));
            startActivity(intent);

        } catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + "101799174956163"));
            startActivity(intent);

        }

    }


    private void ShareOnFacebook() {
        try {
            setFacebookShareDialogue();
            Handler handler = new Handler();
            int TRANSITION_SCREEN_LOADING_TIME = 4500;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent shareOnFacebookIntent = new Intent(Intent.ACTION_SEND);
                    shareOnFacebookIntent.setType("text/plain");
                    final String appPackageName = getApplicationContext().getPackageName();
                    ShareOnFacebookString = "https://play.google.com/store/apps/details?id=" +
                            "" + appPackageName;
                    shareOnFacebookIntent.putExtra(Intent.EXTRA_TEXT, ShareOnFacebookString);
                    shareOnFacebookIntent.setPackage("com.facebook.katana");
                    startActivity(shareOnFacebookIntent);
                    Animatoo.animateSpin(HomeActivity.this);
                }
            }, TRANSITION_SCREEN_LOADING_TIME);


        } catch (Exception FacebookException) {

            Toasty.warning(HomeActivity.this, "Install the Facebook App", Toast.LENGTH_LONG).show();

        }
    }

    private void setFacebookShareDialogue() {
        final FacebookShareDialogue facebookShareDialogue = new FacebookShareDialogue(HomeActivity.this);
        facebookShareDialogue.startFacebookShareDialogue();
        Handler handler = new Handler();
        int TRANSITION_SCREEN_TIME = 4000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                facebookShareDialogue.dismissDialogue();
            }
        }, TRANSITION_SCREEN_TIME);
    }

    private void ShareOnTwitter() {
        try {
            setTwitterShareDialogue();
            Handler handler = new Handler();
            int TRANSITION_SCREEN_LOADING_TIME = 4500;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent shareOnTwitterIntent = new Intent(Intent.ACTION_SEND);
                    shareOnTwitterIntent.setType("text/plain");
                    final String appPackageName = getApplicationContext().getPackageName();
                    ShareOnTwitterString = "https://play.google.com/store/apps/details?id=" + appPackageName;
                    shareOnTwitterIntent.putExtra(Intent.EXTRA_TEXT, "Rolex:A Crown For Every Achievement      " +
                            "" + ShareOnTwitterString);
                    shareOnTwitterIntent.setPackage("com.twitter.android");
                    startActivity(shareOnTwitterIntent);
                    Animatoo.animateSpin(HomeActivity.this);
                }
            }, TRANSITION_SCREEN_LOADING_TIME);

        } catch (Exception TwitterException) {

            Toasty.warning(HomeActivity.this, "Install the Twitter App", Toast.LENGTH_LONG).show();

        }
    }

    private void setTwitterShareDialogue() {
        final TwitterShareDialogue twitterShareDialogue = new TwitterShareDialogue(HomeActivity.this);
        twitterShareDialogue.startTwitterShareDialogue();
        Handler handler = new Handler();
        int TRANSITION_SCREEN_TIME = 4000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                twitterShareDialogue.dismissDialogue();
            }
        }, TRANSITION_SCREEN_TIME);
    }

    private void openCompanyInfo() {
        Intent openCompanyInfoActivityFromMain = new Intent(HomeActivity.this, CompanyInfo.class);
        startActivity(openCompanyInfoActivityFromMain);
        Animatoo.animateSwipeRight(HomeActivity.this);
    }

    public void updateNavHeader() {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerCrop().into(navUserPhoto);
                final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
                displayDataEmailPassword(databaseReference);
            }
        });
        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                navUserMail.setText(currentUser.getEmail());
                navUsername.setText(currentUser.getDisplayName());
                Glide.with(HomeActivity.this).load(currentUser.getPhotoUrl()).into(navUserPhoto);
            }
        });

    }

    private void displayDataEmailPassword(DatabaseReference databaseReference) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userProfile = dataSnapshot.getValue(UserProfile.class);


                navUsername.setText(userProfile.getUserName());
                navUserMail.setText(userProfile.getUserEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toasty.error(HomeActivity.this, databaseError.getCode(), Toast.LENGTH_LONG).show();

            }
        });
    }


    private void itemTouchCategories() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(categoriesRecyclerView);
    }

    final ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {

            final int position = viewHolder.getAdapterPosition();
            final CategoriesData categoriesItem = myCategoriesList.get(position);
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    deletedCategories.add(categoriesItem);
                    myCategoriesList.remove(position);
                    myCategoriesAdapter.notifyItemRemoved(position);
                    Snackbar.make(categoriesRecyclerView, "Deleted.", Snackbar.LENGTH_LONG).show();
                    break;
                case ItemTouchHelper.RIGHT:
                    favouriteCategories.add(categoriesItem);
                    myCategoriesAdapter.notifyItemRemoved(position);
                    Snackbar.make(categoriesRecyclerView, "Added to favourites.", Snackbar.LENGTH_LONG).show();
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView categoriesRecyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(HomeActivity.this, c, categoriesRecyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_deleted_swipe_main)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.green))
                    .addSwipeRightActionIcon(R.drawable.ic_favourite_swipe_main)
                    .setActionIconTint(ContextCompat.getColor(categoriesRecyclerView.getContext(), android.R.color.white))
                    .create()
                    .decorate();

            super.onChildDraw(c, categoriesRecyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    private void loadMainData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(HomeActivity.this, 1);
        categoriesRecyclerView.setLayoutManager(gridLayoutManager);
        myCategoriesList = new ArrayList<>();
        mCategoriesData = new CategoriesData("DAY-DATE", "Day-Date\n" +
                "36 mm in Everose gold\n" +
                "\n" +
                "Precious Model:- ARMOR PLATINUM PRESIDENT 228206 ICE BLUE DIAMOND DIAL\n" +
                "From Its Launch In 1956, The Day-Date Was Immediately\n" +
                "Recognised as The Watch Worn by Influential People.\n" +
                "The Day-Date Was the First Calendar Wristwatch to Display the Day, As Well As the Date, In Its Entirety. Faithful To Its Noble Origins, The Day-Date Is Made Exclusively Of Gold Or Platinum, The Precious Metals\n", "49995", R.drawable.daydate_categories);
        myCategoriesList.add(mCategoriesData);
        mCategoriesData = new CategoriesData("DATE-JUST", "DateJust\n" +
                "36 mm in steel and yellow gold\n" +
                "\n" +
                "Precious Model:- ARMOR DATEJUST 116185 DIAMOND BEZEL & DIAL\n" +
                "The Elegant Design And Cyclops Eye Magnifying The  Date Have Made The Oyster Perpetual " +
                "Datejust, Originally Introduced In 1945, One Of The World’s Most Recognizable Watches.\n"
                , "15995", R.drawable.datejust_categories);
        myCategoriesList.add(mCategoriesData);
        mCategoriesData = new CategoriesData("GMT-MASTER 2", "Gmt-Master ii\n" +
                "40 mm in gold\n" +
                "\n" +
                "Precious Model:- VINTAGE ARMOR GMT-MASTER 6542 BAKELITE BEZEL NIPPLE DIAL\n" +
                "The GMT-Master, Introduced In 1955, Was Developed To Meet The Needs Of International Pilots. The GMT-Master II, Unveiled In 2005, Has Proved to Be Even More Invaluable to Those Whose Professions Involve Long-Distance Travel.\n" +
                "Featuring A Rotatable 24-Hour Graduated Bezel and A Separate 24-Hour Hand, It Allows Those Who Travel the World to Read Three Different Time Zones. Two Simultaneously.\n"
                , "174950", R.drawable.gmtmaster2_categories);
        myCategoriesList.add(mCategoriesData);
        mCategoriesData = new CategoriesData("SUBMARINER", "Submariner Date\n" +
                "40 Mm In Steel\n" +
                "\n" +
                "Precious Model:- ARMOR 116618LN SUBMARINER\n" +
                "The Oyster Perpetual Submariner Is The Quintessence Of Diving Watches, The Reference Watch In Its Category.\n" +
                "Introduced In 1953 During The Pioneering Era Of Scuba Diving, It Became The First Hermetic Watch Capable Of Withstanding Up To 100 Meters (330 Feet). Since Then, This Iconic Watch Has Evolved with A Series of Technical Innovations Patented by Rolex, \n" +
                "Guaranteeing Its Reliability and Precision to A Depth Of 300 Meters (1,000 Feet). Although Its Most Familiar Environment Is the Oceans, Where It Continues to Be an Indispensable Instrument for Every Diver, The Submariner Conquered the Mainland as The Action Clock with A Robust Elegance of Its Own. The Submariner Is Available With Or Without Date Display"
                , "59895", R.drawable.submariner_categories);
        myCategoriesList.add(mCategoriesData);
        mCategoriesData = new CategoriesData("COSMOGRAPH DAYTONA", "Cosmograph Daytona\n" +
                "40 Mm In Everose Gold\n" +
                "\n" +
                "Precious Model:- 18K YELLOW GOLD ARMOR DAYTONA WHITE DIAL\n" +
                "The Cosmograph Daytona, Introduced In 1963, Was Designed To Meet The Demands Of Professional Racing Drivers. With Its Highly Reliable Chronograph and Bezel with Tachymetric Scale, It Allows Drivers to Perfectly Measure Average Speeds Up To 400 Kilometres or Miles Per Hour, As They Choose. An Icon Eternally Joined In Name And Function To The High-Performance World Of Motor Sport.\n", "23995", R.drawable.cosmographdaytona_categories);
        myCategoriesList.add(mCategoriesData);
        mCategoriesData = new CategoriesData("YACHT-MASTER", "Yacht-Master\n" +
                "40 Mm In Steel\n" +
                "\n" +
                "Precious Model:- ARMOR YACHT-MASTER 226659 WHITE GOLD\n" +
                "Sleek, Sporty, Distinguished: The Oyster Perpetual Yacht Master Symbolises The Privileged Ties Between Rolex And The World Of Sailing That Stretch Back To The 1950s. It Is The Only Oyster Professional Model Offered In Three Sizes:\n" +
                "40, 35 And 29 Mm.\n"
                , "28995", R.drawable.yachtmaster_categories);
        myCategoriesList.add(mCategoriesData);
        mCategoriesData = new CategoriesData("SEA-DWELLER", "Sea-Dweller\n" +
                "Oyster, 43 Mm, Oystersteel And Yellow Gold\n" +
                "\n" +
                "Precious Model:- VINTAGE ARMOR SEA-DWELLER 1665 RAIL DIAL 1979\n" +
                "The Sea-Dweller And Rolex Deepsea Are Ultra-Resistant Divers’ Watches\n" +
                "Engineered By Rolex For Deep-Sea Exploration. Waterproof To A Depth Of 4,000 Feet\n" +
                "(1,220 Metres) For The Rolex Sea-Dweller, Launched In 1967, And 12,800 Feet (3,900\n" +
                "Metres) For The Rolex Deepsea Unveiled In 2008, They Are The Ultimate Manifestation\n" +
                "Of Rolex’s Leadership In Divers’ Watches And The Result Of Decades Of Collaboration\n" +
                "With Diving Professionals. \n"

                , "24995", R.drawable.seadweller_categories);
        myCategoriesList.add(mCategoriesData);
        mCategoriesData = new CategoriesData("EXPLORER II", "Explorer II\n" +
                "39 mm in steel\n" +
                "\n" +
                "Precious Model:- VINTAGE 1979 ARMOR EXPLORER II 1655 MARK 2 DIAL\n" +
                "The Explorer is inspired by Rolex’s decades of experience in the Himalayas, and pays tribute to the first successful ascent of Mount Everest in 1953 by an expedition equipped with Rolex Oyster Perpetual chronometers." +
                " It was first launched that very year as the ultimate expedition and mountaineering watch.\n", "18995", R.drawable.explorer_categories);
        myCategoriesList.add(mCategoriesData);
        mCategoriesData = new CategoriesData("SKY-DWELLER", "Sky-Dweller\n" +
                "42 mm in white gold\n" +
                "\n" +
                "Precious Model:- ARMOR SKY-DWELLER 326938\n" +
                "A technological masterpiece protected by 14 patents,\n" +
                "the Oyster Perpetual Sky-Dweller provides the information global travellers need to keep track of time at a glance.\n" +
                "Launched in 2012, the Sky-Dweller is a compelling timepiece of revolutionary design that blends to perfection mechanical sophistication and ease of use.\n", "37995", R.drawable.skydweller_categories);
        myCategoriesList.add(mCategoriesData);
        mCategoriesData = new CategoriesData("MILGAUSS", "Milgauss\n" +
                "40 mm in steel\n" +
                "\n" +
                "Precious Model:- VINTAGE 1967 ARMOR MILGAUSS 1019 CERN DIAL\n" +
                "The Milgauss, introduced in 1956, was designed to meet the demands of the scientific community and is capable of withstanding magnetic fields of up to 1,000 gauss.\n" +
                "It became known notably as the watch worn by scientists at the European Organization for Nuclear Research (CERN) in Geneva. The new-generation Milgauss, introduced in\n" +
                "2007, features several innovative components that enhance protection from magnetic interference.\n", "32295", R.drawable.milgauss_categories);
        myCategoriesList.add(mCategoriesData);
        mCategoriesData = new CategoriesData("PEARLMASTER", "The Oyster Perpetual Pearlmaster\n" +
                "26 mm in steel\n" +
                "\n" +
                "Precious Model:- ROLEX PEARLMASTER 39MM OYSTER EVEROSE DIAMONDS\n" +
                "The Oyster Perpetual Pearlmaster has stood out since its launch in 1992, exemplifying a delicate balance of elegance and style, thanks to its elegant lines and precious materials." +
                " Set with diamonds, rubies, sapphires or emeralds, it is a new, feminine reinterpretation of Rolex’s emblematic Datejust.\n", "21367", R.drawable.pearlmaster_categories);
        myCategoriesList.add(mCategoriesData);
        mCategoriesData = new CategoriesData("AIR-KING", "Air-King\n" +
                "40 mm in steel\n" +
                "\n" +
                "Precious Model:- ARMOR AIR-KING 116900 BLACK ARABIC DIAL\n" +
                "The Rolex Air-King pays tribute to the pioneers of flight and the Oyster’s roles in\n" +
                "the epic story of aviation. With its 40mm case in Oystersteel, solid-link Oyster\n" +
                "bracelet with Oysterclasp, and distinctive black dial, the Air-King perpetuates the\n" +
                "aeronautical heritage of the original Rolex Oyster.\n", "5795", R.drawable.airking_categories);
        myCategoriesList.add(mCategoriesData);

        myCategoriesAdapter = new MyCategoriesAdapter(HomeActivity.this, myCategoriesList);
        categoriesRecyclerView.setAdapter(myCategoriesAdapter);
    }


}