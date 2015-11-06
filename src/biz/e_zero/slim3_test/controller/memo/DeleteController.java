package biz.e_zero.slim3_test.controller.memo;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.StringUtil;

import biz.e_zero.slim3_test.model.Memo;
import biz.e_zero.slim3_test.service.MemoService;

// Controllerを継承してIndexContorollerを作成する。
public class DeleteController extends Controller {

    @Override
    public Navigation run() throws Exception {
        // リクエストパラメータからidを取得
        String id = request.getParameter("id");

        // idがブランクの場合処理スキップ
        if (!StringUtil.isEmpty(id)) {
            // MemoServiceを作成
            MemoService service = new MemoService();
            // 対象idのMemoオブジェクトの削除を行う
            service.delete(Datastore.createKey(Memo.class, Integer.valueOf(id)));
        }
        // IndexContorllerにリダイレクト
        return redirect("index");
    }
}
