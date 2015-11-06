package biz.e_zero.slim3_test.controller.memo;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Key;

import biz.e_zero.slim3_test.model.Memo;
import biz.e_zero.slim3_test.service.MemoService;

//Controllerを継承してEditContorollerを作成する。
public class EditController extends Controller {
    @Override
    public Navigation run() throws Exception {
        // リクエストパラメータからidを取得
        String id  = request.getParameter("id");

        Memo memo = null;

        // 新規作成 or 変更の分岐(P4)
        if (StringUtil.isEmpty(id)) {
            // idがnullまたは空白だった場合は新規作成
            // memoオブジェクトを新規作成
            memo = new Memo();
        } else {
            // それ以外は変更(P4)
            // MemoServiceを作成(P4)
            MemoService service = new MemoService();

            // idからMemoクラス用のKeyオブジェクトを作成(P4)
            Key key = Datastore.createKey(Memo.class, Integer.valueOf(id));
            // keyに一致するmemoオブジェクトを取得(P4)
            memo = service.get(key);
        }     

        // memoオブジェクトのスコープを設定
        requestScope("memo", memo);  
        
        // edit.jspへフォワード
        return forward("edit.jsp");
    }
}
