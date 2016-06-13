package com.marioaliaga.tipcalc.fragments;

import com.marioaliaga.tipcalc.models.TipRecord;

/**
 * Created by maliaga on 12-06-16.
 */
public interface TipHistoryFragmentsListener {
    void addToList(TipRecord record);
    void clearList();
}
