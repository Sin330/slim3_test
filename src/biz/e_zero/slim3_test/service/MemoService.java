package biz.e_zero.slim3_test.service;

import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

import biz.e_zero.slim3_test.meta.MemoMeta;
import biz.e_zero.slim3_test.model.Memo;
import biz.e_zero.slim3_test.service.dao.DaoBaseEx;

// <DaoBaseEx>クラスを継承
public class MemoService  extends DaoBaseEx<Memo>{
    /**
     * Get Memo object by key from DS.
     * @param key
     * @return Memo
     */
    public Memo get(Key key) {
        // Datastoreのメソッドからsuper classのメソッドに変更(P5)
        // return Datastore.getOrNull(MemoMeta.get(), key);
        return super.getOrNull(key);
    }
    
    /**
     * Get all Memo object from DS.
     * @return List<Memo>
     */
    public List<Memo> list() {
        MemoMeta m = MemoMeta.get();
        // Datastoreのメソッドからsuper classのメソッドに変更(P5)
        // return Datastore.query(m).sort(m.updateDate.desc).asList();
        List<Key> keys = Datastore.query(m).sort(m.updateDate.desc).asKeyList();
        return super.get(keys);
    }
    
    /**
     * Insert Memo object into DS.
     * @param memo
     */
    public void insert(Memo memo) {
        memo.setKey(Datastore.allocateId(MemoMeta.get()));
        // Datastoreのメソッドからsuper classのメソッドに変更(P5)
        // Datastore.put(memo);
        super.put(memo);
    }

    /**
     * Update Memo object in DS. 
     * @param memo
     */
    public void update(Memo memo) {
        // Datastoreのメソッドからsuper classのメソッドに変更(P5)
        // Datastore.put(memo);
        super.put(memo);
    }
    
    /**
     * Delete Memo object from DS.
     * @param key
     */
    public void delete(Key key) {
        // Datastoreのメソッドからsuper classのメソッドに変更(P5)
        // Datastore.delete(key);
        super.delete(key);
    }
    
    public void upsert(Memo memo) {
        if( memo.getKey() ==  null || get(memo.getKey()) == null ) {
            insert(memo);
        } else {
            update(memo);
        }
    }
}
