package biz.e_zero.slim3_test.service;

import java.util.Date;
import java.util.List;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import biz.e_zero.slim3_test.model.Memo;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MemoServiceTest extends AppEngineTestCase {

    // MemoServiceを新規作成
    private MemoService service = new MemoService();

    // [@Test]アノテーションをつけることでテストケースとして認識される。
    // クラス名は何でもよい。
    @Test
    public void curd_test() throws Exception {
        // 作成されたサービスがNullではない。
        assertThat(service, is(notNullValue()));
        
        // list()メソッドにより全オブジェクトの一覧を取得
        List<Memo> list = service.list();

        // 取得リストがNullではなく空である。（初回実行のため）
        assertNotNull(list);
        assertTrue(list.isEmpty());
        
        // === Insert TEST ===
        // Memo Classにデータをセット
        Memo memo = new Memo();
        memo.setTitle("TITLE");
        memo.setMemo("MEMOMEMO");
        memo.setUpdateDate(new Date());

        // service.insert()メソッドによりDatastoreにオブジェクトを格納する。
        service.insert(memo);
        
        // service.list()メソッドにより全オブジェクトを再取得
        list = service.list();
        
        // 取得リストがNullではなくSize==1である。
        assertNotNull(list);
        assertTrue(list.size() == 1);
        
        // Insertしたモデルと取得したモデルを比較
        Memo memo2 = list.get(0);
        assertEqualMemo(memo, memo2);
        
        // 取得したデータのバージョン番号は1である。
        assertThat(memo2.getVersion(), is(1L));
        
        // === Update TEST ===
        // モデルのTitleプロパティの内容を変更する。
        memo2.setTitle("TITLE UPDATED");
        // service.updateメソッドで変更してオブジェクトを更新
        service.update(memo2);
        
        // service.get(key)メソッドで更新したモデルを取得
        Memo memo3 = service.get(memo2.getKey());
        // Updateしたモデルと取得したモデルを比較
        assertEqualMemo(memo2, memo3);
        // 取得したデータのバージョン番号は2である。
        assertThat(memo3.getVersion(), is(2L));
        
        // === Delete TEST ===

        // service.delete(key)メソッドを使用してKeyで指定したオブジェクトを削除
        service.delete(memo3.getKey());
        // service.list()メソッドにより全オブジェクトを再取得
        list = service.list();


        // 取得リストがNullではなくSize==0である。
        assertNotNull(list);
        assertTrue(list.size() == 0);
    }
    
    private void assertEqualMemo(Memo memo1, Memo memo2){
        assertNotNull(memo1);
        assertNotNull(memo2);
        assertThat(memo2.getTitle(), is(memo1.getTitle()));
        assertThat(memo2.getMemo(), is(memo1.getMemo()));
        assertThat(memo2.getUpdateDate(), is(memo1.getUpdateDate()));
    }
}