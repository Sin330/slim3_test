package biz.e_zero.slim3_test.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2016-01-10 23:32:24")
/** */
public final class MemoMeta extends org.slim3.datastore.ModelMeta<biz.e_zero.slim3_test.model.Memo> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<biz.e_zero.slim3_test.model.Memo, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<biz.e_zero.slim3_test.model.Memo, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<biz.e_zero.slim3_test.model.Memo> memo = new org.slim3.datastore.StringAttributeMeta<biz.e_zero.slim3_test.model.Memo>(this, "memo", "memo");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<biz.e_zero.slim3_test.model.Memo> title = new org.slim3.datastore.StringAttributeMeta<biz.e_zero.slim3_test.model.Memo>(this, "title", "title");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<biz.e_zero.slim3_test.model.Memo, java.util.Date> updateDate = new org.slim3.datastore.CoreAttributeMeta<biz.e_zero.slim3_test.model.Memo, java.util.Date>(this, "updateDate", "updateDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<biz.e_zero.slim3_test.model.Memo> userId = new org.slim3.datastore.StringAttributeMeta<biz.e_zero.slim3_test.model.Memo>(this, "userId", "userId");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<biz.e_zero.slim3_test.model.Memo, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<biz.e_zero.slim3_test.model.Memo, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final MemoMeta slim3_singleton = new MemoMeta();

    /**
     * @return the singleton
     */
    public static MemoMeta get() {
       return slim3_singleton;
    }

    /** */
    public MemoMeta() {
        super("Memo", biz.e_zero.slim3_test.model.Memo.class);
    }

    @Override
    public biz.e_zero.slim3_test.model.Memo entityToModel(com.google.appengine.api.datastore.Entity entity) {
        biz.e_zero.slim3_test.model.Memo model = new biz.e_zero.slim3_test.model.Memo();
        model.setKey(entity.getKey());
        model.setMemo((java.lang.String) entity.getProperty("memo"));
        model.setTitle((java.lang.String) entity.getProperty("title"));
        model.setUpdateDate((java.util.Date) entity.getProperty("updateDate"));
        model.setUserId((java.lang.String) entity.getProperty("userId"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        biz.e_zero.slim3_test.model.Memo m = (biz.e_zero.slim3_test.model.Memo) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("memo", m.getMemo());
        entity.setProperty("title", m.getTitle());
        entity.setProperty("updateDate", m.getUpdateDate());
        entity.setProperty("userId", m.getUserId());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        biz.e_zero.slim3_test.model.Memo m = (biz.e_zero.slim3_test.model.Memo) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        biz.e_zero.slim3_test.model.Memo m = (biz.e_zero.slim3_test.model.Memo) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        biz.e_zero.slim3_test.model.Memo m = (biz.e_zero.slim3_test.model.Memo) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        biz.e_zero.slim3_test.model.Memo m = (biz.e_zero.slim3_test.model.Memo) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        biz.e_zero.slim3_test.model.Memo m = (biz.e_zero.slim3_test.model.Memo) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getMemo() != null){
            writer.setNextPropertyName("memo");
            encoder0.encode(writer, m.getMemo());
        }
        if(m.getTitle() != null){
            writer.setNextPropertyName("title");
            encoder0.encode(writer, m.getTitle());
        }
        if(m.getUpdateDate() != null){
            writer.setNextPropertyName("updateDate");
            encoder0.encode(writer, m.getUpdateDate());
        }
        if(m.getUserId() != null){
            writer.setNextPropertyName("userId");
            encoder0.encode(writer, m.getUserId());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected biz.e_zero.slim3_test.model.Memo jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        biz.e_zero.slim3_test.model.Memo m = new biz.e_zero.slim3_test.model.Memo();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("memo");
        m.setMemo(decoder0.decode(reader, m.getMemo()));
        reader = rootReader.newObjectReader("title");
        m.setTitle(decoder0.decode(reader, m.getTitle()));
        reader = rootReader.newObjectReader("updateDate");
        m.setUpdateDate(decoder0.decode(reader, m.getUpdateDate()));
        reader = rootReader.newObjectReader("userId");
        m.setUserId(decoder0.decode(reader, m.getUserId()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}