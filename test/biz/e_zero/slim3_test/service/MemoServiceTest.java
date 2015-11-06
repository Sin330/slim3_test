package biz.e_zero.slim3_test.service;

import java.util.Date;
import java.util.List;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;

import biz.e_zero.slim3_test.model.Memo;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MemoServiceTest extends AppEngineTestCase {

    // MemoService��V�K�쐬
    private MemoService service = new MemoService();

    // [@Test]�A�m�e�[�V���������邱�ƂŃe�X�g�P�[�X�Ƃ��ĔF�������B
    // �N���X���͉��ł��悢�B
    @Test
    public void curd_test() throws Exception {
        // �쐬���ꂽ�T�[�r�X��Null�ł͂Ȃ��B
        assertThat(service, is(notNullValue()));
        
        // list()���\�b�h�ɂ��S�I�u�W�F�N�g�̈ꗗ���擾
        List<Memo> list = service.list();

        // �擾���X�g��Null�ł͂Ȃ���ł���B�i������s�̂��߁j
        assertNotNull(list);
        assertTrue(list.isEmpty());
        
        // === Insert TEST ===
        // Memo Class�Ƀf�[�^���Z�b�g
        Memo memo = new Memo();
        memo.setTitle("TITLE");
        memo.setMemo("MEMOMEMO");
        memo.setUpdateDate(new Date());

        // service.insert()���\�b�h�ɂ��Datastore�ɃI�u�W�F�N�g���i�[����B
        service.insert(memo);
        
        // service.list()���\�b�h�ɂ��S�I�u�W�F�N�g���Ď擾
        list = service.list();
        
        // �擾���X�g��Null�ł͂Ȃ�Size==1�ł���B
        assertNotNull(list);
        assertTrue(list.size() == 1);
        
        // Insert�������f���Ǝ擾�������f�����r
        Memo memo2 = list.get(0);
        assertEqualMemo(memo, memo2);
        
        // �擾�����f�[�^�̃o�[�W�����ԍ���1�ł���B
        assertThat(memo2.getVersion(), is(1L));
        
        // === Update TEST ===
        // ���f����Title�v���p�e�B�̓��e��ύX����B
        memo2.setTitle("TITLE UPDATED");
        // service.update���\�b�h�ŕύX���ăI�u�W�F�N�g���X�V
        service.update(memo2);
        
        // service.get(key)���\�b�h�ōX�V�������f�����擾
        Memo memo3 = service.get(memo2.getKey());
        // Update�������f���Ǝ擾�������f�����r
        assertEqualMemo(memo2, memo3);
        // �擾�����f�[�^�̃o�[�W�����ԍ���2�ł���B
        assertThat(memo3.getVersion(), is(2L));
        
        // === Delete TEST ===

        // service.delete(key)���\�b�h���g�p����Key�Ŏw�肵���I�u�W�F�N�g���폜
        service.delete(memo3.getKey());
        // service.list()���\�b�h�ɂ��S�I�u�W�F�N�g���Ď擾
        list = service.list();


        // �擾���X�g��Null�ł͂Ȃ�Size==0�ł���B
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