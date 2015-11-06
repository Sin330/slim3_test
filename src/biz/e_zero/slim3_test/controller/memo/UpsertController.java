package biz.e_zero.slim3_test.controller.memo;

import java.util.Date;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.StringUtil;

import biz.e_zero.slim3_test.model.Memo;
import biz.e_zero.slim3_test.service.MemoService;

// IndexControllerからupsert部分を切り出してUpsertControllerを作成(P4)
public class UpsertController extends Controller {

    @Override
    public Navigation run() throws Exception {
        // HTTPリクエストがPOSTかの判断を行い、POSTの場合は登録処理を行う
        if( isPost() ) {
            // MemoServiceのオブジェクトを作成
            MemoService service = new MemoService();
            // Memoオブジェクトを作成
            Memo memo = new Memo();

            // IDを取得してmemoオブジェクトに設定(P4)
            String id = request.getParameter("id");

            // idがブランクの場合処理スキップ
            if (!StringUtil.isEmpty(id)) {
                memo.setKey(Datastore.createKey(Memo.class, Integer.valueOf(id)));
            }
            // Memoオブジェクトに登録用データを設定
            memo.setTitle(request.getParameter("title"));
            memo.setMemo(request.getParameter("memo"));
            memo.setUpdateDate(new Date());

            // MemoServiceにMemoオブジェクトを追加（Upsert）
            service.upsert(memo);
        }

        // IndexControllerにリダイレクト
        return redirect("index");
    }
}
