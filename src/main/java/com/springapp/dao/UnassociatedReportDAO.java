package com.springapp.dao;

import com.springapp.model.UnassociatedTag;

import java.util.HashSet;
import java.util.List;

/**
 * Created by michaelgoode on 19/09/2016.
 */
public interface UnassociatedReportDAO {
    public void postBatch(HashSet<UnassociatedTag> tags, String filename);
    public List<UnassociatedTag> getReport();
}
