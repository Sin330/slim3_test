package biz.e_zero.slim3_test.controller.memo;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Key;

import biz.e_zero.slim3_test.model.Memo;
import biz.e_zero.slim3_test.service.MemoService;

//Controller���p������EditContoroller���쐬����B
public class EditController extends Controller {
    @Override
    public Navigation run() throws Exception {
        // ���N�G�X�g�p�����[�^����id���擾
        String id  = request.getParameter("id");

        Memo memo = null;

        // �V�K�쐬 or �ύX�̕���(P4)
        if (StringUtil.isEmpty(id)) {
            // id��null�܂��͋󔒂������ꍇ�͐V�K�쐬
            // memo�I�u�W�F�N�g��V�K�쐬
            memo = new Memo();
        } else {
            // ����ȊO�͕ύX(P4)
            // MemoService���쐬(P4)
            MemoService service = new MemoService();

            // id����Memo�N���X�p��Key�I�u�W�F�N�g���쐬(P4)
            Key key = Datastore.createKey(Memo.class, Integer.valueOf(id));
            // key�Ɉ�v����memo�I�u�W�F�N�g���擾(P4)
            memo = service.get(key);
        }     

        // memo�I�u�W�F�N�g�̃X�R�[�v��ݒ�
        requestScope("memo", memo);  
        
        // edit.jsp�փt�H���[�h
        return forward("edit.jsp");
    }
}
