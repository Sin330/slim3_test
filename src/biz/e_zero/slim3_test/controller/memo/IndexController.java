package biz.e_zero.slim3_test.controller.memo;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import biz.e_zero.slim3_test.model.Memo;
import biz.e_zero.slim3_test.service.MemoService;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

// Controllerを継承してIndexContorollerを作成する。
public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        // ブラウザキャッシュをOFFにする(P4)
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        
        // UserServiceを作成(P6)
        UserService userService =  UserServiceFactory.getUserService();
        // logout用URL作成(P6)
        request.setAttribute("logout_url", userService.createLogoutURL(request.getRequestURI()));

        // MemoServiceのオブジェクトを作成
        MemoService service = new MemoService();

        // Memoオブジェクトのリストを取得
        List<Memo> list = service.list();

        // listオブジェクトのスコープを設定
        requestScope("memoList", list);
        // index.jspへリダイレクト
        return forward("index.jsp");
    }
}
