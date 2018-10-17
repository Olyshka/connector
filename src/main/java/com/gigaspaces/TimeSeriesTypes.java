package com.gigaspaces;

import java.util.HashMap;
import java.util.Map;

public class TimeSeriesTypes {

    // index id key etc
    public Integer id = 0;
    public Map<String, TimeSeriesType> types = new HashMap<>();

    public TimeSeriesTypes() {
    }
}
