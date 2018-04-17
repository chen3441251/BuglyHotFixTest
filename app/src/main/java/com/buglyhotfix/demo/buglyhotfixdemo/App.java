package com.buglyhotfix.demo.buglyhotfixdemo;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @ Creator     :     chenchao
 * @ CreateDate  :     2018/4/16 0016 16:01
 * @ Description :     BuglyHotFixDemo
 */

public class App extends TinkerApplication {

    public App() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.buglyhotfix.demo.buglyhotfixdemo.MyTinkerApplicationLike", "com.tencent.tinker.loader.TinkerLoader", false);
    }

}
