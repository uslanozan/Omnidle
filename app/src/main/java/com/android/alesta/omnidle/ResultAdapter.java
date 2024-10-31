package com.android.alesta.omnidle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<ArrayList<String>> resultList;

    public ResultAdapter(Context context, ArrayList<ArrayList<String>> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @Override
    public int getCount() {

        return resultList.size();
    }

    @Override
    public Object getItem(int position) {

        return resultList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        }

        ArrayList<String> rowData = resultList.get(position);

        TextView txtQuestion = convertView.findViewById(R.id.txtResultQuestion);
        TextView txtRealAnswer = convertView.findViewById(R.id.txtResultReal);
        TextView txtUserAnswer = convertView.findViewById(R.id.txtResultUser);

        //TODO: RIDVANDAN GERÇEK CEVABI YA DA USER CEVABINI ALMAM LAZIM BURASI GÜNCELLENECEK
        txtRealAnswer.setText(rowData.get(1));
        txtQuestion.setText(rowData.get(2));
        txtUserAnswer.setText(rowData.get(3));



        return convertView;
    }
}
