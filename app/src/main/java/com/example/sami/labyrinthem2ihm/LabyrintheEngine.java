package com.example.sami.labyrinthem2ihm;

import android.app.Service;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.sami.labyrinthem2ihm.Models.Bille;
import com.example.sami.labyrinthem2ihm.Models.Bloc;

import java.util.ArrayList;
import java.util.List;

public class LabyrintheEngine {
    private Bille mBoule = null;
    public Bille getBoule() {
        return mBoule;
    }

    public void setBoule(Bille pBoule) {
        this.mBoule = pBoule;
    }

    // Le labyrinthe
    private List<Bloc> mBlocks = null;

    private LevelsActivity mActivity = null;

    private SensorManager mManager = null;
    private Sensor mAccelerometre = null;

    SensorEventListener mSensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent pEvent) {
            float x = pEvent.values[0];
            float y = pEvent.values[1];

            if(mBoule != null) {
                // On met à jour les coordonnées de la boule
                RectF hitBox = mBoule.putXAndY(x, y);

                // Pour tous les blocs du labyrinthe
                for(Bloc block : mBlocks) {
                    // On crée un nouveau rectangle pour ne pas modifier celui du bloc
                    RectF inter = new RectF(block.getRectangle());
                    if(inter.intersect(hitBox)) {
                        // On agit différement en fonction du type de bloc
                        switch(block.getType()) {
                            case TROU:
                                mActivity.showDialog(LevelsActivity.DEFEAT_DIALOG);
                                break;

                            case DEPART:
                                break;

                            case ARRIVEE:
                                mActivity.showDialog(LevelsActivity.VICTORY_DIALOG);
                                break;
                        }
                        break;
                    }
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor pSensor, int pAccuracy) {

        }
    };

    public LabyrintheEngine(LevelsActivity pView) {
        mActivity = pView;
        mManager = (SensorManager) mActivity.getBaseContext().getSystemService(Service.SENSOR_SERVICE);
        mAccelerometre = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    // Remet à zéro l'emplacement de la boule
    public void reset() {
        mBoule.reset();
    }

    // Arrête le capteur
    public void stop() {
        mManager.unregisterListener(mSensorEventListener, mAccelerometre);
    }

    // Redémarre le capteur
    public void resume() {
        mManager.registerListener(mSensorEventListener, mAccelerometre, SensorManager.SENSOR_DELAY_GAME);
    }

    // Construit le labyrinthe
    public List<Bloc> buildLabyrinthe() {
        mBlocks = new ArrayList<Bloc>();

        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 0));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 1));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 2));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 3));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 4));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 5));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 6));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 7));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 8));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 9));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 10));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 11));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 12));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 0, 13));

        mBlocks.add(new Bloc(Bloc.Type.TROU, 5, 0));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 5, 1));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 5, 2));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 5, 3));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 5, 4));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 5, 5));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 5, 6));

        mBlocks.add(new Bloc(Bloc.Type.TROU, 6, 0));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 6, 1));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 6, 2));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 6, 3));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 6, 4));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 6, 5));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 6, 6));


        mBlocks.add(new Bloc(Bloc.Type.TROU, 11, 7));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 11, 8));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 11, 9));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 11, 10));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 11, 11));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 11, 12));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 11, 13));

        mBlocks.add(new Bloc(Bloc.Type.TROU, 12, 7));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 12, 8));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 12, 9));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 12, 10));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 12, 11));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 12, 12));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 12, 13));




        mBlocks.add(new Bloc(Bloc.Type.TROU, 17, 0));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 17, 1));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 17, 2));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 17, 3));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 17, 4));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 17, 5));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 17, 6));

        mBlocks.add(new Bloc(Bloc.Type.TROU, 18, 0));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 18, 1));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 18, 2));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 18, 3));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 18, 4));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 18, 5));
        mBlocks.add(new Bloc(Bloc.Type.TROU, 18, 6));




        Bloc b = new Bloc(Bloc.Type.DEPART, 2, 3);
        mBoule.setInitialRectangle(new RectF(b.getRectangle()));
        mBlocks.add(b);

        mBlocks.add(new Bloc(Bloc.Type.ARRIVEE, 22, 3));

        return mBlocks;
    }


}
