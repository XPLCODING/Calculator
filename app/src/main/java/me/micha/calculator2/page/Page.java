package me.micha.calculator2.page;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.micha.calculator2.MainActivity;

/**
 * Created by micha on 26.03.2018.
 */

public class Page extends Fragment {

    private Page.OnFragmentInteractionListener mListener;
    private int fragment_id;
    private boolean rotatable = true;

    public Page() {

    }

    public void setFragmentId(int fragment_id) {
        this.fragment_id = fragment_id;
    }

    public void setRotatable(boolean rotatable) {
        this.rotatable = rotatable;
    }

    public boolean isRotatable() {
        return rotatable;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(fragment_id, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        onCreate();
    }

    public View findViewById(int id) {
        return getView().findViewById(id);
    }

    public void onCreate() {}

    public void onSelected() {}

    public void onResumePage() {
        System.out.print("RESUME");
        if(getActivity() != null) getActivity().setRequestedOrientation(isRotatable() ? ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(getActivity() != null) System.out.println(getActivity().getRequestedOrientation());
    }

    public void onPausePage() {
        if(getActivity() != null) getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Page.OnFragmentInteractionListener) {
            mListener = (Page.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
