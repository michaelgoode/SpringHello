package com.springapp.dao;

import com.springapp.model.BurberryShoe;

import java.util.HashSet;
import java.util.List;

/**
 * Created by michaelgoode on 29/11/2016.
 */
public interface BurberryShoeDAO {
    public void saveShoe(String code, String image);
    public List<String> findShoeReferences(String shoeRef );
    public void postBatch(HashSet<BurberryShoe> items, String filename);
}
