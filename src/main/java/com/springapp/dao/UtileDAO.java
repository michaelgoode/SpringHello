package com.springapp.dao;

import com.springapp.model.utile.Machine;

import java.util.List;

/**
 * Created by michaelgoode on 08/11/2016.
 */
public interface UtileDAO {
    public List<Machine> searchMachine(String machineName);
}
