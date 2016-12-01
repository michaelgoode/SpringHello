package com.springapp.dao;

import com.springapp.model.Header;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by michaelgoode on 06/08/2016.
 */

public interface HeaderDAO {

    public List<Header> list();

}
