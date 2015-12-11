package biz.e_zero.slim3_test.model;

import com.google.appengine.api.datastore.Key;

public interface Slim3Model {
    Key getKey();
    Long getVersion();
}