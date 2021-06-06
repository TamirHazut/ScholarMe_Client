package com.example.scholarme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements Callback<List<Scholarship>> {
    private Form_Fragment form_fragment;
    private Fragment_Base fragment;
    private FragmentManager fragment_manager;
    private Publish_Scholarship_Fragment publish_scolarship;
    private Results_Fragment results_fragment;
    private Gson gson;
    private Retrofit retrofit;
    private ScholarshipsAPI scholarshipsAPI;
    private Search_Scholarship search_scholarship;
    private MeowBottomNavigation bottomNav;
    private List<Scholarship> scholarshipsResults;

  //  private BottomNavigationView bottomNav;

//    private Call_Back_SearchForm call_back_searchForm = new Call_Back_SearchForm() {
//        @Override
//        public void searchForm() {
//            form_fragment = new Form_Fragment();
//            form_fragment.setCall_back_searchForm(call_back_searchForm);
//            fragment = form_fragment;
//            loadFragment(fragment);
//        }
//    };
    private Call_Back_Results call_back_results = new Call_Back_Results() {
        @Override
        public void showResults() {
            results_fragment = new Results_Fragment();
            results_fragment.setCall_back_showResults(call_back_results);
            fragment = results_fragment;
            loadFragment(fragment);
        }
    };
    private Call_Back_Publish_Scholarship call_back_publish_scholarship = new Call_Back_Publish_Scholarship() {
        @Override
        public void publishScholarship() {
            publish_scolarship = new Publish_Scholarship_Fragment();
            publish_scolarship.setCall_back_publishScholarship(call_back_publish_scholarship);
            fragment = publish_scolarship;
            loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.scholarshipsResults = new ArrayList<>();
        this.gson = new GsonBuilder().setLenient().create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.scholarshipsAPI = retrofit.create(ScholarshipsAPI.class);


        findviews();
        initviews();
        //Glide.with(this).load("https://image.freepik.com/free-vector/abstract-low-polygonal-graduation-cap_127544-825.jpg").into(this.background_IMG);

    }

    private void initviews() {

        fragment_manager = getFragmentManager();
        form_fragment = new Form_Fragment();
        form_fragment.setCall_back_searchForm(new Call_Back_SearchForm() {
            @Override
            public void searchForm(Search_Scholarship search_scholarship) {
                getScholarships(search_scholarship);
            }
        });
        loadFragment(form_fragment);

        //add items
        bottomNav.add(new MeowBottomNavigation.Model(1,R.drawable.ic_search));
        bottomNav.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        bottomNav.add(new MeowBottomNavigation.Model(3,R.drawable.ic_publish));

        bottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                        case 1:
                            if(search_scholarship != null && !bottomNav.isShowing(1)) {
// String institute, String degree, String location, String sectors, String study_year, String graduation_year, Gender gender, Contribution contribution
                                //Search_Scholarship search_scholarship =new Search_Scholarship("1", "1", "1", "1", "1", "1", Gender.FEMALE, true);
                                //Log.d("pttt", gson.toJson(search_scholarship));
                                showResultFragment();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Please Fill The Form First", Toast.LENGTH_SHORT).show();
                            }
                            break;

                        case 2:
                            Log.d("pttt","products");
                            form_fragment= new Form_Fragment();
                            form_fragment.setCall_back_searchForm(new Call_Back_SearchForm() {
                                @Override
                                public void searchForm(Search_Scholarship search_scholarship) {
                                    getScholarships(search_scholarship);
                                }
                            });
                            //fragment = form_fragment;
                            loadFragment(form_fragment);
                            break;
                        case 3:
                            Log.d("pttt","cart");
                            publish_scolarship = new Publish_Scholarship_Fragment();
                            fragment = publish_scolarship;
                            loadFragment(fragment);
                            break;
                    }
            }
        });

        bottomNav.show(2,true);


        bottomNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(),"You Clicked- "+item.getId(),Toast.LENGTH_SHORT).show();
            }
        });

        bottomNav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(),"You Reselected- "+item.getId(),Toast.LENGTH_SHORT).show();
            }
        });
        //bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void findviews() {
        bottomNav = findViewById(R.id.main_BAR_bottomNav);

    }
    private void loadFragment(Fragment_Base fragment) {
        // load fragment
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fragment_manager.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.main_LAY_frame,fragment);
        fragmentTransaction.commit(); // save the changes
    }

    private void showResultFragment() {
        bottomNav.setCount(1, String.valueOf(scholarshipsResults.size()));
        Bundle args = new Bundle();
        results_fragment = new Results_Fragment();
        args.putString(Constants.SCHOLARSHIPS, gson.toJson(scholarshipsResults));
        results_fragment.setArguments(args);
        loadFragment(results_fragment);
    }

    @Override
    public void onResponse(Call<List<Scholarship>> call, Response<List<Scholarship>> response) {
        if (response.isSuccessful()) {
            scholarshipsResults = response.body();
            bottomNav.show(1, true);
            //showResultFragment();
        } else {
            Log.e("pttt", "Response " + response.code() + " - " + response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<List<Scholarship>> call, Throwable t) {
        Log.e("pttt", "Failed: " + t.getMessage());
    }

    public void getScholarships(Search_Scholarship scholarship) {
        search_scholarship = scholarship;
        Call<List<Scholarship>> call = scholarshipsAPI.getScholarships(scholarship);
        call.enqueue(this);

//        Bundle args = new Bundle();
//        results_fragment = new Results_Fragment();
//        args.putString(Constants.SCHOLARSHIPS, gson.toJson(getMock()));
//        results_fragment.setArguments(args);
//        loadFragment(results_fragment);
    }

//    public List<Scholarship> getMock() {
//        List<Scholarship> scholarships = new ArrayList<>();
//        // String id, String name, Date create_time, String description, List<String> links, Set<String> tags, double matchingPercentage
//        scholarships.add(new Scholarship("1", "1", new Date(), "1", new ArrayList<>(), new HashSet<>(), 100));
//        scholarships.add(new Scholarship("2", "2", new Date(), "2", new ArrayList<>(), new HashSet<>(), 90));
//        scholarships.add(new Scholarship("3", "3", new Date(), "3", new ArrayList<>(), new HashSet<>(), 80));
//        scholarships.add(new Scholarship("4", "4", new Date(), "4", new ArrayList<>(), new HashSet<>(), 70));
//        scholarships.add(new Scholarship("5", "5", new Date(), "5", new ArrayList<>(), new HashSet<>(), 60));
//        scholarships.add(new Scholarship("6", "6", new Date(), "6", new ArrayList<>(), new HashSet<>(), 50));
//        scholarships.add(new Scholarship("7", "7", new Date(), "7", new ArrayList<>(), new HashSet<>(), 40));
//        scholarships.add(new Scholarship("8", "8", new Date(), "8", new ArrayList<>(), new HashSet<>(), 100));
//        return scholarships;
//    }

    private OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}