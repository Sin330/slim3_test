package biz.e_zero.slim3_test.controller.memo;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import biz.e_zero.slim3_test.model.Memo;
import biz.e_zero.slim3_test.service.MemoService;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

// Controller���p������IndexContoroller���쐬����B
public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        // �u���E�U�L���b�V����OFF�ɂ���(P4)
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        
        // UserService���쐬(P6)
        UserService userService =  UserServiceFactory.getUserService();
        // logout�pURL�쐬(P6)
        request.setAttribute("logout_url", userService.createLogoutURL(request.getRequestURI()));

        // MemoService�̃I�u�W�F�N�g���쐬
        MemoService service = new MemoService();

        // Memo�I�u�W�F�N�g�̃��X�g���擾
        List<Memo> list = service.list();

        // list�I�u�W�F�N�g�̃X�R�[�v��ݒ�
        requestScope("memoList", list);
        // index.jsp�փ��_�C���N�g
        return forward("index.jsp");
    }
}
