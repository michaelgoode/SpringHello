package com.springapp.dao;

import com.springapp.model.EPC;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by michaelgoode on 24/08/2016.
 */


public interface EPCDAO {

        public List<EPC> searchEPC( String epcNo );
        public EPC checkEPC(String epcNo );

}
