package com.springapp.dao;

import com.springapp.model.Contract;
import com.springapp.model.EPC;

import java.util.List;

/**
 * Created by michaelgoode on 08/09/2016.
 */
public interface ContractDAO {
    public List<Contract> searchContract(String contractNo );
}
