package biz.e_zero.slim3_test.controller.memo;

import java.util.Date;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.StringUtil;

import biz.e_zero.slim3_test.model.Memo;
import biz.e_zero.slim3_test.service.MemoService;

// IndexController����upsert������؂�o����UpsertController���쐬(P4)
public class UpsertController extends Controller {

    @Override
    public Navigation run() throws Exception {
        // HTTP���N�G�X�g��POST���̔��f���s���APOST�̏ꍇ�͓o�^�������s��
        if( isPost() ) {
            // MemoService�̃I�u�W�F�N�g���쐬
            MemoService service = new MemoService();
            // Memo�I�u�W�F�N�g���쐬
            Memo memo = new Memo();

            // ID���擾����memo�I�u�W�F�N�g�ɐݒ�(P4)
            String id = request.getParameter("id");

            // id���u�����N�̏ꍇ�����X�L�b�v
            if (!StringUtil.isEmpty(id)) {
                memo.setKey(Datastore.createKey(Memo.class, Integer.valueOf(id)));
            }
            // Memo�I�u�W�F�N�g�ɓo�^�p�f�[�^��ݒ�
            memo.setTitle(request.getParameter("title"));
            memo.setMemo(request.getParameter("memo"));
            memo.setUpdateDate(new Date());

            // MemoService��Memo�I�u�W�F�N�g��ǉ��iUpsert�j
            service.upsert(memo);
        }

        // IndexController�Ƀ��_�C���N�g
        return redirect("index");
    }
}
