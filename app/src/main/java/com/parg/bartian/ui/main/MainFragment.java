package com.parg.bartian.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parg.bartian.R;
import com.parg.bartian.data.preference.PreferenceHelper;
import com.parg.bartian.data.service.AdvisoryInformationService;
import com.parg.bartian.data.service.AllStationsService;
import com.parg.bartian.data.service.ServiceLocator;
import com.parg.bartian.domain.Advisory;
import com.parg.bartian.domain.Bsa;
import com.parg.bartian.domain.Estimate;
import com.parg.bartian.domain.Etd;
import com.parg.bartian.domain.Fare;
import com.parg.bartian.domain.Fares;
import com.parg.bartian.domain.Leg;
import com.parg.bartian.domain.RealTimeInformation;
import com.parg.bartian.domain.Request;
import com.parg.bartian.domain.ScheduleDetail;
import com.parg.bartian.domain.ScheduleRealTimeInformation;
import com.parg.bartian.domain.Station;
import com.parg.bartian.domain.Stations;
import com.parg.bartian.domain.Trip;
import com.parg.bartian.ui.custom.VadaProgressBar;
import com.parg.bartian.ui.dialog.AdvisoryDialog;
import com.parg.bartian.ui.dialog.FareDialogFragment;
import com.parg.bartian.ui.util.DateUtil;
import com.parg.bartian.ui.util.ScheduleUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public static String TAG = "com.parg.bartian.MainFragment - ";
    private Spinner originSpinner;
    private Spinner destinationSpinner;
    private List<Station> stationList;
    private Button getScheduleButton;
    private TextView nextTrainIn;
    private VadaProgressBar nextTrainPgBar;
    Timer progressTimer = new Timer();
    private ProgressBarTimerTask progressBarTimerTask;
    private String defaultOriginStation;
    private String defaultDestinationStation;
    private static final String DEFAULT_ORIGIN_STATION = "defaultOriginStation";
    private static final String DEFAULT_DESTINATION_STATION = "defaultDestinationStation";
    private String hexcolor;
    private TextView transferAt;
    private HashMap<String, String> stationCodeName = new HashMap<>();
    private LinearLayout transferAtContainer;
    private TextView getFareTextView;
    private ArrayList<String> fareList = new ArrayList<>();
    private static final String GET_SCHEDULE = "Please wait ...";
    private static final String NEXT_TRAIN = "Get departure time";
    private int RE_REQUEST_DELAY_TIME_IN_MILLIS = 5000;
    private Station selectedOriginStation;
    private Station selectedDestStation;
    private ImageView swapStation;
    private ImageView bikeFlag;
    private TextView platform;
    private ImageView advisoryImageview;
    private String advisoryTextMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        setRetainInstance(true);
        originSpinner = view.findViewById(R.id.origin_spinner);
        destinationSpinner = view.findViewById(R.id.destination_spinner);
        getScheduleButton = view.findViewById(R.id.getSchedule);
        nextTrainIn = view.findViewById(R.id.nextTrainTime);
        nextTrainPgBar = view.findViewById(R.id.next_train_pgbar);
        transferAt = view.findViewById(R.id.transferAt);
        transferAtContainer = view.findViewById(R.id.transferAtContainer);
        getFareTextView = view.findViewById(R.id.getFare);
        swapStation = view.findViewById(R.id.swapStation);
        bikeFlag = view.findViewById(R.id.bike);
        platform = view.findViewById(R.id.platform);
        advisoryImageview = view.findViewById(R.id.advisory);

        advisoryImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(advisoryTextMessage != null) {
                    AdvisoryDialog advisoryDialog = AdvisoryDialog.newInstance(advisoryTextMessage);
                    advisoryDialog.show(getFragmentManager(), "AdvisoryDialog");
                }
            }
        });

        SpannableString getFareSpannable = new SpannableString("Get Fare");
        getFareSpannable.setSpan(new UnderlineSpan(), 0, getFareSpannable.length(), 0);
        getFareTextView.setText(getFareSpannable);
        getFareTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fareList.size() > 0) {
                    FareDialogFragment fareDialogFragment = FareDialogFragment.newInstance(fareList);
                    fareDialogFragment.show(getFragmentManager(), "FareDialog");
                }
            }
        });

        swapStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                originSpinner.setSelection(ScheduleUtil.INSTANCE.getStationIndex(stationList, defaultDestinationStation));
                destinationSpinner.setSelection(ScheduleUtil.INSTANCE.getStationIndex(stationList, defaultOriginStation));
                String tempOriginStation = defaultDestinationStation;
                String tempDestStation = defaultOriginStation;
                defaultOriginStation = tempOriginStation;
                defaultDestinationStation = tempDestStation;
                getSchedule(defaultOriginStation, defaultDestinationStation);
            }
        });

        getScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                fareList.clear();
                Station origStation = (Station)originSpinner.getSelectedItem();
                Station destStation = (Station)destinationSpinner.getSelectedItem();

                if(origStation.getAbbr().contentEquals(destStation.getAbbr())) {
                    Toast.makeText(getActivity(),"Origin & Destination couldn't be same",Toast.LENGTH_SHORT).show();
                } else {
                    defaultOriginStation = origStation.getAbbr();
                    defaultDestinationStation = destStation.getAbbr();
                    getSchedule(defaultOriginStation, defaultDestinationStation);
                }
            }
        });

        originSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedOriginStationAbbr = stationList.get(position).getAbbr();
                if(selectedOriginStation == null) {
                    selectedOriginStation = stationList.get(position);
                    return;
                }
                if(selectedOriginStation !=null && !selectedOriginStation.getAbbr().contentEquals(selectedOriginStationAbbr)) {
                    selectedOriginStation = stationList.get(position);
                    getFareTextView.setVisibility(View.GONE);
                    transferAtContainer.setVisibility(View.GONE);
                    nextTrainIn.setText(null);
                    nextTrainPgBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}

        });

        destinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedDestinationStationAbbr = stationList.get(position).getAbbr();
                if(selectedDestStation == null) {
                    selectedDestStation = stationList.get(position);
                    return;
                }
                if(selectedDestStation !=null && !selectedDestStation.getAbbr().contentEquals(selectedDestinationStationAbbr)) {
                    selectedDestStation = stationList.get(position);
                    getSchedule(selectedOriginStation.getAbbr(), selectedDestStation.getAbbr());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        if(savedInstanceState == null) {
            defaultOriginStation = PreferenceHelper.getStringPreference(getContext(), DEFAULT_ORIGIN_STATION, null);
            defaultDestinationStation = PreferenceHelper.getStringPreference(getContext(), DEFAULT_DESTINATION_STATION, null);
            stationList = getStationLocalList();
            createStationNameCodePair(stationList);
            setOriginStation(stationList, defaultOriginStation);
            setDestinationStation(stationList, defaultDestinationStation);
            if(defaultOriginStation != null && defaultDestinationStation != null) {
                getSchedule(defaultOriginStation, defaultDestinationStation);
            }
        } else {
            if(stationList != null && !stationList.isEmpty()) {
                setOriginStation(stationList, defaultOriginStation);
                setDestinationStation(stationList, defaultDestinationStation);
            }
            nextTrainIn.setText(savedInstanceState.getString("nextTrainIn"));
        }

        return view;
    }

    private void showProgress() {
        getFareTextView.setVisibility(View.GONE);
        getScheduleButton.setEnabled(false);
        getScheduleButton.setText(GET_SCHEDULE);
    }

    private void hideProgress() {
        getFareTextView.setVisibility(View.VISIBLE);
        getScheduleButton.setEnabled(true);
        getScheduleButton.setText(NEXT_TRAIN);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        getSchedule(defaultOriginStation, defaultDestinationStation);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("nextTrainIn", nextTrainIn.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    public void getStationList() {
//        showProgressBar();
        AllStationsService allStationsService = ServiceLocator.INSTANCE.getAllStationsService();
        Observable<Stations> observableAllStationService = allStationsService.Observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observableAllStationService.subscribe(new Observer<Stations>() {
            @Override
            public void onCompleted() {

                Log.d("","Subscriber --- onCompleted() ---");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Stations stations) {
                Log.d("","Subscriber --- onNext() ---");
                if(stations!=null && stations.getStations()!=null && !stations.getStations().isEmpty()) {
                    stationList = stations.getStations();
                    createStationNameCodePair(stationList);
                    setOriginStation(stations.getStations(), defaultOriginStation);
                    setDestinationStation(stations.getStations(), defaultDestinationStation);
                }
            }
        });
    }

    private void createStationNameCodePair(List<Station> stationList) {
        if(stationList !=null && !stationList.isEmpty()) {
            for(Station station:stationList) {
                stationCodeName.put(station.getAbbr(), station.getName());
            }
        }

    }

    public void setOriginStation(List<Station> stationList, String defaultOriginStation) {
        ArrayAdapter<Station> fromStationsAdapter= new ArrayAdapter(getActivity(), R.layout.station_listitem,R.id.station_name, stationList);
        originSpinner.setAdapter(fromStationsAdapter);
        originSpinner.setSelection(ScheduleUtil.INSTANCE.getStationIndex(stationList, defaultOriginStation));
    }

    public void setDestinationStation(List<Station> stationList, String defaultDestStation) {
        ArrayAdapter<Station> toStationsAdapter= new ArrayAdapter(getActivity(), R.layout.station_listitem,R.id.station_name, stationList);
        destinationSpinner.setAdapter(toStationsAdapter);
        destinationSpinner.setSelection(ScheduleUtil.INSTANCE.getStationIndex(stationList, defaultDestStation));
    }

    private void getSchedule(final String originAbbr, final String destinationAbbr) {
        Observable<ScheduleDetail> scheduleDetailObservable = ServiceLocator.INSTANCE
                .getScheduleService()
                .getSchedule(originAbbr, destinationAbbr)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<RealTimeInformation> realTimeInformationObservable = ServiceLocator.INSTANCE
                .getRealTimeService()
                .getRealTimeInformation(originAbbr)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        Observable<ScheduleRealTimeInformation> combined = Observable.zip(scheduleDetailObservable, realTimeInformationObservable, new Func2<ScheduleDetail, RealTimeInformation, ScheduleRealTimeInformation>() {
            @Override
            public ScheduleRealTimeInformation call(ScheduleDetail scheduleDetail, RealTimeInformation realTimeInformation) {
                return new ScheduleRealTimeInformation(scheduleDetail, realTimeInformation);
            }
        });

        combined.subscribe(new Subscriber<ScheduleRealTimeInformation>() {
            @Override
            public void onCompleted() {
                hideProgress();
//                hideProgressBar();
                Log.d(TAG, "--- onCompleted | ScheduleRealTimeInformation --- ");
            }

            @Override
            public void onError(Throwable e) {
//                hideProgressBar();
                hideProgress();
                Log.d(TAG, "--- onError | ScheduleRealTimeInformation --- ");
                e.printStackTrace();
            }

            @Override
            public void onNext(ScheduleRealTimeInformation scheduleRealTimeInformation) {
                Log.d(TAG, "--- onNext | ScheduleRealTimeInformation --- ");
                PreferenceHelper.setStringPreference(getContext(), DEFAULT_ORIGIN_STATION, originAbbr);
                PreferenceHelper.setStringPreference(getContext(), DEFAULT_DESTINATION_STATION, destinationAbbr);
                showScheduleDetails(scheduleRealTimeInformation.getScheduleDetail(), scheduleRealTimeInformation.getRealTimeInformation());
            }
        });
    }

    private void showScheduleDetails(ScheduleDetail scheduleDetail, RealTimeInformation realTimeInformation) {
        getAdvisory();
        Request scheduleRequest = scheduleDetail.getSchedule().getRequest();
        if(isRealTimeInformationEtdAvailable(realTimeInformation) && isScheduleTripAvaialble(scheduleDetail)) {
            Trip trip = scheduleRequest.getTrip().get(0);
            setFareList(trip.getFares());
            List<Leg> legList = trip.getLeg();
            if(legList != null && ! legList.isEmpty()) {
                Leg firstLeg = legList.get(0);
                if(firstLeg !=null) {
                    Etd etd = ScheduleUtil.getInstance().getEtd(realTimeInformation.getStation().getEtdList(), firstLeg.getTrainHeadStation());
                    if(etd != null) {
                        Estimate estimate = etd.getEstimateList().get(0);
                        String departureTime;
                        try {
                            hexcolor = estimate.getHexcolor();
                            if(estimate.getPlatform() > 0) {
                                platform.setVisibility(View.VISIBLE);
                                platform.setText("Platform " + estimate.getPlatform() + ", " + estimate.getLength() + " cars");
                            } else {
                                platform.setVisibility(View.GONE);
                            }
                            if(estimate.getBikeflag() >0) {
                                bikeFlag.setVisibility(View.VISIBLE);
                            } else {
                                bikeFlag.setVisibility(View.GONE);
                            }
                            if (firstLeg.getTransferCode() != null && !firstLeg.getTransferCode().isEmpty()) {
                                transferAtContainer.setVisibility(View.VISIBLE);
                                transferAt.setText(stationCodeName.get(firstLeg.getDestination()));
                            } else {
                                transferAtContainer.setVisibility(View.GONE);
                            }
                            int estimatedMinutes = Integer.valueOf(estimate.getMinutes());
                            departureTime = DateUtil.INSTANCE.getDepartureTime(ScheduleUtil.INSTANCE.getRealTimeInfoDateAndTime(realTimeInformation), estimatedMinutes);
                            String departureDateAndTime = ScheduleUtil.INSTANCE.getRealTimeInfoDateAndTime(realTimeInformation);
                            setRemainingTimer(departureDateAndTime, estimatedMinutes);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            departureTime = "Leaving ...";
                        }
                        nextTrainIn.setText(departureTime);
                    } else {
                        nextTrainIn.setText("No departure details currently available for the selected route. \n"+ new Date());
                        platform.setVisibility(View.GONE);
                        transferAtContainer.setVisibility(View.GONE);
                        getFareTextView.setVisibility(View.GONE);
                    }
                } else {
                    platform.setVisibility(View.GONE);
                    nextTrainIn.setText("No departure details currently available for the route \n"+ new Date());
                    transferAtContainer.setVisibility(View.GONE);
                    getFareTextView.setVisibility(View.GONE);
                }
            } else {
                platform.setVisibility(View.GONE);
                nextTrainIn.setText("No departure details currently available for the route \n"+ new Date());
                transferAtContainer.setVisibility(View.GONE);
                getFareTextView.setVisibility(View.GONE);
            }
        } else {
            platform.setVisibility(View.GONE);
            nextTrainIn.setText("No departure details currently available for the route \n"+ new Date());
            transferAtContainer.setVisibility(View.GONE);
            getFareTextView.setVisibility(View.GONE);
        }
    }

    private void setFareList(Fares fares) {
        List<Fare> fareList1 = fares.getFareList();
        if(fareList1 != null && !fareList1.isEmpty()) {
            fareList.clear();
            for(Fare fare: fareList1) {
                StringBuilder builder = new StringBuilder();
                builder.append(fare.getName());
                builder.append(" --  $");
                builder.append(fare.getAmount());
                fareList.add(builder.toString());
            }
        }
    }

    private void setRemainingTimer(String departureDateAndTime, int estimatedMinutes) {
        Date departureDate = DateUtil.INSTANCE.getDepartureDate(departureDateAndTime, estimatedMinutes);
        diffInSecs = DateUtil.getRemainingTimeInSec(departureDate);
        origDiffInSecs = diffInSecs;
        if (diffInSecs > 0) {
            nextTrainPgBar.setVisibility(View.VISIBLE);
            if(progressBarTimerTask == null) {
                progressBarTimerTask = new ProgressBarTimerTask();
            } else {
                progressBarTimerTask.cancel();
                progressTimer.purge();
                progressBarTimerTask = new ProgressBarTimerTask();
                progress = 0;
            }
            if(hexcolor != null) {
                int color = (int)Long.parseLong(hexcolor.replace("#",""), 16);
                int r = (color >> 16) & 0xFF;
                int g = (color >> 8) & 0xFF;
                int b = (color >> 0) & 0xFF;
                nextTrainPgBar.setInnerBackgroundColor(Color.rgb(r, g, b));
            }
            progressTimer.scheduleAtFixedRate(progressBarTimerTask, 0, 1000);
        } else {
            nextTrainPgBar.setVisibility(View.GONE);
        }
    }


    private boolean isRealTimeInformationEtdAvailable(RealTimeInformation realTimeInformation) {
        if(realTimeInformation != null &&
                realTimeInformation.getStation() != null &&
                realTimeInformation.getStation().getEtdList() != null &&
                !realTimeInformation.getStation().getEtdList().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isScheduleTripAvaialble(ScheduleDetail scheduleDetail) {
        if(scheduleDetail !=null &&
                scheduleDetail.getSchedule() != null &&
                scheduleDetail.getSchedule().getRequest() != null &&
                scheduleDetail.getSchedule().getRequest().getTrip() != null &&
                !scheduleDetail.getSchedule().getRequest().getTrip().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private float progress;
    private float diffInSecs;
    private float origDiffInSecs;

    class ProgressBarTimerTask extends TimerTask {

        @Override
        public void run() {
            updateNextTrainProgress();
        }
    }

    private synchronized void updateNextTrainProgress() {
        if((origDiffInSecs -  diffInSecs) == origDiffInSecs+1) {
            diffInSecs = 0;
            origDiffInSecs = 0;
            progress = 0;
            progressBarTimerTask.cancel();
        }
        if(diffInSecs >= 0 && getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    float diff = (diffInSecs / origDiffInSecs) * 100;
                    if(diffInSecs == 0) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Station origStation = (Station)originSpinner.getSelectedItem();
                                Station destStation = (Station)destinationSpinner.getSelectedItem();

                                if(origStation.getAbbr().contentEquals(destStation.getAbbr())) {
                                    Toast.makeText(getActivity(),"Origin & Destination couldn't be same",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                getSchedule(origStation.getAbbr(), destStation.getAbbr());
                            }
                        }, RE_REQUEST_DELAY_TIME_IN_MILLIS);
                        nextTrainPgBar.setProgress(0);
                        nextTrainPgBar.setText("Leaving...");
                    } else {
                        nextTrainPgBar.setProgress(100 - diff);
                        nextTrainPgBar.setText(getMinSec((long) diffInSecs));
                    }
                }
            });

            diffInSecs = diffInSecs -1;
            progress = progress + 1;
        }
    }

    private void getAdvisory() {
        AdvisoryInformationService advisoryInformationService = ServiceLocator.INSTANCE.getAdvisoryService();
        Observable<Advisory> observableAllStationService = advisoryInformationService.Observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observableAllStationService.subscribe(new Observer<Advisory>() {
            @Override
            public void onCompleted() {

                Log.d("","Subscriber --- onCompleted() ---");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Advisory advisory) {
                Log.d("","Subscriber --- onNext() ---");
                if(advisory!= null && advisory.getBsa() !=null &&
                        advisory.getBsa().getDescription()!=null &&
                        !advisory.getBsa().getDescription().contains("No delays")) {
                    advisoryImageview.setVisibility(View.VISIBLE);
                    advisoryTextMessage = advisory.getBsa().getDescription();
                } else {
                    advisoryImageview.setVisibility(View.GONE);
                }
            }
        });
    }

    private String getMinSec(long totalSecs) {
        long hours = totalSecs / 3600;
        long minutes = (totalSecs % 3600) / 60;
        long seconds = totalSecs % 60;
        if(hours > 0) {
            return hours+":"+minutes+":"+seconds;
        }

        if(minutes > 0) {
            return minutes+":"+seconds;
        }

        if(seconds > 0) {
            return seconds+"";
        }
        return "";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        progressTimer.cancel();
        progressTimer = null;
    }

    public List<Station> getStationLocalList() {
        List<Station> stationList = new ArrayList<>();
        Station s1 = new Station();
        s1.setName("12th St. Oakland City Center");
        s1.setAbbr("12TH");
        stationList.add(s1);

        Station s2 = new Station();
        s2.setName("16th St. Mission");
        s2.setAbbr("16TH");
        stationList.add(s2);

        Station s3 = new Station();
        s3.setName("19th St. Oakland");
        s3.setAbbr("19TH");
        stationList.add(s3);

        Station s4 = new Station();
        s4.setName("24th St. Mission");
        s4.setAbbr("24TH");
        stationList.add(s4);

        Station s5 = new Station();
        s5.setName("Antioch");
        s5.setAbbr("ANTC");
        stationList.add(s5);

        Station s6 = new Station();
        s6.setName("Ashby");
        s6.setAbbr("ASHB");
        stationList.add(s6);

        Station s7 = new Station();
        s7.setName("Balboa Park");
        s7.setAbbr("BALB");
        stationList.add(s7);

        Station s8 = new Station();
        s8.setName("Bay Fair");
        s8.setAbbr("BAYF");
        stationList.add(s8);

        Station s9 = new Station();
        s9.setName("Castro Valley");
        s9.setAbbr("CAST");
        stationList.add(s9);

        Station s10 = new Station();
        s10.setName("Civic Center/UN Plaza");
        s10.setAbbr("CIVC");
        stationList.add(s10);

        Station s11 = new Station();
        s11.setName("Coliseum");
        s11.setAbbr("COLS");
        stationList.add(s11);

        Station s12 = new Station();
        s12.setName("Colma");
        s12.setAbbr("COLM");
        stationList.add(s12);

        Station s13 = new Station();
        s13.setName("Concord");
        s13.setAbbr("CONC");
        stationList.add(s13);

        Station s14 = new Station();
        s14.setName("Daly City");
        s14.setAbbr("DALY");
        stationList.add(s14);

        Station s15 = new Station();
        s15.setName("Downtown Berkeley");
        s15.setAbbr("DBRK");
        stationList.add(s15);

        Station s16 = new Station();
        s16.setName("Dublin/Pleasanton");
        s16.setAbbr("DUBL");
        stationList.add(s16);

        Station s17 = new Station();
        s17.setName("El Cerrito del Norte");
        s17.setAbbr("DELN");
        stationList.add(s17);

        Station s18 = new Station();
        s18.setName("El Cerrito Plaza");
        s18.setAbbr("PLZA");
        stationList.add(s18);

        Station s19 = new Station();
        s19.setName("Embarcadero");
        s19.setAbbr("EMBR");
        stationList.add(s19);

        Station s20 = new Station();
        s20.setName("Fremont");
        s20.setAbbr("FRMT");
        stationList.add(s20);

        Station s21 = new Station();
        s21.setName("Fruitvale");
        s21.setAbbr("FTVL");
        stationList.add(s21);

        Station s22 = new Station();
        s22.setName("Glen Park");
        s22.setAbbr("GLEN");
        stationList.add(s22);

        Station s24 = new Station();
        s24.setName("Hayward");
        s24.setAbbr("HAYW");
        stationList.add(s24);

        Station s25 = new Station();
        s25.setName("Lafayette");
        s25.setAbbr("LAFY");
        stationList.add(s25);

        Station s26 = new Station();
        s26.setName("Lake Merritt");
        s26.setAbbr("LAKE");
        stationList.add(s26);

        Station s27 = new Station();
        s27.setName("MacArthur");
        s27.setAbbr("MCAR");
        stationList.add(s27);

        Station s28 = new Station();
        s28.setName("Millbrae");
        s28.setAbbr("MLBR");
        stationList.add(s28);

        Station s29 = new Station();
        s29.setName("Montgomery St.");
        s29.setAbbr("MONT");
        stationList.add(s29);

        Station s30 = new Station();
        s30.setName("North Berkeley");
        s30.setAbbr("NBRK");
        stationList.add(s30);

        Station s31 = new Station();
        s31.setName("North Concord/Martinez");
        s31.setAbbr("NCON");
        stationList.add(s31);

        Station s32 = new Station();
        s32.setName("Oakland International Airport");
        s32.setAbbr("OAKL");
        stationList.add(s32);

        Station s33 = new Station();
        s33.setName("Orinda");
        s33.setAbbr("ORIN");
        stationList.add(s33);

        Station s34 = new Station();
        s34.setName("Pittsburg/Bay Point");
        s34.setAbbr("PITT");
        stationList.add(s34);

        Station s35 = new Station();
        s35.setName("Pittsburg Center");
        s35.setAbbr("PCTR");
        stationList.add(s35);

        Station s36 = new Station();
        s36.setName("Pleasant Hill/Contra Costa Centre");
        s36.setAbbr("PHIL");
        stationList.add(s36);

        Station s37 = new Station();
        s37.setName("Powell St.");
        s37.setAbbr("POWL");
        stationList.add(s37);

        Station s38 = new Station();
        s38.setName("Richmond");
        s38.setAbbr("RICH");
        stationList.add(s38);

        Station s39 = new Station();
        s39.setName("Rockridge");
        s39.setAbbr("ROCK");
        stationList.add(s39);

        Station s40 = new Station();
        s40.setName("San Bruno");
        s40.setAbbr("SBRN");
        stationList.add(s40);

        Station s41 = new Station();
        s41.setName("San Francisco International Airport");
        s41.setAbbr("SFIA");
        stationList.add(s41);

        Station s42 = new Station();
        s42.setName("San Leandro");
        s42.setAbbr("SANL");
        stationList.add(s42);

        Station s43 = new Station();
        s43.setName("South Hayward");
        s43.setAbbr("SHAY");
        stationList.add(s43);

        Station s44 = new Station();
        s44.setName("South San Francisco");
        s44.setAbbr("SSAN");
        stationList.add(s44);

        Station s45 = new Station();
        s45.setName("Union City");
        s45.setAbbr("UCTY");
        stationList.add(s45);

        Station s47 = new Station();
        s47.setName("Walnut Creek");
        s47.setAbbr("WCRK");
        stationList.add(s47);

        Station s48 = new Station();
        s48.setName("Warm Springs/South Fremont");
        s48.setAbbr("WARM");
        stationList.add(s48);

        Station s49 = new Station();
        s49.setName("West Dublin/Pleasanton");
        s49.setAbbr("WDUB");
        stationList.add(s49);

        Station s50 = new Station();
        s50.setName("West Oakland");
        s50.setAbbr("WOAK");
        stationList.add(s50);

        return stationList;
    }
}
