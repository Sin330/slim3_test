package biz.e_zero.slim3_test.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

import biz.e_zero.slim3_test.meta.MemoMeta;
import biz.e_zero.slim3_test.model.Memo;

public class MemoService {
        /**
     * Get Memo object by key from DS.
     * @param key
     * @return Memo
     */
    public Memo get(Key key) {
        return Datastore.getOrNull(MemoMeta.get(), key);
    }
    
    /**
     * Get all Memo object from DS.
     * @return List<Memo>
     */
    public List<Memo> list() {
        MemoMeta m = MemoMeta.get();
        return Datastore.query(m).sort(m.updateDate.desc).asList();
    }
    
    /**
     * Insert Memo object into DS.
     * @param memo
     */
    public void insert(Memo memo) {
        memo.setKey(Datastore.allocateId(MemoMeta.get()));
        Datastore.put(memo);
    }

    /**
     * Update Memo object in DS. 
     * @param memo
     */
    public void update(Memo memo) {
        Datastore.put(memo);
    }
    
    /**
     * Delete Memo object from DS.
     * @param key
     */
    public void delete(Key key) {
        Datastore.delete(key);
    }
    
    public void upsert(Memo memo) {
        if( memo.getKey() ==  null || get(memo.getKey()) == null ) {
            insert(memo);
        } else {
            update(memo);
        }
    }
}
