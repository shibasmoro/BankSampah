package com.example.userbanksampah.helper;

import androidx.recyclerview.widget.DiffUtil;

import com.example.userbanksampah.model.Sampah;

import java.util.ArrayList;

public class NoteDiffCallback extends DiffUtil.Callback {
    private final ArrayList<Sampah> mOldNoteList;
    private final ArrayList<Sampah> mNewNoteList;
    public NoteDiffCallback(ArrayList<Sampah> oldNoteList, ArrayList<Sampah> newNoteList) {
        this.mOldNoteList = oldNoteList;
        this.mNewNoteList = newNoteList;
    }



    @Override
    public int getOldListSize() {
        return mOldNoteList.size();
    }
    @Override
    public int getNewListSize() {
        return mNewNoteList.size();
    }
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldNoteList.get(oldItemPosition).getSampah() == mNewNoteList.get(newItemPosition).getSampah();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

        final Sampah oldData = mOldNoteList.get(oldItemPosition);
        final Sampah newData= mNewNoteList.get(newItemPosition);

        return oldData.getSampah() == newData.getSampah();

    }


}
