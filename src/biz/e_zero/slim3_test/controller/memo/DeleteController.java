package biz.e_zero.slim3_test.controller.memo;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.StringUtil;

import biz.e_zero.slim3_test.model.Memo;
import biz.e_zero.slim3_test.service.MemoService;

// Controller���p������IndexContoroller���쐬����B
public class DeleteController extends Controller {

    @Override
    public Navigation run() throws Exception {
        // ���N�G�X�g�p�����[�^����id���擾
        String id = request.getParameter("id");

        // id���u�����N�̏ꍇ�����X�L�b�v
        if (!StringUtil.isEmpty(id)) {
            // MemoService���쐬
            MemoService service = new MemoService();
            // �Ώ�id��Memo�I�u�W�F�N�g�̍폜���s��
            service.delete(Datastore.createKey(Memo.class, Integer.valueOf(id)));
        }
        // IndexContorller�Ƀ��_�C���N�g
        return redirect("index");
    }
}
