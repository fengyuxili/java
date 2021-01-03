package com.fxs.designpattern.template.jdbc;

import java.sql.ResultSet;

public interface RowMapper<T> {
    T mapRow(ResultSet rs, int rumNum) throws Exception ;
}
